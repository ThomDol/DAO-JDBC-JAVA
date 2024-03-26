package cda.tom.model;

import java.util.ArrayList;

public class Marque {
	private int id_marque;
	private String nom_marque;
	private Pays pays;
	private Fabricant fabricant;
	private static ArrayList<Marque> marqueResult= new ArrayList<Marque>();
	
	
	public Marque () {}
	
	public Marque(int id,String nom,Pays pays,Fabricant fabricant) {
		this.id_marque=id;
		this.nom_marque=nom;
		this.pays=pays;
		this.fabricant=fabricant;
	}
	
	public int getId_marque() {
		return id_marque;
	}


	public void setId_marque(int id_marque) {
		this.id_marque = id_marque;
	}


	public String getNom_marque() {
		return nom_marque;
	}


	public void setNom_marque(String nom_marque) {
		this.nom_marque = nom_marque;}
	
	public Fabricant getFabricant() {
		return fabricant;}


	public void setFabricant(Fabricant fabricant) {
		this.fabricant = fabricant;}
	
	public Pays getPays() {
		return pays;}


	public void setPays(Pays pays) {
		this.pays = pays;}


	public static ArrayList<Marque> getMarqueResult() {
		return marqueResult;
	}

	public static void setMarqueResult(Marque marque) {
		marqueResult.add(marque);
	}


			
}
