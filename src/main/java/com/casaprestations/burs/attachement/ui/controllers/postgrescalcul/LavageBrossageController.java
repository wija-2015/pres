package com.casaprestations.burs.attachement.ui.controllers.postgrescalcul;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.casaprestations.burs.attachement.entity.db.calcul.LavageBrossage;
import com.casaprestations.burs.attachement.metier.calcul.balayagemecanise.BalayageBean;
import com.casaprestations.burs.attachement.metier.calcul.balayagemecanise.SumBean;
import com.casaprestations.burs.attachement.metier.calcul.brossage.BrossageBean;
import com.casaprestations.burs.attachement.metier.calcul.brossage.ILavageBrossageMetier;

@RestController
@RequestMapping("/brossage")
public class LavageBrossageController {
	

	@Autowired
	private ILavageBrossageMetier lavageBrossageMetier;
	
	private void setHeader(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers",
				"Origin, X-Requested-With, Content-Type, Accept, Authorization");
	}
	
	@RequestMapping(value = "/brossagesSita", method = RequestMethod.GET)
	public List<BrossageBean> findBalayagesOfSitaByYearAndMonth(String dateString, HttpServletResponse response) {
		setHeader(response);
		return lavageBrossageMetier.findBrossagesOfSitaByYearAndMonth(dateString);
	}
}
