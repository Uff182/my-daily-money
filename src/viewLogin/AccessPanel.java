/**
*
*@author Andrea Candini <formulauff@gmail.com>
*/

package viewLogin;

/* Classe astratta che funge da base per le schermate
	di login e registrazione, fornendo gli opportuni
	metodi per ottenere lo username e la password
	inseriti.
*/
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public abstract class AccessPanel extends JPanel{
	
	protected JLabel username, password;
	protected JTextField usernameInserimento;
	protected JPasswordField passwordInserimento;
	
	public abstract String getNomeUtente();
	public abstract String getPassword();
}
