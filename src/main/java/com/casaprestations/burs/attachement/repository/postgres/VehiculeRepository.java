package com.casaprestations.burs.attachement.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casaprestations.burs.attachement.entity.db.postgres.Vehicule;

public interface VehiculeRepository extends JpaRepository<Vehicule, Integer>{

}
