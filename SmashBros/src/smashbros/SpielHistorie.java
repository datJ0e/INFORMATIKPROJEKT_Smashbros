package smashbros;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

public class SpielHistorie extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JScrollPane scrollBar;
	public JPanel Ausgabe;
	String Benutzer;
	
	public SpielHistorie(String Spieler1, String Spieler2) {
		getContentPane().setLayout(new BorderLayout(0, 50));
		
		this.Benutzer = Spieler1;
		this.setSize(1000, 800);
		scrollBar = new JScrollPane();
		getContentPane().add(scrollBar, BorderLayout.CENTER);
		scrollBar.getVerticalScrollBar().setUnitIncrement(16);
		
		JLabel  Titel = new JLabel("SpielErgebnisse");
		Titel.setAlignmentX(CENTER_ALIGNMENT);
		Titel.setHorizontalAlignment(JLabel.CENTER);
		Titel.setVerticalAlignment(JLabel.CENTER);
		Titel.setBackground(Color.MAGENTA);
		
		JButton BSpieler1 = new JButton(Spieler1);
		BSpieler1.setHorizontalAlignment(SwingConstants.LEFT);
		BSpieler1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Spieler(Spieler1);
			}
		});
		
		JButton BSpieler2 = new JButton(Spieler2);
		BSpieler2.setHorizontalAlignment(SwingConstants.RIGHT);
		BSpieler2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Spieler(Spieler2);
			}
		});
		
		JButton BExit = new JButton("Back");
		BExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exit(Spieler1, Spieler2);
			}
		});
		
		BExit.setHorizontalAlignment(SwingConstants.CENTER);
		JPanel GanzOben = new JPanel();
		GanzOben.setLayout(new BoxLayout(GanzOben, BoxLayout.X_AXIS));
		GanzOben.add(BSpieler1);
		GanzOben.add(BExit);
		GanzOben.add(BSpieler2);
		
		JSplitPane ObereSplittung = new JSplitPane(JSplitPane.VERTICAL_SPLIT, GanzOben, Titel);
		getContentPane().add(ObereSplittung, BorderLayout.NORTH);
		
		initH();

		
		this.setVisible(true);
	}
	
	public void initH() {
		Ausgabe = new JPanel();
		Ausgabe.setLayout(new BoxLayout(Ausgabe, BoxLayout.Y_AXIS));
		
		jonasmethode(Benutzer);
		
		scrollBar.setViewportView(Ausgabe);
	}
	
	public void addSpiel(String Date, String Name1, String Punkte1, String Score1, String Name2, String Punkte2, String Score2) {
		Ausgabe.add(Box.createRigidArea(new Dimension(0,3)));
		JPanel Spiel = new JPanel();
		Spiel.setLayout(new GridLayout(3, 3));
		Spiel.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel Datum = new JLabel(Date);
		JLabel Spieler1 = new JLabel(Name1);
		JLabel Spieler2 = new JLabel(Name2);
		JLabel Ergebnis = new JLabel("Ergebnis:");
		JLabel Punkt1 = new JLabel(Punkte1);
		JLabel Punkt2 = new JLabel(Punkte2);
		JLabel Punktzahl = new JLabel("Punkte:");
		JLabel Punktzahl1 = new JLabel(Score1);
		JLabel Punktzahl2 = new JLabel(Score2);
		
		Spieler1.setHorizontalAlignment(JLabel.CENTER);
		Spieler2.setHorizontalAlignment(JLabel.CENTER);
		Punkt1.setHorizontalAlignment(JLabel.CENTER);
		Punkt2.setHorizontalAlignment(JLabel.CENTER);
		Punktzahl1.setHorizontalAlignment(JLabel.CENTER);
		Punktzahl2.setHorizontalAlignment(JLabel.CENTER);
		
		Spiel.add(Datum);
		Spiel.add(Spieler1);
		Spiel.add(Spieler2);
		Spiel.add(Ergebnis);
		Spiel.add(Punkt1);
		Spiel.add(Punkt2);
		Spiel.add(Punktzahl);
		Spiel.add(Punktzahl1);
		Spiel.add(Punktzahl2);
		
		Spiel.setBorder(BorderFactory.createLineBorder(Color.black));
		Spiel.setBackground(Color.CYAN);
		Spiel.setMaximumSize(new Dimension(350, 100));
		Spiel.setMinimumSize(new Dimension(300, 80));
		
		Ausgabe.add(Spiel);
		
		Ausgabe.add(Box.createRigidArea(new Dimension(0,3)));
	}
	
	
	public void jonasmethode(String s) {
		DBHelper.spielergebnisseAusgeben(s);
		for(int i = 0; i < Spielergebnisse.spErList.size(); i++) {
			addSpiel(Spielergebnisse.spErList.get(i).date.toString(), Spielergebnisse.spErList.get(i).s1.toString(), Spielergebnisse.spErList.get(i).getErgebnis1().toString(), Integer.toString(Spielergebnisse.spErList.get(i).getLsc1()), Spielergebnisse.spErList.get(i).s2.toString(), Spielergebnisse.spErList.get(i).getErgebnis2().toString(), Integer.toString(Spielergebnisse.spErList.get(i).getLsc2()));
		}
	
	}
	
	public void exit(String Spieler1, String Spieler2) {
		this.dispose();
		new MainMenue(Spieler1, Spieler2);
	}
	
	public void Spieler(String s) {
		Benutzer = s;
		Ausgabe.removeAll();
		initH();
	}
	
	public static void main(String[] args) {
		new SpielHistorie("Ich", "Er");
	}
}
