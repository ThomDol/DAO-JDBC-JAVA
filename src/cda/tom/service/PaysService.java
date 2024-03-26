package cda.tom.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cda.tom.config.DataBaseConnection;
import cda.tom.model.Continent;
import cda.tom.model.Pays;
import cda.tom.model.TypeBiere;

public class PaysService extends DAO<Pays> {

	public List<Pays> findAll() throws ClassNotFoundException {
		try {
			Connection connection = DataBaseConnection.getInstance();

			String query = "SELECT P.ID_PAYS, P.NOM_PAYS,C.ID_CONTINENT,C.NOM_CONTINENT FROM PAYS AS P INNER JOIN CONTINENT AS C ON P.ID_CONTINENT=C.ID_CONTINENT";

			try (PreparedStatement statement = connection.prepareStatement(query);
					ResultSet res = statement.executeQuery()) {
				while (res.next()) {
					Continent continent = new Continent(res.getInt(3), res.getString(4));
					Pays paysObtained = new Pays(res.getInt(1), res.getString(2), continent);
					Pays.setPaysResult(paysObtained);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Pays.getPaysResult();
	}

	public Pays find(long id) throws ClassNotFoundException {
		Connection connection = null;
		Pays paysObtained = null;
		try {
			connection = DataBaseConnection.getInstance();
			String query = "SELECT P.ID_PAYS, P.NOM_PAYS,C.ID_CONTINENT,C.NOM_CONTINENT FROM PAYS AS P INNER JOIN CONTINENT AS C ON P.ID_CONTINENT=C.ID_CONTINENT WHERE ID_PAYS="
					+ id;
			try (PreparedStatement statement = connection.prepareStatement(query);
					ResultSet res = statement.executeQuery()) {
				if (res.next()) {
					Continent continent = new Continent(res.getInt(3), res.getString(4));
					paysObtained = new Pays(res.getInt(1), res.getString(2), continent);

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

		return paysObtained;
	}

	public Pays findByName(String name) throws ClassNotFoundException {
		Connection connection = null;
		Pays paysObtained = null;
		try {
			connection = DataBaseConnection.getInstance();
			String query = "SELECT P.ID_PAYS, P.NOM_PAYS,C.ID_CONTINENT,C.NOM_CONTINENT FROM PAYS AS P INNER JOIN CONTINENT AS C ON P.ID_CONTINENT=C.ID_CONTINENT WHERE NOM_PAYS=?";
			try (PreparedStatement statement = connection.prepareStatement(query)) {
				statement.setString(1, name);
				try (ResultSet res = statement.executeQuery()) {
					if (res.next()) {
						Continent continent = new Continent(res.getInt(3), res.getString(4));
						paysObtained = new Pays(res.getInt(1), res.getString(2), continent);
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

		return paysObtained;
	}

	

	public void save(Pays pays) throws ClassNotFoundException, SQLException {
		PaysService paysService = new PaysService();
		Pays paysTarget = paysService.findByName(pays.getNom_pays());
		Connection connection = null;
		if (paysTarget == null) {
			try {
				connection = DataBaseConnection.getInstance();
				String query = "INSERT INTO PAYS(NOM_PAYS,ID_CONTINENT) VALUES(?,?)";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, pays.getNom_pays());
				statement.setLong(2, pays.getContinent().getId_Continent());
				int rowsAdded = statement.executeUpdate();
				if (rowsAdded > 0) {
					System.out.println("Fabricant créé.");
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
			System.out.println("Ce pays existe déjà");
		}
	}
	
	
	public void delete (Pays pays) throws ClassNotFoundException {
		PaysService paysService = new PaysService();
		Pays paysTarget = paysService.find(pays.getId_pays());
		Connection connection = null;
		if (paysTarget != null) {
			
			try {
				connection = DataBaseConnection.getInstance();
				String query = "DELETE FROM PAYS WHERE ID_PAYS=?";

				PreparedStatement statement = connection.prepareStatement(query);
				statement.setLong(1, pays.getId_pays());

				int rowsAdded = statement.executeUpdate();
				if (rowsAdded > 0) {
					System.out.println("Pays supprimé.");
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
			System.out.println("pays inexistant");
		}

	}

	
	
	public void displayPays(Pays pays) {
		System.out.println("{" + pays.getId_pays() + " / " + pays.getNom_pays() + " / "
				+ pays.getContinent().getNom_continent() + "}");
	}

	public void displayResult(List<Pays> ArrayPays) {
		for (Pays pays : ArrayPays) {
			displayPays(pays);
		}
	}



}
