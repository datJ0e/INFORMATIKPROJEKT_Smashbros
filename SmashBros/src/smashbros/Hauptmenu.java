package smashbros;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Hauptmenu {
	
	public Hauptmenu() {
		JFrame menu = new JFrame();
		menu.getContentPane().setBackground(Color.WHITE);
		menu.getContentPane().setForeground(Color.WHITE);
		menu.getContentPane().setFont(new Font("Segoe Script", Font.ITALIC, 24));
		menu.setSize(450, 530);
		menu.getContentPane().setLayout(null);
		
		JTextPane txtpnSmashbros = new JTextPane();
		txtpnSmashbros.setBackground(Color.WHITE);
		txtpnSmashbros.setFont(new Font("Segoe Script", Font.ITALIC, 30));
		txtpnSmashbros.setEditable(false);
		txtpnSmashbros.setText("SmashBros");
		txtpnSmashbros.setBounds(111, 11, 194, 60);
		menu.getContentPane().add(txtpnSmashbros);
		
		JButton btnNewButton = new JButton("Neues Spiel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				neuesSpiel();
			}
		});
		btnNewButton.setBounds(111, 130, 194, 51);
		menu.getContentPane().add(btnNewButton);
		
		JButton btnSpielehistorie = new JButton("SpieleHistorie");
		btnSpielehistorie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spieleHistorie();
			}
		});
		btnSpielehistorie.setBounds(111, 236, 194, 51);
		menu.getContentPane().add(btnSpielehistorie);
		
		JButton btnNutzloserKnopf = new JButton("Nutzloser Knopf");
		btnNutzloserKnopf.setBounds(111, 335, 194, 51);
		menu.getContentPane().add(btnNutzloserKnopf);
		Dimension windowSize = menu.getSize();
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Point centerPoint = ge.getCenterPoint();
		int dx = centerPoint.x - windowSize.width / 2;
        int dy = centerPoint.y - windowSize.height / 2;    
        menu.setLocation(dx, dy);
		
		menu.setVisible(true);
	}
	
	public void neuesSpiel() {
		
	}
	
	public void spieleHistorie() {
		
	}
	
	public static void main(String[] args) {
		new Hauptmenu();
	}
}
