package smashbros;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainMenue extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static MainMenue Hauptmenu;
	String Benutzer1;
	String Benutzer2;
	
	
	public MainMenue(String name1, String name2) {
		this.Benutzer1 = name1;
		this.Benutzer2 = name2;
		Hauptmenu = this;
		this.setLayout(new GridLayout(0, 3));
		this.setSize(new Dimension(800, 800));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		dreiKleber();
		
		JButton Starten = new JButton("Spiel Starten");
		JButton Beenden = new JButton("Spiel Beenden");
		JButton SpieleHistorie = new JButton("SpieleHistorie");
		JLabel Titel = new JLabel("Smashbros", NORMAL);
		Titel.setFont(new Font("Serif", Font.PLAIN, 30));
		Titel.setForeground(new Color(0xdd00dd));
		
		Starten.addActionListener(new java.awt.event.ActionListener() {
            
            	public void actionPerformed(java.awt.event.ActionEvent e) {
            		startGame();
            	}
            });
		
		Beenden.addActionListener(new java.awt.event.ActionListener() {
            
        	public void actionPerformed(java.awt.event.ActionEvent e) {
        		Hauptmenu.dispose();
        	}
        });
		
		SpieleHistorie.addActionListener(new java.awt.event.ActionListener() {
            
        	public void actionPerformed(java.awt.event.ActionEvent e) {
        		startSpieleHistorie();
        	}
        });
		
		this.addComp(Titel);
		this.addComp(Starten);
		this.addComp(SpieleHistorie);
		this.addComp(Beenden);
		
		this.setVisible(true);
	}
	
	//Fügt eine Komponente dem Menü hinzu
	public void addComp(Component Komponente) {
		
		this.add(Box.createHorizontalGlue());
		this.add(Komponente);
		this.add(Box.createHorizontalGlue());
		
		dreiKleber();
	}
	
	//Eine Reihe voller Kleber
	public void dreiKleber() {
		this.add(Box.createGlue());
		this.add(Box.createGlue());
		this.add(Box.createGlue());
	}
	
	public void startGame() {
		this.dispose();
	}
	
	public void startSpieleHistorie() {
		this.dispose();
		new SpielHistorie(this.Benutzer1, this.Benutzer2);
	}
	
	public static void main(String[] args) {
//		new MainMenue();
	}
}
