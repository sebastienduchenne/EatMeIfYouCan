 package Interface;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.SwingConstants;

import classeMetier.Joueur;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class VignetteJoueurDistribPanel extends JPanel{
	private JPanel nomPointsPanel;
	private JLabel pseudoLbl;
	private JLabel nbPointsLbl;
	private JComboBox persoCbox;
	private JPanel persoPanel;
	private ImagePanel persoImage;
	private JLabel valeurLbl;
	
	private String persoSelected;
	private ArrayList<String> persoDispoCBox;
	
	
	public VignetteJoueurDistribPanel(Joueur jVignette) {
		setBackground(Color.WHITE);
		setLayout(null);
		
		nomPointsPanel = new JPanel();
		nomPointsPanel.setBounds(0, 0, 120, 40);
		nomPointsPanel.setBackground(Color.WHITE);
		nomPointsPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLUE));
		add(nomPointsPanel);
		nomPointsPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		pseudoLbl = new JLabel(jVignette.getPseudo());
		pseudoLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nomPointsPanel.add(pseudoLbl);
		
		nbPointsLbl = new JLabel("5 points");
		setNbPoints(jVignette.getNbPoint());
		nbPointsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nomPointsPanel.add(nbPointsLbl);
		
		persoCbox = new JComboBox();
		persoCbox.setBackground(Color.WHITE);
		persoCbox.setBounds(0, 45, 120, 30);
		add(persoCbox);
		
		persoPanel = new JPanel();
		persoPanel.setBackground(Color.WHITE);
		persoPanel.setBounds(10, 80, 100, 100);
		add(persoPanel);
		persoPanel.setLayout(null);
		
		persoImage = new ImagePanel(getImageFromName("blancMINI.jpg"));
		persoImage.setBounds(0, 0, 100, 100);
		persoImage.setBackground(Color.WHITE);
		persoPanel.add(persoImage);
		
		valeurLbl = new JLabel("");
		valeurLbl.setHorizontalAlignment(SwingConstants.CENTER);
		valeurLbl.setBounds(0, 185, 120, 30);
		add(valeurLbl);
		/*
		persoDispoCBox = new ArrayList<String>();
		persoDispoCBox.add("Personnage");
		persoDispoCBox.add("Loup");
		persoDispoCBox.add("Mère cochon");
		persoDispoCBox.add("Petit cochon");
		persoDispoCBox.add("Chaperon rouge");
		*/
		
		persoSelected = "Personnage";
	}
	
	public JPanel getPersoPanel() {
		return persoPanel;
	}

	public void setPersoPanel(JPanel persoPanel) {
		this.persoPanel = persoPanel;
	}
	
	public String getPseudo(){
		return pseudoLbl.getText();
	}

	public void setPseudo(String pseudo){
		pseudoLbl.setText(pseudo);
	}
	
	public void setNbPoints(int nbPoints){
		if(nbPoints < 2){
			nbPointsLbl.setText(nbPoints + " point");
		} else {
			nbPointsLbl.setText(nbPoints + " points");
		}
	}
	
	public void SetPoints(int newPoints){
		if(newPoints < 2){
			nbPointsLbl.setText(newPoints + " point");
		} else {
			nbPointsLbl.setText(newPoints + " points");
		}
	}
	
	
	public int getIndexPersoCbox(){
		return persoCbox.getSelectedIndex();
	}
	
	public JComboBox getPersoCbox(){
		return persoCbox;
	}
	
	public void setPersoPanel(String file){
		//System.out.println("dans setpersopanel : " + file);
		persoPanel.removeAll();
		ImagePanel img = new ImagePanel(getImageFromName(file));
		img.setBounds(0, 0, 100, 100);
		persoPanel.add(img);
		persoPanel.revalidate();
		persoPanel.repaint();
	}
	
	public String getPersoSelected() {
		return persoSelected;
	}
	
	public void setPersoSelected(String perso) {
		persoSelected = perso;
	}
	
	
	public void setValeur(int valeur){
		if(valeur == -1){
			valeurLbl.setText("");
		} else if(valeur < 2){
			valeurLbl.setText(valeur + " point");
		} else {
			valeurLbl.setText(valeur + " points");
		}
	}

	public Image getImageFromName(String nomFichier){
		InputStream i = this.getClass().getClassLoader().getResourceAsStream(nomFichier);
		Image logo = null;
		try {
			logo = ImageIO.read(i);
		} catch (IOException e) {
		}
		return logo;
		
		//return null;
	}
}
