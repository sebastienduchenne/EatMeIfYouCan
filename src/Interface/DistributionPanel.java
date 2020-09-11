package Interface;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.SwingConstants;

import classeMetier.CarteGentil;
import classeMetier.CarteLoup;
import classeMetier.CartePersonnage;
import classeMetier.Joueur;

import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.awt.BorderLayout;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

public class DistributionPanel extends JPanel {

	private JLabel tourNLbl;
	private VignetteJoueurDistribPanel j1;
	private VignetteJoueurDistribPanel j2;
	private VignetteJoueurDistribPanel j3;
	private VignetteJoueurDistribPanel j4;
	private VignetteJoueurDistribPanel j5;
	private VignetteJoueurDistribPanel j6;
	private JLabel pseudo2Lbl;
	private JLabel nbPoints2Lbl;
	private JPanel image2Panel;
	private ImagePanel img2;
	private JLabel infoLbl;
	private JButton validerDistribBt;
	private Frame fra;
	

	private ArrayList<VignetteJoueurDistribPanel> vignettes;
	private ArrayList<String> persoDispoCBox;
	private Socket soc;
	private ArrayList<Joueur> joueurs;
	
	private RecupJoueursNouvellePartieThread recupJoueursNewPartThread;
	
	public DistributionPanel(Socket soc, Joueur j, ArrayList<Joueur> lesJoueurs, Frame fr) {
		this.soc = soc;
		this.joueurs = lesJoueurs;
		this.fra = fr;
		setBackground(Color.WHITE);
		setLayout(null);
		
		int nbJoueur = joueurs.size();
		
		tourNLbl = new JLabel("Tour n");
		tourNLbl.setBounds(0, 0, 800, 30);
		tourNLbl.setBackground(Color.WHITE);
		tourNLbl.setHorizontalAlignment(SwingConstants.CENTER);
		add(tourNLbl);

		JPanel autresJoueursPanel = new JPanel();
		autresJoueursPanel.setBounds(0, 30, 800, 260);
		autresJoueursPanel.setBackground(Color.WHITE);

		vignettes = new ArrayList<VignetteJoueurDistribPanel>();
		autresJoueursPanel.setLayout(null);
		
		int index = joueurs.indexOf(j);
		joueurs.remove(j);
		
		//création des joueurs
		j2 = new VignetteJoueurDistribPanel(joueurs.get(0));
		j2.setSize(120, 250);
		j2.setLocation(33, 0);
		autresJoueursPanel.add(j2);
		vignettes.add(j2);
		
		j3 = new VignetteJoueurDistribPanel(joueurs.get(1));
		j3.setSize(120, 250);
		j3.setLocation(183, 0);
		autresJoueursPanel.add(j3);
		vignettes.add(j3);
		
		j4 = new VignetteJoueurDistribPanel(joueurs.get(0));
		if(nbJoueur >= 4){
			j4 = new VignetteJoueurDistribPanel(joueurs.get(2));
			j4.setSize(120, 250);
			j4.setLocation(339, 0);
			autresJoueursPanel.add(j4);
			vignettes.add(j4);
		}
		
		j5 = new VignetteJoueurDistribPanel(joueurs.get(0));
		if(nbJoueur >= 5){
			j5 = new VignetteJoueurDistribPanel(joueurs.get(3));
			j5.setSize(120, 250);
			j5.setLocation(493, 0);
			autresJoueursPanel.add(j5);
			vignettes.add(j5);
		}
		
		j6 = new VignetteJoueurDistribPanel(joueurs.get(0));
		if(nbJoueur == 6){
			j6 = new VignetteJoueurDistribPanel(joueurs.get(4));
			j6.setBounds(645, 0, 120, 250);
			j6.setLocation(645, 0);
			autresJoueursPanel.add(j6);
			vignettes.add(j6);
		}
		
		autresJoueursPanel.add(j5);
		joueurs.add(index, j);
		
		add(autresJoueursPanel);
		
		
		
		JPanel joueurPanel = new JPanel();
		joueurPanel.setBounds(0, 290, 800, 300);
		joueurPanel.setBackground(Color.WHITE);
		add(joueurPanel);
		joueurPanel.setLayout(null);
		
		JPanel profilPanel = new JPanel();
		profilPanel.setBounds(40, 50, 180, 250);
		profilPanel.setBackground(Color.WHITE);
		profilPanel.setLayout(null);
		joueurPanel.add(profilPanel);
		
		j1 = new VignetteJoueurDistribPanel(j);
		j1.setBounds(0, 0, 120, 250);
		profilPanel.add(j1);
		vignettes.add(j1);
		
		JPanel actionPanel = new JPanel();
		actionPanel.setBounds(280, 0, 440, 240);
		actionPanel.setBackground(Color.WHITE);
		joueurPanel.add(actionPanel);
		actionPanel.setLayout(null);

		infoLbl = new JLabel("Distribuez les personnages");
		infoLbl.setBounds(0, 0, 418, 60);
		infoLbl.setHorizontalAlignment(SwingConstants.CENTER);
		infoLbl.setBackground(Color.WHITE);
		actionPanel.add(infoLbl);

		JPanel persoPanel = new JPanel();
		persoPanel.setBounds(0, 60, 418, 120);
		persoPanel.setBackground(Color.WHITE);
		actionPanel.add(persoPanel);
		persoPanel.setLayout(null);

		JPanel boutonsPanel = new JPanel();
		boutonsPanel.setBounds(0, 210, 418, 60);
		boutonsPanel.setBackground(Color.WHITE);
		actionPanel.add(boutonsPanel);

		validerDistribBt = new JButton("Valider");
		validerDistribBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				recupJoueursNewPartThread = new RecupJoueursNouvellePartieThread(DistributionPanel.this.soc, fra);
				recupJoueursNewPartThread.start();
				
				for (VignetteJoueurDistribPanel v : vignettes) {
					String s = (String)v.getPersoCbox().getSelectedItem();
					for (Joueur joueur : joueurs) {
						if(v.getPseudo().equals(joueur.getPseudo())){
							CartePersonnage carte = null;
							if(s.equals("Loup")){
								carte = new CarteLoup();
								carte.setNom(s);
							} else if(s.startsWith("Petit Cochon")){
								carte = new CarteGentil();
								carte.setNom("Petit Cochon");
							} else {
								carte = new CarteGentil();
								carte.setNom(s);
							}
							joueur.setPersonnage(carte);
						}
					}
				}
				
				try {
					OutputStream os = DistributionPanel.this.soc.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(os);
					//oos.flush();

					oos.writeObject(joueurs);
					oos.flush();
					oos.close();
				} catch(IOException ioe){
					ioe.printStackTrace();
				}
				
			}
		});
		boutonsPanel.add(validerDistribBt);
		
		//remplissage de la liste des personnages disponibles
		persoDispoCBox = new ArrayList<String>();
		persoDispoCBox.add("Personnage");
		persoDispoCBox.add("Loup");
		persoDispoCBox.add("Mere Cochon");
		persoDispoCBox.add("Petit Cochon 1");
		persoDispoCBox.add("Chaperon Rouge");
		persoDispoCBox.add("Petit Cochon 2");
		persoDispoCBox.add("Petit Cochon 3");
		
		
		// remplissage du JComboBox
		remplirJcomboBox();
		
		// gestion des évènements venant des JComboBox
		j1.getPersoCbox().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectionItem(j1);
			}
		});
		
		j2.getPersoCbox().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectionItem(j2);
			}
		});
		
		j3.getPersoCbox().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectionItem(j3);
			}
		});
		
		j4.getPersoCbox().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectionItem(j4);
			}
		});
		
		j5.getPersoCbox().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectionItem(j5);
			}
		});
		
		j6.getPersoCbox().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectionItem(j6);
			}
		});
		
		
	}
	
	
	public void selectionItem(VignetteJoueurDistribPanel j){
		String s = (String) j.getPersoCbox().getSelectedItem();
		affichageImage(j);
		
		if(s.equals("Personnage")){
			if(!j.getPersoSelected().equals("Personnage")){
				persoDispoCBox.add(j.getPersoSelected());
				addItemComboBox(j, j.getPersoSelected());
				j.setPersoSelected(s);
			}
		} else {
			persoDispoCBox.remove(s);
			deleteItemComboBox(j, s);
			if(!j.getPersoSelected().equals("Personnage")){
				persoDispoCBox.add(j.getPersoSelected());
				addItemComboBox(j, j.getPersoSelected());
			}
			j.setPersoSelected(s);
		}
	}
	
	public JButton getValiderDistribBt() {
		return validerDistribBt;
	}
	
	
	public void affichageImage(VignetteJoueurDistribPanel joueur){
		String s = (String)joueur.getPersoCbox().getSelectedItem();
		JPanel pan = joueur.getPersoPanel();
		ImagePanel img = null;
		pan.removeAll();
		
		if(s.equals("Personnage")){
			img = new ImagePanel(getImageFromName("blancMINI.jpg"));
			joueur.setValeur(-1);
		} else if(s.equals("Loup")){
			img = new ImagePanel(getImageFromName("loupMINI.jpg"));
			joueur.setValeur(-1);
		} else if(s.equals("Mere Cochon")){
			img = new ImagePanel(getImageFromName("mere cochonMINI.jpg"));
			joueur.setValeur(2);
		} else if(s.startsWith("Petit Cochon")){
			img = new ImagePanel(getImageFromName("petit cochonMINI.jpg"));
			joueur.setValeur(1);
		} else if(s.equals("Chaperon Rouge")){
			img = new ImagePanel(getImageFromName("chaperon rougeMINI.jpg"));
			joueur.setValeur(3);
		}
		img.setBounds(0, 0, 100, 100);
		pan.add(img);
		pan.revalidate();
		pan.repaint();
		
	}
	
	
	public void deleteItemComboBox(VignetteJoueurDistribPanel excepteLeJoueur, String s){
		for (VignetteJoueurDistribPanel v : vignettes) {
			if(v != excepteLeJoueur){
				v.getPersoCbox().removeItem(s);
			}
		}
	}
	
	public void addItemComboBox(VignetteJoueurDistribPanel excepteLeJoueur, String s){
		for (VignetteJoueurDistribPanel v : vignettes) {
			if(v != excepteLeJoueur){
				v.getPersoCbox().addItem(s);
			}
		}
	}
	

	public void remplirJcomboBox(){
		
		for(int i = 0; i < vignettes.size(); i++){
			int nbElement = 4; // quand le nombre de joueur est de 3
			
			if(vignettes.size() == 4){
				nbElement += 1;
			} else if(vignettes.size() == 5){
				nbElement += 2;
			} else if(vignettes.size() == 6){
				nbElement += 3;
			}
			vignettes.get(i).getPersoCbox().removeAllItems();
			for(int j = 0; j < nbElement; j++){
				vignettes.get(i).getPersoCbox().addItem(persoDispoCBox.get(j));
			}
		}
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

}
