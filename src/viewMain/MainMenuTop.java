/**
*
*@author Andrea Candini <formulauff@gmail.com>
*/

package viewMain;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenuTop extends JPanel{

	private JLabel label;
	
	public MainMenuTop(String s) {
		label = new JLabel(s);
		add(label);
	}
}