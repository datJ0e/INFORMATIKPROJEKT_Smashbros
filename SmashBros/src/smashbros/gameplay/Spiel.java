/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smashbros.gameplay;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;

import smashbros.Background;

/**
 * Klasse die alle Objekte usw managet
 * @author fre.riedmann
 */
public class Spiel {
	
	private Spieler spieler1;
	private Spieler spieler2;
	private ArrayList<Boden> bodense = new ArrayList<Boden>();
	private ArrayList<Attack> attacks = new ArrayList<Attack>();
	private Background bg;
	private Spiel instance;
	private JFrame fenster;
	private boolean running;
	private InputController ic;
	
	public Spiel(JFrame frame) {   //screen breite: 1600, höhe: 900
		this.fenster = frame;
		spieler1 = new Spieler(this, 0, 0, frame.getWidth()/(1600/60), frame.getHeight()/(900/100), false);   //spieler bild ist ca 50 hoch & 30 breit
		spieler2 = new Spieler(this, 300, 0, frame.getWidth()/(1600/60), frame.getHeight()/(900/100), true);
		bodense.add(new Boden(this, frame.getWidth()*0.33f, frame.getHeight()*0.66f, frame.getWidth()*0.33f));
		bodense.add(new Boden(this, frame.getWidth()*0.1f, frame.getHeight()*0.5f, frame.getWidth()*0.2f));
		bodense.add(new Boden(this, frame.getWidth()*0.7f, frame.getHeight()*0.5f, frame.getWidth()*0.2f));
		instance = this;
		running = true;
		ic = new InputController(frame);
		ic.newControll(spieler1, 65, 87, 83, 68, 17);
		ic.newControll(spieler2, 37, 38, 40, 39, 96);
		bg = new Background(frame.getWidth(), frame.getHeight());
		bg.load();
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
		if(spieler1.posY>fenster.getHeight()) spieler1.die();
		if(spieler2.posY>fenster.getHeight()) spieler2.die();
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
		checkGameOver();
	}
	
	public void checkGameOver() {
		if(spieler1.getDeaths() >= 5 || spieler2.getDeaths() >= 5) {
			// game over
		}
	}
	
	/**
	 * Zeichnet alles was sich bewegt oder verändert
	 * @param g
	 */
	public void drawMovingStuff(Graphics g) {
		g.setColor(Color.BLUE);
		spieler1.draw(g);
		g.setColor(Color.RED);
		spieler2.draw(g);
		g.setColor(Color.RED);
		drawZentriertenText(g, (spieler1.getDeaths() + " - " + spieler2.getDeaths()), new Font("Century Gothic", Font.BOLD, 48), fenster.getWidth()/2, 2);
//		drawZentriertenText(g, ("Dmg: " + spieler1.getDamageTaken() + " - " + spieler2.getDamageTaken()), new Font("Century Gothic", Font.BOLD, 24), fenster.getWidth()/2, heightLineOne + 5);
		Font damageFont = new Font("Century Gothic", Font.BOLD, 12);
		drawZentriertenText(g, ("" + spieler1.getDamageTaken()), damageFont, (int)(spieler1.getPosX()+spieler1.getWidth()/2f), (int)(spieler1.getPosY() - 5 - g.getFontMetrics(damageFont).getHeight()));
		drawZentriertenText(g, ("" + spieler2.getDamageTaken()), damageFont, (int)(spieler2.getPosX()+spieler2.getWidth()/2f), (int)(spieler2.getPosY() - 5 - g.getFontMetrics(damageFont).getHeight()));
		for(Attack a : getAttacks()) a.draw(g);
	}
	
	/**
	 * Zeichnet alles was sich nicht bewegt, also den hintergrund und die böden
	 * @param g
	 */
	public void drawStaticStuff(Graphics g) {
		bg.paint(g);
		for(Boden b : bodense) b.draw(g);
	}
	
	/**
	 * Zeichnet alle Objekte des Spiels
	 * @param g Graphics Objekt mit dem alles gezeichnet wird
	 */
	public void draw(Graphics g) {
		drawStaticStuff(g);
		drawMovingStuff(g);
	}
	
	/**
	 * Zeichnet den gegebenen Text zentriert auf der höhe y
	 * @param g Graphics Objekt zum Zeichnen
	 * @param text Text der gezeichnet wird
	 * @param font Schriftart
	 * @param posY höhe
	 * @return wie hoch der Text war
	 */
	public int drawZentriertenText(Graphics g, String text, Font font, int posX, int posY) {
	    FontMetrics metrics = g.getFontMetrics(font);
	    int x = (int)(posX - (float)((float)metrics.stringWidth(text) / 2f));
	    int y = metrics.getHeight() + posY;
	    g.setFont(font);
	    g.drawString(text, x, y);
	    return y;
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
	
	public JFrame getFenster() {
		return fenster;
	}

	public ArrayList<Attack> getAttacks() {
		return attacks;
	}

	public void setAttacks(ArrayList<Attack> attacks) {
		this.attacks = attacks;
	}
    
}
