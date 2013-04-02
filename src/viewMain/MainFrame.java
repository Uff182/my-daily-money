/**
*
*@author Andrea Candini <formulauff@gmail.com>
*/

package viewMain;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import controller.CentraFrame;

public class MainFrame extends JFrame{

	public MainFrame() {
		
		MainMenuTop north, center, west, south;
		InserisciSpesaPanel east;
		north = new MainMenuTop("Nord");
		center = new MainMenuTop("Centro");
		east = new InserisciSpesaPanel();
		west = new MainMenuTop("Ovest");
		south = new MainMenuTop("Sud");
		CentraFrame cf = new CentraFrame(this, 350, 300, true);
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);
		
		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(center, BorderLayout.CENTER);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(west, BorderLayout.WEST);
		getContentPane().add(south, BorderLayout.SOUTH);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		cf.run();
	}
}
