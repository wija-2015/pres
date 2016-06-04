package com.casaprestations.burs.attachement.itextpdf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casaprestations.burs.attachement.metier.calcul.MethodsForMetier;
import com.casaprestations.burs.attachement.metier.calcul.balayagemecanise.BalayageBean;
import com.casaprestations.burs.attachement.metier.calcul.balayagemecanise.IBalayageMecaniseMetier;
import com.casaprestations.burs.attachement.metier.calcul.balayagemecanise.SumBean;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Section;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class GeneratePdfSitaMetierImpl implements IGeneratePdfSitaMetier {

	@Autowired
	private MethodsForMetier methodsForMetier;
	@Autowired
	private IBalayageMecaniseMetier balayageMecaniseMetier;
	@Autowired
	private MethodsForPdf methodsForPdf;

	@Override
	public void generatePdfOfBalayagesSita(String dateString)
			throws DocumentException, MalformedURLException, IOException {
		List<BalayageBean> balayageBeans = balayageMecaniseMetier.findBalayagesOfSitaByYearAndMonth(dateString);
		List<SumBean> sumBeans = balayageMecaniseMetier.getSumOfBalayagesBySita(dateString);
		List<Integer> tabDaysOfMonth = balayageMecaniseMetier.getTabDaysMonth(dateString);
		Random generator = new Random();
		int choice = generator.nextInt(100) + 1;

		// Listing 1. Instantiation of document object
		Document document = new Document();
		document.setPageSize(new Rectangle(1300, 700));

		// Listing 2. Creation of PdfWriter object
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("d:/test/wijdane" + choice + ".pdf"));

		document.open();

		// Listing 3. Creation of paragraph object
		Anchor anchorTarget = new Anchor("First page of the document");
		anchorTarget.setName("BackToTop");
		Paragraph paragraph1 = new Paragraph();
		paragraph1.setSpacingBefore(50);
		paragraph1.add(anchorTarget);

		// Listing 4. Creation of chapter object
		Paragraph title1 = new Paragraph();
		Chapter chapter1 = new Chapter(title1, 1);
		chapter1.setNumberDepth(0);

		// Listing 5. Creation of section object
		Paragraph title11 = new Paragraph();
		Section section1 = chapter1.addSection(title11);
		Image image2 = Image.getInstance("logo.jpg");
		image2.scaleAbsolute(120f, 120f);
		image2.setAbsolutePosition(1150, 570);
		section1.add(image2);
		Image image3 = Image.getInstance("logo.jpg");
		image3.scaleAbsolute(120f, 120f);
		image3.setAbsolutePosition(40, 570);
		section1.add(image3);

		float[] columnWidths = new float[tabDaysOfMonth.size() + 5];
		columnWidths[0]=2;columnWidths[1]=(float) 2.7;columnWidths[2]=3;columnWidths[3]=(float) 2.5;columnWidths[4]=(float) 1.8;
		for (int i = 5; i < columnWidths.length; i++) {
			columnWidths[i] = 1;
			System.out.println(columnWidths[i]);
		}
		// Listing 6. Creation of table object
		PdfPTable tableCapte = new PdfPTable(columnWidths);
		tableCapte.setWidthPercentage(100);
		tableCapte.setSpacingBefore(100);


		// inserer Header de la table des balayage captés
		methodsForPdf.insertHeaderOfTableCapte(tabDaysOfMonth, tableCapte);

		// Inserer les vehicule + affectation + type + km Captés+ balayages
		List<VehiculeDataBeanForPdf> vehiculesDatas = methodsForPdf.getVehiculesDatas(balayageBeans, sumBeans);
		methodsForPdf.insertSumAndBalayagesCapte(tableCapte, vehiculesDatas);

		PdfPTable tableTotal = new PdfPTable(columnWidths);
		tableTotal.setWidthPercentage(100);
		tableTotal.setSpacingBefore(10);

		// inserer Header de la table des balayage captés
		methodsForPdf.insertHeaderOfTableTotal(tabDaysOfMonth, tableTotal);

		// Inserer les vehicule + affectation + type + km totaux+ balayages
		methodsForPdf.insertSumAndBalayagesTotal(tableTotal, vehiculesDatas);

		section1.add(tableCapte);
		section1.add(tableTotal);

		// Listing 10. Addition of a chapter to the main document
		document.add(chapter1);
		document.close();

	}

}
