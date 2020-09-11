package Serveur;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

import classeMetier.Joueur;

public class InscriptionThread extends Thread {

	private Socket soc;
	private ArrayList<Joueur> joueurs;
	private ArrayList<ArrayList<Joueur>> listePartiesJoueurs;
	private ArrayList<ArrayList<Socket>> listeSocs;

	public InscriptionThread(Socket soc, ArrayList<ArrayList<Socket>> listeSocs, ArrayList<Joueur> joueursParties,
			ArrayList<ArrayList<Joueur>> listePartiesJoueurs) {
		this.soc = soc;
		this.joueurs = joueursParties;
		this.listePartiesJoueurs = listePartiesJoueurs;
		this.listeSocs = listeSocs;
	}

	public void run() {
		InputStream is;
		try {
			is = soc.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);

			Joueur joueur = new Joueur("", "");

			Object o = ois.readObject();

			if (o instanceof Joueur) {
				joueur = (Joueur) o;

				joueur.Inscription();

			}

			VerificationThread vt = new VerificationThread(soc, joueurs, listePartiesJoueurs, listeSocs);
			vt.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
