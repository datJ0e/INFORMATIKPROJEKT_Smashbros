package smashbros;
import javax.swing.JTextPane;

public class LoginHelper {



public static void anmelden(String passwort, String benutzername, JTextPane ausgabe) {
	if(passwort != null && benutzername != null) {
		if(DBHelper.prüfeAnmeldedaten(benutzername, passwort)) {
			ausgabe.setText("Willkommen " + benutzername + "!");
		} else {
			ausgabe.setText("Falscher Benutzername oder falsches Passwort");
		}
	}
}

public static void registrieren(String passwort, String benutzername, JTextPane ausgabe) {
	if(passwort != null && benutzername != null) {
		if(!DBHelper.neuenBenutzerAnlegen(benutzername, passwort)) {
			ausgabe.setText("Diesen Benutzer gibt es leider schon.");
		}
	}
}
}
