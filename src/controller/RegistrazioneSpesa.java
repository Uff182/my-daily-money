/**
*
*@author Andrea Candini <formulauff@gmail.com>
*/

package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import model.Spesa;

public class RegistrazioneSpesa {

	private Spesa spesa;
	private boolean registrazioneEffettuata = false;

	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String name = "adminSpese", password = "adminSpese";

	public RegistrazioneSpesa() {
		String url = "jdbc:mysql://localhost/gestione_spese";

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, name, password);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public RegistrazioneSpesa(Spesa spesa) {
		this.spesa = spesa;
		String url = "jdbc:mysql://localhost/gestione_spese";

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, name, password);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean effettuaRegistrazione() {

		try {
			//Ottengo l'id da utilizzare per la registrazione
			pstmt = con.prepareStatement("SELECT next_value " + 
					"FROM sequence");

			rs = pstmt.executeQuery();
			int id = -1;
			while (rs.next()) {
				id = rs.getInt(1);
			}
			
			//INSERT INTO spese (Id, Inserimento, Spesa, Tipo) VALUES(1, '2013-03-29 10:37:00', 15.97, 'Spesa');
			pstmt = con.prepareStatement("INSERT INTO spese (Id, Inserimento, Spesa, Tipo) " +
					"VALUES(?, ?, ?, ?)");

			Calendar calendar = Calendar.getInstance();
			Date now = calendar.getTime();
			Timestamp timestamp = new Timestamp(now.getTime());

			pstmt.setInt(1, id);
			pstmt.setTimestamp(2, timestamp);
			pstmt.setBigDecimal(3, spesa.getPrezzo());
			pstmt.setString(4, spesa.getTipologia());
			pstmt.execute();

			//Aggiorno l'id per il prossimo inserimento
			pstmt = con.prepareStatement("INSERT INTO sequence (next_value) " +
					"VALUES(?)");
			pstmt.setInt(1, ++id);
			pstmt.execute();

			rs.close();
			pstmt.close();
			con.close();
			
			registrazioneEffettuata = true;
		} catch (SQLException e) {
			registrazioneEffettuata = false;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				registrazioneEffettuata = false;
            }
		}
		return registrazioneEffettuata;
	}

	public String[] aggiornaListaTipologia() {
		
		String[] listaTipologia = null;
		
		//Ottengo l'id da utilizzare per la registrazione
		try {
			
			//Per prima cosa conto quante entry ho
			pstmt = con.prepareStatement("SELECT next_value " + 
					"FROM sequence_tipologia");
			rs = pstmt.executeQuery();
			int id = -1;
			while (rs.next()) {
				id = rs.getInt(1);
			}
			listaTipologia = new String[id-1];
			
			//Seleziono tutte le tipologie di spese
			pstmt = con.prepareStatement("SELECT * " + 
					"FROM tipologia");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				id = rs.getInt(1);
				listaTipologia[id-1] = rs.getString(2);
			}
			
			//Ordino la stringa ottenuta
			Arrays.sort(listaTipologia);
			
			rs.close();
			pstmt.close();
			con.close();
			
		} catch (SQLException e) {
			//In caso di errore ritorno null
			listaTipologia = null;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				listaTipologia = null;
            }
		}
		
		return listaTipologia;
		
	}
}
