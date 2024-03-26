package cda.tom.model;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Couleur {
	
	private int id_Couleur;
	private String nom_couleur;
	private static ArrayList<Couleur> couleurResult= new ArrayList();
	
	
	public Couleur() {}
	
	public Couleur (int id,String nom) {
		this.id_Couleur=id;
		this.nom_couleur=nom;
	}
	
	
	public int getId_Couleur() {
		return id_Couleur;
	}


	public void setId_Couleur(int id_Couleur) {
		this.id_Couleur = id_Couleur;
	}


	public String getNom_couleur() {
		return nom_couleur;
	}


	public void setNom_couleur(String nom_couleur) {
		this.nom_couleur = nom_couleur;
	}


	public static ArrayList<Couleur> getCouleurResult() {
		return couleurResult;
	}

	public static void setCouleurResult(Couleur couleur) {
		couleurResult.add(couleur);
	}
}
