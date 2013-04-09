/**
*
*@author Andrea Candini <formulauff@gmail.com>
*/

package viewLogin;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class RegistrazionePanel extends AccessPanel implements DocumentListener, MouseListener{

	private JLabel confermaPassword;
	private JPasswordField confermaPasswordInserimento;
	private JTextArea usernameCorretto, passwordCorretta, confermaPasswordCorretta;
	private Boolean usrnmCorretto = false, passwordUguali = false;
	
	public RegistrazionePanel() {
		
		LayoutManager layout = new GridLayout(3, 3);
		this.setLayout(layout);
		
		Font font = new Font(null, Font.BOLD, 12);
		
		//Inizializzazione JLabel
		username = new JLabel("Nome utente: ");
		password = new JLabel("Password: ");
		confermaPassword = new JLabel("Conferma password: ");
		
		//Inizializzazione interazione con l'utente
		usernameInserimento = new JTextField("Inserisci il tuo nome utente");
		usernameInserimento.setEditable(true);
		passwordInserimento = new JPasswordField();
		passwordInserimento.setEditable(true);
		confermaPasswordInserimento = new JPasswordField();
		confermaPasswordInserimento.setEditable(true);

		
		//Inizializzazione controllo dati inseriti
		usernameCorretto = new JTextArea();
		usernameCorretto.setEditable(false);
		usernameCorretto.setFont(font);
		passwordCorretta = new JTextArea();
		passwordCorretta.setEditable(false);
		passwordCorretta.setFont(font);
		confermaPasswordCorretta = new JTextArea();
		confermaPasswordCorretta.setEditable(false);
		confermaPasswordCorretta.setFont(font);
		
		add(username); add(usernameInserimento); add(usernameCorretto);
		add(password); add(passwordInserimento); add(passwordCorretta);
		add(confermaPassword); add(confermaPasswordInserimento); add(confermaPasswordCorretta);
		
		usernameInserimento.getDocument().addDocumentListener(this);
		usernameInserimento.addMouseListener(this);
		passwordInserimento.getDocument().addDocumentListener(this);
		confermaPasswordInserimento.getDocument().addDocumentListener(this);
		
	}

	
	//Proprietà
	public String getNomeUtente() {
		return usernameInserimento.getText();
	}
	
	public String getPassword() {
		return new String(passwordInserimento.getPassword());
	}
	
	public boolean registrazioneCorretta() {
		if (usrnmCorretto && passwordUguali) return true;
		else return false;
	}
	//Implementazione metodi DocumentListener
	@Override
	public void changedUpdate(DocumentEvent arg0) { }

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		
		if (arg0.getDocument() == usernameInserimento.getDocument())
		valutaCorrettezzaUsername(usernameInserimento.getText());
		if (arg0.getDocument() == passwordInserimento.getDocument())
			valutaRobustezzaPassword(passwordInserimento.getPassword());
		if (arg0.getDocument() == confermaPasswordInserimento.getDocument())
			controllaPasswordUguali(passwordInserimento.getPassword(), confermaPasswordInserimento.getPassword());
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		valutaCorrettezzaUsername(usernameInserimento.getText());
	}

	//Metodi di supporto
	private void valutaCorrettezzaUsername(String username) {
		//Avviso correttezza username inserito
		if (!username.equals("")) {
		if (!username.contains(" ")) {
			usrnmCorretto = true;
			usernameCorretto.setForeground(Color.GREEN);
			usernameCorretto.setText("Username corretto");
		}
		else {
			usrnmCorretto = false;
			usernameCorretto.setForeground(Color.RED);
			usernameCorretto.setText("Username errato");
		}
		} else if (username.equals("")) {
			usrnmCorretto = false;
			usernameCorretto.setForeground(Color.RED);
			usernameCorretto.setText("Username errato");
		}
	}
	
	private void valutaRobustezzaPassword(char[] password) {
		
		int lunghezzaPassword = password.length;
		
		if (lunghezzaPassword < 5) {
			passwordCorretta.setForeground(Color.RED);
			passwordCorretta.setText("Sicurezza bassa");
		}
		else if (lunghezzaPassword >= 5 && lunghezzaPassword < 10) {
			passwordCorretta.setForeground(Color.YELLOW);
			passwordCorretta.setText("Sicurezza media");
		}
		else if (lunghezzaPassword >= 10) {
			passwordCorretta.setForeground(Color.GREEN);
			passwordCorretta.setText("Sicurezza buona");
		}
	}
	
	private void controllaPasswordUguali(char[] passwordIns, char[] passwordConf) {
		String pwdIns = new String(passwordIns);
		String pwdConf = new String(passwordConf);
		
		if (!pwdConf.equals(pwdIns)) {
			confermaPasswordCorretta.setForeground(Color.RED);
			confermaPasswordCorretta.setText("Password diversa");
			passwordUguali = false;
		}
		else {
			confermaPasswordCorretta.setForeground(Color.GREEN);
			confermaPasswordCorretta.setText("Password uguale");
			passwordUguali = true;
		}
	}
	
	//Implementazione metodi MouseListener
	@Override
	public void mouseClicked(MouseEvent e) {
		if (usernameInserimento.getText().equals("Inserisci il tuo nome utente"))
		usernameInserimento.setText("");
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}

}
