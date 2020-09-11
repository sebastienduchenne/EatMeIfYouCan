package Interface;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import java.awt.Color;

public class ImagePanel extends JPanel {
	Image img;

	public ImagePanel(String s) {
		setBackground(Color.WHITE);
		img = getToolkit().getImage(s);
		//img = newImageIcon( getClass().getResource( "/images/pack_editors.png" )); 
	}
	
	public ImagePanel(Image img){
		this.img = img;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
}
