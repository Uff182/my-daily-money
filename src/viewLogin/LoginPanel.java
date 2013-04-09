/**
*
*@author Andrea Candini <formulauff@gmail.com>
*/

package viewLogin;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends AccessPanel{
	
	public LoginPanel() {
		

		username = new JLabel("Nome utente: ");
		usernameInserimento = new JTextField();
		usernameInserimento.setColumns(20);
		add(username); add(usernameInserimento);

		password = new JLabel("Password:    ");
		passwordInserimento = new JPasswordField();
		passwordInserimento.setColumns(20);

		add(password); add(passwordInserimento);
	}
	
	//Proprietà
	public String getNomeUtente() {
		return usernameInserimento.getText();
	}

	public String getPassword() {
		return new String(passwordInserimento.getPassword());
	}
}
