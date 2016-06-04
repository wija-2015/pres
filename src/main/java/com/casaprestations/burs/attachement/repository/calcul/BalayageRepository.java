package com.casaprestations.burs.attachement.repository.calcul;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.casaprestations.burs.attachement.entity.db.calcul.LavageBalayage;
import com.casaprestations.burs.attachement.entity.db.calcul.VehiculeDestination;

public interface BalayageRepository extends JpaRepository<LavageBalayage, Integer> {

	// Sita(S34, S33) : Liste des vehicules de balayge mecanisé de Sita
	@Query("select v from VehiculeDestination v where v.nomVehicule LIKE 'S34%' or v.nomVehicule LIKE 'S33%' order by nomVehicule")
	List<VehiculeDestination> getVehiculesOfBalaygeSita();

	// Averda(A34) : Liste des vehicules de balayage mecanisé de Averda
	@Query("select v from VehiculeDestination v where v.nomVehicule LIKE 'A34%' order by nomVehicule")
	List<VehiculeDestination> findVehiculesOfBalaygeAverda();

	// Sita (S34,S33) : Liste des Balayages mécanisés de Sita
	@Query("select bal  from LavageBalayage bal, VehiculeDestination v where bal.vehicule.idV=v.idV and EXTRACT(YEAR FROM bal.dateJour) =:x and extract(MONTH from bal.dateJour) =:y and (v.nomVehicule LIKE 'S34%' or v.nomVehicule LIKE 'S33%')  order by dateJour")
	List<LavageBalayage> findBalayagesOfSitaByYearAndMonth(@Param("x") int year, @Param("y") int month);

	// Calcule de la somme de (kilomitrage balayaé, kilomatrage parcouru, jours
	// travaillés) d'un mois
	@Query("select v.nomVehicule, SUM(bal.travaille), SUM(bal.kmCapte),SUM(bal.kmTotal) from LavageBalayage bal,VehiculeDestination v  where bal.vehicule.idV=v.idV and EXTRACT(YEAR FROM bal.dateJour)=:x and extract(MONTH from bal.dateJour)=:y and (v.nomVehicule LIKE 'S34%' or v.nomVehicule LIKE 'S33%') GROUP BY v.nomVehicule ORDER BY v.nomVehicule")
	List<Object[]> getSumOfBalayagesBySita(@Param("x") int year, @Param("y") int month);

}