/**
*
*@author Andrea Candini <formulauff@gmail.com>
*/

package viewLogin;

import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controller.CentraFrame;

public class IntroFrame extends JFrame{
	
	private CentraFrame centraFrame;
	
	public IntroFrame(String titolo) {
		super(titolo);
		
		try {
			UIManager.setLookAndFeel(
			        UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		//Creazione di un layout apposito per disporre i contenuti
		LayoutManager layout = new FlowLayout();
		setLayout(layout);
		
		//Immagine di presentazione
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(getClass().getResource("../resources/intro_benvenuto.jpg"));
		} catch (IOException e) {
			System.out.println("Errore nell'apertura dell'immagine.");
			e.printStackTrace();
		}
		JLabel messaggioBenvenuto = new JLabel(new ImageIcon(myPicture));
		JPanel immagineBenvenuto = new JPanel();
		immagineBenvenuto.add(messaggioBenvenuto);
		
		//Pannello con pulsanti per interazione iniziale
		JPanel introPanel = new IntroPanel(this);
		int introPanelHeight = 75;
		
		//Aggiunta JPanel al frame
		getContentPane().add(messaggioBenvenuto);
		getContentPane().add(introPanel);
		
		pack();
		
//		centraFrame(myPicture.getWidth(), myPicture.getHeight()+introPanelHeight);
		centraFrame = new CentraFrame(this, myPicture.getWidth(), myPicture.getHeight()+introPanelHeight);
		centraFrame.run();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
