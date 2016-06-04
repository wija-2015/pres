package com.casaprestations.burs.attachement.repository.calcul;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.casaprestations.burs.attachement.entity.db.calcul.LavageBrossage;

public interface LavageBrossageRepository extends JpaRepository<LavageBrossage, Integer> {


		// Sita (S34,S33) : Liste des Lavagaes/Brossages mécanisés de Sita
		@Query("select bross  from LavageBrossage bross, Place p  where bross.place.idPlace=p.idPlace and EXTRACT(YEAR FROM bross.dateJour) =:x and extract(MONTH from bross.dateJour) =:y  order by dateJour")
		List<LavageBrossage> findLavagesBrossagesOfSitaByYearAndMonth(@Param("x") int year, @Param("y") int month);

		// Calcule de la somme de (kilomitrage balayaé, kilomatrage parcouru, jours
		// travaillés) d'un mois
		//@Query("select v.nomVehicule, SUM(bal.travaille), SUM(bal.kmCapte),SUM(bal.kmTotal) from LavageBalayage bal,VehiculeDestination v  where bal.vehicule.idV=v.idV and EXTRACT(YEAR FROM bal.dateJour)=:x and extract(MONTH from bal.dateJour)=:y and (v.nomVehicule LIKE 'S34%' or v.nomVehicule LIKE 'S33%') GROUP BY v.nomVehicule ORDER BY v.nomVehicule")
		//List<Object[]> getSumOfLavagesBrossagesBySita(@Param("x") int year, @Param("y") int month);
}
