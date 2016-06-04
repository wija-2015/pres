package com.casaprestations.burs.attachement.metier.postgres;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casaprestations.burs.attachement.entity.db.postgres.Categorie;
import com.casaprestations.burs.attachement.repository.postgres.CategorieRepository;

@Service
public class CategorieMetierImpl implements ICategorieMetier{
	
	@Autowired
	private CategorieRepository categorieRepository;

	@Override
	public List<Categorie> allCategories() {
		// TODO Auto-generated method stub
		return categorieRepository.findAll();
	}
	@Override
	public Categorie saveCategorie(Categorie c) {
		// TODO Auto-generated method stub
		return categorieRepository.save(c);
	}
	public CategorieRepository getCategorieRepository() {
		return categorieRepository;
	}

	public void setCategorieRepository(CategorieRepository categorieRepository) {
		this.categorieRepository = categorieRepository;
	}
	
}
