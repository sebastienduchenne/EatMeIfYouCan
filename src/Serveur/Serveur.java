package Serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import classeMetier.Joueur;

public class Serveur {

	public static void main(String[] args) throws IOException {
		
		Logs logJournal = new Logs();
		Logs.insererLogINFO("Instanciation Serveur");
		ServerSocket ss = new ServerSocket(8080);
		ArrayList<SocketToAllSocketThread> stas = new ArrayList<SocketToAllSocketThread>();
		
		
		
		ArrayList<Joueur> joueurs = new ArrayList<>();
		ArrayList<ArrayList<Joueur>> listePartiesJoueurs = new ArrayList<>();

		ConnectionThread threadAccept = new ConnectionThread(ss, stas, joueurs, listePartiesJoueurs);
		threadAccept.start();

		/*
		 * Boucle de lancement des parties voir si on met dans un Thread a part
		 * pour pouvoir continuer de actions dans le serveur.
		 */
		

		// il faut aussi mettre les parties dans un thread

		/*
		 * Joueur j1 = new Joueur("j1"); Joueur j2 = new Joueur("j2"); Joueur j3
		 * = new Joueur("j3"); Joueur j4 = new Joueur("j4");
		 * 
		 * ArrayList<Joueur> joueurs = new ArrayList<Joueur>(); joueurs.add(j1);
		 * joueurs.add(j2); joueurs.add(j3); joueurs.add(j4);
		 */

		// PartieThread partie = new PartieThread(joueurs);

	}

}
