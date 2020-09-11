package Serveur;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import classeMetier.CarteGentil;
import classeMetier.CarteLoup;
import classeMetier.Joueur;
import classeMetier.Tour;

public class PartieThread extends Thread {

	private String theme;
	private ArrayList<Tour> tours;
	private Joueur gagnant;
	private ArrayList<Joueur> joueurs;
	private CarteLoup loup;
	private ArrayList<CarteGentil> gentils;
	private ArrayList<Socket> socs;
	// private Socket soc;

	public PartieThread(Socket soc, ArrayList<Joueur> j, ArrayList<Socket> socs) {
		// this.soc = soc;
		this.socs = socs;
		this.joueurs = j;
		this.tours = new ArrayList<Tour>();
		this.loup = new CarteLoup();
		this.gentils = new ArrayList<CarteGentil>();
		gentils.add(new CarteGentil(3, "Chaperon Rouge")); // chaperon rouge
		gentils.add(new CarteGentil(2, "Mere Cochon")); // mère cochon
		gentils.add(new CarteGentil(1, "Petit Cochon")); // petit cochon

		for (int i = 0; i < j.size() - 3; i++) { // petit cochon qui dépend du
													// nombre de joueurs
			gentils.add(new CarteGentil());
		}

	}

	public void run() {
		OutputStream os;

		while (this.gagnant == null) { // début d'un tour
			Tour tourEnCours = new Tour();
			tours.add(tourEnCours);

			Logs.insererLogINFO("PartieThread");

			if (this.tours.size() == 1) {
				Logs.insererLogINFO("PremiereDistribution");
				distribution();
			} else {
				distributionThread dt = new distributionThread(tours.get(tours.size() - 2).getPerdant(), joueurs, socs);
				dt.start();
			}

			try {
				for (Socket soc : socs) {

					os = soc.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(os);

					oos.writeObject(joueurs);
					oos.flush();

				}

			} catch (IOException e) {
				e.printStackTrace();
			}

			actiongentil();
			actionLoup();
			// les gentils choisissent une carte maison
			// le loup choisit un gentil

			attribuerPoints(tourEnCours);

			determinerGagnantTour(tourEnCours);

			

		}

		finDePartie();

	}

	private void actionLoup() {
		Joueur jRecep;
		try {

			for (Socket soc : socs) {
				if (joueurs.get(socs.indexOf(soc)).getPersonnage() instanceof CarteLoup) {

					InputStream is = soc.getInputStream();
					ObjectInputStream ois = new ObjectInputStream(is);

					jRecep = (Joueur) ois.readObject();
					this.loup = (CarteLoup) joueurs.get(socs.indexOf(soc)).getPersonnage();
					loup.setChoix(jRecep);
					joueurs.get(socs.indexOf(soc)).setPersonnage(loup);
				}
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void actiongentil() {
		Joueur jRecep;

		try {
			for (Socket soc : socs) {
				if (joueurs.get(socs.indexOf(soc)).getPersonnage() instanceof CarteGentil) {

					InputStream is = soc.getInputStream();
					ObjectInputStream ois = new ObjectInputStream(is);

					jRecep = (Joueur) ois.readObject();
					joueurs.set(socs.indexOf(soc), jRecep);
				}
			}
			int index = 0;

			for (Joueur jCour : joueurs) {
				if (jCour.getPersonnage() instanceof CarteLoup) {
					index = joueurs.indexOf(jCour);
				}
			}

			for (Socket soc : socs) {

				if (socs.indexOf(soc) == index) {

					OutputStream os = soc.getOutputStream();
					ObjectOutputStream ois = new ObjectOutputStream(os);
					ois.flush();
					ois.writeObject("au loup de jouer");

				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void finDePartie() {
		try {
			for (Socket soc : socs) {

				OutputStream os = soc.getOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(os);
				
				// j'ajoute en indice 0 un joueur avec en pseudo une clé d'identification
				// puis en indice 1 le gagnant
				//ces 2 joueurs seront retiré lors de leur réception
				joueurs.add(0, new Joueur("lbzibtjlmqizvbslqleb"));
				joueurs.add(1,gagnant);
				oos.writeObject(joueurs);
				oos.flush();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/*
	 * Distribution premier tour
	 */
	public void distribution() {
		for (Joueur joueur : joueurs) {
			joueur.setPersonnage(gentils.get(2));
		}

		int nbAleatoire = (int) (ThreadLocalRandom.current().nextInt(0, joueurs.size()));
		joueurs.get(nbAleatoire).setPersonnage(this.loup);

		boolean distribueMere = false;
		boolean distribueChaperon = false;

		for (Joueur joueur : joueurs) {
			if (!distribueMere && joueur.getPersonnage() != loup) {
				joueur.setPersonnage(gentils.get(1));
				distribueMere = true;
			}
			if (!distribueChaperon && joueur.getPersonnage() == gentils.get(2) && joueurs.size() > 3) {
				joueur.setPersonnage(gentils.get(0));
				distribueChaperon = true;
			}
		}
	}

	public void attribuerPoints(Tour t) {
		CarteGentil choix = (CarteGentil) this.loup.getChoix().getPersonnage();
		Joueur j;
		ArrayList<Joueur> gagnants = new ArrayList<>();
		if (choix.isDort()) { // si le loup gagne
			for (Joueur loupCourant : joueurs) {
				gagnants.add(loupCourant);
				if (loupCourant.getPersonnage() instanceof CarteLoup) {
					j = loupCourant;
					j.setNbPoint(j.getNbPoint() + choix.getValeur());
				}
				if (this.loup.getChoix().getPseudo().equals(loupCourant.getPseudo())) {
					if (loupCourant.getNbPoint() >= choix.getValeur()) {
						loupCourant.setNbPoint(loupCourant.getNbPoint() - choix.getValeur());
					} else {
						loupCourant.setNbPoint(0);
					}
					gagnants.remove(loupCourant);
					t.setPerdant(loupCourant);
				}
			}

		} else {

			for (Joueur loupCourant : joueurs) {
				gagnants.add(loupCourant);
				if (loupCourant.getPersonnage() instanceof CarteLoup) {
					j = loupCourant;
					if (choix.getValeur() <= loupCourant.getNbPoint()) {
						loupCourant.setNbPoint(loupCourant.getNbPoint() - choix.getValeur());
					} else {
						loupCourant.setNbPoint(0);
					}
					gagnants.remove(loupCourant);
					t.setPerdant(loupCourant);

				}
				if (this.loup.getChoix().getPseudo().equals(loupCourant.getPseudo())) {
					j = loupCourant;
					j.setNbPoint(j.getNbPoint() + choix.getValeur());

				}
			}				
			
		}
		
		for (Joueur loupCourant : joueurs) {
			if (loupCourant.getPersonnage() instanceof CarteGentil
					&& !(loupCourant.getPseudo().equals(this.loup.getChoix().getPseudo()))) {
				CarteGentil cg = (CarteGentil) loupCourant.getPersonnage();
				if (cg.isDort()) {
					loupCourant.setNbPoint(loupCourant.getNbPoint() + cg.getValeur());
				}

			}
		}
		
		t.setGagnantsTour(gagnants);
		
	}

	public void determinerGagnantTour(Tour tourEnCours) {
		// tableau des joueurs ayant >= 10 points
		ArrayList<Joueur> supOuEgalA10 = new ArrayList<Joueur>();
		for (Joueur j : tourEnCours.getGagnantsTour()) { // pour chaque gagnant
															// du tour
			if (j.getNbPoint() >= 10) {
				supOuEgalA10.add(j);
			}
		}

		// détermination du gagnant
		if (supOuEgalA10.size() > 1) {
			// on cherche le joueur qui plus de point que les autres
			this.gagnant = joueurMostPoint(supOuEgalA10);

			// on cherche si le loup ou son choix a gagné
			if (this.gagnant != null) {
				for (Joueur j : supOuEgalA10) {
					if ((j.getPersonnage() == this.loup) || (j == this.loup.getChoix())) {
						this.gagnant = j;
					}
				}
			}

			// s'il n'y a pas d'embuscade, on cherche celui qui est le plus
			// proche du loup dans le sens horaire
			if (this.gagnant == null) {
				this.gagnant = supOuEgalA10.get(0);
				for (Joueur j : supOuEgalA10) {
					int position = j.getPersonnage().getPosition();
					if (position > this.loup.getPosition() && position < this.gagnant.getPersonnage().getPosition()) {
						this.gagnant = j;
					}
				}
			}
		} else if (supOuEgalA10.size() == 1) {
			this.gagnant = supOuEgalA10.get(0);
		}
		
		
	}

	public Joueur joueurMostPoint(ArrayList<Joueur> supOuEgalA10) {
		Joueur j = null;
		int greatestPoint = 0;
		int many = 0;

		for (Joueur joueur : supOuEgalA10) {// recherche des points les plus
											// élevés
			if (joueur.getNbPoint() > j.getNbPoint()) {
				greatestPoint = joueur.getNbPoint();
				j = joueur;
			}
		}

		// recherche du nombre de joueurs qui ont autant que greatestPoint
		for (Joueur joueur : supOuEgalA10) {
			if (joueur.getNbPoint() == greatestPoint) {
				many++;
			}
		}

		if (many > 1) {
			j = null;
		}

		return j;
	}

}