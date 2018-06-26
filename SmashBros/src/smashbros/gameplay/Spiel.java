/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smashbros.gameplay;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;

/**
 * Klasse die alle Objekte usw managet
 * @author fre.riedmann
 */
public class Spiel {
	
	private Spieler spieler1;
	private Spieler spieler2;
	private ArrayList<Boden> bodense = new ArrayList<Boden>();
	private ArrayList<Attack> attacks = new ArrayList<Attack>();
	private Spiel instance;
	private JFrame fenster;
	private boolean running;
	private InputController ic;
	
	public Spiel(JFrame frame) {   //screen breite: 1600, höhe: 900
		spieler1 = new Spieler(this, 0, 0, 60, 100);   //spieler bild ist ca 50 hoch & 30 breit
		spieler2 = new Spieler(this, 300, 0, 60, 100);
		bodense.add(new Boden(this, 600, 800, 400));
		bodense.add(new Boden(this, 50, 300, 400));
		bodense.add(new Boden(this, 900, 500, 400));
		instance = this;
		running = true;
		ic = new InputController(frame);
		ic.newControll(spieler1, 65, 87, 83, 68, 17);
		ic.newControll(spieler2, 37, 38, 40, 39, 96);
		this.fenster = frame;
	}
	
	/**
	 * Startet das Spiel
	 */
	public void start() {
		running = true;
		updater.start();
	}
	
	/**
	 * Updated alles für die vergangene Zeit seit dem letzten update in milisekunden
	 * @param time zeit seit dem letzten update in millisekunden
	 */
	public void update(long time) {
		spieler1.update(time);
		spieler2.update(time);
		if(spieler1.posY>fenster.getHeight()) spieler1.setPosY(10);
		if(spieler2.posY>fenster.getHeight()) spieler2.setPosY(10);
		for(Boden b : bodense) b.update(time);
		
		//iterator wegen concurrency gefahr
		Iterator<Attack> i = attacks.iterator();
		while(i.hasNext()) {
			Attack a = i.next();
			a.update(time);
			if(spieler1 != a.getCaster() && a.intersects(spieler1.hitbox))a.hit(spieler1);
			if(spieler2 != a.getCaster() && a.intersects(spieler2.hitbox))a.hit(spieler2);
			if(a.isOld) i.remove();
		}
	}
	
	/**
	 * Zeichnet alle Objekte des Spiels
	 * @param g Graphics Objekt mit dem alles gezeichnet wird
	 */
	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		spieler1.draw(g);
		g.setColor(Color.RED);
		spieler2.draw(g);
		for(Boden b : bodense) b.draw(g);
		for(Attack a : getAttacks()) a.draw(g);
	}
	
	/**
	 * Runnable die alles genau 60 mal pro Sekunde berechnet
	 */
	Thread updater = new Thread() {
		@Override
		public void run() {
			long last = System.currentTimeMillis()-16;
			while(running) {
				instance.update(System.currentTimeMillis() - last);
				last = System.currentTimeMillis();
				try {Thread.sleep(16);} catch (InterruptedException e) {e.printStackTrace();}
			}
		}
	};
	
	
	
	public ArrayList<Boden> getAlleBodens() {
		return bodense;
	}
	
	public Spieler[] getSpieler() {
		Spieler[] s = new Spieler[2];
		s[0] = spieler1;
		s[1] = spieler2;
		return s;
	}

	public ArrayList<Attack> getAttacks() {
		return attacks;
	}

	public void setAttacks(ArrayList<Attack> attacks) {
		this.attacks = attacks;
	}
    
}
