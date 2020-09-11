package Interface;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

import classeMetier.Joueur;

public class AfficherResultatsPartieThread extends Thread{
	private Socket soc;
	private Frame fr;
	
	public AfficherResultatsPartieThread(Socket soc, Frame fr) {
		this.soc = soc;
		this.fr = fr;
	}
	
	public void run() {
		InputStream is;
		try {
			is = soc.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			ArrayList<Joueur> joueurs = (ArrayList<Joueur>) ois.readObject();
			fr.afficherFinDePartiePanel(joueurs, this);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
