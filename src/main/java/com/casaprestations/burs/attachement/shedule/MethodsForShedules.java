package com.casaprestations.burs.attachement.shedule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casaprestations.burs.attachement.entity.db.postgres.Historique;
import com.casaprestations.burs.attachement.metier.postgres.IHistoriqueMetier;
import com.vividsolutions.jts.geom.Point;

@Service
public class MethodsForShedules {

	@Autowired
	IHistoriqueMetier historiqueMetier;

	// Methode pour récuperer la date d'hier
	public Date getDateYesterday() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, -1);
		Date d = c.getTime();
		return d;
	}

	// Methode pour calculer les kms captés(capteur_tor actif) de balayage à
	// partir d'une liste de Historiques
	public Double getKmCapteFromListBalayages(Double kmCapte, List<Historique> historiques1) {
		for (int i = 0; i < historiques1.size(); i++) {
			if ((historiques1.get(i).getCapteurTor() == 64 || historiques1.get(i).getCapteurTor() == 128
					|| historiques1.get(i).getCapteurTor() == 192) && (i != (historiques1.size() - 1))) {
				for (int j = i + 1; j < i + 2; j++) {
					List<Historique> historiques2 = new ArrayList<Historique>();
					historiques2.add(historiques1.get(i));
					historiques2.add(historiques1.get(j));
					List<Point> TwoPoints = historiqueMetier.createPointsFromHistory(historiques2);
					kmCapte = kmCapte + historiqueMetier.getDistanceFromPoints(TwoPoints);
				}
			}
		}
		return kmCapte;
	}

	// Methode pour calculer les kms(total) parcourus(capteur_tor actif/non
	// actif) de balayage à partir d'une liste de Historiques
	public Double getKmTotalFromListBalayages(List<Historique> historiques1) {
		List<Point> points = historiqueMetier.createPointsFromHistory(historiques1);
		Double kmTotal = historiqueMetier.getDistanceFromPoints(points);
		return kmTotal;
	}
}
