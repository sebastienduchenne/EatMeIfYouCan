package Serveur;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import classeMetier.Joueur;

public class distributionThread extends Thread {

	private Joueur perdant;
	private ArrayList<Joueur> joueurs;
	ArrayList<Socket> socs ;

	public distributionThread(Joueur joueurCourant, ArrayList<Joueur> joueurs, ArrayList<Socket> socs) {
		this.perdant = joueurCourant;
		this.joueurs = joueurs;
		this.socs = socs;
	}

	public void run() {
		OutputStream os;
		InputStream is;
		try {
			os = socs.get(joueurs.indexOf(perdant)).getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			
			oos.flush();
			oos.writeObject(joueurs);
			oos.flush();
			
			
			is = perdant.getSoc().getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);			

			Object o = ois.readObject();
			
			joueurs = (ArrayList<Joueur>) o;
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
