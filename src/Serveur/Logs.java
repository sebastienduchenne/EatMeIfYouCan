package Serveur;
import java.io.IOException;
import java.util.logging.*;
import java.text.SimpleDateFormat; 
import java.util.Date; 
import java.util.Locale;

public class Logs {
	protected static Logger logger = Logger.getLogger("MonJournalDeLogs"); 
	// Le logger correspond au de logs, c'est lui qu'on va appeller 
	//(donc un systeme avec un seul journal) pour entrer les logs du programme
	
	
	
	public Logs(){
		
		FileHandler fh = null; //C'est un pointeur vers notre fichier log
		
		//Ici on recupere la date du syst�me pour l'incorporer au nom de notre fichier log
		Date d=new Date(); 
	   	SimpleDateFormat f=new SimpleDateFormat("dd_MM_yy_H'h'm'm's's'");//On formate la date : 01/01/15 par exemple 
	   	
	   	
		try {
			//Ici on indique au FileHandler le nom et l'emplacement du fichier log
			//Il le cr�era si il n'existe pas
			fh = new FileHandler("logs/log"+f.format(d)+".log"); 
			
			//On associe notre journal de logs au  FileHandler
			logger.addHandler(fh);
			
			//On formatte le fichier log (Choix XML ou simple)
			fh.setFormatter(new SimpleFormatter());
			
		} catch (SecurityException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
//		logger.log(Level.INFO, "D�marrage du serveur");
//		
//		try {
//			Thread.sleep(5000);
//			logger.log(Level.INFO, "60S APRES");
//			fh.close();
//			
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		fh.close();//On ferme le fhandler
		
		//Gerer egalement un transfert de log du client au serveur tel que : "Le client demande une auth"
		//Avec le SocketHandler ?
	}	
	
	public static void insererLogINFO(String message){
		logger.log(Level.INFO, message);
	}
	
	public static void insererLogWARNING(String message){
		logger.log(Level.WARNING, message);
	}
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Logs Testlog = new Logs();
//		
//
//
//	}

}
