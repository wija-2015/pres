package com.casaprestations.burs.attachement.ui.controllers.postgressabatier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.casaprestations.burs.attachement.entity.db.postgres.Vehicule;
import com.casaprestations.burs.attachement.repository.postgres.VehiculeRepository;

@RestController
@RequestMapping("/vehicules")
public class VehiculeController {
	
	@Autowired
	private VehiculeRepository vehiculeRepository;
	
	@RequestMapping(value = "/getVehicule", method = RequestMethod.GET)
	public Vehicule oneVehicule(Integer id) {
		return vehiculeRepository.findOne(id);
	}
	@RequestMapping(value = "/allVehicules", method = RequestMethod.GET)
	public List<Vehicule> allVehicules() {
		return vehiculeRepository.findAll();
	}
}
