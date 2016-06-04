package com.casaprestations.burs.attachement.itextpdf;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.DocumentException;

public interface IGeneratePdfSitaMetier {
	
	public void generatePdfOfBalayagesSita(String dateString) throws DocumentException, MalformedURLException, IOException;

}
