package com.casaprestations.burs.attachement.itextpdf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Section;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class TestPdf {
	/**
	 * @param args
	 * @throws DocumentException
	 * @throws IOException
	 * @throws MalformedURLException
	 */
	public static void main(String[] args) throws DocumentException,
			MalformedURLException, IOException {
		// TODO Auto-generated method stub

		// Listing 1. Instantiation of document object
		Document document = new Document();
		document.setPageSize(new Rectangle(1100,700)) ;

		// Listing 2. Creation of PdfWriter object
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream("d:/test/wijdane00.pdf"));
		
		document.open();

		// Listing 3. Creation of paragraph object
		Anchor anchorTarget = new Anchor("First page of the document.");
		anchorTarget.setName("BackToTop");
		Paragraph paragraph1 = new Paragraph();
		

		paragraph1.setSpacingBefore(100);

		paragraph1.add(anchorTarget);


		// Listing 4. Creation of chapter object
		Paragraph title1 = new Paragraph();
		Chapter chapter1 = new Chapter(title1, 9);
		chapter1.setNumberDepth(0);

		// Listing 5. Creation of section object
		Paragraph title11 = new Paragraph();
		Section section1 = chapter1.addSection(title11);
		Image image2 = Image.getInstance("logo.jpg");
		image2.scaleAbsolute(120f, 120f);
		image2.setAbsolutePosition(870, 570);
		section1.add(image2);
		Image image3 = Image.getInstance("logo.jpg");
		image3.scaleAbsolute(120f, 120f);
		image3.setAbsolutePosition(40, 570);
		section1.add(image3);
		
		
		float[] columnWidths = {4, 4, 4,9};
		// Listing 6. Creation of table object
		PdfPTable t = new PdfPTable(columnWidths);
		  t.setWidthPercentage(100);
			t.setSpacingBefore(200);
		
	
	
		PdfPCell c1 = new PdfPCell(new Phrase("Header1"));
		BaseColor myColor = WebColors.getRGBColor("#d9b3ff");
		c1.setBackgroundColor(myColor);
		t.addCell(c1);
		PdfPCell c2 = new PdfPCell(new Phrase("Header2"));
		t.addCell(c2);
		PdfPCell c3 = new PdfPCell(new Phrase("Header3"));
		t.addCell(c3);
		t.addCell("1.1");
		t.addCell("1.2");
		t.addCell("1.3");
		t.addCell("1.9");
		t.addCell(c3);
		t.addCell("1.1");
		t.addCell("1.2");
		t.addCell("1.3");
		t.addCell("1.9");
		c3.setRowspan(3);

		section1.add(t);
		PdfPTable t12 = new PdfPTable(columnWidths); 
		t12.setSpacingBefore(10);
		t12.addCell("1.2");
		t12.addCell("1.3");
		t12.addCell("1.9");
		t12.addCell(c3);
		t12.addCell("1.1");
		t12.addCell("1.2");
		t12.addCell("1.3");
		t12.addCell("1.9");
		
		section1.add(t12);

		// Listing 10. Addition of a chapter to the main document
		document.add(chapter1);
		document.close();

	}
}

