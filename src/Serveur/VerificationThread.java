package Serveur;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import classeMetier.Joueur;

public class VerificationThread extends Thread {

	private Socket soc;
	private ArrayList<Joueur> listeJoueurs;
	private ArrayList<ArrayList<Joueur>> listePartiesJoueurs;
	private ArrayList<ArrayList<Socket>> listeSocs;

	public VerificationThread(Socket soc, ArrayList<Joueur> joueursParties,
			ArrayList<ArrayList<Joueur>> listePartiesJoueurs, ArrayList<ArrayList<Socket>> listeSocs) {
		this.soc = soc;
		this.listeJoueurs = joueursParties;
		this.listePartiesJoueurs = listePartiesJoueurs;
		this.listeSocs = listeSocs;
	}

	public void run() {

		/*
		 * Recup�ration des infos de connexions via InputStream
		 */
		try {
			InputStream is = soc.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);

			Joueur joueur = new Joueur("", "");

			Object o = ois.readObject();
			// ois.close();

			if (o instanceof Joueur) {
				joueur = (Joueur) o;
				/*
				 * Verif en base
				 */

				joueur.Authentification();
				// joueur.setSoc(soc);
				OutputStream os = soc.getOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(os);
				oos.flush();
				oos.writeObject(joueur);
				oos.flush();

				if (joueur.getConnect()) {
					listeJoueurs.add(joueur);
					Logs.insererLogINFO("vers Menu");
					MenuPrincipalThread mpt = new MenuPrincipalThread(soc, joueur, listeJoueurs, listePartiesJoueurs, listeSocs);
					mpt.start();
				} else {
					Logs.insererLogINFO("Vers Connec");
					VerificationThread vt = new VerificationThread(soc, listeJoueurs, listePartiesJoueurs, listeSocs);
					vt.start();
				}

			}
			if (o instanceof String) {
				Logs.insererLogINFO("Vers Inscription");
				InscriptionThread it = new InscriptionThread(soc, listeSocs, listeJoueurs, listePartiesJoueurs);
				it.start();
			}

			/*
			 * Lancement du Thread de cr�ation de groupe pour les parties
			 */

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
