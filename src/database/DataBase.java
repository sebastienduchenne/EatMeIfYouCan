package database;


import classeMetier.Joueur;// Import de la classe Joueur
import Serveur.Logs;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import com.mysql.jdbc.Connection;



public class DataBase {
	static Connection connexion = null;
	static Statement statement = null; //L'objet qui permet de parler avec la database
	
	public DataBase(){//Constructeur
		
	}
	
	private void connexionDataBase(){
		
		// Declaration des informations d'accès à la base de données
		String url = "jdbc:mysql://localhost/emiyc";
	  	String user = "root";
		String passwd = "";//root sur linux

		
		try{

			// Chargement du driver
			Class.forName("com.mysql.jdbc.Driver");
			
			//Récupération de la connexion
			connexion = (Connection) DriverManager.getConnection(url, user, passwd);
			
			//Récupération de la connexion dans un statement
			statement = connexion.createStatement(); 
			Logs.insererLogINFO("Connexion à la base de donnée");

		} catch(Exception e){
			
			Logs.insererLogWARNING("Connexion à la base de donnée echouée");
			e.printStackTrace();

		}
	}
	
	public static boolean inscrireJoueur(Joueur joueur){
		
		boolean statusInscription = false;
		boolean resIsEmpty = true; //Pour savoir si le res retourne une ligne vide
		
		try{
			
			//Ici on se connecte a la db 
			DataBase db = new DataBase();
			db.connexionDataBase();
			
			//on regarde que le joueur n'est pas deja inscrit en fonction de son pseudo
			ResultSet resultatRequeteSelect = statement.executeQuery("SELECT `iduser`, `pseudo`, `password` FROM `user` WHERE pseudo='"+joueur.getPseudo()+"'");
						
			//Verification du retour de la requete pour voir si c'est une ligne vide en essayant de recuperer des infos
			while (resultatRequeteSelect.next()) {  
				  resIsEmpty = false;
				}
			
				if(resIsEmpty == true){
					
					//Ici on execute la requete pour inserer le user dans la base
					int resultatRequeteInsert = statement.executeUpdate("INSERT INTO `user`(`pseudo`, `password`) VALUES ('"+joueur.getPseudo()+"', '"+joueur.getPassword()+"')");
					
					//On regarde si la requête renvoie quelque chose
					if(resultatRequeteInsert >= 1){
						statusInscription = true; //SI oui on dit que l'inscription est faite
						Logs.insererLogINFO("Inscription d'un utilisateur");
					}
					
				}else{
					
					statusInscription = false;//Sinon on dit qu'elle n'a pas été faite et on le signale au user
					Logs.insererLogINFO("Inscription d'un utilisateur echouée");
				}
				
		} catch(SQLException e){

			e.printStackTrace();

		}finally {
			try {
				//On librere la memoire
				connexion.close();
				
				statement.close();	
				
				Logs.insererLogINFO("Fermeture connexion base de donnée");
				
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		return statusInscription;
	}	
	
	public static Joueur connecterJoueur(Joueur joueur){
		
		try{

			DataBase db = new DataBase();
			db.connexionDataBase();
			
			String passwordsql = null;//MOt de passe récupéré dans la db
			boolean resIsEmpty = true;
			
			ResultSet resultatRequeteSelect = statement.executeQuery("SELECT * FROM `user` WHERE pseudo='"+joueur.getPseudo()+"'");
			Logs.insererLogINFO("Verification correspondance joueur dans la base de donnée");
				
			while (resultatRequeteSelect.next()) { 
				
				  passwordsql = resultatRequeteSelect.getString("password");
				  resIsEmpty = false;
				  if(passwordsql.equals(joueur.getPassword())){//SI le mot de passe est bon 
						
					  Logs.insererLogINFO("Connexion d'un joueur accepté");
					  
					  joueur.setConnect(true);
					  //Ici je set les stats etc..
					  
						
					}else{
						
						JOptionPane.showMessageDialog(null,"Erreur dans votre mot de passe");
						Logs.insererLogINFO("Connexion d'un joueur refusé");
						//On renvoie au user que son mot de passe est incorrect
						
					}
				  
				}
			
			if(resIsEmpty == true){
				JOptionPane.showMessageDialog(null,"Erreur pseudo ou vous n'êtes pas inscrit");
				Logs.insererLogINFO("Connexion d'un joueur refusé");
			}
			
		} catch(SQLException e){
			
			e.printStackTrace();
		
		} finally {
			try {
				connexion.close();
				
				statement.close();
				
				Logs.insererLogINFO("Fermeture connexion base de donnée");
				
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		return joueur;
	}

	public static void syncroJoueur (Joueur joueur){
		
		try
		{
			//Ici on se connecte a la db 
			DataBase db = new DataBase();
			db.connexionDataBase();			
			
		    statement.executeUpdate("UPDATE `user` SET `pseudo`='"+ joueur.getPseudo()+"',`password`= '"+joueur.getPassword()+"' WHERE pseudo = '"+joueur.getPseudo()+"'");
		    Logs.insererLogINFO("Synchronisation compte effectu�e");

		} catch(SQLException e){
			
			e.printStackTrace();
		
		} finally {
			try {
				connexion.close();
				
				statement.close();
				
				Logs.insererLogINFO("Fermeture connexion base de donnée");
				
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		
		
	}
	
	public static void changerPasswordJoueur(Joueur joueur){
		
		try{

			DataBase db = new DataBase();
			db.connexionDataBase();
			
			statement.executeUpdate("UPDATE `user` SET `password`= '"+joueur.getPassword()+"' WHERE pseudo = '"+joueur.getPseudo()+"'");
			//Le password sera set dans l'objet Joueur avant l'appel de cette methode
			JOptionPane.showMessageDialog(null,"Mot de passe changé");
			
		} catch(SQLException e){
			
			e.printStackTrace();
		
		} finally {
			try {
				connexion.close();
				
				statement.close();
				
				Logs.insererLogINFO("Fermeture connexion base de donnée");
				
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
	}

//	public static void main(String[] args){
//		String pseudotest = "test";
//		
//		Joueur testjoueur = new Joueur(pseudotest);
//		testjoueur.setPassword("testmdp");
//		
//		//DataBase.inscrireJoueur(testj);
//		DataBase.changerPasswordJoueur(testjoueur);
//		
//		
//	}

}