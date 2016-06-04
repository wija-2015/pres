package com.casaprestations.burs.attachement.shedule;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// aprs qu'on a developpe des declenchurs qui automatisent le calcul du
// kmitrgae du bayalage, lavage et lavagae brossage
// Mntn on travaille sur l'integration de ces calculs dans une plate forme
// web pour pouvoir consulter ces attachements pr les 2 deleg bien sur sita et averda , meme les avoir ss format
// pdf ou excel
import com.casaprestations.burs.attachement.entity.db.calcul.LavageBalayage;
import com.casaprestations.burs.attachement.entity.db.calcul.VehiculeDestination;
import com.casaprestations.burs.attachement.entity.db.postgres.Historique;
import com.casaprestations.burs.attachement.entity.db.postgres.Vehicule;
import com.casaprestations.burs.attachement.metier.calcul.IVehiculeDestinationMetier;
import com.casaprestations.burs.attachement.metier.postgres.IHistoriqueMetier;
import com.casaprestations.burs.attachement.metier.postgres.IVehiculeMetier;
import com.casaprestations.burs.attachement.repository.calcul.BalayageRepository;

@Service
public class SheduleForAverda {

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

	// Shedule pour inserer les balayages mécanisés d'Averda dans la table LavagaeBalayag
	public void scheduleForInsertingBalayageMecaniseAverda() {
		Date dateYesterday = methodsForShedules.getDateYesterday();
		List<Integer> idVehicules = historiqueMetier.findVehiculesOfBalayageAverdaByDate(dateYesterday);
		for (Integer idV : idVehicules) {
			Double kmCapte = (double) 0;
			List<Historique> historiques1 = historiqueMetier.findBalayagesAverdaByDateAndVehicule(dateYesterday, idV);
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
	
	// Shedule pour inserer les lavages mécanisés d'Averda dans la table LavagaeBalayag
		public void scheduleForInsertingLavageMecaniseAverda() {
			Date dateYesterday = methodsForShedules.getDateYesterday();
			List<Integer> idVehicules = historiqueMetier.findVehiculesOfLavageAverdaByDate(dateYesterday);
			for (Integer idV : idVehicules) {
				Double kmCapte = (double) 0;
				List<Historique> historiques1 = historiqueMetier.findLavagesAverdaByDateAndVehicule(dateYesterday, idV);
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
