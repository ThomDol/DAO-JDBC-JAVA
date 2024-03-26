package cda.tom.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cda.tom.config.DataBaseConnection;
import cda.tom.model.Couleur;
import cda.tom.model.Fabricant;

public class CouleurService extends DAO<Couleur> {

	public List<Couleur> findAll() throws ClassNotFoundException {
		Connection connection = null;
		try {
			connection = DataBaseConnection.getInstance();
			String query = "SELECT * FROM COULEUR";

			try (PreparedStatement statement = connection.prepareStatement(query);
					ResultSet res = statement.executeQuery()) {

				while (res.next()) {
					Couleur couleurObtained = new Couleur();
					couleurObtained.setId_Couleur(res.getInt(1));
					couleurObtained.setNom_couleur(res.getString(2));
					Couleur.setCouleurResult(couleurObtained);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return Couleur.getCouleurResult();
	}

	public Couleur find(long id) throws ClassNotFoundException {
		Connection connection = null;
		try {
			connection = DataBaseConnection.getInstance();
			String query = "SELECT * FROM COULEUR WHERE ID_COULEUR=" + id;
			try (PreparedStatement statement = connection.prepareStatement(query);
					ResultSet res = statement.executeQuery()) {
				if (res.next()) {
					Couleur couleurObtained = new Couleur(res.getInt(1), res.getString(2));
					return couleurObtained;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	public Couleur findByName(String name) throws ClassNotFoundException {
		Connection connection = null;
		try {
			connection = DataBaseConnection.getInstance();
			String query = "SELECT * FROM COULEUR WHERE NOM_COULEUR=?";
			try (PreparedStatement statement = connection.prepareStatement(query)) {
				statement.setString(1, name);
				try (ResultSet res = statement.executeQuery()) {
					if (res.next()) {
						Couleur couleurObtained = new Couleur(res.getInt(1), res.getString(2));
						return couleurObtained;
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	public void save(Couleur couleur) throws SQLException, ClassNotFoundException {
		CouleurService couleurService = new CouleurService();
		couleur = couleurService.findByName(couleur.getNom_couleur());
		if (couleur == null) {
			Connection connection = null;
			try {
				connection = DataBaseConnection.getInstance();
				String query = "INSERT INTO COULEUR(NOM_COULEUR) VALUES (?)";

				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, couleur.getNom_couleur());

				int rowsAdded = statement.executeUpdate();
				if (rowsAdded > 0) {
					System.out.println("Couleur créée.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			System.out.println("Cette couleur existe déjà");
		}
	}

	public void delete(Couleur couleur) throws SQLException, ClassNotFoundException {
		Connection connection = null;
		try {
			connection = DataBaseConnection.getInstance();
			String query = "DELETE FROM COULEUR WHERE NOM_COULEUR=?";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, couleur.getNom_couleur());

			int rowsAdded = statement.executeUpdate();
			if (rowsAdded > 0) {
				System.out.println("Couleur supprimé.");
			} else {
				System.out.println("Couleur non existante");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void displayCouleur(Couleur couleur) {
		System.out.println("{" + couleur.getId_Couleur() + " / " + couleur.getNom_couleur() + "}");
	}

	public static void displayResult(List<Couleur> ArrayCouleur) {
		for (Couleur couleur : ArrayCouleur) {
			displayCouleur(couleur);
		}
	}





}
