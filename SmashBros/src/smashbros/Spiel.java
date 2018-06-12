/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smashbros;

import java.awt.Graphics;

import javax.swing.JFrame;

/**
 * Klasse die alle Objekte usw managet
 * @author fre.riedmann
 */
public class Spiel {
	
	private Spieler spieler1;
	private Spieler spieler2;
	private Spiel instance;
	private boolean running;
	private InputController ic;
	
	public Spiel(JFrame frame) {
		spieler1 = new Spieler(0, 0, 60, 100);   //spieler bild ist ca 50 hoch & 30 breit
		spieler2 = new Spieler(300, 0, 60, 100);
		instance = this;
		running = true;
		ic = new InputController(frame);
		ic.newControll(spieler1, 'a', 'w', 's', 'd');
		ic.newControll(spieler2, 'j', 'i', 'k', 'l');
	}
	
	/**
	 * Startet das Spiel
	 */
	public void start() {
		running = true;
		updater.run();
	}
	
	/**
	 * Updated alles für die vergangene Zeit seit dem letzten update in milisekunden
	 * @param time zeit seit dem letzten update in millisekunden
	 */
	public void update(long time) {
		spieler1.update(time);
		spieler2.update(time);
	}
	
	/**
	 * Zeichnet alle Objekte des Spiels
	 * @param g Graphics Objekt mit dem alles gezeichnet wird
	 */
	public void draw(Graphics g) {
		spieler1.draw(g);
		spieler2.draw(g);
	}
	
	/**
	 * Runnable die alles genau 60 mal pro Sekunde berechnet
	 */
	Runnable updater = new Runnable() {
		@Override
		public void run() {
			long last = System.currentTimeMillis();
			while(running) {
				instance.update(System.currentTimeMillis() - last);
				last = System.currentTimeMillis();
				try {Thread.sleep(16);} catch (InterruptedException e) {e.printStackTrace();}
			}
		}
	};
    
}
