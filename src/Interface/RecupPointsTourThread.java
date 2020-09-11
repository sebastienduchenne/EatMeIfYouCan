package Interface;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

import classeMetier.Joueur;

public class RecupPointsTourThread extends Thread {
	private Socket soc;
	private Frame fr;

	public RecupPointsTourThread(Socket soc, Frame fr) {
		this.soc = soc;
		this.fr = fr;
	}

	public void run() {

		InputStream is;
		try {
			is = soc.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			
			ArrayList<Joueur> joueurs = (ArrayList<Joueur>) ois.readObject();
			
			if(joueurs.get(0).getPseudo().equals("lbzibtjlmqizvbslqleb")){
				Joueur gagnant = joueurs.get(1);
				joueurs.remove(0);
				joueurs.remove(1);
				fr.afficherFinDePartiePanel(joueurs, this);
			} else {
				fr.getPartiePanel().affichagePointsTour(joueurs);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
