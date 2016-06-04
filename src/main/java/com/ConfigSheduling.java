package com;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.geotools.jdbc.JDBCDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.casaprestations.burs.attachement.entity.db.calcul.LavageBalayage;
import com.casaprestations.burs.attachement.entity.db.postgres.Historique;
import com.casaprestations.burs.attachement.metier.postgres.IHistoriqueMetier;
import com.casaprestations.burs.attachement.repository.calcul.BalayageRepository;
import com.casaprestations.burs.attachement.repository.calcul.LavageBrossageRepository;
import com.vividsolutions.jts.geom.Point;

@Configuration
@EnableScheduling
public class ConfigSheduling {

	@Autowired
	private IHistoriqueMetier historiqueMetier;
	@Autowired
	private BalayageRepository balayageRepository;
	@Autowired
	private LavageBrossageRepository lavageBrossageRepository;
	private static String ZONE_INTERVENTION_TABLE_NAME = "places";
	@Autowired
	private JDBCDataStore postGisDataStore;

	// Schedule pour inserer les calcul de distance du balayage mecanisé dans la
	// table LavageBalayage
	@Scheduled(cron = "0 39 12 * * *")
	public void scheduleForInsertingLavageBalayage() {
		Date dateCourant = new Date();
		dateCourant.setDate(dateCourant.getDate() - 1);
		Date dateAvant = dateCourant;
		List<Integer> idVehicules = historiqueMetier.findVehiculesOfBalayageAverdaByDate(dateAvant);
		Double km = (double) 0;
		for (Integer idV : idVehicules) {
			List<Historique> historiques1 = historiqueMetier.findBalayagesAverdaByDateAndVehicule(dateAvant, idV);
			for (int i = 0; i < historiques1.size(); i++) {
				if ((historiques1.get(i).getCapteurTor() == 64 || historiques1.get(i).getCapteurTor() == 128
						|| historiques1.get(i).getCapteurTor() == 192)) {
					if (i != (historiques1.size() - 1)) {
						for (int j = i + 1; j < i + 2; j++) {
							List<Historique> historiques2 = new ArrayList<Historique>();
							historiques2.add(historiques1.get(i));
							historiques2.add(historiques1.get(j));
							List<Point> TwoPoints = historiqueMetier.createPointsFromHistory(historiques2);
							km = km + historiqueMetier.getDistanceFromPoints(TwoPoints);
						}
					}
				}
			}
			LavageBalayage mec = new LavageBalayage();
			mec.setDateJour(dateAvant);
			// mec.setIdVehicule(historiques1.get(0).getVehicule().getId());
			mec.setKmCapte(km);
			// mec.setNomVehicule(historiques1.get(0).getVehicule().getNom());
			LavageBalayage obj = balayageRepository.save(mec);
			System.out.println(obj.getId());
			// System.out.println(obj.getIdVehicule());
			System.out.println(obj.getDateJour());
			System.out.println(obj.getKmCapte());
		}
	}

}
