import java.sql.*;
import java.text.DateFormat;
import java.util.GregorianCalendar;
public class DBHelper {
	static Connection c = null;
    static Statement stmt = null;
	public static void main(String[] args) { 
	      db_tabellenErstellen();
		  benutzerdatenAusgeben();
		//  neuenBenutzerAnlegen("Ich", "123");
		  //neuenBenutzerAnlegen("Du", "1234");
		  //neuenBenutzerAnlegen("Er", "12345");
		 // db1UpdateSpielerdaten("Er", 4,5);
		  //neuesSpielAnlegen("Er","Ich","1:5",100,10,aktuellesDatum());
		 // neuesSpielAnlegen("Du","Ich","1:5",100,10,aktuellesDatum());
		  //neuesSpielAnlegen("Er","Ich","10:5",100,10,aktuellesDatum());
		  //neuesSpielAnlegen("Er","Ich","1:299",100,10,aktuellesDatum());
		  spielergebnisseAusgeben("Ich");
	}
	//Gibt das aktuelle Datum zurück
	static String aktuellesDatum() {
		GregorianCalendar now = new GregorianCalendar(); 
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		String date=df.format(now.getTime());
        return date;
    } 
	//Erstellt die Tabellen (wenn noch nicht vorhanden)
	public static void db_tabellenErstellen() {
		try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:test.db");
	         System.out.println("Opened database successfully");
	         stmt = c.createStatement();
	         String sql1="CREATE TABLE IF NOT EXISTS 'spielername_passwort_highscore' "+
	                    "(spielername	TEXT NOT NULL UNIQUE, "+
	        			"Passwort	    TEXT NOT NULL, "+
	        			"pershighscore	INTEGER DEFAULT 0,"+
	        			"highscoreSpielId	INTEGER )";
	         String sql2="CREATE TABLE IF NOT EXISTS 'spielergebnisse' "+ 
	        		 	"(Spieler1 TEXT NOT NULL,"+
	        		 	"Spieler2 TEXT NOT NULL,"+
	        		 	"Ergebnis TEXT," 
	        		 	+"Spiel_ID INTEGER NOT NULL UNIQUE," 
	        		 	+"LocalScoreS1 INTEGER," 
	        		 	+"LocalScoreS2 INTEGER,"
	        		 	+"Datum TEXT NOT NULL )";
	         stmt.executeUpdate(sql1);
	         stmt.executeUpdate(sql2);
	         stmt.close();
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      }
	      System.out.println("Table created successfully");
	}
	//Erstellt eine Liste aller Ergebnisse des angegebenen Spielers
	public static void spielergebnisseAusgeben(String s) {
		try {
			Spielergebnisse.spErList.clear();
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			stmt = c.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * From spielergebnisse WHERE Spieler1='"+s+"'or Spieler2='"+s+"'");
			while(rs.next()) {
				String s1=rs.getString("Spieler1");
				String s2=rs.getString("Spieler2");
				String erg=rs.getString("Ergebnis");
				int spiel_ID=rs.getInt("Spiel_ID");
				int lsc1=rs.getInt("LocalScoreS1");
				int lsc2=rs.getInt("LocalScoreS2");
				String date=rs.getString("Datum");
				Spielergebnisse.spErList.add(new Spielergebnisse(s1, s2, erg, lsc1, lsc2, spiel_ID, date));
				System.out.println(s1+"-"+s2+"-"+erg+"-"+Integer.toString(spiel_ID)+"-"+Integer.toString(lsc1)+"-"+Integer.toString(lsc2)+"-"+date);
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 System.out.println("Opened database successfully");
	}
	//generiert eine einzigartige SpielID
	public static int SpielIDgenerieren() {
		try {
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			stmt = c.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT Spiel_ID From spielergebnisse Order By Spiel_ID DESC");
			if(rs.isBeforeFirst()) {
				rs.getInt(1); 
				int id=rs.getInt("Spiel_ID")+1;
				rs.close();
				stmt.close();
				c.close();
				return id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	//die Spielerdaten werden im Profil des Spielers aktuallisiert
	public static boolean db1UpdateSpielerdaten(String s,int lsc,int spiel_ID) {
		try {
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			stmt = c.createStatement();
			stmt.executeUpdate("Update spielername_passwort_highscore SET pershighscore= "+Integer.toString(lsc)+","
					+ " highscoreSpielId="+Integer.toString(spiel_ID)+" WHERE spielername='"+s+"';");
					stmt.close();
					c.close();
					return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	//ein neues Spiel wird in die Datenbank eingetragen
	public static boolean neuesSpielAnlegen(String s1,String s2,String Ergebnis,int lsc1,int lsc2,String date) {
		try {
		int i=SpielIDgenerieren();
		c = DriverManager.getConnection("jdbc:sqlite:test.db");
		String query="INSERT Into spielergebnisse(Spieler1,Spieler2,Ergebnis,Spiel_ID,LocalScoreS1,LocalScoreS2,Datum) values(?,?,?,?,?,?,?)";
		PreparedStatement pst=c.prepareStatement(query);
		pst.setString(1, s1);
		pst.setString(2, s2);
		pst.setString(3, Ergebnis);
		pst.setInt(4, i);
		pst.setInt(5, lsc1);
		pst.setInt(6, lsc2);
		pst.setString(7, date);
		pst.execute();
		pst.close();
		c.close();
		DBHelper.db1UpdateSpielerdaten(s1,lsc1,i);
		DBHelper.db1UpdateSpielerdaten(s2,lsc2,i);
		return true;
		}catch(SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		return false;
	}
	//ein neuer Spieler wird in die Datenbak eingetragen
	public static boolean neuenBenutzerAnlegen(String name,String pw) {
		try {
			if(prüfeNamen(name)) {
				c = DriverManager.getConnection("jdbc:sqlite:test.db");
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
	//prüft obder Name zulässig ist
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
	//Anmeldedaten werden überprüft
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
			e.printStackTrace();
		}
		return false;
	}
}
