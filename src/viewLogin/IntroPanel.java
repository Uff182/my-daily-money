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

public class IntroPanel extends JPanel implements ActionListener{

	private JButton login, registrazione, esci;
	private JFrame introFrame;
	
	public IntroPanel(JFrame introPanel) {

		this.introFrame = introPanel;
		login = new JButton("Login");
		registrazione = new JButton("Registrazione");
		esci = new JButton("Esci");
		
		add(login);
		add(registrazione);
		add(esci);
		
		esci.addActionListener(this);
		registrazione.addActionListener(this);
		login.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object pulsantePremuto = e.getSource();
		
		if (pulsantePremuto == login) {
			JFrame login = new LoginFrame("Pannello di login", introFrame);
		}
		else if (pulsantePremuto == esci) System.exit(0);
		else if (pulsantePremuto == registrazione) {
			JFrame registrazioneFrame = new RegistrazioneFrame("Pannello di registrazione");
		}
		
		else {
			JOptionPane.showMessageDialog(this, "Opzione non ancora implementata.", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		

	}
	
}
