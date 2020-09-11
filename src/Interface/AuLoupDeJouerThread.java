package Interface;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

import classeMetier.Joueur;

public class AuLoupDeJouerThread extends Thread {
	private Socket soc;
	private Joueur j;
	private PartiePanel partiePanel;

	public AuLoupDeJouerThread(Socket soc, PartiePanel partiePanel) {
		this.soc = soc;
		this.partiePanel = partiePanel;
	}

	public void run() {
		InputStream is;
		try {
			is = soc.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			String go = (String) ois.readObject();
			if (go.equals("au loup de jouer")) {
				partiePanel.auLoupDeJouer();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
