/**
*
*@author Andrea Candini <formulauff@gmail.com>
*/

package viewMain;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenuTop extends JPanel{

	private String s;
	private JLabel label;
	
	public MainMenuTop(String s) {
		this.s = s;
		label = new JLabel(s);
		add(label);
	}
}