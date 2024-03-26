package cda.tom.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Continent {
	private long id_Continent;
	private String nom_continent;
	private static ArrayList<Continent> continentResult= new ArrayList();
	
	

	public Continent() {}
	
	public Continent(long id,String nom) {
		this.id_Continent=id;
		this.nom_continent=nom;
	}
	
	public long getId_Continent() {
		return id_Continent;
	}

	public void setId_Continent(long id) {
		this.id_Continent= id;
	}

	public String getNom_continent() {
		return nom_continent;
	}

	public void setNom_continent(String nom_continent) {
		this.nom_continent = nom_continent;
	}
	
	public static ArrayList<Continent> getContinentResult() {
		return continentResult;
	}

	public static void setContinentResult(Continent continent) {
		continentResult.add(continent);
	}

	
	
		
}
