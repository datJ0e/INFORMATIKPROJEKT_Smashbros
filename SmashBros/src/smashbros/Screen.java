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
    public Screen(int width, int height, String name, String bgimg) {
    f = new JFrame(name);
    d = new Drawer();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setSize(new Dimension(width, height));
    f.add(d);
    f.setLocation(50,50);
    f.setVisible(true);
    s = new Spiel(f);
    }
    public static void main (String[] args) {
        new Screen(500,400,"test","test");
    }       
    
    public class Drawer extends Canvas {
        
        @Override
        public void paint(Graphics g){
            s.draw(g);
        }
    }
}
