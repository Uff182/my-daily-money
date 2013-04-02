/**
*
*@author Andrea Candini <formulauff@gmail.com>
*/

package viewLogin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import viewMain.MainFrame;

import controller.RegistrazioneUtente;

import model.Utente;

public class ConfermaPanel extends JPanel implements ActionListener{

	private JButton conferma, annulla;
	private JFrame frame;
	private AccessPanel panel;
	private boolean registrazioneEffettuata = false;
	private String loginEffettuato;
	
	public ConfermaPanel(String azione, JFrame frame, AccessPanel panel) {
		this.frame = frame;
		this.panel = panel;
		
		conferma = new JButton(azione);
		annulla = new JButton("Annulla");

		add(conferma); add(annulla);
		conferma.addActionListener(this);
		annulla.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object pulsantePremuto = e.getSource();

		if (pulsantePremuto == annulla) frame.dispose();
		if (pulsantePremuto == conferma) {
			
			// Registrazione utente
			if (panel.getClass().getSimpleName().equals("RegistrazionePanel")) { 
			if (((RegistrazionePanel) panel).registrazioneCorretta()) {
				Utente utente = new Utente(panel.getNomeUtente(), 
						panel.getPassword());
				RegistrazioneUtente reg = new RegistrazioneUtente(utente);
				registrazioneEffettuata = reg.registraUtente();
				
				if (!registrazioneEffettuata) {
					JOptionPane.showMessageDialog(this, "Registrazione non effettuata.\nNome utente già presente.",
							"Errore nome utente", JOptionPane.ERROR_MESSAGE);
				}
				else frame.dispose();
			}
			else {
				JOptionPane.showMessageDialog(this, "Registrazione non effettuata.\nNome utente o password errati.",
						"Errore registrazione", JOptionPane.ERROR_MESSAGE);
			}
		}//if registrazionePanel
			else if (panel.getClass().getSimpleName().equals("LoginPanel")) {
				
				//Login utente
				Utente utente = new Utente(panel.getNomeUtente(), 
						panel.getPassword());
				RegistrazioneUtente reg = new RegistrazioneUtente(utente);
				loginEffettuato = reg.loginUtente();
				
				//Controllo del login
				if (loginEffettuato.equals("LOGIN_CORRETTO")) {
					frame.dispose();
					JFrame frameParent = (JFrame)SwingUtilities.getRoot(this);
					frameParent.dispose();
					MainFrame mainFrame = new MainFrame();
					mainFrame.setVisible(true);
				}
				if (loginEffettuato.equals("LOGIN_ERRATO")) 
					JOptionPane.showMessageDialog(this, "I dati inseriti non sono corretti",
							"Errore", JOptionPane.ERROR_MESSAGE);;
				if (loginEffettuato.equals("UTENTE_NON_REGISTRATO"))
					JOptionPane.showMessageDialog(this, "Utente non registrato",
							"Errore", JOptionPane.ERROR_MESSAGE);;
			}
			else {
				JOptionPane.showMessageDialog(this, "Richiesta sconosciuta",
					"Errore", JOptionPane.ERROR_MESSAGE);
			}
		}//if conferma
	}

}
