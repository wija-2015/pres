package com.casaprestations.burs.attachement.metier.calcul.balayagemecanise;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casaprestations.burs.attachement.entity.db.calcul.LavageBalayage;
import com.casaprestations.burs.attachement.entity.db.calcul.VehiculeDestination;
import com.casaprestations.burs.attachement.metier.calcul.MethodsForMetier;
import com.casaprestations.burs.attachement.repository.calcul.BalayageRepository;

@Service
public class BalayageMecaniseMetierImpl implements IBalayageMecaniseMetier {

	@Autowired
	private BalayageRepository balayageRepository;
	@Autowired
	private MethodsForMetier methodsForMetier;

	@Override
	public List<BalayageBean> findBalayagesOfSitaByYearAndMonth(String dateString) {
		// parse String to Date
		Date date = methodsForMetier.parseStringToDate(dateString);
		Calendar cal = Calendar.getInstance();
		// get l'année et le mois d'une date
		int year = methodsForMetier.getYear(date);
		int month = methodsForMetier.getMonth(date);
		// Obtenir les balayages de sita pendant l'année year et le mois month
		List<LavageBalayage> balayages = balayageRepository.findBalayagesOfSitaByYearAndMonth(year, month);
		System.out.println("all balayages size " + balayages.size());
		// Trouver tous les vehicules de balayage mécanisé de sita commencant
		// par S34,S33
		List<VehiculeDestination> listesV = balayageRepository.getVehiculesOfBalaygeSita();
		System.out.println("Vehicules de sita de balayage de " + listesV.size());
		// get le nombre de jour du mois de dateString
		int days = methodsForMetier.getDaysOfMonth(date);
		System.out.println("nbr de jour " + days);
		List<BalayageBean> balayageBeans=new ArrayList<BalayageBean>();
		// Boucle sur les vehicules de sita du balayage
		for (VehiculeDestination veh : listesV) {
			System.out.println("this veh is " + veh.getNomVehicule());
			List<LavageBalayage> results = methodsForMetier.getBalayagesOfThisVehicule(balayages, veh);
			System.out.println("balayages of this vehicule  " + results.size());
			BalayageBean bean = new BalayageBean();
			bean.setNomVehicule(veh.getNomVehicule());
			bean.setType(veh.getType());
			bean.setAffectation(veh.getAffectation());
			List<KmDataBean> kmDataBeans= new ArrayList<KmDataBean>();
			// boucle sur les n jours du mois
			label: for (int i = 1; i <= days; i++) {
				KmDataBean kmDataBean=new KmDataBean();
				// boucle sur les balayges faits par le vehicule listeV
				for (LavageBalayage bal : results) {
					// si le jour de ce balayage est égale à i, on garde les
					// informations de ce balayage
					int jour = methodsForMetier.getJourFromLavageBalayge(bal);
					if (jour == i) {
						kmDataBean.setDateJour(bal.getDateJour());
						kmDataBean.setKmCapte(bal.getKmCapte());
						kmDataBean.setKmTotal(bal.getKmTotal());
						kmDataBean.setTravaille(bal.getTravaille());
						kmDataBeans.add(kmDataBean);
						continue label;
					}
				}
				// si ce vehicule n'a pas travaillé le jour i , on affecte des 0
				// à kmTravaille,kmTotal,kmCapte
				KmDataBean kmDataBean2 = methodsForMetier.affectBalayageToVehicule(cal, year, month, veh, i, kmDataBean);
				kmDataBeans.add(kmDataBean2);
			}
			bean.setKmDataBeans(kmDataBeans);
			System.out.println("balayageBeans of " + veh + " : " + kmDataBeans.size());
			balayageBeans.add(bean);
		}
		return balayageBeans;
	}

	@Override
	public List<SumBean> getSumOfBalayagesBySita(String dateString) {
		Date date = methodsForMetier.parseStringToDate(dateString);
		int year = methodsForMetier.getYear(date);
		int month = methodsForMetier.getMonth(date);
		List<Object[]> sums = balayageRepository.getSumOfBalayagesBySita(year, month);
		List<VehiculeDestination> listesV = balayageRepository.getVehiculesOfBalaygeSita();
		List<SumBean> sumBeans = new ArrayList<SumBean>();
		label: for (VehiculeDestination veh : listesV) {
			SumBean bean = new SumBean();
			for (Object[] sum : sums) {
				String nomV = sum[0].toString();
				if (veh.getNomVehicule().equals(nomV)) {
					bean.setNomVehicule(nomV);
					bean.setType(veh.getType());
					bean.setAffectation(veh.getAffectation());
					long taravailles = (long) sum[1];
					Integer taravailles2 = (int) (long) taravailles;
					bean.setSumTravaille(taravailles2);
					bean.setSumKmCapte((double) sum[2]);
					bean.setSumKmTotal((double) sum[3]);
					sumBeans.add(bean);
					continue label;
				}
			}
			SumBean obj = methodsForMetier.getSumOfVehicule(veh, bean);
			sumBeans.add(obj);
		}
		
		ComparatorSumBean comparator= new ComparatorSumBean();
		Collections.sort(sumBeans, comparator);
		return sumBeans;
	}

	// get un tableau de jour d'un mois donné
	@Override
	public List<Integer> getTabDaysMonth(String dateString) {
		Date date = methodsForMetier.parseStringToDate(dateString);
		// get le mois d'une date
		int month = methodsForMetier.getMonth(date);
		// get le mois d'une date
		int year = methodsForMetier.getYear(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		//System.out.println(days);
		List<Integer> d = new ArrayList<Integer>();
		for (int i = 1; i <= days; i++) {
			d.add(i);
			//System.out.println(i);
		}
		return d;
	}
}