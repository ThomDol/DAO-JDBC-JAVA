package cda.tom.model;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TypeBiere {
	private int id_type;
	private String nom_type;
	private static ArrayList<TypeBiere> typeBiereResult= new ArrayList();
	
	public TypeBiere() {}
	
	public TypeBiere(int id,String nom) {
		this.id_type=id;
		this.nom_type=nom;
	}
	
	public int getId_type() {
		return id_type;
	}

	public void setId_type(int id_type) {
		this.id_type = id_type;
	}

	public String getNom_type() {
		return nom_type;
	}

	public void setNom_typeBiere(String nom_type) {
		this.nom_type = nom_type;
	}

	public static ArrayList<TypeBiere> getTypeBiereResult() {
		return typeBiereResult;
	}

	public static void setTypeBiereResult(TypeBiere typeBiere) {
		typeBiereResult.add(typeBiere);
	}

	
}
