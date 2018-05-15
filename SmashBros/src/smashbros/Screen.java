/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smashbros;
import javax.swing.JFrame;
import java.awt.Dimension;
/**
 *
 * @author joh.warnke
 */
public class Screen{
    
    public Screen(int width, int height, String name, String bgimg) {
    JFrame f = new JFrame(name);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setSize(new Dimension(width, height));
    f.setLocation(50,50);
    f.setVisible(true);
    }
    public static void main (String[] args) {
        new Screen(500,400,"test","test");
    }       
}
