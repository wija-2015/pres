package com.casaprestations.burs.attachement.metier.postgres;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.casaprestations.burs.attachement.entity.db.postgres.Historique;
import com.casaprestations.burs.attachement.entity.db.postgres.Vehicule;
import com.vividsolutions.jts.geom.Point;

public interface IHistoriqueMetier {

	List<Historique> findHistoriquesByDateAndVehicule(String date, Integer idVehicule);
	Point createPointfromXY(Double x, Double y);
	List<Point> createPointsFromHistory(List<Historique> historiques);
	Double getDistanceFromPoints(List<Point> points);
	Vehicule findVehicule(Integer id);

	// Balayages Averda
	List<Integer> findVehiculesOfBalayageAverdaByDate(Date dateAvant);
	List<Historique> findBalayagesAverdaByDateAndVehicule(Date date, Integer idVehicule);
	
	//Lavages Averda
	List<Integer> findVehiculesOfLavageAverdaByDate(Date date);
	List<Historique> findLavagesAverdaByDateAndVehicule(Date date, Integer idVehicule);

	// Balayages Sita
	List<Integer> findVehiculesOfBalayageSitaByDate(Date dateAvant);
	List<Historique> findBalayagesSitaByDateAndVehicule(Date date, Integer idVehicule);
	
	//Lavages Sita
	List<Integer> findVehiculesOfLavageSitaByDate(Date date);
	List<Historique> findLavagesSitaByDateAndVehicule(Date date, Integer idVehicule);
	

	// Brossage lavage
	List<Integer> findVehiculesOfBrossageLavageByDate(Date dateAvant);
	List<Historique> findBrossagesLavageByDateAndVehicule(Date date, Integer idVehicule);
	List<Historique> findBrossagesByDate(Date date);

	Long count();
	Point createPointfromXY_43(Double x, Double y);

}
