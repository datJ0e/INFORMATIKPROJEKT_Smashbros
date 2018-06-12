/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smashbros;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Canvas;
import java.awt.Graphics;
/**
 *
 * @author joh.warnke
 */
public class Screen{
    JFrame f;
    Drawer d;
    Spiel s;
    boolean running = true;
    public Screen(int width, int height, String name, String bgimg) {
    f = new JFrame(name);
    d = new Drawer();
    d.setFocusable(false);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setSize(new Dimension(width, height));
    f.add(d);
    f.setLocation(50,50);
    f.setVisible(true);
    s = new Spiel(f);
    s.start();
    updater.run();
    }
    public static void main (String[] args) {
        new Screen(500,400,"test","test");
    }       
    
    public class Drawer extends Canvas {
		private static final long serialVersionUID = 1L;

		@Override
        public void paint(Graphics g){
            s.draw(g);
        }
    }
    
    //FUNKTIONIERT NICHT
	Runnable updater = new Runnable() {
		@Override
		public void run() {
			System.out.println("repainter staterded");
			long last = System.currentTimeMillis()-16;
			while(true) {
				f.repaint();
				System.out.println("repainted");
				last = System.currentTimeMillis();
				try {Thread.sleep(16);} catch (InterruptedException e) {e.printStackTrace();}
			}
		}
	};
    
    
}
