package classeMetier;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tour implements Serializable{
	private Joueur perdantTour;
	private ArrayList<Joueur> gagnantsTour;
	
	
	public Joueur getPerdant() {
		return perdantTour;
	}
	
	public void setPerdant(Joueur perdant) {
		this.perdantTour = perdant;
	}
	
	public ArrayList<Joueur> getGagnantsTour() {
		return gagnantsTour;
	}
	
	public void setGagnantsTour(ArrayList<Joueur> gagnantsTour) {
		this.gagnantsTour = gagnantsTour;
	}
	
	
	
}
