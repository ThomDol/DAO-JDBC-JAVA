package cda.tom.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cda.tom.config.DataBaseConnection;
import cda.tom.model.Article;
import cda.tom.model.Ticket;
import cda.tom.model.Vendre;

public class VendreService extends DAO<Vendre> {

	public List<Vendre> findAll() throws ClassNotFoundException {
		try {
			Connection connection = DataBaseConnection.getInstance();
			String query = "{call findAllVendre(12000,60)}";

			try (PreparedStatement statement = connection.prepareStatement(query);
					ResultSet res = statement.executeQuery()) {

				while (res.next()) {
					int articleId = res.getInt(3);
					ArticleService articleService = new ArticleService();
					TicketService ticketService = new TicketService();
					Article article = articleService.find(articleId);
					if (article != null) {
						Ticket ticket = ticketService.findById(res.getInt(1), res.getInt(2));
						Vendre vendreObtained = new Vendre();
						vendreObtained.setTicket(ticket);
						vendreObtained.setArticle(article);
						vendreObtained.setQuantite(res.getInt(4));
						vendreObtained.setPrix_vente(res.getFloat(5));
						Vendre.setVendreResult(vendreObtained);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Vendre.getVendreResult();
	}

	public Vendre findById(int annee, int num, long idArticle) throws ClassNotFoundException {
		Connection connection = null;
		Vendre vendreObtained = null;
		TicketService ticketService = new TicketService();
		ArticleService articleService = new ArticleService();
		try {
			connection = DataBaseConnection.getInstance();
			String query = "SELECT * FROM VENDRE WHERE ANNEE=? AND NUMERO_TICKET=? AND ID_ARTICLE=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, annee);
			statement.setInt(2, num);
			statement.setLong(3, idArticle);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				int articleId = res.getInt(3);
				Article article = articleService.find(articleId);
				if (article != null) {
					vendreObtained= new Vendre();
					Ticket ticket = ticketService.findById(res.getInt(1), res.getInt(2));
					vendreObtained.setTicket(ticket);
					vendreObtained.setArticle(article);
					vendreObtained.setQuantite(res.getInt(4));
					vendreObtained.setPrix_vente(res.getFloat(5));
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
		return vendreObtained;
	}

	public void save(Vendre vendre) throws ClassNotFoundException, SQLException {
		Connection connection = null;
		try {
			connection = DataBaseConnection.getInstance();
			String query = "INSERT INTO VENDRE(ANNEE,NUMERO_TICKET,ID_ARTICLE,QUANTITE,PRIX_VENTE) VALUES(?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, vendre.getTicket().getAnnee());
			statement.setInt(2, vendre.getTicket().getNumero_ticket());
			statement.setLong(3, vendre.getArticle().getId_article());
			statement.setInt(4, vendre.getQuantite());
			statement.setFloat(5, vendre.getPrix_vente());
			int rowAdded = statement.executeUpdate();
			if (rowAdded > 0) {
				System.out.println("Ligne de Vente créée.");
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

	public void delete(Vendre vendre) throws ClassNotFoundException, SQLException {
		Connection connection = null;
		try {
			connection = DataBaseConnection.getInstance();
			String query = "DELETE FROM VENDRE WHERE ANNEE=? AND NUMERO_TICKET=? AND ID_ARTICLE=?";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, vendre.getTicket().getAnnee());
			statement.setInt(2, vendre.getTicket().getNumero_ticket());
			statement.setLong(3, vendre.getArticle().getId_article());
			int rowAdded = statement.executeUpdate();
			if (rowAdded > 0) {
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

	public void update(Vendre vendre) throws ClassNotFoundException {
		Connection connection = null;
		VendreService vendreService = new VendreService();
		Vendre vendreTarget = vendreService.findById(vendre.getTicket().getAnnee(),
				vendre.getTicket().getNumero_ticket(), vendre.getArticle().getId_article());
		if(vendreTarget!=null) {
			try {
				connection=DataBaseConnection.getInstance();
				String query = "UPDATE VENDRE ID_ARTICLE=?,QUANTITE=?,PRIX_VENTE=? WHERE SET ANNEE=? AND NUMERO_TICKET=?";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setLong(1, vendre.getArticle().getId_article());
				statement.setInt(2,vendre.getQuantite());
				statement.setDouble(3, vendre.getPrix_vente());
				statement.setInt(4, vendre.getTicket().getAnnee());
				statement.setInt(5, vendre.getTicket().getNumero_ticket());
				int rowAdded = statement.executeUpdate();
				if (rowAdded > 0) {
					System.out.println("ligne de vente mise à jour.");
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
			System.out.println("ligne de vente inexistante");
		}

	}
				
	public static void displayVente(Vendre vendre) {
		System.out.println("{" + vendre.getTicket().getAnnee() + " / " + vendre.getTicket().getNumero_ticket()
				+ " / " + vendre.getArticle().getNom_article() + " / " + vendre.getQuantite() + " / "
				+ vendre.getPrix_vente() + "}");
	}
	

	public static void displayResult(List<Vendre> ArrayVendre) {
		for (Vendre vendre : ArrayVendre) {
			displayVente(vendre);
		}

	}

	@Override
	public Vendre find(long id) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}