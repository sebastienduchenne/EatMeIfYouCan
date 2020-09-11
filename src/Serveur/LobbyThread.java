package Serveur;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketOption;
import java.util.ArrayList;

import classeMetier.Joueur;

public class LobbyThread extends Thread {

	private Joueur joueur;
	private ArrayList<ArrayList<Joueur>> listePartiesJoueurs;
	private Socket soc;
	private ArrayList<ArrayList<Socket>> listeSocs;
	private int nbJoueur;

	public LobbyThread(Joueur joueur, ArrayList<ArrayList<Joueur>> listePartiesJoueurs, ArrayList<Joueur> listeJoueurs,
			Socket soc, ArrayList<ArrayList<Socket>> listeSocs, int nbJoueur) {
		this.joueur = joueur;
		this.listePartiesJoueurs = listePartiesJoueurs;
		this.soc = soc;
		this.listeSocs = listeSocs;
		this.nbJoueur = nbJoueur;
	}

	public void run() {

		boolean ajout = false;

		/*
		 * Si il exiuste des groupes déja existant.
		 */
		try {

			joueur.setTaillePartie(nbJoueur);

			/*
			 * On parcourt les groupes voir si il ya de la place pour une partie
			 * de la taille choisit
			 */
			for (ArrayList<Joueur> listeCourante : listePartiesJoueurs) {
				if (listeCourante.size() < joueur.getTaillePartie()
						&& listeCourante.get(0).getTaillePartie() == joueur.getTaillePartie()) {
					listeCourante.add(joueur);
					listeSocs.get(listePartiesJoueurs.indexOf(listeCourante)).add(soc);
					ajout = true;
				}

				if (!listeCourante.get(0).isInGame()
						&& listeCourante.get(0).getTaillePartie() == listeCourante.size()) {

					PartieThread pt = new PartieThread(soc,listeCourante,
							listeSocs.get(listePartiesJoueurs.indexOf(listeCourante)));
					pt.start();
				}
			}
			/*
			 * Sinon on créer un nouveau groupe de la taille choisit
			 */
			if (ajout == false) {
				ArrayList<Joueur> newLobby = new ArrayList<>();
				newLobby.add(joueur);
				listePartiesJoueurs.add(newLobby);
				ArrayList<Socket> socs = new ArrayList<>();
				socs.add(soc);
				listeSocs.add(socs);
			}
		}
		/*
		 * Si il n'existe aucun groupe dans la liste.
		 */
		catch (NullPointerException ListeVide) {
			ArrayList<Joueur> newLobby = new ArrayList<>();
			newLobby.add(joueur);
			listePartiesJoueurs.add(newLobby);
			ArrayList<Socket> socs = new ArrayList<>();
			socs.add(soc);
			listeSocs.add(socs);
		}
	}
}
