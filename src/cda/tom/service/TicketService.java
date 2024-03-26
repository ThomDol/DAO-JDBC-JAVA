package cda.tom.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cda.tom.config.DataBaseConnection;
import cda.tom.model.Ticket;

public class TicketService extends DAO<Ticket> {

	public ArrayList<Ticket> findAll() throws ClassNotFoundException {
		Connection connection = null;
		try {
			connection = DataBaseConnection.getInstance();
			String query = "SELECT * FROM TICKET";

			try (PreparedStatement statement = connection.prepareStatement(query);
					ResultSet res = statement.executeQuery()) {

				while (res.next()) {
					Ticket ticketObtained = new Ticket();
					ticketObtained.setAnnee(res.getInt(1));
					ticketObtained.setNumero_ticket(res.getInt(2));
					ticketObtained.setDate_vente(res.getString(3));
					ticketObtained.setHeure_vente(res.getString(4));
					Ticket.setTicketResult(ticketObtained);
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
		return Ticket.getTicketResult();
	}

	public Ticket findById(long annee, long num) throws ClassNotFoundException {
		Connection connection = null;
		Ticket ticketObtained = null;
		try {
			connection = DataBaseConnection.getInstance();
			String query = "SELECT * FROM TICKET WHERE ANNEE=? AND NUMERO_TICKET=?";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setLong(1, annee);
			statement.setLong(2, num);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				ticketObtained = new Ticket(res.getInt(1), res.getInt(2), res.getString(3), res.getString(4));

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
		return ticketObtained;
	}

	public void save(Ticket ticket) throws ClassNotFoundException {
		Connection connection = null;
		try {
			connection = DataBaseConnection.getInstance();
			String queryMax = "SELECT MAX(ANNEE),MAX(NUMERO_TICKET) FROM TICKET";
			PreparedStatement statementMax = connection.prepareStatement(queryMax);
			ResultSet res = statementMax.executeQuery();
			if (res.next()) {
			if (ticket.getAnnee()>res.getInt(1)) {
			String query = "INSERT INTO TICKET(ANNEE,NUMERO_TICKET,DATE_VENTE,HEURE_VENTE) VALUES (?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, ticket.getAnnee());
			statement.setInt(2, 1);
			statement.setString(3, ticket.getDate_vente());
			statement.setString(4, ticket.getHeure_vente());

			int rowsAdded = statement.executeUpdate();
			if (rowsAdded > 0) {
				System.out.println("Ticket cree");
			}}
			else if (ticket.getAnnee()==res.getInt(1)) {
				String queryTicketMax = "SELECT MAX(NUMERO_TICKET) FROM TICKET WHERE ANNEE="+res.getInt(1);
				PreparedStatement statementTicketMax = connection.prepareStatement(queryTicketMax);
				ResultSet ResTicketMax=statementTicketMax.executeQuery();
				if(ResTicketMax.next()) {
				String query = "INSERT INTO TICKET(ANNEE,NUMERO_TICKET,DATE_VENTE,HEURE_VENTE) VALUES (?,?,?,?)";
				PreparedStatement statement = connection.prepareStatement(query);
				
				
				statement.setInt(1, ticket.getAnnee());
				statement.setInt(2, ResTicketMax.getInt(1)+1);
				statement.setString(3, ticket.getDate_vente());
				statement.setString(4, ticket.getHeure_vente());

				int rowsAdded = statement.executeUpdate();
				if (rowsAdded > 0) {
					System.out.println("Ticket cree");
				}}
			}
		}} catch (SQLException e) {
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

	public void delete(Ticket ticket) throws ClassNotFoundException {
		Connection connection = null;
		try {
			connection = DataBaseConnection.getInstance();

			String query = "DELETE FROM TICKET WHERE ANNEE=? AND NUMERO_TICKET=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, ticket.getAnnee());
			statement.setInt(2, ticket.getNumero_ticket());
			int rowsAdded = statement.executeUpdate();
			if (rowsAdded > 0) {
				System.out.println("Ticket supprimé");
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

	public void displayTicket(Ticket ticket) {
		if (ticket!=null) {
		System.out.println("{" + ticket.getAnnee() + " / " + ticket.getNumero_ticket() + " / " + ticket.getDate_vente()
				+ " / " + ticket.getHeure_vente() + "}");}
		else {System.out.println("aucun ticket correspondant");}
	}

	public void displayResult(List<Ticket> ArrayTicket) {
		for (Ticket ticket : ArrayTicket) {
			displayTicket(ticket);
		}
	}
	
	public void save2 (Ticket ticket) throws ClassNotFoundException {
		Connection connection = null;
		try {
			connection = DataBaseConnection.getInstance();
			String queryMax = "SELECT MAX(ANNEE),MAX(NUMERO_TICKET) FROM TICKET";
			PreparedStatement statementMax = connection.prepareStatement(queryMax);
			ResultSet res = statementMax.executeQuery();
			if (res.next()) {
			System.out.println(ticket.getAnnee());
			System.out.println(res.getInt(1));}
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
	

	@Override
	public Ticket find(long id) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
