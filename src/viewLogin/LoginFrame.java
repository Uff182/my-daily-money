/**
*
*@author Andrea Candini <formulauff@gmail.com>
*/

package viewLogin;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import javax.swing.JFrame;
import controller.CentraFrame;

public class LoginFrame extends JFrame{

	private LoginPanel loginPanel;
	private JFrame frame;
	private ConfermaPanel confermaPanel;
	private CentraFrame centraFrame;

	public LoginFrame(String titolo, JFrame frame) {
		super(titolo);
		this.frame = frame;
		LayoutManager layoutFrame = new GridLayout(2,1);
		this.setLayout(layoutFrame);
		
		loginPanel = new LoginPanel();
		getContentPane().add(loginPanel);
		confermaPanel = new ConfermaPanel("Login", frame, loginPanel);
		getContentPane().add(confermaPanel);
		this.pack();
		setVisible(true);

		centraFrame = new CentraFrame(this, 320, 120);
		centraFrame.run();
	}

}
