/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smashbros;
import java.net.URL;
import java.awt.GraphicsEnvironment;
import java.awt.Point;

import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
/**
 *
 * @author joh.warnke
 */
public class Screen{
    JFrame f;
    Drawer d;
    Spiel s;
    LoginHelper lh;
    ScreenSwitch sw = Main.getScreenSwitch();
    Screen thisScreen = this;
    boolean running = true;
    
    public Screen(int width, int height, String name, String type) {
	    f = new JFrame(name);  
	    URL iconURL = getClass().getResource("../favicon.png");
	    System.out.println(iconURL);
		ImageIcon icon = new ImageIcon(iconURL);
		f.setIconImage(icon.getImage());
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.setSize(new Dimension(width, height));
	    f.setResizable(false);
	    //f.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    Dimension windowSize = f.getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();

        int dx = centerPoint.x - windowSize.width / 2;
        int dy = centerPoint.y - windowSize.height / 2;    
        f.setLocation(dx, dy);
	    switch(type){
	    	case"game": this.createAndStartGame();
		    			break;
	    	case"menu": this.createMenu();
	    				break;
	    	case"login":this.createLogin();
	    				break;
	    	default: System.out.println("switch Fehler");
	    }
	    
    }
    
    
    
    private void createAndStartGame() {
    	d = new Drawer();
		d.setFocusable(false);
		f.add(d);
		f.setVisible(true);
		s = new Spiel(f);
		s.start();
		updater.start();
    }
    
    private void createMenu() {
    	
    	f.getContentPane().setBackground(Color.WHITE);
		f.getContentPane().setForeground(Color.WHITE);
		f.getContentPane().setFont(new Font("Segoe Script", Font.ITALIC, 24));
		f.getContentPane().setLayout(null);
		
		JTextPane txtpnSmashbros = new JTextPane();
		txtpnSmashbros.setFont(new Font("Segoe Script", Font.ITALIC, 30));
		txtpnSmashbros.setBackground(Color.WHITE);
		txtpnSmashbros.setEditable(false);
		txtpnSmashbros.setText("SmashBros");
		txtpnSmashbros.setBounds(111, 11, 194, 60);
		f.getContentPane().add(txtpnSmashbros);
		
		JButton btnNewButton = new JButton("Neues Spiel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sw.switchAndDelete(thisScreen, new Screen(1600,900,"SmashBros","game"));
			}
		});
		btnNewButton.setBounds(111, 130, 194, 51);
		f.getContentPane().add(btnNewButton);
		
		JButton btnSpielehistorie = new JButton("SpieleHistorie");
		btnSpielehistorie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("noch nicht implementieret");
			}
		});
		btnSpielehistorie.setBounds(111, 236, 194, 51);
		f.getContentPane().add(btnSpielehistorie);
		
		JButton btnNutzloserKnopf = new JButton("Nutzloser Knopf");
		btnNutzloserKnopf.setBounds(111, 335, 194, 51);
		f.getContentPane().add(btnNutzloserKnopf);
    	}
    
    private void createLogin() {
    	System.out.println("login");
    	f.getContentPane().setLayout(null);
    	JPasswordField passwordField = new JPasswordField();
		passwordField.setToolTipText("Geben Sie hier ihr Passwort ein.");
		passwordField.setBounds(117, 153, 253, 20);
		f.getContentPane().add(passwordField);
		
		JTextPane txtpnBitteLoggenSie = new JTextPane();
		txtpnBitteLoggenSie.setEditable(false);
		txtpnBitteLoggenSie.setText("Willkommen, bitte melden sie sich an");
		txtpnBitteLoggenSie.setBounds(100, 30, 220, 20);
		f.getContentPane().add(txtpnBitteLoggenSie);
		
		JTextField benutzernameField = new JTextField();
		benutzernameField.setToolTipText("Geben Sie hier Ihren Benutzernamen ein.");
		benutzernameField.setBounds(117, 90, 253, 20);
		f.getContentPane().add(benutzernameField);
		benutzernameField.setColumns(10);
		
		JTextPane txtpnBenutzername = new JTextPane();
		txtpnBenutzername.setEditable(false);
		txtpnBenutzername.setText("Benutzername");
		txtpnBenutzername.setBounds(10, 90, 85, 20);
		f.getContentPane().add(txtpnBenutzername);
		
		JTextPane txtpnPasswort = new JTextPane();
		txtpnPasswort.setEditable(false);
		txtpnPasswort.setText("Passwort");
		txtpnPasswort.setBounds(10, 153, 85, 20);
		f.getContentPane().add(txtpnPasswort);
		
		JButton btnLogIn = new JButton("Anmelden");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO: Auskommentieren wenn DB ready
				/*String passwort = new String(passwordField.getPassword());
				String benutzername = benutzernameField.getText();
				
				LoginHelper.anmelden(passwort, benutzername, txtpnBitteLoggenSie);*/
				sw.switchAndDelete(thisScreen,new Screen(450,530,"SmashBros","menu"));
			}
		});
		btnLogIn.setBounds(256, 203, 114, 23);
		f.getContentPane().add(btnLogIn);
		
		JButton btnRegestrierung = new JButton("Registrieren");
		btnRegestrierung.setToolTipText("Noch keinen Account?");
		btnRegestrierung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String passwort = new String(passwordField.getPassword());
				String benutzername = benutzernameField.getText();
				
				LoginHelper.registrieren(passwort, benutzername, txtpnBitteLoggenSie);
			}
		});
		btnRegestrierung.setBounds(117, 203, 114, 23);
		f.getContentPane().add(btnRegestrierung);
		f.setVisible(true);
	}
    
    
    
    public void setVisible(boolean bool) {
    	f.setVisible(bool);
    }
    
    public void deleteFrame() {
    	f.dispose();
    }
    public class Drawer extends Canvas {
		private static final long serialVersionUID = 1L;

		@Override
        public void paint(Graphics g){
            if(s != null)s.draw(g);
        }
    }
    
	Thread updater = new Thread() {
		@Override
		public void run() {
			while(true) {
				d.repaint();
				try {Thread.sleep(16);} catch (InterruptedException e) {e.printStackTrace();}
			}
		}
	};
    
    
}
