package com.casaprestations.burs.attachement.poi;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;

import com.casaprestations.burs.attachement.metier.calcul.balayagemecanise.BalayageBean;
import com.casaprestations.burs.attachement.metier.calcul.balayagemecanise.KmDataBean;
import com.casaprestations.burs.attachement.metier.calcul.balayagemecanise.SumBean;

@Service
public class MethodsForPoi {

	public List<VehiculeDataBeanForPoi> getVehiculesDatas(List<BalayageBean> balayageBeans, List<SumBean> sums) {
		List<VehiculeDataBeanForPoi> vehiculeDataBeanForPois = new ArrayList<VehiculeDataBeanForPoi>();
		for (SumBean sum : sums) {
			VehiculeDataBeanForPoi vehiculeData = new VehiculeDataBeanForPoi();
			for (BalayageBean balayage : balayageBeans) {
				if (sum.getNomVehicule().equals(balayage.getNomVehicule())) {
					vehiculeData.setBalayageBean(balayage);
					vehiculeData.setSumBean(sum);
					vehiculeDataBeanForPois.add(vehiculeData);
				}
			}
		}

		return vehiculeDataBeanForPois;
	}

	public void insertDaysOfMonth(List<Integer> tabDaysOfMonth, Row rowOfDate, Sheet mySheet) {
		Integer cellOfDate = 5;
		for (Integer day : tabDaysOfMonth) {
			rowOfDate.createCell(cellOfDate).setCellValue(day);
			cellOfDate++;
		}
	}

	public void insertSumAndBalayagesCapte(Sheet mySheet, List<VehiculeDataBeanForPoi> vehiculesDatas, Integer numRow) {
		for (VehiculeDataBeanForPoi vehiculeData : vehiculesDatas) {
			Row rowOfSum = mySheet.createRow(numRow);
			List<KmDataBean> kmDataBeans = vehiculeData.getBalayageBean().getKmDataBeans();
			rowOfSum.createCell(0).setCellValue(vehiculeData.getSumBean().getNomVehicule());
			rowOfSum.createCell(1).setCellValue(vehiculeData.getSumBean().getType());
			rowOfSum.createCell(2).setCellValue(vehiculeData.getSumBean().getAffectation());
			rowOfSum.createCell(3).setCellValue(vehiculeData.getSumBean().getSumTravaille());
			rowOfSum.createCell(4).setCellValue(vehiculeData.getSumBean().getSumKmCapte());
			Integer cellOfRow = 5;
			for (KmDataBean kmDataBean : kmDataBeans) {
				rowOfSum.createCell(cellOfRow).setCellValue(kmDataBean.getKmCapte());
				cellOfRow++;
			}
			numRow++;
		}
	}

	public void insertSumAndBalayagesTotal(Sheet mySheet, List<VehiculeDataBeanForPoi> vehiculesDatas, Integer numRow) {
		for (VehiculeDataBeanForPoi vehiculeData : vehiculesDatas) {
			Row rowOfSum = mySheet.createRow(numRow);
			List<KmDataBean> kmDataBeans = vehiculeData.getBalayageBean().getKmDataBeans();
			rowOfSum.createCell(0).setCellValue(vehiculeData.getSumBean().getNomVehicule());
			rowOfSum.createCell(1).setCellValue(vehiculeData.getSumBean().getType());
			rowOfSum.createCell(2).setCellValue(vehiculeData.getSumBean().getAffectation());
			rowOfSum.createCell(3).setCellValue(vehiculeData.getSumBean().getSumTravaille());
			rowOfSum.createCell(4).setCellValue(vehiculeData.getSumBean().getSumKmTotal());
			Integer cellOfRow = 5;
			for (KmDataBean kmDataBean : kmDataBeans) {
				rowOfSum.createCell(cellOfRow).setCellValue(kmDataBean.getKmCapte());
				cellOfRow++;
			}
			numRow++;
		}
	}
}