package cda.tom.model;

import java.util.ArrayList;

public class Article {
	private long id_article;
	private String nom_article;
	private double prix_achat;
	private int volume;
	private double titrage;
	private Marque marque;
	private Couleur couleur;
	private TypeBiere typeBiere;
	private static ArrayList<Article> articleResult= new ArrayList();
	
	
	public Article() {}
	
	

	public long getId_article() {
		return id_article;}


	public void setId_article(long id_article) {
		this.id_article = id_article;}


	public String getNom_article() {
		return nom_article;}


	public void setNom_article(String nom_article) {
		this.nom_article = nom_article;}


	public double getPrix_achat() {
		return prix_achat;}


	public void setPrix_achat(double prix_achat) {
		this.prix_achat = prix_achat;}


	public int getVolume() {
		return volume;}


	public void setVolume(int volume) {
		this.volume = volume;}


	public double getTitrage() {
		return titrage;}


	public void setTitrage(double titrage) {
		this.titrage = titrage;}
	
	
	public Marque getMarque() {
		return marque;}

	public void setMarque(Marque marque) {
		this.marque = marque;}


	public Couleur getCouleur() {
		return couleur;}


	public void setCouleur(Couleur couleur) {
		this.couleur = couleur;}


	public TypeBiere getTypeBiere() {
		return typeBiere;}


	public void setTypeBiere(TypeBiere typeBiere) {
		this.typeBiere = typeBiere;}

	
	public static ArrayList<Article> getArticleResult() {
		return articleResult;
	}


	public static void setArticleResult(Article article) {
		articleResult.add(article);
	}




	
	
	
	
	
	

}
