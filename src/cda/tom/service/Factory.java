package cda.tom.service;

import cda.tom.model.Article;
import cda.tom.model.Continent;
import cda.tom.model.Fabricant;
import cda.tom.model.Marque;
import cda.tom.model.Pays;
import cda.tom.model.Vendre;

public class Factory {

	public static DAO<Pays> paysService(){
		return new PaysService();
	}
	
	
	public static DAO<Article> articleService(){
		return new ArticleService();
	}
	
	public static DAO<Fabricant> fabricantService(){
		return new FabricantService();
	}
	
	public static DAO<Marque> marqueService(){
		return new MarqueService();
	}
	
	public static DAO<Continent> continentService(){
		return new ContinentService();
	}
	
	public static DAO<Vendre> vendreService(){
		return new VendreService();
	}
		
}
