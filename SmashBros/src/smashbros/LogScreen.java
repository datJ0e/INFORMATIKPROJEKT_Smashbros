package smashbros;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;

import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JInternalFrame;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogScreen {
	
	private JPasswordField passwordField;
	private JTextField benutzernameField;
	JFrame frame;
	public int spieler=1;
	public static String Spieler1;
	public static String Spieler2;
	
	public LogScreen(int spieler) {
		JFrame f = new JFrame();
		this.spieler=spieler;
		frame = f;
		f.setSize(450, 300);
		f.getContentPane().setLayout(null);
		Dimension windowSize = f.getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();

        int dx = centerPoint.x - windowSize.width / 2;
        int dy = centerPoint.y - windowSize.height / 2;    
        f.setLocation(dx, dy);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Geben Sie hier ihr Passwort ein.");
		passwordField.setBounds(117, 153, 253, 20);
		f.getContentPane().add(passwordField);
		
		JTextPane txtpnBitteLoggenSie = new JTextPane();
		txtpnBitteLoggenSie.setEditable(false);
		txtpnBitteLoggenSie.setText("Bitte loggen Sie sich ein.");
		txtpnBitteLoggenSie.setBounds(130, 55, 220, 20);
		f.getContentPane().add(txtpnBitteLoggenSie);
		
		benutzernameField = new JTextField();
		benutzernameField.setToolTipText("Geben Sie hier Ihren Benutzernamen ein.");
		benutzernameField.setBounds(117, 110, 253, 20);
		f.getContentPane().add(benutzernameField);
		benutzernameField.setColumns(10);
		
		JTextPane txtpnBenutzername = new JTextPane();
		txtpnBenutzername.setEditable(false);
		txtpnBenutzername.setText("Benutzername");
		txtpnBenutzername.setBounds(10, 110, 85, 20);
		f.getContentPane().add(txtpnBenutzername);
		
		JTextPane txtpnPasswort = new JTextPane();
		txtpnPasswort.setEditable(false);
		txtpnPasswort.setText("Passwort");
		txtpnPasswort.setBounds(10, 153, 85, 20);
		f.getContentPane().add(txtpnPasswort);
		
		JButton btnLogIn = new JButton("Anmelden");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				@SuppressWarnings("deprecation")
				String passwort = passwordField.getText();
				String benutzername = benutzernameField.getText();
				
				//Hier kommt der Code hin was beim Einloggen passiert!
				anmelden(passwort, benutzername, txtpnBitteLoggenSie);
			}
		});
		btnLogIn.setBounds(256, 203, 114, 23);
		f.getContentPane().add(btnLogIn);
		
		JButton btnRegestrierung = new JButton("Registrieren");
		btnRegestrierung.setToolTipText("Noch keinen Account?");
		btnRegestrierung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				@SuppressWarnings("deprecation")
				String passwort = passwordField.getText();
				String benutzername = benutzernameField.getText();
				
				//Hier kommt der Code hin was bei der Regestrierung passiert!
				if(passwort != null && benutzername != null) {
					if(!DBHelper.neuenBenutzerAnlegen(benutzername, passwort)) {
						txtpnBitteLoggenSie.setText("Diesen Benutzer gibt es leider schon.");
					}
				}
			}
		});
		btnRegestrierung.setBounds(117, 203, 114, 23);
		f.getContentPane().add(btnRegestrierung);
		f.setVisible(true);
		
	}
	
	public static void main (String[] args) {
		new LogScreen(1);
		
	}
	
	public void anmelden(String passwort, String benutzername, JTextPane ausgabe) {
		if(passwort != null && benutzername != null) {
			if(DBHelper.prüfeAnmeldedaten(benutzername, passwort)) {
				if(spieler==1) {
					ausgabe.setText("Willkommen " + benutzername + " ! Nun Spieler 2 !");
					Spieler1=benutzername;
					spieler=2;
				}
				else {
					if(!benutzername.equalsIgnoreCase(Spieler1)) {
						ausgabe.setText("Willkommen " + benutzername + " ! Lade Hauptmenü !");
						Spieler2=benutzername;
						spieler=1;
						Frameweg();
						new MainMenue(Spieler1, Spieler2);
					}else {
						ausgabe.setText("Dieser Spieler ist schon angemeldet!");
					}
				}
			} else {
				ausgabe.setText("Falscher Benutzername oder falsches Passwort");
			}
		}
	}
	public void Frameweg() {
		frame.dispose();
	}
}
