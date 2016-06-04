package com.casaprestations.burs.attachement.poi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casaprestations.burs.attachement.metier.calcul.MethodsForMetier;
import com.casaprestations.burs.attachement.metier.calcul.balayagemecanise.BalayageBean;
import com.casaprestations.burs.attachement.metier.calcul.balayagemecanise.IBalayageMecaniseMetier;
import com.casaprestations.burs.attachement.metier.calcul.balayagemecanise.SumBean;

@Service
public class GenerateExcelSitaMetierImpl implements IGenerateExcelSitaMetier {
	@Autowired
	MethodsForMetier methodsForMetier;
	@Autowired
	IBalayageMecaniseMetier balayageMecaniseMetier;
	@Autowired
	MethodsForPoi methodsForPoi;

	@Override
	public void generateExcelOfBalayagesSita(String dateString) {
		List<BalayageBean> balayageBeans = balayageMecaniseMetier.findBalayagesOfSitaByYearAndMonth(dateString);
		List<SumBean> sumBeans = balayageMecaniseMetier.getSumOfBalayagesBySita(dateString);
		List<Integer> tabDaysOfMonth = balayageMecaniseMetier.getTabDaysMonth(dateString);
		Random generator = new Random();
		int choice = generator.nextInt(100) + 1;

		FileOutputStream out;
		try {
			out = new FileOutputStream("D:\\wijdane" + choice + ".xls");
			Workbook wb = new HSSFWorkbook();
			Sheet mySheet = wb.createSheet();

			// Inserer les jours du mois+ une partie fusionnée:Vehicules / Jours
			Row rowOfDate = mySheet.createRow(4);
			rowOfDate.createCell(0).setCellValue("Vehicules / Jours");
			mySheet.addMergedRegion(new CellRangeAddress(4, 5, 0, 3));
			methodsForPoi.insertDaysOfMonth(tabDaysOfMonth, rowOfDate, mySheet);

			// Inserer les vehicule + affectation + type + km Captés+ balayages
			List<VehiculeDataBeanForPoi> vehiculesDatas = methodsForPoi.getVehiculesDatas(balayageBeans, sumBeans);
			methodsForPoi.insertSumAndBalayagesCapte(mySheet, vehiculesDatas, 6);

			// Inserer les jours du mois+ une partie fusionnée:Vehicules / Jours
			Row rowOfDate2 = mySheet.createRow(19);
			rowOfDate2.createCell(0).setCellValue("Vehicules / Jours");
			mySheet.addMergedRegion(new CellRangeAddress(19, 20, 0, 3));
			methodsForPoi.insertDaysOfMonth(tabDaysOfMonth, rowOfDate2, mySheet);

			// Inserer les vehicule + affectation + type + km Captés+ balayages
			methodsForPoi.insertSumAndBalayagesTotal(mySheet, vehiculesDatas, 21);

			wb.write(out);
			out.close();
		} catch (

		FileNotFoundException e)

		{
			e.printStackTrace();
		} catch (

		IOException e)

		{
			e.printStackTrace();
		}
	}
}
