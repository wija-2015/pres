package com.casaprestations.burs.attachement.shedule;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casaprestations.burs.attachement.entity.db.calcul.LavageBalayage;
import com.casaprestations.burs.attachement.entity.db.calcul.VehiculeDestination;
import com.casaprestations.burs.attachement.entity.db.postgres.Historique;
import com.casaprestations.burs.attachement.entity.db.postgres.Vehicule;
import com.casaprestations.burs.attachement.metier.calcul.IVehiculeDestinationMetier;
import com.casaprestations.burs.attachement.metier.postgres.IHistoriqueMetier;
import com.casaprestations.burs.attachement.metier.postgres.IVehiculeMetier;
import com.casaprestations.burs.attachement.repository.calcul.BalayageRepository;

@Service
public class SheduleForSita {

	@Autowired
	private IHistoriqueMetier historiqueMetier;
	@Autowired
	private BalayageRepository balayageRepository;
	@Autowired
	private IVehiculeMetier vehiculeMetier;
	@Autowired
	private MethodsForShedules methodsForShedules;
	@Autowired
	private IVehiculeDestinationMetier vehiculeDestinationMetier;

	public void scheduleForInsertingBalayageMecaniseSita() {
		Date dateYesterday = methodsForShedules.getDateYesterday();
		List<Integer> idVehicules = historiqueMetier.findVehiculesOfBalayageSitaByDate(dateYesterday);
		for (Integer idV : idVehicules) {
			Double kmCapte = (double) 0 ;
			List<Historique> historiques1 = historiqueMetier.findBalayagesSitaByDateAndVehicule(dateYesterday, idV);
			Double kmTotal = methodsForShedules.getKmTotalFromListBalayages(historiques1);
			kmCapte = methodsForShedules.getKmCapteFromListBalayages(kmCapte, historiques1);
			LavageBalayage balayage = new LavageBalayage();
			Vehicule v = vehiculeMetier.findVehicule(idV);
			VehiculeDestination vd = vehiculeDestinationMetier.getVehiculeByNom(v.getNom());
			balayage.setDateJour(dateYesterday);
			balayage.setKmCapte(kmCapte);
			balayage.setKmTotal(kmTotal);
			balayage.setTravaille(1);
			balayage.setVehicule(vd);
			LavageBalayage obj = balayageRepository.save(balayage);
		}
	}
	
	public void scheduleForInsertingLavageMecaniseSita() {
		Date dateYesterday = methodsForShedules.getDateYesterday();
		List<Integer> idVehicules = historiqueMetier.findVehiculesOfLavageSitaByDate(dateYesterday);
		for (Integer idV : idVehicules) {
			Double kmCapte = (double) 0;
			List<Historique> historiques1 = historiqueMetier.findLavagesSitaByDateAndVehicule(dateYesterday, idV);
			Double kmTotal = methodsForShedules.getKmTotalFromListBalayages(historiques1);
			kmCapte = methodsForShedules.getKmCapteFromListBalayages(kmCapte, historiques1);
			LavageBalayage balayage = new LavageBalayage();
			Vehicule v = vehiculeMetier.findVehicule(idV);
			VehiculeDestination vd = vehiculeDestinationMetier.getVehiculeByNom(v.getNom());
			System.out.println("veh nom : " + vd.getAffectation());
			balayage.setDateJour(dateYesterday);
			balayage.setKmCapte(kmCapte);
			balayage.setKmTotal(kmTotal);
			balayage.setTravaille(1);
			balayage.setVehicule(vd);
			LavageBalayage obj = balayageRepository.save(balayage);
		}
	}

}
