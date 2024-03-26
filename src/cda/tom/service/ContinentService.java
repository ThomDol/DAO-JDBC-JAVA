package cda.tom.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cda.tom.config.DataBaseConnection;
import cda.tom.model.Article;
import cda.tom.model.Continent;
import cda.tom.model.Fabricant;
import cda.tom.model.TypeBiere;

public class ContinentService extends DAO<Continent> {

	public List<Continent> findAll() throws ClassNotFoundException {
		Connection connection = null;
		try {
			connection = DataBaseConnection.getInstance();
			String query = "SELECT * FROM CONTINENT";

			try (PreparedStatement statement = connection.prepareStatement(query);
					ResultSet res = statement.executeQuery()) {

				while (res.next()) {
					Continent continentObtained = new Continent(res.getInt(1), res.getString(2));
					Continent.setContinentResult(continentObtained);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return Continent.getContinentResult();
	}

	public Continent find(long id) throws ClassNotFoundException {
		Connection connection = null;
		Continent continentObtained = null;
		try {
			connection = DataBaseConnection.getInstance();
			String query = "SELECT * FROM CONTINENT WHERE ID_CONTINENT=" + id;
			try (PreparedStatement statement = connection.prepareStatement(query);
					ResultSet res = statement.executeQuery()) {
				if (res.next()) {
					continentObtained = new Continent(res.getLong(1), res.getString(2));

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

		return continentObtained;
	}

	public Continent findByName(String name) throws ClassNotFoundException {

		Connection connection = null;
		Continent continentObtained = null;
		try {
			connection = DataBaseConnection.getInstance();
			String query = "SELECT * FROM CONTINENT WHERE NOM_CONTINENT=?";
			try (PreparedStatement statement = connection.prepareStatement(query)) {
				statement.setString(1, name);
				try (ResultSet res = statement.executeQuery()) {
					if (res.next()) {
						continentObtained = new Continent(res.getLong(1), res.getString(2));
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

		return continentObtained;
	}

	public void save(Continent continent) throws SQLException, ClassNotFoundException {
		ContinentService continentService = new ContinentService();
		continent = continentService.findByName(continent.getNom_continent());
		if (continent == null) {
			Connection connection = null;
			try {
				connection = DataBaseConnection.getInstance();
				String query = "INSERT INTO FABRICANT(NOM_FABRICANT) VALUES (?)";

				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, continent.getNom_continent());

				int rowsAdded = statement.executeUpdate();
				if (rowsAdded > 0) {
					System.out.println("Continent créé.");
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
			System.out.println("Ce fabricant existe déjà");
		}
	}

	public void delete(Continent continent) throws SQLException, ClassNotFoundException {
		Connection connection = null;
		try {
			connection = DataBaseConnection.getInstance();
			String query = "DELETE FROM CONTINENT WHERE ID_CONTINENT=?";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, (int) continent.getId_Continent());

			int rowsAdded = statement.executeUpdate();
			if (rowsAdded > 0) {
				System.out.println("Continent supprimé.");
			} else {
				System.out.println("Continent non existant");
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

	public void displayContinent(Continent continent) {
		System.out.println("{" + continent.getId_Continent() + " / " + continent.getNom_continent() + "}");
	}

	public void displayResult(List<Continent> list) {
		for (Continent continent : list) {
			displayContinent(continent);
		}
	}

}
