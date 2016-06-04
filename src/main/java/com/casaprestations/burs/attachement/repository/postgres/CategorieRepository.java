package com.casaprestations.burs.attachement.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casaprestations.burs.attachement.entity.db.postgres.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Integer>{
		
}