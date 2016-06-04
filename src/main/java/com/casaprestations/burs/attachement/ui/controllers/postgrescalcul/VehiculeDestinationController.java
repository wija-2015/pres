package com.casaprestations.burs.attachement.ui.controllers.postgrescalcul;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.casaprestations.burs.attachement.entity.db.calcul.VehiculeDestination;
import com.casaprestations.burs.attachement.metier.calcul.IVehiculeDestinationMetier;

@RestController
@RequestMapping("/vehiculesDestination")
public class VehiculeDestinationController {
	
	@Autowired
	private IVehiculeDestinationMetier vehiculeDestinationMetier;
	
	private void setHeader(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers",
				"Origin, X-Requested-With, Content-Type, Accept, Authorization");
	}
	
	@RequestMapping(value = "/allVehicules", method = RequestMethod.GET)
	public Page<VehiculeDestination> allVehicules(int page, HttpServletResponse response) {
		setHeader(response);
		return vehiculeDestinationMetier.findAllVehicules(new PageRequest(page, 5));
	}
	
	@RequestMapping(value = "/saveVehicule", method = RequestMethod.POST, consumes={"application/json"}, produces ={"application/json"})
	@ResponseBody
	public VehiculeDestination saveVehicule(@RequestBody VehiculeDestination v, HttpServletResponse response) {
		setHeader(response);
		return vehiculeDestinationMetier.saveVehicule(v);
	}
}
