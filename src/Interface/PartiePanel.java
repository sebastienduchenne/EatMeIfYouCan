package Interface;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.SwingConstants;

import Serveur.Logs;
import classeMetier.CarteGentil;
import classeMetier.CarteLoup;
import classeMetier.Joueur;

import javax.swing.JButton;
import javax.swing.JFrame;

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
import java.awt.FlowLayout;

public class PartiePanel extends JPanel {

	private JLabel tourNLbl;
	private VignetteJoueurPanel j1;
	private VignetteJoueurPanel j2;
	private VignetteJoueurPanel j3;
	private VignetteJoueurPanel j4;
	private VignetteJoueurPanel j5;
	private JLabel pseudo2Lbl;
	private JLabel nbPoints2Lbl;
	private JPanel image2Panel;
	private ImagePanel img2;
	private JLabel infoLbl;
	private JPanel btGauchePanel;
	private JPanel btDroitPanel;
	private JButton maisonGaucheBt;
	private JButton maisonDroiteBt;
	private JLabel infoActionLbl;
	private JButton gaucheBt;
	
	public JButton getGaucheBt() {
		return gaucheBt;
	}

	private JButton droitBt;

	private Socket soc;
	private Joueur j;
	private ArrayList<Joueur> joueurs;
	private AuLoupDeJouerThread loupThread;
	private RecupPointsTourThread pointsThread;
	private AfficherPageDistributionThread afficherDistribThread;
	private JPanel boutonsPanel;
	private ArrayList<VignetteJoueurPanel> vignettes;
	private Frame fr;

	

	public PartiePanel() {

		setBackground(Color.WHITE);
		setLayout(null);

		tourNLbl = new JLabel("Tour n");
		tourNLbl.setBounds(0, 0, 800, 30);
		tourNLbl.setBackground(Color.WHITE);
		tourNLbl.setHorizontalAlignment(SwingConstants.CENTER);
		add(tourNLbl);

		JPanel autresJoueursPanel = new JPanel();
		autresJoueursPanel.setBounds(0, 30, 800, 300);
		autresJoueursPanel.setBackground(Color.WHITE);
		autresJoueursPanel.setLayout(null);

		int index = joueurs.indexOf(j);
		joueurs.remove(j);

		boolean isLoup = false;
		if (j.getPersonnage() instanceof CarteLoup) {
			isLoup = true;
		}

		j1 = new VignetteJoueurPanel(joueurs.get(0), isLoup);
		j1.setBounds(33, 0, 120, 300);
		autresJoueursPanel.add(j1);

		j2 = new VignetteJoueurPanel(joueurs.get(1), isLoup);
		j2.setBounds(183, 0, 120, 300);
		autresJoueursPanel.add(j2);

		j3 = new VignetteJoueurPanel(joueurs.get(0), isLoup);
		if (joueurs.size() >= 3) {
			j3 = new VignetteJoueurPanel(joueurs.get(2), isLoup);
			j3.setBounds(339, 0, 120, 300);
			autresJoueursPanel.add(j3);
		}

		j4 = new VignetteJoueurPanel(joueurs.get(0), isLoup);
		if (joueurs.size() >= 4) {
			j4 = new VignetteJoueurPanel(joueurs.get(3), isLoup);
			j4.setBounds(493, 0, 120, 300);
			autresJoueursPanel.add(j4);
		}

		j5 = new VignetteJoueurPanel(joueurs.get(0), isLoup);
		if (joueurs.size() == 5) {
			j5 = new VignetteJoueurPanel(joueurs.get(4), isLoup);
			j5.setBounds(645, 0, 120, 300);
			autresJoueursPanel.add(j5);
		}

		joueurs.add(index, j);

		add(autresJoueursPanel);

		JPanel joueurPanel = new JPanel();
		joueurPanel.setBounds(0, 330, 800, 300);
		joueurPanel.setBackground(Color.WHITE);
		add(joueurPanel);
		joueurPanel.setLayout(null);

		JPanel profilPanel = new JPanel();
		profilPanel.setBounds(30, 10, 180, 200);
		profilPanel.setBackground(Color.WHITE);
		profilPanel.setLayout(null);
		joueurPanel.add(profilPanel);

		JPanel nomPoints2Panel = new JPanel();
		nomPoints2Panel.setBackground(Color.WHITE);
		nomPoints2Panel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLUE));
		nomPoints2Panel.setBounds(10, 0, 170, 40);
		profilPanel.add(nomPoints2Panel);
		nomPoints2Panel.setLayout(null);

		pseudo2Lbl = new JLabel(j.getPseudo());
		pseudo2Lbl.setHorizontalAlignment(SwingConstants.CENTER);
		pseudo2Lbl.setBounds(0, 0, 170, 20);
		nomPoints2Panel.add(pseudo2Lbl);

		nbPoints2Lbl = new JLabel("x points");
		setNbPoints2Lbl(j.getNbPoint());
		nbPoints2Lbl.setHorizontalAlignment(SwingConstants.CENTER);
		nbPoints2Lbl.setBounds(0, 20, 170, 20);
		nomPoints2Panel.add(nbPoints2Lbl);

		image2Panel = new JPanel();
		image2Panel.setBounds(10, 40, 170, 170);
		profilPanel.add(image2Panel);
		image2Panel.setLayout(new BorderLayout(0, 0));

		ImagePanel img2 = null;
		String nomPerso = j.getPersonnage().getNom();
		if (nomPerso.equals("Loup")) {
			img2 = new ImagePanel(getImageFromName("loupGRAND.jpg"));
		} else if (nomPerso.equals("Mere Cochon")) {
			img2 = new ImagePanel(getImageFromName("mere cochonGRAND.jpg"));
		} else if (nomPerso.equals("Petit Cochon")) {
			img2 = new ImagePanel(getImageFromName("petit cochonGRAND.jpg"));
		} else if (nomPerso.equals("Chaperon Rouge")) {
			img2 = new ImagePanel(getImageFromName("chaperon rougeGRAND.jpg"));
		}

		image2Panel.add(img2);

		JPanel actionPanel = new JPanel();
		actionPanel.setBounds(280, 0, 440, 240);
		actionPanel.setBackground(Color.WHITE);
		joueurPanel.add(actionPanel);
		actionPanel.setLayout(null);

		infoLbl = new JLabel("New label");
		infoLbl.setBounds(0, 0, 418, 60);
		infoLbl.setHorizontalAlignment(SwingConstants.CENTER);
		infoLbl.setBackground(Color.WHITE);
		actionPanel.add(infoLbl);

		infoActionLbl = new JLabel("");
		infoActionLbl.setBounds(100, 180, 418, 30);
		infoActionLbl.setBackground(Color.WHITE);
		actionPanel.add(infoActionLbl);

		boutonsPanel = new JPanel();
		boutonsPanel.setBounds(0, 210, 418, 60);
		boutonsPanel.setBackground(Color.WHITE);
		actionPanel.add(boutonsPanel);

		maisonGaucheBt = new JButton(new ImageIcon(getImageFromName("litMINI.jpg")));
		maisonDroiteBt = new JButton(new ImageIcon(getImageFromName("piegeMINI.jpg")));
		gaucheBt = new JButton("Valider");
		droitBt = new JButton("Valider");

		if (j.getPersonnage() instanceof CarteLoup) {
			infoLbl.setText("Veuillez patienter.");
			loupThread = new AuLoupDeJouerThread(soc, this);
			loupThread.start();

		} else {
			infoLbl.setText("Choisissez votre maison");

			btGauchePanel = new JPanel();
			btGauchePanel.setBounds(80, 60, 120, 120);
			btGauchePanel.setBackground(Color.WHITE);
			actionPanel.add(btGauchePanel);
			btGauchePanel.setLayout(null);

			maisonGaucheBt.setBounds(5, 5, 110, 110);
			btGauchePanel.add(maisonGaucheBt);

			btDroitPanel = new JPanel();
			btDroitPanel.setBackground(Color.WHITE);
			btDroitPanel.setBounds(220, 60, 120, 120);
			actionPanel.add(btDroitPanel);
			btDroitPanel.setLayout(null);

			maisonDroiteBt.setBounds(5, 5, 110, 110);
			btDroitPanel.add(maisonDroiteBt);
		}

		// gestion �v�nements des boutons des vignettes
		j1.getPersoBt().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionButton(j1);
			}
		});

		j2.getPersoBt().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionButton(j2);
			}
		});

		j3.getPersoBt().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionButton(j3);
			}
		});

		j4.getPersoBt().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionButton(j4);
			}
		});

		j5.getPersoBt().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionButton(j5);
			}
		});

		maisonGaucheBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				maisonBt(maisonGaucheBt);
				try {
					OutputStream os = PartiePanel.this.soc.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(os);
					// oos.flush();
					CarteGentil g = (CarteGentil) PartiePanel.this.j.getPersonnage();
					g.setDort(true);
					oos.writeObject(PartiePanel.this.j);
					oos.flush();
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		maisonDroiteBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				maisonBt(maisonDroiteBt);
				try {
					OutputStream os = PartiePanel.this.soc.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(os);
					// oos.flush();
					CarteGentil g = (CarteGentil) PartiePanel.this.j.getPersonnage();
					g.setDort(false);
					oos.writeObject(PartiePanel.this.j);
					oos.flush();
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});

	}

	public PartiePanel(Socket soc2, Joueur joueur, ArrayList<Joueur> joueurs2) {
		this.soc = soc2;
		this.j = joueur;
		this.joueurs = joueurs2;

		setBackground(Color.WHITE);
		setLayout(null);

		tourNLbl = new JLabel("");
		tourNLbl.setBounds(0, 0, 800, 30);
		tourNLbl.setBackground(Color.WHITE);
		tourNLbl.setHorizontalAlignment(SwingConstants.CENTER);
		add(tourNLbl);

		JPanel autresJoueursPanel = new JPanel();
		autresJoueursPanel.setBounds(0, 30, 800, 300);
		autresJoueursPanel.setBackground(Color.WHITE);
		autresJoueursPanel.setLayout(null);

		Logs.insererLogINFO(j.toString());
		Logs.insererLogINFO(joueurs2.toString());

		int index = 0;
		Joueur joueurSuppr = j;

		for (Joueur joueur2 : joueurs) {
			if (joueur2.getPseudo().equals(j.getPseudo())) {
				index = joueurs2.indexOf(joueur2);
				joueurSuppr = joueur2;
			}
		}
		// joueurs.remove(joueurs2);

		// int index = joueurs.indexOf(j);
		joueurs.remove(index);

		boolean isLoup = false;
		if (joueurSuppr.getPersonnage() instanceof CarteLoup) {
			isLoup = true;
		}
		Logs.insererLogINFO(joueurs.get(0).getPersonnage().getNom());
		j1 = new VignetteJoueurPanel(joueurs.get(0), isLoup);
		j1.setBounds(33, 0, 120, 300);
		autresJoueursPanel.add(j1);

		j2 = new VignetteJoueurPanel(joueurs.get(1), isLoup);
		j2.setBounds(183, 0, 120, 300);
		autresJoueursPanel.add(j2);

		j3 = new VignetteJoueurPanel(joueurs.get(0), isLoup);
		if (joueurs.size() >= 3) {
			j3 = new VignetteJoueurPanel(joueurs.get(2), isLoup);
			j3.setBounds(339, 0, 120, 300);
			autresJoueursPanel.add(j3);
		}

		j4 = new VignetteJoueurPanel(joueurs.get(0), isLoup);
		if (joueurs.size() >= 4) {
			j4 = new VignetteJoueurPanel(joueurs.get(3), isLoup);
			j4.setBounds(493, 0, 120, 300);
			autresJoueursPanel.add(j4);
		}

		j5 = new VignetteJoueurPanel(joueurs.get(0), isLoup);
		if (joueurs.size() == 5) {
			j5 = new VignetteJoueurPanel(joueurs.get(4), isLoup);
			j5.setBounds(645, 0, 120, 300);
			autresJoueursPanel.add(j5);
		}

		joueurs.add(index, joueurSuppr);
		j = joueurSuppr;

		add(autresJoueursPanel);

		JPanel joueurPanel = new JPanel();
		joueurPanel.setBounds(0, 330, 800, 300);
		joueurPanel.setBackground(Color.WHITE);
		add(joueurPanel);
		joueurPanel.setLayout(null);

		JPanel profilPanel = new JPanel();
		profilPanel.setBounds(30, 10, 180, 200);
		profilPanel.setBackground(Color.WHITE);
		profilPanel.setLayout(null);
		joueurPanel.add(profilPanel);

		JPanel nomPoints2Panel = new JPanel();
		nomPoints2Panel.setBackground(Color.WHITE);
		nomPoints2Panel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLUE));
		nomPoints2Panel.setBounds(10, 0, 170, 40);
		profilPanel.add(nomPoints2Panel);
		nomPoints2Panel.setLayout(null);

		pseudo2Lbl = new JLabel(j.getPseudo());
		pseudo2Lbl.setHorizontalAlignment(SwingConstants.CENTER);
		pseudo2Lbl.setBounds(0, 0, 170, 20);
		nomPoints2Panel.add(pseudo2Lbl);

		nbPoints2Lbl = new JLabel("x points");
		setNbPoints2Lbl(joueurSuppr.getNbPoint());
		nbPoints2Lbl.setHorizontalAlignment(SwingConstants.CENTER);
		nbPoints2Lbl.setBounds(0, 20, 170, 20);
		nomPoints2Panel.add(nbPoints2Lbl);

		image2Panel = new JPanel();
		image2Panel.setBounds(10, 40, 170, 170);
		profilPanel.add(image2Panel);
		image2Panel.setLayout(new BorderLayout(0, 0));

		ImagePanel img2 = null;
		String nomPerso = joueurSuppr.getPersonnage().getNom();
		if (nomPerso.equals("Loup")) {
			img2 = new ImagePanel(getImageFromName("loupGRAND.jpg"));
		} else if (nomPerso.equals("Mere Cochon")) {
			img2 = new ImagePanel(getImageFromName("mere cochonGRAND.jpg"));
		} else if (nomPerso.equals("Petit Cochon")) {
			img2 = new ImagePanel(getImageFromName("petit cochonGRAND.jpg"));
		} else if (nomPerso.equals("Chaperon Rouge")) {
			img2 = new ImagePanel(getImageFromName("chaperon rougeGRAND.jpg"));
		}

		image2Panel.add(img2);

		JPanel actionPanel = new JPanel();
		actionPanel.setBounds(280, 0, 440, 240);
		actionPanel.setBackground(Color.WHITE);
		joueurPanel.add(actionPanel);
		actionPanel.setLayout(null);

		infoLbl = new JLabel("New label");
		infoLbl.setBounds(0, 0, 418, 60);
		infoLbl.setHorizontalAlignment(SwingConstants.CENTER);
		infoLbl.setBackground(Color.WHITE);
		actionPanel.add(infoLbl);

		infoActionLbl = new JLabel("");
		infoActionLbl.setBounds(100, 180, 418, 30);
		infoActionLbl.setBackground(Color.WHITE);
		actionPanel.add(infoActionLbl);

		boutonsPanel = new JPanel();
		boutonsPanel.setBounds(0, 210, 418, 60);
		boutonsPanel.setBackground(Color.WHITE);
		actionPanel.add(boutonsPanel);

		maisonGaucheBt = new JButton(new ImageIcon(getImageFromName("litMINI.jpg")));
		maisonDroiteBt = new JButton(new ImageIcon(getImageFromName("piegeMINI.jpg")));
		gaucheBt = new JButton("Distribuer");
		droitBt = new JButton("Valider");

		if (joueurSuppr.getPersonnage() instanceof CarteLoup) {
			infoLbl.setText("Veuillez patienter");
			loupThread = new AuLoupDeJouerThread(soc, this);
			loupThread.start();

		} else {
			infoLbl.setText("Choisissez votre maison");

			btGauchePanel = new JPanel();
			btGauchePanel.setBounds(80, 60, 120, 120);
			btGauchePanel.setBackground(Color.WHITE);
			actionPanel.add(btGauchePanel);
			btGauchePanel.setLayout(null);

			maisonGaucheBt.setBounds(5, 5, 110, 110);
			btGauchePanel.add(maisonGaucheBt);

			btDroitPanel = new JPanel();
			btDroitPanel.setBackground(Color.WHITE);
			btDroitPanel.setBounds(220, 60, 120, 120);
			actionPanel.add(btDroitPanel);
			btDroitPanel.setLayout(null);

			maisonDroiteBt.setBounds(5, 5, 110, 110);
			btDroitPanel.add(maisonDroiteBt);
		}

		// gestion �v�nements des boutons des vignettes
		j1.getPersoBt().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionButton(j1);
			}
		});

		j2.getPersoBt().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionButton(j2);
			}
		});

		j3.getPersoBt().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionButton(j3);
			}
		});

		j4.getPersoBt().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionButton(j4);
			}
		});

		j5.getPersoBt().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionButton(j5);
			}
		});
		
		getGaucheBt().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fr.affDistribThread.interrupt();
				fr.afficherDistribPanel(joueurs);
			}
		});

		maisonGaucheBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				maisonBt(maisonGaucheBt);
				try {
					OutputStream os = PartiePanel.this.soc.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(os);
					// oos.flush();
					CarteGentil g = (CarteGentil) PartiePanel.this.j.getPersonnage();
					g.setDort(true);
					oos.writeObject(PartiePanel.this.j);
					oos.flush();
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		maisonDroiteBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				maisonBt(maisonDroiteBt);
				try {
					OutputStream os = PartiePanel.this.soc.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(os);
					// oos.flush();
					CarteGentil g = (CarteGentil) PartiePanel.this.j.getPersonnage();
					g.setDort(false);
					oos.writeObject(PartiePanel.this.j);
					oos.flush();
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});

	}

	public void maisonBt(JButton bt) {
		if (bt == maisonGaucheBt) {
			btGauchePanel.setBackground(Color.BLUE);
			infoActionLbl.setText("Votre choix : dormir");
		} else if (bt == maisonDroiteBt) {
			btDroitPanel.setBackground(Color.BLUE);
			infoActionLbl.setText("Votre choix : le piege");
		}
		maisonGaucheBt.setEnabled(false);
		maisonDroiteBt.setEnabled(false);
		infoLbl.setText("Veuillez attendre.");
		pointsThread = new RecupPointsTourThread(soc, fr);
		pointsThread.start();
	}

	public void setBtGauchePanel() {

	}

	public void actionButton(VignetteJoueurPanel j) {
		/*
		 * j1.getCadreBtPersoPanel().setBackground(Color.WHITE);
		 * j2.getCadreBtPersoPanel().setBackground(Color.WHITE);
		 * j3.getCadreBtPersoPanel().setBackground(Color.WHITE);
		 * j4.getCadreBtPersoPanel().setBackground(Color.WHITE);
		 * j5.getCadreBtPersoPanel().setBackground(Color.WHITE); if(j == j1){
		 * j1.getCadreBtPersoPanel().setBackground(Color.BLUE);
		 * infoActionLbl.setText("Votre victime : " + j1.getPseudo()); } else
		 * if(j == j2){ j2.getCadreBtPersoPanel().setBackground(Color.BLUE);
		 * infoActionLbl.setText("Votre victime : " + j2.getPseudo()); } else
		 * if(j == j3){ j3.getCadreBtPersoPanel().setBackground(Color.BLUE);
		 * infoActionLbl.setText("Votre victime : " + j3.getPseudo()); } else
		 * if(j == j4){ j4.getCadreBtPersoPanel().setBackground(Color.BLUE);
		 * infoActionLbl.setText("Votre victime : " + j4.getPseudo()); } else
		 * if(j == j5){ j5.getCadreBtPersoPanel().setBackground(Color.BLUE);
		 * infoActionLbl.setText("Votre victime : " + j5.getPseudo()); }
		 */

		j.getCadreBtPersoPanel().setBackground(Color.BLUE);
		j1.getPersoBt().setEnabled(false);
		j2.getPersoBt().setEnabled(false);
		j3.getPersoBt().setEnabled(false);
		j4.getPersoBt().setEnabled(false);
		j5.getPersoBt().setEnabled(false);
		loupThread.interrupt();
		pointsThread = new RecupPointsTourThread(soc, fr);

		Joueur joueur = j.getJoueur();
		try {
			OutputStream os = PartiePanel.this.soc.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			// oos.flush();
			oos.writeObject(joueur);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void setPseudo2(String pseudo) {
		pseudo2Lbl.setText(pseudo);
	}

	public void setNbPoints2Lbl(int nbPoints2) {
		if (nbPoints2 < 2) {
			nbPoints2Lbl.setText(nbPoints2 + " point");
		} else {
			nbPoints2Lbl.setText(nbPoints2 + " points");
		}
	}

	public void setImage2(String file) {
		image2Panel.removeAll();
		image2Panel = new ImagePanel(getImageFromName(file));
		image2Panel.revalidate();
		image2Panel.repaint();
	}

	public Image getImageFromName(String nomFichier) {
		InputStream i = this.getClass().getClassLoader().getResourceAsStream(nomFichier);
		Image logo = null;
		try {
			logo = ImageIO.read(i);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return logo;
	}

	public void setTourN(int tourN) {
		tourNLbl.setText("Tour " + tourN);
	}

	public void setGainPerso(int gain) {
		if (gain < -1) {
			infoLbl.setText("Perdu : " + gain + " points");
		} else if (gain == -1) {
			infoLbl.setText("Perdu : " + gain + " point");
		} else if (gain == 0) {
			infoLbl.setText(gain + " point");
		} else if (gain == 1) {
			infoLbl.setText("Gagne : " + gain + " point");
		} else if (gain > 1) {
			infoLbl.setText("Gagne : " + gain + " points");
		}
	}

	public void setInfo(String text) {
		infoLbl.setText(text);
	}

	public void setActionLbl(String text) {
		infoActionLbl.setText(text);
	}

	public void setImageMaison(String file) {

	} 

	public void auLoupDeJouer() {
		loupThread.interrupt();
		j1.auLoupDeJouer();
		j2.auLoupDeJouer();
		j3.auLoupDeJouer();
		j4.auLoupDeJouer();
		j5.auLoupDeJouer();
		infoLbl.setText("Choisissez une victime");
	}

	public void affichagePointsTour(ArrayList<Joueur> joueurs) {
		pointsThread.interrupt();
		int index = 0;
		Joueur joueur = null;
		
		for (int i = 0; i < joueurs.size(); i++) {
			if (j.getPseudo().equals(joueurs.get(i).getPseudo())) {
				index = i;
				joueur = joueurs.get(i);
				int gain = joueur.getNbPoint();
				if (gain < 1) {
					infoLbl.setText("Perdu : " + gain + " points");
					afficherDistribThread = new AfficherPageDistributionThread(soc, fr);
					afficherDistribThread.start();
					boutonsPanel.removeAll();
					boutonsPanel.setBounds(0, 210, 418, 60);
					boutonsPanel.setBackground(Color.WHITE);
					boutonsPanel.add(gaucheBt);
					boutonsPanel.repaint();
				} else if (gain == 0) {
					infoLbl.setText("" + gain + " point");
				} else if (gain == 1) {
					infoLbl.setText("Gagne ! +" + gain + " point");
				} else if (gain > 1) {
					infoLbl.setText("Gagne ! +" + gain + " points");
				}
				
				if(gain >= 0){
					infoActionLbl.setText("Veuillez patienter le temps de la distribution des cartes.");
				}
				joueurs.remove(i);
			}
		}
		
		for (Joueur j : joueurs) {
			int point = j.getNbPoint();

			for (VignetteJoueurPanel v : vignettes) {
				if(v.getPseudo().equals(j.getPseudo())){
					v.setGain(point);
				}
			}

		}

		joueurs.add(index, joueur);

	}
	
	
	public JFrame getFr() {
		return fr;
	}

	public void setFr(Frame fr) {
		this.fr = fr;
	}
	
	
	
}