package cda.tom.model;

import java.util.ArrayList;

public class Pays {
	private int id_pays;
	private String nom_pays;
	private Continent continent;
	private static ArrayList<Pays> paysResult= new ArrayList<Pays>();
	
	public Pays () {}
	
	public Pays (int id, String nom,Continent continent) {
		this.id_pays=id;
		this.nom_pays=nom;
		this.continent=continent;
	}
	
	
	public int getId_pays() {
		return id_pays;}
	
	public void setId_pays(int id_pays) {
		this.id_pays = id_pays;}
	
	public String getNom_pays() {
		return nom_pays;}
	
	public void setNom_pays(String nom_pays) {
		this.nom_pays = nom_pays;}
	
	public void setContinent(Continent continent) {
		this.continent = continent;}
	
	public Continent getContinent () {
		return this.continent;}
	
	public static ArrayList<Pays> getPaysResult() {
		return paysResult;}
	
	public static void setPaysResult(Pays pays) {
		paysResult.add(pays);}
	
	
	

}
