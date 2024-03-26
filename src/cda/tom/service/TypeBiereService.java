package cda.tom.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cda.tom.config.DataBaseConnection;
import cda.tom.model.Fabricant;
import cda.tom.model.TypeBiere;

public class TypeBiereService extends DAO<TypeBiere> {

	public List<TypeBiere> findAll() throws ClassNotFoundException {
		Connection connection = null;
		try {
			connection = DataBaseConnection.getInstance();
			String query = "SELECT * FROM TYPEBIERE";

			try (PreparedStatement statement = connection.prepareStatement(query);
					ResultSet res = statement.executeQuery()) {
				while (res.next()) {
					TypeBiere typeBiereObtained = new TypeBiere();
					typeBiereObtained.setId_type(res.getInt(1));
					typeBiereObtained.setNom_typeBiere(res.getString(2));
					TypeBiere.setTypeBiereResult(typeBiereObtained);
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
		return TypeBiere.getTypeBiereResult();
	}

	public TypeBiere find(long id) throws ClassNotFoundException {
		Connection connection = null;
		try {
			connection = DataBaseConnection.getInstance();
			String query = "SELECT * FROM TYPEBIERE  WHERE ID_TYPE=" + id;
			try (PreparedStatement statement = connection.prepareStatement(query);
					ResultSet res = statement.executeQuery()) {
				if (res.next()) {
					TypeBiere TypeBiereObtained = new TypeBiere(res.getInt(1), res.getString(2));
					return TypeBiereObtained;
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

	public TypeBiere findByName(String name) throws ClassNotFoundException {
		Connection connection = null;
		try {
			connection = DataBaseConnection.getInstance();
			String query = "SELECT * FROM TYPEBIERE WHERE NOM_TYPE=?";
			try (PreparedStatement statement = connection.prepareStatement(query)) {
				statement.setString(1, name);
				try (ResultSet res = statement.executeQuery()) {
					if (res.next()) {
						TypeBiere TypeBiereObtained = new TypeBiere(res.getInt(1), res.getString(2));
						return TypeBiereObtained;
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

	public void save(TypeBiere type) throws SQLException, ClassNotFoundException {
		TypeBiereService typeBiereService = new TypeBiereService();
		type = typeBiereService.findByName(type.getNom_type());
		if (type == null) {
			Connection connection = null;
			try {
				connection = DataBaseConnection.getInstance();
				String query = "INSERT INTO TYPEBIERE(NOM_Type) VALUES (?)";

				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, type.getNom_type());

				int rowsAdded = statement.executeUpdate();
				if (rowsAdded > 0) {
					System.out.println("TypeBiere créé.");
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
			System.out.println("type déjà existant");
		}
	}

	public void delete(TypeBiere type) throws SQLException, ClassNotFoundException {
		Connection connection = null;
		try {
			connection = DataBaseConnection.getInstance();
			String query = "DELETE FROM TYPEBIERE WHERE NOM_TYPE=?";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, type.getNom_type());

			int rowsAdded = statement.executeUpdate();
			if (rowsAdded > 0) {
				System.out.println("Type supprimé.");
			} else {
				System.out.println("Type non existant");
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

	public static void displayType(TypeBiere typeBiere) {
		System.out.println("{" + typeBiere.getId_type() + " / " + typeBiere.getNom_type() + "}");
	}

	public static void displayResult(List<TypeBiere> ArrayTypeBiere) {
		for (TypeBiere typeBiere : ArrayTypeBiere) {
			displayType(typeBiere);
		}
	}

}
