package Serveur;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

import classeMetier.Joueur;

public class MenuPrincipalThread extends Thread {
	private Joueur joueur;
	private Socket soc;
	private ArrayList<ArrayList<Joueur>> listePartiesJoueurs;
	private ArrayList<ArrayList<Socket>> listeSocs;
	private ArrayList<Joueur> listeJoueurs;

	public MenuPrincipalThread(Socket soc, Joueur joueur, ArrayList<Joueur> listeJoueurs, ArrayList<ArrayList<Joueur>> listePartiesJoueurs, ArrayList<ArrayList<Socket>> listeSocs) {
		this.joueur = joueur;
		this.soc = soc;
		this.listePartiesJoueurs = listePartiesJoueurs;
		this.listeSocs = listeSocs;
		this.listeJoueurs = listeJoueurs;

	}

	public void run() {

		InputStream is;
		try {
			
			is = soc.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);

			String choixMenu = (String) ois.readObject();
			switch (choixMenu.charAt(0)) {
			case '1':
				ois = new ObjectInputStream(is);
				int nbJoueur = (Integer) ois.readObject();
				Logs.insererLogINFO("vers Lobby" + nbJoueur);
				LobbyThread lt = new LobbyThread(joueur, listePartiesJoueurs, listeJoueurs, soc, listeSocs, nbJoueur);
				lt.start();
				break;
			// case "2":
			// StatThread st = new StatThread();
			// st.start();
			// break;
			case '3':
				DecoThread dt = new DecoThread(joueur, listePartiesJoueurs, listeJoueurs, soc, listeSocs);
				// System.out.println(joueur.getPseudo().toString());
				dt.start();
				break;
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
