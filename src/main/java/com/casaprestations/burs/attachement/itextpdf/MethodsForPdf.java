package com.casaprestations.burs.attachement.itextpdf;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;

import com.casaprestations.burs.attachement.metier.calcul.balayagemecanise.BalayageBean;
import com.casaprestations.burs.attachement.metier.calcul.balayagemecanise.KmDataBean;
import com.casaprestations.burs.attachement.metier.calcul.balayagemecanise.SumBean;
import com.casaprestations.burs.attachement.poi.VehiculeDataBeanForPoi;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

@Service
public class MethodsForPdf {
	
	public List<VehiculeDataBeanForPdf> getVehiculesDatas(List<BalayageBean> balayageBeans, List<SumBean> sums) {
		List<VehiculeDataBeanForPdf> vehiculeDataBeanForPdfs = new ArrayList<VehiculeDataBeanForPdf>();
		for (SumBean sum : sums) {
			VehiculeDataBeanForPdf vehiculeData = new VehiculeDataBeanForPdf();
			for (BalayageBean balayage : balayageBeans) {
				if (sum.getNomVehicule().equals(balayage.getNomVehicule())) {
					vehiculeData.setBalayageBean(balayage);
					vehiculeData.setSumBean(sum);
					vehiculeDataBeanForPdfs.add(vehiculeData);
				}
			}
		}
		return vehiculeDataBeanForPdfs;
	}

	public void insertHeaderOfTableCapte(List<Integer> tabDaysOfMonth, PdfPTable t) {
		BaseColor myColor = WebColors.getRGBColor("#d9b3ff");
		PdfPCell c1 = new PdfPCell(new Phrase("Vehicule"));
		c1.setBackgroundColor(myColor);
		t.addCell(c1);
		PdfPCell c2 = new PdfPCell(new Phrase("Type"));
		c2.setBackgroundColor(myColor);
		t.addCell(c2);
		PdfPCell c3 = new PdfPCell(new Phrase("Affectation"));
		c3.setBackgroundColor(myColor);
		t.addCell(c3);
		PdfPCell c4 = new PdfPCell(new Phrase("Jours Travaillés"));
		c4.setBackgroundColor(myColor);
		t.addCell(c4);
		PdfPCell c5 = new PdfPCell(new Phrase("Km Totaux"));
		c5.setBackgroundColor(myColor);
		t.addCell(c5);
		for (int i = 1; i <= tabDaysOfMonth.size(); i++) {
			PdfPCell cell = new PdfPCell(new Phrase("" + i));
			cell.setBackgroundColor(myColor);
			t.addCell(cell);
		}
	}
	public void insertHeaderOfTableTotal(List<Integer> tabDaysOfMonth, PdfPTable t) {
		BaseColor myColor = WebColors.getRGBColor("#d9b3ff");
		PdfPCell c1 = new PdfPCell(new Phrase("Vehicule"));
		c1.setBackgroundColor(myColor);
		t.addCell(c1);
		PdfPCell c2 = new PdfPCell(new Phrase("Type"));
		c2.setBackgroundColor(myColor);
		t.addCell(c2);
		PdfPCell c3 = new PdfPCell(new Phrase("Affectation"));
		c3.setBackgroundColor(myColor);
		t.addCell(c3);
		PdfPCell c4 = new PdfPCell(new Phrase("Jours Travaillés"));
		c4.setBackgroundColor(myColor);
		t.addCell(c4);
		PdfPCell c5 = new PdfPCell(new Phrase("Km Captés"));
		c5.setBackgroundColor(myColor);
		t.addCell(c5);
		for (int i = 1; i <= tabDaysOfMonth.size(); i++) {
			PdfPCell cell = new PdfPCell(new Phrase("" + i));
			cell.setBackgroundColor(myColor);
			t.addCell(cell);
		}
	}
	
	
	public void insertSumAndBalayagesCapte(PdfPTable t, List<VehiculeDataBeanForPdf> vehiculesDatas) {
		for (VehiculeDataBeanForPdf vehiculeData : vehiculesDatas) {
			List<KmDataBean> kmDataBeans = vehiculeData.getBalayageBean().getKmDataBeans();
			t.addCell(vehiculeData.getSumBean().getNomVehicule());
			t.addCell(vehiculeData.getSumBean().getType());
			t.addCell(vehiculeData.getSumBean().getAffectation());
			PdfPCell cellT = new PdfPCell(new Phrase("" + vehiculeData.getSumBean().getSumTravaille()));
			t.addCell(cellT);
			PdfPCell cellSumKmC = new PdfPCell(new Phrase("" + vehiculeData.getSumBean().getSumKmCapte()));
			t.addCell(cellSumKmC);
			for (KmDataBean kmDataBean : kmDataBeans) {
				PdfPCell cellKmC = new PdfPCell(new Phrase("" + kmDataBean.getKmCapte()));
				t.addCell(cellKmC);
			}
		}
	}
	public void insertSumAndBalayagesTotal(PdfPTable t, List<VehiculeDataBeanForPdf> vehiculesDatas) {
		for (VehiculeDataBeanForPdf vehiculeData : vehiculesDatas) {
			List<KmDataBean> kmDataBeans = vehiculeData.getBalayageBean().getKmDataBeans();
			t.addCell(vehiculeData.getSumBean().getNomVehicule());
			t.addCell(vehiculeData.getSumBean().getType());
			t.addCell(vehiculeData.getSumBean().getAffectation());
			PdfPCell cellT = new PdfPCell(new Phrase("" + vehiculeData.getSumBean().getSumTravaille()));
			t.addCell(cellT);
			PdfPCell cellSumKmC = new PdfPCell(new Phrase("" + vehiculeData.getSumBean().getSumKmTotal()));
			t.addCell(cellSumKmC);
			for (KmDataBean kmDataBean : kmDataBeans) {
				PdfPCell cellKmC = new PdfPCell(new Phrase("" + kmDataBean.getKmTotal()));
				t.addCell(cellKmC);
			}
		}
	}

}
