package com.casaprestations.burs.attachement.ui.controllers.postgrescalcul;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.casaprestations.burs.attachement.metier.calcul.balayagemecanise.IBalayageMecaniseMetier;

@RestController
@RequestMapping("/date")
public class DateController {

	@Autowired
	private IBalayageMecaniseMetier balayageMecaniseMetier;

	private void setHeader(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers",
				"Origin, X-Requested-With, Content-Type, Accept, Authorization");
	}
	@RequestMapping(value = "/tabDaysOfMonth", method = RequestMethod.GET)
	public List<Integer> tabDaysOfMonth(String dateString, HttpServletResponse response) {
		setHeader(response);
		return balayageMecaniseMetier.getTabDaysMonth(dateString);
	}
}