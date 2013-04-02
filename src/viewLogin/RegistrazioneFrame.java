/**
*
*@author Andrea Candini <formulauff@gmail.com>
*/

package viewLogin;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import javax.swing.JFrame;

import controller.CentraFrame;

public class RegistrazioneFrame extends JFrame{

	private AccessPanel registrazionePanel;
	private ConfermaPanel confermaPanel;
	private CentraFrame centraFrame;
	
	public RegistrazioneFrame(String titolo) {
		super(titolo);
		LayoutManager layout = new GridLayout(2,1);
		this.setLayout(layout);
		registrazionePanel = new RegistrazionePanel();
		confermaPanel = new ConfermaPanel("Conferma", this, registrazionePanel);
		getContentPane().add(registrazionePanel);
		getContentPane().add(confermaPanel);
		pack();
		setVisible(true);
		centraFrame = new CentraFrame(this, 450, 125);
		centraFrame.run();
	}
	
}
