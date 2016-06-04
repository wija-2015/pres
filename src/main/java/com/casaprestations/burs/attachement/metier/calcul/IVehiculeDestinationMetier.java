package com.casaprestations.burs.attachement.metier.calcul;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.casaprestations.burs.attachement.entity.db.calcul.VehiculeDestination;

public interface IVehiculeDestinationMetier {

	public VehiculeDestination getVehiculeByNom(String nom);

	public Page<VehiculeDestination> findAllVehicules(Pageable p);

	public VehiculeDestination saveVehicule(VehiculeDestination v);

	List<String> getNomVehiculesOfBalaygeSita();

}
