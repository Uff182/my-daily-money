/**
*
*@author Andrea Candini <formulauff@gmail.com>
*/

package controller;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class CentraFrame extends Thread{
	private JFrame frame;
	private int width, height;
	private boolean resizable = false;
	
	public CentraFrame(JFrame frame, int width, int height) {
		this.frame = frame;
		this.width = width;
		this.height = height;
	}
	
	public CentraFrame(JFrame frame, int width, int height, boolean resizable) {
		this.frame = frame;
		this.width = width;
		this.height = height;
		this.resizable = resizable;
	}
	
	public void run() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension scrnsize = toolkit.getScreenSize();
		int x = (int) scrnsize.getWidth()/2;
		int y = (int) scrnsize.getHeight()/2;
		
		x -= width/2;
		y -= height/2;
		
		frame.setBounds(x, y, width, height);
		frame.setResizable(resizable);
	}
	
}
