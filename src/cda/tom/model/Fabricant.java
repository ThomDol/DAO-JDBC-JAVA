package cda.tom.model;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Fabricant {
	private int id_fabricant;
	private String nom_fabricant;
	private static ArrayList<Fabricant> fabricantResult= new ArrayList();
	
	
	public Fabricant() {}
	
	public Fabricant (int id, String nom) {
		this.id_fabricant=id;
		this.nom_fabricant=nom;
	}

	public int getId_fabricant() {
		return id_fabricant;
	}

	public void setId_fabricant(int id_fabricant) {
		this.id_fabricant = id_fabricant;
	}

	public String getNom_fabricant() {
		return nom_fabricant;
	}

	public void setNom_fabricant(String nom_fabricant) {
		this.nom_fabricant = nom_fabricant;
	}

	public static ArrayList<Fabricant> getFabricantResult() {
		return fabricantResult;
	}

	public static void setFabricantResult(Fabricant fabricant) {
		fabricantResult.add(fabricant);
	}
	
	
}
