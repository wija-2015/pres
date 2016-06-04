package com.casaprestations.burs.attachement.metier.postgres;

import java.util.List;

import com.casaprestations.burs.attachement.entity.db.postgres.Categorie;

public interface ICategorieMetier {
	
	public List<Categorie> allCategories();
	public Categorie saveCategorie(Categorie c);
	
}
