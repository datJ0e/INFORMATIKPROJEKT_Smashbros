/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smashbros;

/**
 * Klasse die alle Objekte usw managet
 * @author fre.riedmann
 */
public class Spiel {
	
	private Spieler spieler1;
	private Spieler spieler2;
	private Spiel instance;
	private boolean running;
	
	public Spiel() {
		spieler1 = new Spieler(0, 0, 60, 100);   //spieler bild ist ca 50 hoch & 30 breit
		spieler2 = new Spieler(300, 0, 60, 100);
		instance = this;
		running = true;
	}
	
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
