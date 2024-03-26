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
import cda.tom.model.Couleur;
import cda.tom.model.Fabricant;
import cda.tom.model.Marque;
import cda.tom.model.Pays;
import cda.tom.model.TypeBiere;

public class ArticleService extends DAO<Article> {

	public ArrayList<Article> findAll() throws ClassNotFoundException {
		Connection connection = null;
		try {
			connection = DataBaseConnection.getInstance();
			String query = "{call findAllArticle}";

			try (PreparedStatement statement = connection.prepareStatement(query);
					ResultSet res = statement.executeQuery()) {

				while (res.next()) {
					Article articleObtained = new Article();
					createArticle(articleObtained, res);
					Article.setArticleResult(articleObtained);
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
		return Article.getArticleResult();
	}

	public Article find(long id) throws ClassNotFoundException {
		Connection connection = null;
		Article articleObtained = null;
		try {
			connection = DataBaseConnection.getInstance();
			String query = "{call findOneByIdArticle(" + id + ")}";
			try (PreparedStatement statement = connection.prepareStatement(query);
					ResultSet res = statement.executeQuery()) {
				if (res.next()) {
					articleObtained = new Article();
					createArticle(articleObtained, res);
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

		return articleObtained;
	}

	public void save(Article article) throws SQLException, ClassNotFoundException {
		Connection connection = null;
		try {
			connection = DataBaseConnection.getInstance();
			String query = "INSERT INTO ARTICLE(NOM_ARTICLE,PRIX_ACHAT,VOLUME,TITRAGE,ID_MARQUE,ID_COULEUR,ID_TYPE) VALUES (?,?,?,?,?,?,?)";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, article.getNom_article());
			statement.setDouble(2, article.getPrix_achat());
			statement.setInt(3, article.getVolume());
			statement.setDouble(4, article.getTitrage());
			statement.setInt(5, article.getMarque().getId_marque());
			statement.setInt(6, article.getCouleur().getId_Couleur());
			statement.setInt(7, article.getTypeBiere().getId_type());

			int rowsAdded = statement.executeUpdate();
			if (rowsAdded > 0) {
				System.out.println("Article créé.");
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

	public void delete(Article article) throws SQLException, ClassNotFoundException {
		Connection connection = null;
		try {
			connection = DataBaseConnection.getInstance();
			String query = "DELETE FROM ARTICLE WHERE ID_ARTICLE=?";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(1, article.getId_article());

			int rowsAdded = statement.executeUpdate();
			if (rowsAdded > 0) {
				System.out.println("article supprimé.");
			} else {
				System.out.println("article non existant");
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

	public void update(Article article) throws ClassNotFoundException {
		Connection connection = null;
		ArticleService articleService = new ArticleService();
		Article articleTarget = articleService.find(article.getId_article());
		if (articleTarget != null) {
			try {
				connection = DataBaseConnection.getInstance();
				String query = "UPDATE ARTICLE SET NOM_ARTICLE=?,PRIX_ACHAT=?,VOLUME=?,TITRAGE=?,ID_MARQUE=?,ID_COULEUR=?,ID_TYPE=? WHERE ID_ARTICLE=? ";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, article.getNom_article());
				statement.setDouble(2, article.getPrix_achat());
				statement.setInt(3, article.getVolume());
				statement.setDouble(4, article.getTitrage());
				statement.setInt(5, article.getMarque().getId_marque());
				statement.setInt(6, article.getCouleur().getId_Couleur());
				statement.setInt(7, article.getTypeBiere().getId_type());
				statement.setLong(8, article.getId_article());
				int rowsAdded = statement.executeUpdate();
				if (rowsAdded > 0) {
					System.out.println("article mis à jour.");
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
			System.out.println("article inexistant");
		}

	}

	public static Article createArticle(Article article, ResultSet res) throws SQLException {
		article.setId_article(res.getLong(1));
		article.setNom_article(res.getString(2));
		article.setPrix_achat(res.getDouble(3));
		article.setVolume(res.getInt(4));
		article.setTitrage(res.getInt(5));
		Fabricant fabricant = new Fabricant(res.getInt(18), res.getString(19));
		Continent continent = new Continent(res.getInt(22), res.getString(23));
		Pays pays = new Pays(res.getInt(15), res.getString(16), continent);
		Marque marque = new Marque(res.getInt(9), res.getString(10), pays, fabricant);
		article.setMarque(marque);
		Couleur couleur = new Couleur(res.getInt(13), res.getString(14));
		article.setCouleur(couleur);
		TypeBiere typeBiere = new TypeBiere(res.getInt(20), res.getString(21));
		article.setTypeBiere(typeBiere);
		return article;
	}

	public void displayResult(List<Article> ArrayArticle) {
		for (Article article : ArrayArticle) {

			displayArticle(article);
		}
	}

	public void displayArticle(Article article) {
		System.out.println("{" + article.getId_article() + " / " + article.getNom_article() + " / "
				+ article.getPrix_achat() + " / " + article.getVolume() + " / " + article.getTitrage() + " / "
				+ article.getMarque().getNom_marque() + " / " + article.getCouleur().getNom_couleur() + " / "
				+ article.getTypeBiere().getNom_type() + "}");
	}

}
