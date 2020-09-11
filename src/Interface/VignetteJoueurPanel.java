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
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;

import javax.swing.SwingConstants;

import Serveur.Logs;
import classeMetier.CarteGentil;
import classeMetier.CarteLoup;
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

public class VignetteJoueurPanel extends JPanel{
	private JPanel nomPointsPanel;
	private JLabel pseudoLbl;
	private JLabel nbPointsLbl;
	private JButton persoBt;
	private JLabel valeurLbl;
	private JPanel maisonImagePanel;
	private ImagePanel maisonImage;
	private JPanel choixLoupImagePanel;
	private ImagePanel choixLoupImage;
	private JPanel cadreBtPersoPanel;
	private JLabel lblGain;
	
	private Socket soc;
	private Joueur joueur;
	
	public VignetteJoueurPanel(Joueur joueur, boolean clientIsLoup) {
		this.joueur = joueur;
		setBackground(Color.WHITE);
		setLayout(null);
		
		nomPointsPanel = new JPanel();
		nomPointsPanel.setBounds(0, 0, 120, 40);
		nomPointsPanel.setBackground(Color.WHITE);
		nomPointsPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLUE));
		add(nomPointsPanel);
		nomPointsPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		pseudoLbl = new JLabel(joueur.getPseudo());
		pseudoLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nomPointsPanel.add(pseudoLbl);
		
		nbPointsLbl = new JLabel("");
		SetPoints(joueur.getNbPoint());
		nbPointsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nomPointsPanel.add(nbPointsLbl);
		
		cadreBtPersoPanel = new JPanel();
		ImagePanel imgp = null;
		
		persoBt = new JButton(new ImageIcon(getImageFromName("loupMINI.jpg")));
		
		cadreBtPersoPanel.setBackground(Color.WHITE);
		cadreBtPersoPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		
		
		
		Logs.insererLogINFO(joueur.getPersonnage().getNom());
		
		if(joueur.getPersonnage().getNom().equals("Petit Cochon")){
			imgp = new ImagePanel(getImageFromName("petit cochonMINI.jpg"));
			persoBt = new JButton(new ImageIcon(getImageFromName("petit cochonMINI.jpg")));
		} else if(joueur.getPersonnage().getNom().equals("Mere Cochon")){
			imgp = new ImagePanel(getImageFromName("mere cochonMINI.jpg"));
			persoBt = new JButton(new ImageIcon(getImageFromName("mere cochonMINI.jpg")));
		} else if(joueur.getPersonnage().getNom().equals("Chaperon Rouge")){
			imgp = new ImagePanel(getImageFromName("chaperon rougeMINI.jpg"));
			persoBt = new JButton(new ImageIcon(getImageFromName("chaperon rougeMINI.jpg")));
		} else if(joueur.getPersonnage().getNom().equals("Loup")){
			imgp = new ImagePanel(getImageFromName("loupMINI.jpg"));
			persoBt = new JButton(new ImageIcon(getImageFromName("loupMINI.jpg")));
		}
		
		cadreBtPersoPanel.setBounds(10, 50, 100, 100);
		cadreBtPersoPanel.add(imgp);
		add(cadreBtPersoPanel);
		/*
		if(!clientIsLoup){ // client = gentil
			cadreBtPersoPanel.setBounds(10, 50, 100, 100);
			cadreBtPersoPanel.add(imgp);
			add(cadreBtPersoPanel);
		} else { // client = loup
			cadreBtPersoPanel.setBounds(0, 40, 120, 120);
			add(cadreBtPersoPanel);
			cadreBtPersoPanel.setLayout(null);
			persoBt.setBounds(5, 5, 110, 110);
			cadreBtPersoPanel.add(persoBt);
		}
		*/
		
		
		valeurLbl = new JLabel("");
		
		if(joueur.getPersonnage() instanceof CarteLoup){
			
		} else {
			setValeur( ((CarteGentil)joueur.getPersonnage()).getValeur() );
		}
		
		
		valeurLbl.setBounds(0, 160, 120, 20);
		valeurLbl.setBackground(Color.WHITE);
		valeurLbl.setHorizontalAlignment(SwingConstants.CENTER);
		add(valeurLbl);
		
		maisonImagePanel = new JPanel();
		maisonImagePanel.setBounds(40, 180, 40, 40);
		add(maisonImagePanel);
		maisonImagePanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		maisonImage = new ImagePanel(getImageFromName("blancMINI.jpg"));
		maisonImagePanel.add(maisonImage);
		
		choixLoupImagePanel = new JPanel();
		choixLoupImagePanel.setBounds(40, 220, 40, 40);
		add(choixLoupImagePanel);
		choixLoupImagePanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		choixLoupImage = new ImagePanel(getImageFromName("blancMINI.jpg"));
		choixLoupImagePanel.add(choixLoupImage);
		
		lblGain = new JLabel("");
		lblGain.setHorizontalAlignment(SwingConstants.CENTER);
		lblGain.setBounds(0, 260, 120, 30);
		add(lblGain);
		
		
	}
	
	public JPanel getCadreBtPersoPanel() {
		return cadreBtPersoPanel;
	}

	public JButton getPersoBt() {
		return persoBt;
	}
	
	public String getPseudo(){
		return pseudoLbl.getText();
	}
	
	public void SetPseudo(String newPseudo){
		pseudoLbl.setText(newPseudo);
	}
	
	public Joueur getJoueur() {
		return joueur;
	}
	
	public void SetPoints(int newPoints){
		if(newPoints < 2){
			nbPointsLbl.setText(newPoints + " point");
		} else {
			nbPointsLbl.setText(newPoints + " points");
		}
	}
	
	public void setGain(int gain){
		if(gain < -1){
			lblGain.setText(gain + " points");
		} else if(gain == -1){
			lblGain.setText(gain + " point");
		} else if(gain == 0){
			lblGain.setText(gain + " point");
		} else if(gain == 1){
			lblGain.setText(gain + " point");
		} else if(gain > 1){
			lblGain.setText(gain + " points");
		}
	}

	//à utiliser pour la vue LOUP
	public void setPersoBtGENTILS(String file) {
		cadreBtPersoPanel.removeAll();
		JButton bt = new JButton(new ImageIcon(getImageFromName(file)));
		bt.setBounds(0, 40, 120, 120);
		cadreBtPersoPanel.add(bt);
		cadreBtPersoPanel.revalidate();
		cadreBtPersoPanel.repaint();
	}
	
	// à utiliser pour la vue GENTILS
	public void setPersoImgLOUP(String file) {
		cadreBtPersoPanel.removeAll();
		ImagePanel img = new ImagePanel(getImageFromName(file));
		img.setBounds(0, 40, 120, 120);
		cadreBtPersoPanel.add(img);
		cadreBtPersoPanel.revalidate();
		cadreBtPersoPanel.repaint();
	}

	public void setValeur(int newValeur) {
		if(newValeur == 1){
			valeurLbl.setText(newValeur + " point");
		} else {
			valeurLbl.setText(newValeur + " points");
		}
	}

	
	public void setChoixLoupValidation(String file) {
		choixLoupImagePanel.removeAll();
		ImagePanel imgp = new ImagePanel(getImageFromName("validationMini.jpg"));
		choixLoupImagePanel.setLayout(new GridLayout(0, 1, 0, 0));
		choixLoupImagePanel.add(imgp);
		choixLoupImagePanel.revalidate();
		choixLoupImagePanel.repaint();
	}
	
	public void removeChoixLoupImage(){
		choixLoupImagePanel.removeAll();
		choixLoupImagePanel.revalidate();
		choixLoupImagePanel.repaint();
	}

	
	public Image getImageFromName(String nomFichier){
		InputStream i = this.getClass().getClassLoader().getResourceAsStream(nomFichier);
		Image logo = null;
		try {
			logo = ImageIO.read(i);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return logo;
	}
	
	public void setImageMaison(String file) {
		maisonImagePanel.removeAll();
		ImagePanel imgp = new ImagePanel(getImageFromName(file));
		maisonImagePanel.setLayout(new GridLayout(0, 1, 0, 0));
		maisonImagePanel.add(imgp);
		maisonImagePanel.revalidate();
		maisonImagePanel.repaint();
	}
	
	public void auLoupDeJouer(){
		cadreBtPersoPanel.removeAll();
		cadreBtPersoPanel.setBounds(0, 40, 120, 120);
		add(cadreBtPersoPanel);
		cadreBtPersoPanel.setLayout(null);
		persoBt.setBounds(5, 5, 110, 110);
		cadreBtPersoPanel.add(persoBt);
	}
	
	public void affichagePointsTour(int points){
		setGain(points);
	}
}
