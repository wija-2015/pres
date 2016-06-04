package com.casaprestations.burs.attachement.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casaprestations.burs.attachement.entity.db.postgres.Evenement;

public interface EvenementRepository extends JpaRepository<Evenement, Integer>{

}
