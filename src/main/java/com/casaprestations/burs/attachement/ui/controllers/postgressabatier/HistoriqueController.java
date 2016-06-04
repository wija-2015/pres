package com.casaprestations.burs.attachement.ui.controllers.postgressabatier;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.casaprestations.burs.attachement.entity.db.postgres.Historique;
import com.casaprestations.burs.attachement.metier.postgres.IHistoriqueMetier;
import com.vividsolutions.jts.geom.Point;

@RestController
@RequestMapping("/historics")
public class HistoriqueController {

	@Autowired
	private IHistoriqueMetier historiqueMetier;
	
	@RequestMapping(value = "/historicsByDateAndVehicule", method = RequestMethod.GET)
	public List<Historique> findHistoriquesByDateAndVehicule(String date,Integer idVehicule) {
		return historiqueMetier.findHistoriquesByDateAndVehicule(date, idVehicule);
	}
	
	@RequestMapping(value = "/distanceFromPoints", method = RequestMethod.GET)
	public Double getDistanceFromPoints(String date,Integer idVehicule) {
		List<Historique> historiques=historiqueMetier.findHistoriquesByDateAndVehicule(date, idVehicule);
		List<Point> points=historiqueMetier.createPointsFromHistory(historiques);
		Double distance=historiqueMetier.getDistanceFromPoints(points);
			return distance/1000;
	}
	
   //******************************************************************************//
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public Long count() {
		return historiqueMetier.count();
	}


}
