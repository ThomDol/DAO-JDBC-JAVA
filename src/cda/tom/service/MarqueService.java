package cda.tom.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cda.tom.config.DataBaseConnection;
import cda.tom.model.Continent;
import cda.tom.model.Fabricant;
import cda.tom.model.Marque;
import cda.tom.model.Pays;

public class MarqueService extends DAO<Marque> {

	public List<Marque> findAll() throws ClassNotFoundException {
		Connection connection=null;;
		try {
			connection = DataBaseConnection.getInstance();
			String query = "{call findAllMarque}";

			try (PreparedStatement statement = connection.prepareStatement(query);
					ResultSet res = statement.executeQuery()) {
				while (res.next()) {
					Continent continent = new Continent(res.getInt(7), res.getString(8));
					Pays pays = new Pays(res.getInt(5), res.getString(6), continent);
					Fabricant fabricant = new Fabricant(res.getInt(3), res.getString(4));
					Marque marqueObtained = new Marque(res.getInt(1), res.getString(2), pays, fabricant);
					Marque.setMarqueResult(marqueObtained);
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
                }}}
		return Marque.getMarqueResult();
	}

	public Marque find(long id) throws ClassNotFoundException {
		Connection connection = null;
		Marque marqueObtained=null;
		try {
			connection = DataBaseConnection.getInstance();
			String query = "select ID_MARQUE,NOM_MARQUE,FABRICANT.*,PAYS.ID_PAYS,PAYS.NOM_PAYS,CONTINENT.* From MARQUE\r\n"
					+ "inner join PAYS ON PAYS.ID_PAYS=MARQUE.ID_PAYS\r\n"
					+ "INNER JOIN FABRICANT ON FABRICANT.ID_FABRICANT=MARQUE.ID_FABRICANT\r\n"
					+ "INNER JOIN CONTINENT ON CONTINENT.ID_CONTINENT=PAYS.ID_CONTINENT\r\n" + "where marque.ID_MARQUE="
					+ id;
			try (PreparedStatement statement = connection.prepareStatement(query);
					ResultSet res = statement.executeQuery()) {
				if (res.next()) {
					Continent continent = new Continent(res.getInt(7), res.getString(8));
					Pays pays = new Pays(res.getInt(5), res.getString(6), continent);
					Fabricant fabricant = new Fabricant(res.getInt(3), res.getString(4));
					marqueObtained = new Marque(res.getInt(1), res.getString(2), pays, fabricant);
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
                }}}

		return marqueObtained;
	}
	
	
public Marque findByName(String name) throws ClassNotFoundException {
		
		Connection connection = null; 
		try {
			connection = DataBaseConnection.getInstance();
			String query = "select ID_MARQUE,NOM_MARQUE,FABRICANT.*,PAYS.ID_PAYS,PAYS.NOM_PAYS,CONTINENT.* From MARQUE\r\n"
					+ "inner join PAYS ON PAYS.ID_PAYS=MARQUE.ID_PAYS\r\n"
					+ "INNER JOIN FABRICANT ON FABRICANT.ID_FABRICANT=MARQUE.ID_FABRICANT\r\n"
					+ "INNER JOIN CONTINENT ON CONTINENT.ID_CONTINENT=PAYS.ID_CONTINENT\r\n" + "where marque.NOM_MARQUE=?"
					;
			try (PreparedStatement statement = connection.prepareStatement(query)) {
				statement.setString(1, name);
				try (ResultSet res = statement.executeQuery()) {
					if (res.next()) {
						Continent continent = new Continent(res.getInt(7), res.getString(8));
						Pays pays = new Pays(res.getInt(5), res.getString(6), continent);
						Fabricant fabricant = new Fabricant(res.getInt(3), res.getString(4));
						Marque marqueObtained = new Marque(res.getInt(1), res.getString(2), pays, fabricant);
						return marqueObtained;
					}
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
                }}}

		return null;
	}
	
	
	public void save(Marque marque) throws SQLException, ClassNotFoundException {
		MarqueService marqueService = new MarqueService();
		Marque marqueTarget = marqueService.findByName(marque.getNom_marque());
		if (marqueTarget == null) {
			Connection connection = null;
			try {
				connection = DataBaseConnection.getInstance();
				String query = "INSERT INTO MARQUE(NOM_MARQUE,ID_PAYS,ID_FABRICANT) VALUES (?,?,?)";

				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, marque.getNom_marque());
				statement.setInt(2, marque.getPays().getId_pays());
				statement.setInt(3, marque.getFabricant().getId_fabricant());
				

				int rowsAdded = statement.executeUpdate();
				if (rowsAdded > 0) {
					System.out.println("Marque créée.");
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
	
	
	public void delete(Marque marque) throws SQLException, ClassNotFoundException {
		Connection connection = null;
		try {
			connection = DataBaseConnection.getInstance();
			String query = "DELETE FROM MARQUE WHERE ID_MARQUE=?";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, marque.getId_marque());

			int rowsAdded = statement.executeUpdate();
			if (rowsAdded > 0) {
				System.out.println("Marque supprimée avec Id "+marque.getId_marque());
			} else {
				System.out.println("Marque non existante");
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
	

	public void displayMarque(Marque marque) {
		System.out.println("{" + marque.getId_marque() + " / " + marque.getNom_marque() + " / "
				+ marque.getPays().getNom_pays() + " / " + marque.getFabricant().getNom_fabricant() + "}");
	}

	public void displayResult(List<Marque> ArrayMarque) {
		for (Marque marque : ArrayMarque) {

			System.out.println("{" + marque.getId_marque() + " / " + marque.getNom_marque() + " / "
					+ marque.getPays().getNom_pays() + " / " + marque.getFabricant().getNom_fabricant() + "}");
		}
	}

}
