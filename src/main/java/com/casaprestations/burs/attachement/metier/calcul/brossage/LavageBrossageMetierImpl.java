package com.casaprestations.burs.attachement.metier.calcul.brossage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casaprestations.burs.attachement.entity.db.calcul.LavageBalayage;
import com.casaprestations.burs.attachement.entity.db.calcul.LavageBrossage;
import com.casaprestations.burs.attachement.entity.db.calcul.Place;
import com.casaprestations.burs.attachement.entity.db.calcul.VehiculeDestination;
import com.casaprestations.burs.attachement.metier.calcul.MethodsForMetier;
import com.casaprestations.burs.attachement.metier.calcul.balayagemecanise.BalayageBean;
import com.casaprestations.burs.attachement.metier.calcul.balayagemecanise.KmDataBean;
import com.casaprestations.burs.attachement.repository.calcul.BalayageRepository;
import com.casaprestations.burs.attachement.repository.calcul.LavageBrossageRepository;
import com.casaprestations.burs.attachement.repository.calcul.PlaceRepository;

@Service
public class LavageBrossageMetierImpl implements ILavageBrossageMetier {

	@Autowired
	private LavageBrossageRepository lavageBrossageRepository;
	@Autowired
	private PlaceRepository placeRepository;
	@Autowired
	private MethodsForMetier methodsForMetier;


	@Override
	public List<BrossageBean> findBrossagesOfSitaByYearAndMonth(String dateString) {
		// parse String to Date
		Date date = methodsForMetier.parseStringToDate(dateString);
		Calendar cal = Calendar.getInstance();
		// get l'année et le mois d'une date
		int year = methodsForMetier.getYear(date);System.out.println(year);
		int month = methodsForMetier.getMonth(date);System.out.println(month);
		// Obtenir les lavages.brossages de sita pendant l'année year et le mois month
		List<LavageBrossage> brossages = lavageBrossageRepository.findLavagesBrossagesOfSitaByYearAndMonth(year, month);
		System.out.println("all brossages size " + brossages.size());
		// Trouver tous les vehicules de balayage mécanisé de sita commencant
		// par S34,S33
		//List<VehiculeDestination> listesV = balayageRepository.getVehiculesOfBalaygeSita();
		List<Place> listePlaces=placeRepository.findAll();
		System.out.println("places size " + listePlaces.size());
		// get le nombre de jour du mois de dateString
		int days = methodsForMetier.getDaysOfMonth(date);
		System.out.println("nbr de jour " + days);
		List<BrossageBean> brossageBeans = new ArrayList<BrossageBean>();
		// Boucle sur les vehicules de sita du balayage
		for (Place place : listePlaces) {
			System.out.println("this place is " + place.getNomPlace());
			List<LavageBrossage> results = methodsForMetier.getBrossagesOfThisPlace(brossages, place);
			System.out.println("broosages of this place  " + results.size());
			Double sumDuree=(double) 0;
			for (LavageBrossage bross : results) {
				sumDuree=sumDuree+bross.getDuree();
			}
			System.out.println("somme duree "+sumDuree);
			BrossageBean brossageBean = new BrossageBean();
			brossageBean.setIdPlace(place.getIdPlace());
			brossageBean.setNomPlace(place.getNomPlace());
			brossageBean.setLot(place.getLot());
			//bean.setPrefecture(place.getPrefecture());
			List<DureeBean> dureeBeans = new ArrayList<DureeBean>();
			// boucle sur les n jours du mois
			label: for (int i = 1; i <= days; i++) {
				DureeBean dureeBean = new DureeBean();
				// boucle sur les brossages concerne la place place
				Double sumDuree2=(double) 0;
				for (LavageBrossage bross : results) {
					// si le jour de ce brossage est égale à i, on garde les
					// informations de ce brossage
					int jour = methodsForMetier.getJourFromBrossage(bross);
					if (jour == i) {
						dureeBean.setDateJour(bross.getDateJour());
						dureeBean.setDuree(sumDuree);;
						dureeBeans.add(dureeBean);
						continue label;
					}
				}
				// si ce vehicule n'a pas travaillé le jour i , on affecte des 0
				// à duree 
				DureeBean dureeBean2 = methodsForMetier.affectBrossageToPlace(cal, year, month, place, i,
						dureeBean);
				dureeBeans.add(dureeBean2);
			}
			brossageBean.setDureeBeans(dureeBeans);
			System.out.println("brossages bean of " + place + " : " + dureeBeans.size());
			brossageBeans.add(brossageBean);
		}
		return brossageBeans;
	}

}
