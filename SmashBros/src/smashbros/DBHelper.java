package smashbros;

import java.sql.*;
public class DBHelper {
	static Connection c = null;
    static Statement stmt = null;
	public static void main(String[] args) { 
	      //db_tabellenErstellen();
		  benutzerdatenAusgeben();
		  neuenBenutzerAnlegen("Ich", "123");
		  neuenBenutzerAnlegen("Du", "1234");
		  neuenBenutzerAnlegen("Er", "12345");
	}
	public static void db_tabellenErstellen() {
		try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:test.db");
	         System.out.println("Opened database successfully");

	         stmt = c.createStatement();
	         String sql1="CREATE TABLE spielername_passwort_highscore" +
	                    "(spielername	TEXT NOT NULL UNIQUE, "+
	        			"Passwort	    TEXT NOT NULL, "+
	        			"pershighscore	INTEGER DEFAULT 0,"+
	        			"highscoreSpielId	INTEGER )";
	         String sql2="CREATE TABLE spielergebnisse"+ 
	        		 	"(Spieler1 TEXT NOT NULL,"+
	        		 	"Spieler2 TEXT NOT NULL,"+
	        		 	"Ergebnis TEXT," 
	        		 	+"Spiel_ID INTEGER NOT NULL UNIQUE," 
	        		 	+"LocalScoreS1 INTEGER," 
	        		 	+"LocalScoreS2 INTEGER,"
	        		 	+"Datum DATE NOT NULL )";
	         stmt.executeUpdate(sql1);
	         stmt.executeUpdate(sql2);
	         stmt.close();
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      }
	      System.out.println("Table created successfully");
	}
	public static boolean neuesSpielAnlegen(String s1,String s2,String Ergebnis,int Spiel_ID,int lsc1,int lsc2,Date date) {
		try {
		c = DriverManager.getConnection("jdbc:sqlite:test.db");
		System.out.println("Opened database successfully");
		String query="INSERT Into spielergebnisse(Spieler1,Spieler2,Ergebnis,Spiel_ID,LocalScoreS1,LocalScoreS2,Datum DATE) + "
				+ "values(?,?,?,?)";
		PreparedStatement pst=c.prepareStatement(query);
		pst.setString(1, s1);
		pst.setString(2, s2);
		pst.setString(3, Ergebnis);
		pst.setInt(4, Spiel_ID);
		pst.setInt(5, lsc1);
		pst.setInt(6, lsc2);
		pst.setDate(7, date);
		pst.execute();
		pst.close();
		c.close();
		return true;
		}catch(SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		return false;
	}
	public static boolean neuenBenutzerAnlegen(String name,String pw) {
		try {
			if(prüfeNamen(name)) {
				c = DriverManager.getConnection("jdbc:sqlite:test.db");
				System.out.println("Opened database successfully");
				String query="INSERT Into spielername_passwort_highscore(spielername,Passwort,pershighscore,highscoreSpielId) "
						+ "values(?,?,?,?)";
				PreparedStatement pst=c.prepareStatement(query);
				pst.setString(1, name);
				pst.setString(2, pw);
				pst.setInt(3, 0);
				pst.setInt(4, 0);
				pst.execute();
				pst.close();
				c.close();
				System.out.println("Spieler erfolgreich angelegt");
				return true;
			}else {
				System.out.println("Spieler nicht angelegt ! Bitte Namen ändern");
			}
			return false;
		} catch (SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		return false;
	}
	private static boolean prüfeNamen(String name) {
		try {
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * From spielername_passwort_highscore");
			while(rs.next()) {
				String n=rs.getString("spielername");
				if(n.equalsIgnoreCase(name)) {
					rs.close();
					stmt.close();
					c.close();
					return false;
				}
			}
			rs.close();
			stmt.close();
			c.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	public static void benutzerdatenAusgeben() {
		try {
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * From spielername_passwort_highscore");
			while(rs.next()) {
				String n=rs.getString("spielername");
				String p=rs.getString("Passwort");
				int perHSC=rs.getInt("pershighscore");
				int hSCgame=rs.getInt("highscoreSpielId");
				System.out.println(n+"-"+p+"-"+Integer.toString(perHSC)+"-"+Integer.toString(hSCgame));
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 System.out.println("Opened database successfully");
	
	}
	public static boolean prüfeAnmeldedaten(String name,String pw) {
		try {
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * From spielername_passwort_highscore");
			while(rs.next()) {
				String n=rs.getString("spielername");
				String p=rs.getString("Passwort");
				if(name.equalsIgnoreCase(n)) {
					if(pw.equalsIgnoreCase(p)) {
						rs.close();
						stmt.close();
						c.close();
						return true;
					}else {
						rs.close();
						stmt.close();
						c.close();
						return false;
					}
				}
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
