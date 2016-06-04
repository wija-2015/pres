package com.casaprestations.burs.attachement.metier.calcul;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.casaprestations.burs.attachement.entity.db.calcul.LavageBalayage;
import com.casaprestations.burs.attachement.entity.db.calcul.LavageBrossage;
import com.casaprestations.burs.attachement.entity.db.calcul.Place;
import com.casaprestations.burs.attachement.entity.db.calcul.VehiculeDestination;
import com.casaprestations.burs.attachement.metier.calcul.balayagemecanise.KmDataBean;
import com.casaprestations.burs.attachement.metier.calcul.balayagemecanise.SumBean;
import com.casaprestations.burs.attachement.metier.calcul.brossage.DureeBean;

@Service
public class MethodsForMetier {

	// Convertir une date de type string en type Date
	public Date parseStringToDate(String dateString) {
		System.out.println("date string " + dateString);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = df.parse(dateString);
			System.out.println("date typeDate " + date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// System.out.println("parse " +date);
		return date;
	}

	// get le nombre de jours d'un mois
	public int getDaysOfMonth(Date date) {
		int month = this.getMonth(date);
		int year =this.getYear(date);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		System.out.println(year);
		System.out.println(month);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println(days);
		return days;
	}

	public int getYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		return year;
	}

	public int getMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH) + 1;
		return month;
	}

	public int getJourFromLavageBalayge(LavageBalayage bal) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(bal.getDateJour());
		int jour = cal.get(Calendar.DAY_OF_MONTH);
		return jour;
	}
	
	public int getJourFromBrossage(LavageBrossage brossage) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(brossage.getDateJour());
		int jour = cal.get(Calendar.DAY_OF_MONTH);
		return jour;
	}

	public SumBean getSumOfVehicule(VehiculeDestination listeV, SumBean bean) {
		bean.setNomVehicule(listeV.getNomVehicule());
		bean.setType(listeV.getType());
		bean.setAffectation(listeV.getAffectation());
		bean.setSumTravaille(0);
		bean.setSumKmCapte(0);
		bean.setSumKmTotal(0);
		return bean;
	}

	public KmDataBean affectBalayageToVehicule(Calendar cal, int year, int month, VehiculeDestination veh, int i,
			KmDataBean kmDataBean) {
		kmDataBean.setKmCapte(0);
		kmDataBean.setKmTotal(0);
		kmDataBean.setTravaille(0);
		cal.set(year, month, i, 0, 0, 0);
		Date d = cal.getTime();
		kmDataBean.setDateJour(d);
		return kmDataBean;
	}

	// filrer les balayages d'un vehicule
	public List<LavageBalayage> getBalayagesOfThisVehicule(List<LavageBalayage> balayages, VehiculeDestination listeV) {
		List<LavageBalayage> results = new ArrayList<LavageBalayage>();
		for (LavageBalayage balayage : balayages) {
			if (balayage.getVehicule().getNomVehicule().equals(listeV.getNomVehicule())) {
				results.add(balayage);
			}
		}
		return results;
	}

	// filrer les lavages/Brossage d'un vehicule
	public List<LavageBrossage> getBrossagesOfThisPlace(List<LavageBrossage> brossages, Place p) {
		List<LavageBrossage> results = new ArrayList<LavageBrossage>();
		for (LavageBrossage brossage : brossages) {
			if (brossage.getPlace().getIdPlace()==p.getIdPlace()) {
				results.add(brossage);
			}
		}
		return results;
	}
	
	public DureeBean affectBrossageToPlace(Calendar cal, int year, int month, Place p, int i,
			DureeBean dureeBean) {
		dureeBean.setDuree((double) 0);
		cal.set(year, month, i, 0, 0, 0);
		Date d = cal.getTime();
		dureeBean.setDateJour(d);
		return dureeBean;
	}

}
