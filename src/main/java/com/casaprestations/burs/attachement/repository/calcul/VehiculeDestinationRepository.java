package com.casaprestations.burs.attachement.repository.calcul;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.casaprestations.burs.attachement.entity.db.calcul.VehiculeDestination;

public interface VehiculeDestinationRepository extends JpaRepository<VehiculeDestination, Integer> {

	// get VehiculeDestination by nomVehicule
	@Query("select veh from VehiculeDestination veh where veh.nomVehicule like :x")
	VehiculeDestination getVehiculeByNom(@Param("x") String nom);

	// Sita(S34, S33) : Liste des noms des vehicules de balayge mecanisé de Sita
	@Query("select v.nomVehicule from VehiculeDestination v where v.nomVehicule LIKE 'S34%' or v.nomVehicule LIKE 'S33%' order by nomVehicule")
	List<String> getNomVehiculesOfBalaygeSita();
	
}
