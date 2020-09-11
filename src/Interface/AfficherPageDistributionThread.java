package Interface;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;

import classeMetier.Joueur;

public class AfficherPageDistributionThread extends Thread {
	private Socket soc;
	private Joueur j;
	private Interface.Frame fr;

	public AfficherPageDistributionThread(Socket soc, Frame fr) {
		this.soc = soc;
		this.fr = fr;
	}

	public void run() {

		InputStream is;
		try {
			is = soc.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			ArrayList<Joueur> joueurs = (ArrayList<Joueur>) ois.readObject();
			fr.setJoueurs(joueurs);
			

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
