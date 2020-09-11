package Interface;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;

import Serveur.Logs;
import classeMetier.CarteGentil;
import classeMetier.CarteLoup;
import classeMetier.Joueur;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;

import classeMetier.CarteGentil;
import classeMetier.CarteLoup;
import classeMetier.Joueur;

public class Frame extends JFrame {

	private ConnexionPanel connexionPanel;

	private InscriptionPanel inscriptionPanel;
	private MenuPrincipalPanel menuPanel;
	private NouvellePartiePanel newPartiePanel;
	private PartiePanel partiePanel;
	private DistributionPanel distribPanel;
	private StatistiquesPanel statPanel;
	private FinPartiePanel finPartiePanel;
	

	private Socket soc;
	private Frame fr = this;
	private Joueur joueur;
	
	private ArrayList<Joueur> joueurs;
	
	

	AfficherPageDistributionThread affDistribThread;
	

	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame window = new Frame();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Frame() throws UnknownHostException, IOException {
		this.joueur = new Joueur("", "");
		System.out.println(joueur.toString());
		//this.soc = new Socket("localhost", 8080);//pour le moment local a changer
	

		setBounds(200, 50, 1000, 600); // position et taille de la fenetre à
										// l'ecran
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Eat me if you can !");
		editerFenetre();

		connexionPanel = new ConnexionPanel(this.soc, this.fr, this.joueur);
		getContentPane().add(connexionPanel);

		connexionPanel.getBtSInscrire().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OutputStream os;
				try {
					os = soc.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(os);
					oos.flush();

					oos.writeObject(new String("test"));
					oos.flush();

					setInscriptionPanel(inscriptionPanel);
					getContentPane().add(getInscriptionPanel());
					getInscriptionPanel().setLayout(new GridLayout(0, 1, 0, 0));
					setContentPane(getInscriptionPanel());
					editerFenetre();
					revalidate();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		connexionPanel.getBtSeConnecter().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String pseudo = connexionPanel.getTextPseudo();
				String mdp = connexionPanel.getTextPassword();// getTextPassword();
				Joueur joueur2 = new Joueur(pseudo, mdp);

				try {
					OutputStream os = soc.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(os);
					oos.flush();

					oos.writeObject(joueur2);
					oos.flush();
					// oos.close();

					InputStream is = soc.getInputStream();
					ObjectInputStream ois = new ObjectInputStream(is);

					joueur = (Joueur) ois.readObject();
					
					Logs.insererLogINFO("TestJoueur Connexion "+ joueur.toString());
					// ois.close();
					if (joueur.getConnect()) {

						menuPanel.setJoueur(joueur);
						menuPanel.setSoc(soc);
						setMenuPanel(menuPanel);
						getContentPane().add(getMenuPanel());
						getMenuPanel().setLayout(new GridLayout(0, 1, 0, 0));
						setContentPane(getMenuPanel());
						editerFenetre();
						revalidate();
					}

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		menuPanel = new MenuPrincipalPanel(this.joueur, this.soc, this.fr);

		menuPanel.getRejoindrePartieBt().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Logs.insererLogINFO("test");

				try {
					Logs.insererLogINFO("test2");
					OutputStream os = soc.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(os);
					oos.flush();
					oos.writeObject(new String("1"));
					oos.flush();
					// oos.close();

					// System.out.println("test");

					setNewPartiePanel(newPartiePanel = new NouvellePartiePanel(joueur, soc, fr));
					getContentPane().add(getNewPartiePanel());

					setContentPane(newPartiePanel);
					// editerFenetre();
					revalidate();
					// repaint();
					// newPartiePanel.repaint();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		menuPanel.getStatBt().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					OutputStream os = soc.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(os);
					oos.flush();

					oos.writeObject(new String("2"));
					oos.flush();
					// oos.close();

					// System.out.println("test");

					// setNewPartiePanel();
					// getContentPane().add();
					// getNewPartiePanel().setLayout(new GridLayout(0, 1, 0,
					// 0));
					// setContentPane(fr.getNewPartiePanel());
					// editerFenetre();
					// revalidate();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		menuPanel.getStatBt().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				afficherStatistiquesPanel(joueur);
			}
		});

		menuPanel.deconnexionBt().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					// Envoyer au serveur que le mec se deco
					OutputStream os = soc.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(os);
					oos.flush();

					oos.writeObject(new String("3"));
					oos.flush();
					// oos.close();

					// System.out.println("test");

					// On rappelle le connexion panel
					setConnexionPanel(connexionPanel);
					getContentPane().add(getConnexionPanel());
					getConnexionPanel().setLayout(new GridLayout(0, 1, 0, 0));
					setContentPane(connexionPanel);
					editerFenetre();
					revalidate();
					repaint();
					connexionPanel.repaint();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		inscriptionPanel = new InscriptionPanel(soc, fr);

		inscriptionPanel.getBtnNewButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String champsManquant = "Tous les champs sont obligatoires";
				String pseudoDejaExistant = "Ce pseudo n'est plus disponible";

				String pseudo = inscriptionPanel.getTextPseudo();
				String mdp = inscriptionPanel.getTextPassword();// getTextPassword();
				Joueur joueur2 = new Joueur(pseudo, mdp);

				try {
					OutputStream os = soc.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(os);
					oos.flush();

					oos.writeObject(joueur2);
					oos.flush();
					// oos.close();

					// System.out.println("test");
					connexionPanel.setJoueur(joueur2);
					connexionPanel.setSoc(soc);
					setConnexionPanel(connexionPanel);
					getContentPane().add(getConnexionPanel());
					getConnexionPanel().setLayout(new GridLayout(0, 1, 0, 0));
					setContentPane(getConnexionPanel());
					editerFenetre();
					revalidate();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		inscriptionPanel.getBtnNewButton_1().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				OutputStream os;
				try {
					os = soc.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(os);
					oos.flush();

					oos.writeObject("");
					oos.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				setConnexionPanel(connexionPanel);
				getContentPane().add(getConnexionPanel());
				getConnexionPanel().setLayout(new GridLayout(0, 1, 0, 0));
				setContentPane(getConnexionPanel());
				editerFenetre();
				revalidate();
			}
		});
		

		
		
		statPanel = new StatistiquesPanel(joueur);
		statPanel.getMenuPrincipalBt().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMenuPanel(menuPanel);
				getContentPane().add(getMenuPanel());
				getMenuPanel().setLayout(new GridLayout(0, 1, 0, 0));
				setContentPane(getMenuPanel());
				editerFenetre();
				revalidate();
			}
		});
		
		//finPartiePanel = new FinPartiePanel(joueurs);
//		finPartiePanel.getMenuPrincipalBt().addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				setMenuPanel(menuPanel);
//				getContentPane().add(getMenuPanel());
//				getMenuPanel().setLayout(new GridLayout(0, 1, 0, 0));
//				setContentPane(getMenuPanel());
//				editerFenetre();
//				revalidate();
//			}
//		});


		newPartiePanel = new NouvellePartiePanel(joueur, soc, fr);

		//affDistribThread = new AfficherPageDistributionThread(soc, fr);
		//affDistribThread.start();
		
		//setPartiePanel(new PartiePanel());

	}

	public void editerFenetre() {
		setBackground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);
		setForeground(Color.WHITE);
		getContentPane().setForeground(Color.WHITE);
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
	}

	public NouvellePartiePanel getNewPartiePanel() {
		return newPartiePanel;
	}

	public void setNewPartiePanel(NouvellePartiePanel newPartiePanel) {
		this.newPartiePanel = newPartiePanel;
	}

	public InscriptionPanel getInscriptionPanel() {
		return inscriptionPanel;
	}

	public void setInscriptionPanel(InscriptionPanel inscriptionPanel) {
		this.inscriptionPanel = inscriptionPanel;
	}

	public MenuPrincipalPanel getMenuPanel() {
		return menuPanel;
	}

	public void setMenuPanel(MenuPrincipalPanel menuPanel) {
		this.menuPanel = menuPanel;
	}

	public PartiePanel getPartiePanel() {
		return partiePanel;
	}

	public void setPartiePanel(PartiePanel partiePanel) {
		this.partiePanel = partiePanel;
		partiePanel.setFr(this);
	}
	
	public ConnexionPanel getConnexionPanel() {
		return connexionPanel;
	}

	public void setConnexionPanel(ConnexionPanel connexionPanel) {
		this.connexionPanel = connexionPanel;
	}
	
	public StatistiquesPanel getStatPanel() {
		return statPanel;
	}

	public void setStatPanel(StatistiquesPanel statPanel) {
		this.statPanel = statPanel;
	}
	

	public void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}
	
	public void afficherDistribPanel(ArrayList<Joueur> joueurs){
		distribPanel = new DistributionPanel(soc, joueur, joueurs, this);
		getContentPane().add(distribPanel);
		distribPanel.setLayout(new GridLayout(0, 1, 0, 0));
		setContentPane(distribPanel);
		editerFenetre();
		revalidate();
		
	}
	
	public void afficherPartiePanel(ArrayList<Joueur> joueurs, RecupJoueursNouvellePartieThread th){
		th.interrupt();
		partiePanel = new PartiePanel(soc, joueur, joueurs);
		partiePanel.setFr(this);
		getContentPane().add(partiePanel);
		partiePanel.setLayout(new GridLayout(0, 1, 0, 0));
		setContentPane(partiePanel);
		editerFenetre();
		revalidate();
	}
	
	public void afficherStatistiquesPanel(Joueur joueur){
		statPanel = new StatistiquesPanel(joueur);
		getContentPane().add(statPanel);
		statPanel.setLayout(new GridLayout(0, 1, 0, 0));
		setContentPane(statPanel);
		editerFenetre();
		revalidate();
	}
	
	public void afficherFinDePartiePanel(ArrayList<Joueur> joueurs, Thread th){
		th.interrupt();
		finPartiePanel = new FinPartiePanel(joueurs);
		getContentPane().add(finPartiePanel);
		finPartiePanel.setLayout(new GridLayout(0, 1, 0, 0));
		setContentPane(finPartiePanel);
		editerFenetre();
		revalidate();
	}

}
