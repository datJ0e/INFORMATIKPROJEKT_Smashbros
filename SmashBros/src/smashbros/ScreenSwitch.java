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
public class ScreenSwitch {

	public Screen currentScreen;
    public void switchAndDelete (Screen s1, Screen s2) {
    	s1.setVisible(false);
    	s2.setVisible(true);
    	s1.deleteFrame();
    	s1 = null;
    	currentScreen = s2;
    }
    
    public void switchAndInvisible(Screen s1, Screen s2) {
    	s1.setVisible(false);
    	s2.setVisible(true);
    	currentScreen = s2;
    }
    
}
