package com.casaprestations.burs.attachement.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casaprestations.burs.attachement.entity.db.postgres.Circuit;
import com.casaprestations.burs.attachement.entity.db.postgres.CircuitPK;

public interface CircuitRepository extends JpaRepository<Circuit, CircuitPK>{

}
