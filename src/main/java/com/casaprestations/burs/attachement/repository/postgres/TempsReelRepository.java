package com.casaprestations.burs.attachement.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casaprestations.burs.attachement.entity.db.postgres.TempsReel;
import com.casaprestations.burs.attachement.entity.db.postgres.TempsReelPK;

public interface TempsReelRepository extends JpaRepository<TempsReel, TempsReelPK>{

}
