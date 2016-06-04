package com.casaprestations.burs.attachement.ui.controllers.postgrescalcul;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.casaprestations.burs.attachement.metier.calcul.balayagemecanise.BalayageBean;
import com.casaprestations.burs.attachement.metier.calcul.balayagemecanise.IBalayageMecaniseMetier;
import com.casaprestations.burs.attachement.metier.calcul.balayagemecanise.SumBean;
import com.casaprestations.burs.attachement.poi.IGenerateExcelSitaMetier;

@RestController
@RequestMapping("/balayage")
public class BalayageMecaniseSitaController {

	@Autowired
	private IBalayageMecaniseMetier balayageMecaniseMetier;
	
	@Autowired
	private IGenerateExcelSitaMetier generateExcelSitaMetier;
	
	private void setHeader(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers",
				"Origin, X-Requested-With, Content-Type, Accept, Authorization");
	}

	@RequestMapping(value = "/balayagesSita", method = RequestMethod.GET)
	public List<BalayageBean> findBalayagesOfSitaByYearAndMonth(String dateString, HttpServletResponse response) {
		setHeader(response);
		return balayageMecaniseMetier.findBalayagesOfSitaByYearAndMonth(dateString);
	}

	@RequestMapping(value = "/sumBalayagesSita", method = RequestMethod.GET)
	public List<SumBean> findSumOfBalayagesBySita(String dateString, HttpServletResponse response) {
		setHeader(response);
		return balayageMecaniseMetier.getSumOfBalayagesBySita(dateString);
	}
	
	@RequestMapping(value = "/getExcelOfBalayagesSita", method = RequestMethod.GET)
	public boolean getExcelOfSita(String dateString, HttpServletResponse response) {
		setHeader(response);
		generateExcelSitaMetier.generateExcelOfBalayagesSita(dateString);
		return true;
	}
	@RequestMapping(value = "/consultBalayagesSita", method = RequestMethod.POST)
	public boolean getBalayagesBetweenTwoDate(String firstDate,String lastDate, HttpServletResponse response) {
		setHeader(response);
		System.out.println("serveur"+firstDate);
		System.out.println("serveur"+lastDate);
		return true;
	}
}