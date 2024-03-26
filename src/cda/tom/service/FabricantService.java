package cda.tom.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cda.tom.config.DataBaseConnection;
import cda.tom.model.Article;
import cda.tom.model.Continent;
import cda.tom.model.Fabricant;

public class FabricantService extends DAO<Fabricant> {
	public List<Fabricant> findAll() throws ClassNotFoundException {
		Connection connection = null;
		try {
			connection = DataBaseConnection.getInstance();

			String query = "SELECT * FROM FABRICANT";

			try (PreparedStatement statement = connection.prepareStatement(query);
					ResultSet res = statement.executeQuery()) {

				while (res.next()) {
					Fabricant fabricantObtained = new Fabricant(res.getInt(1), res.getString(2));
					Fabricant.setFabricantResult(fabricantObtained);
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
		return Fabricant.getFabricantResult();
	}

	public Fabricant find(long id) throws ClassNotFoundException {
		Connection connection = null;
		Fabricant fabricantObtained = null;
		try {
			connection = DataBaseConnection.getInstance();
			String query = "SELECT * FROM FABRICANT WHERE ID_FABRICANT=" + id;
			try (PreparedStatement statement = connection.prepareStatement(query);
					ResultSet res = statement.executeQuery()) {
				if (res.next()) {
					fabricantObtained = new Fabricant(res.getInt(1), res.getString(2));
				} else {
					System.out.println("fabricant inexistant");
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

		return fabricantObtained;
	}

	public Fabricant findByName(String name) throws ClassNotFoundException {
		Connection connection = null;
		Fabricant fabricantObtained = null;
		try {
			connection = DataBaseConnection.getInstance();
			String query = "SELECT * FROM FABRICANT WHERE NOM_FABRICANT=?";
			try (PreparedStatement statement = connection.prepareStatement(query)) {
				statement.setString(1, name);
				try (ResultSet res = statement.executeQuery()) {
					if (res.next()) {
						fabricantObtained = new Fabricant(res.getInt(1), res.getString(2));
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

		return fabricantObtained;
	}

	public void save(Fabricant fabricant) throws SQLException, ClassNotFoundException {
		FabricantService fabricantService = new FabricantService();
		Fabricant fabricantTarget = fabricantService.findByName(fabricant.getNom_fabricant());
		if (fabricantTarget == null) {
			Connection connection = null;
			try {
				connection = DataBaseConnection.getInstance();
				String query = "INSERT INTO FABRICANT(NOM_FABRICANT) VALUES (?)";

				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, fabricant.getNom_fabricant());

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
			System.out.println("Ce fabricant existe déjà");
		}
	}

	public void delete(Fabricant fabricant) throws SQLException, ClassNotFoundException {
		FabricantService fabricantService = new FabricantService();
		Fabricant fabricantTarget = fabricantService.find(fabricant.getId_fabricant());
		Connection connection = null;
		if (fabricantTarget != null) {
			try {
				connection = DataBaseConnection.getInstance();
				String query = "DELETE FROM FABRICANT WHERE NOM_FABRICANT=?";

				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, fabricant.getNom_fabricant());

				int rowsAdded = statement.executeUpdate();
				if (rowsAdded > 0) {
					System.out.println("Fabricant supprimé.");
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
			System.out.println("fabricant inexistant");
		}

	}

	public void update(Fabricant fabricant) throws ClassNotFoundException {
		Connection connection = null;
		FabricantService fabricantService = new FabricantService();
		Fabricant fabricantTarget = fabricantService.find(fabricant.getId_fabricant());
		if (fabricantTarget != null) {
			try {
				connection = DataBaseConnection.getInstance();
				String query = "UPDATE FABRICANT SET NOM_FABRICANT=? WHERE ID_FABRICANT=? ";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, fabricant.getNom_fabricant());
				statement.setInt(2, fabricant.getId_fabricant());
				int rowsAdded = statement.executeUpdate();
				if (rowsAdded > 0) {
					System.out.println("Fabricant mis à jour.");
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
			System.out.println("fabricant inexistant");
		}

	}

	public void displayFabricant(Fabricant fabricant) {
		if (fabricant != null) {
			System.out.println("{" + fabricant.getId_fabricant() + " / " + fabricant.getNom_fabricant() + "}");
		} else {
			System.out.println("pas de correspondance");
		}
	}

	public void displayResult(List<Fabricant> ArrayFabricant) {
		for (Fabricant fabricant : ArrayFabricant) {
			displayFabricant(fabricant);
		}
	}

}
