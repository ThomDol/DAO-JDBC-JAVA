package cda.tom.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Vendre {
	private Ticket ticket;
	private Article article;
	private int quantite;
	private Float prix_vente;
	private static ArrayList<Vendre> vendreResult = new ArrayList<Vendre>();
	
	public Vendre() {};
	
	public Vendre(Ticket ticket,Article article,int quantite,Float prix) {
		this.ticket=ticket;
		this.article= article;
		this.quantite=quantite;
		this.prix_vente=prix;
	}
	
	public Ticket getTicket() {
		return ticket;}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;}

	public Article getArticle() {
		return article;}

	public void setArticle(Article article) {
		this.article = article;}

	public int getQuantite() {
		return quantite;}

	public void setQuantite(int quantite) {
		this.quantite = quantite;}

	public Float getPrix_vente() {
		return prix_vente;}

	public void setPrix_vente(Float prix_vente) {
		this.prix_vente = prix_vente;}

	public static ArrayList<Vendre> getVendreResult() {
		return vendreResult;}

	public static void setVendreResult(Vendre vendre) {
		vendreResult.add(vendre);
	}
	

}
