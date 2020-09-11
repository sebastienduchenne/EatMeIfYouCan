package Serveur;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import classeMetier.Joueur;

public class ConnectionThread extends Thread {

	private ServerSocket ss;
	private ArrayList<SocketToAllSocketThread> stas;
	private ArrayList<Socket> socs;
	private ArrayList<Joueur> joueursParties;
	private ArrayList<ArrayList<Joueur>> listePartiesJoueurs;
	private ArrayList<ArrayList<Socket>> listeSocs;

	public ConnectionThread(ServerSocket ss, ArrayList<SocketToAllSocketThread> stas, ArrayList<Joueur> joueurs,
			ArrayList<ArrayList<Joueur>> listePartiesJoueurs) {
		this.ss = ss;
		this.stas = stas;
		socs = new ArrayList<Socket>();
		this.joueursParties = joueurs;
		this.listePartiesJoueurs = listePartiesJoueurs;
		this.listeSocs = new ArrayList<>();
	}

	public void run() {
		try {

			while (!this.isInterrupted()) {

				/*
				 * COnnection des CLients au serveur
				 */
				Socket soc = ss.accept();
				Logs.insererLogINFO("Connexion");
				socs.add(soc);
			
				VerificationThread vt = new VerificationThread(soc, joueursParties, listePartiesJoueurs, listeSocs);
				vt.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
