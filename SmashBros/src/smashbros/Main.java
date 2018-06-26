/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smashbros;

/**
 *
 * @author joh.warnke
 */
public class Main {
    public static ScreenSwitch sw = new ScreenSwitch();
    public static void main (String[] args) {
        new Screen(450,300,"SmashBros","login");
    } 
    
    public static ScreenSwitch getScreenSwitch() {
    	return sw;
    }
}
