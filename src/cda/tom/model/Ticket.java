package cda.tom.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Ticket {
	private int annee;
	private int numero_ticket;
	private String date_vente;
	private String heure_vente;
	private static ArrayList<Ticket> ticketResult = new ArrayList<Ticket>();
	
	public Ticket () {}
	
	public Ticket (int annee, String date, String heure) {
		this.annee=annee;
		this.date_vente=date;
		this.heure_vente=heure;
	}
	
	public Ticket (int annee,int num, String date, String heure) {
		this.annee=annee;
		this.numero_ticket=num;
		this.date_vente=date;
		this.heure_vente=heure;
	}
	
	public int getAnnee() {
		return annee;
	}



	public void setAnnee(int annee) {
		this.annee = annee;
	}



	public int getNumero_ticket() {
		return numero_ticket;
	}



	public void setNumero_ticket(int numero_ticket) {
		this.numero_ticket = numero_ticket;
	}



	public String getDate_vente() {
		return date_vente;
	}



	public void setDate_vente(String date_vente) {
		this.date_vente = date_vente;
	}



	public String getHeure_vente() {
		return heure_vente;
	}



	public void setHeure_vente(String heure_vente) {
		this.heure_vente = heure_vente;
	}

	public static ArrayList<Ticket> getTicketResult() {
		return ticketResult;
	}



	public static void setTicketResult(Ticket ticket) {
		ticketResult.add(ticket);
	}



}
