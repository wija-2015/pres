package com.casaprestations.burs.attachement.ui.controllers.postgressabatier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.casaprestations.burs.attachement.entity.db.postgres.Categorie;
import com.casaprestations.burs.attachement.metier.postgres.ICategorieMetier;

@RestController
@RequestMapping("/categories")
public class CategorieController {
	
	@Autowired
	private ICategorieMetier categorieMetier;
	
	@RequestMapping(value = "/allCategories", method = RequestMethod.GET)
	public List<Categorie> allCategories() {
		return categorieMetier.allCategories();
	}

}
