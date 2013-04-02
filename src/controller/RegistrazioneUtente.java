/**
*
*@author Andrea Candini <formulauff@gmail.com>
*/

package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import model.Utente;

public class RegistrazioneUtente {

	private Utente utente;
	private boolean nomeUtenteDisponibile = false;
	private File fileRegistrazioni = null;
	
	public RegistrazioneUtente(Utente utente) {
		this.utente = utente;
		fileRegistrazioni = new File("Registrazioni.txt");
	}
	
	//Registrazione utente per uso locale.
	//Sviluppo futuro: interazione con database.
	public boolean registraUtente() {
		
		//Inizializzo nuovamente il valore: non necessario al momento,
		//è fatto nel caso in futuro si implementino metodi ulteriori
		// che lo modificano
		nomeUtenteDisponibile = false;
		
		BufferedReader buffReader = null;
		PrintWriter printWriter = null;
		String utenteDaInserire = utente.getNomeUtente() + " " + utente.getPassword();
		String riga;
		Boolean trovato = false;
		
		//Al primo avvio non ho tale file, devo pertanto crearlo
		if (!fileRegistrazioni.exists())
			try {
				fileRegistrazioni.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		
		//Solo dopo aver creato il file o verificato la sua presenza
		//passo a controllare se è possibile registrare l'utente
		try {
			buffReader = new BufferedReader(new FileReader(fileRegistrazioni));
			
			//Verifica che il nome utente non sia già in uso
			while ((riga = buffReader.readLine()) != null) {
				if (riga.startsWith(utente.getNomeUtente() + " ")) {
					trovato = true;
					break;
				}
			} //while
			
			buffReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Se lo username non viene trovato si procede alla registrazione
		//dell'utente
		if (!trovato) 
			try {
				printWriter = new PrintWriter(new FileWriter(fileRegistrazioni, true));
				printWriter.println(utenteDaInserire);
				nomeUtenteDisponibile = true;
				printWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		return nomeUtenteDisponibile;
	}

	public String loginUtente() {
		if(!fileRegistrazioni.exists()) return "UTENTE_NON_REGISTRATO";
		else {
			
			BufferedReader buffReader = null;
			String riga = null;
			boolean trovato = false;
			
			try {
				buffReader = new BufferedReader(new FileReader(fileRegistrazioni));
				
				//Verifica che il nome utente sia 
				while ((riga = buffReader.readLine()) != null) {
					if (riga.startsWith(utente.getNomeUtente() + " ")) {
						trovato = true;
						break;
					}
				} //while
				
				buffReader.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if (!trovato) return "UTENTE_NON_REGISTRATO";
			else {
				if (riga.equals(utente.getNomeUtente() + " " + utente.getPassword())) {
					return "LOGIN_CORRETTO";
				}
				else return "LOGIN_ERRATO";
			}
		}
	}
}
