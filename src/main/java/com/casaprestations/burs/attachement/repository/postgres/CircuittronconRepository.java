package com.casaprestations.burs.attachement.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casaprestations.burs.attachement.entity.db.postgres.Circuittroncon;
import com.casaprestations.burs.attachement.entity.db.postgres.CircuittronconPK;

public interface CircuittronconRepository extends JpaRepository<Circuittroncon, CircuittronconPK> {

}
