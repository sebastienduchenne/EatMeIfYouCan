package Serveur;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

import classeMetier.Joueur;

public class DecoThread extends Thread {

	private Socket soc;
	private ArrayList<ArrayList<Joueur>> listePartiesJoueurs;
	private ArrayList<ArrayList<Socket>> listeSocs;
	private Joueur joueur;
	private ArrayList<Joueur> joueursParties;
	
	public DecoThread(Joueur joueur2, ArrayList<ArrayList<Joueur>> listePartiesJoueurs2, ArrayList<Joueur> joueursParties,  Socket soc2,
			ArrayList<ArrayList<Socket>> listeSocs2) {
		// TODO Auto-generated constructor stub
		this.joueur = joueur2;
		this.listePartiesJoueurs = listePartiesJoueurs2;
		this.soc = soc2;
		this.listeSocs = listeSocs2;
		this.joueursParties = joueursParties;
	}

	public void run() {
		
		try {
			joueur.deconnexion();
			joueur.setConnect(false);
			//System.out.println(joueursParties.toString());
			
			joueursParties.remove(joueur);
			
			//System.out.println(joueursParties.toString());
			
			VerificationThread vt = new VerificationThread(soc, joueursParties, listePartiesJoueurs, listeSocs);
			vt.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

}
