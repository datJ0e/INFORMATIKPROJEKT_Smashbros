package smashbros;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

/**
 * Klasse die dafür sorgt dass die Spieler sich bewegen wenn eine Taste gedrückt wird
 * @author fre.riedmann
 *
 */


public class InputController {
	
	private JFrame frame;
	
	public InputController(JFrame frame) {
		this.frame = frame;
	}
	
	public void newControll(Spieler spieler, char leftKey, char upKey, char downKey, char rightKey) {
		frame.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				char keyCode = e.getKeyChar();
				if(keyCode == leftKey) spieler.setMovingLeft(false);
				if(keyCode == upKey) spieler.setMovingUp(false);
//				if(keyCode == downKey) spieler.setMovingLeft(true);
				if(keyCode == rightKey) spieler.setMovingRight(false);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				char keyCode = e.getKeyChar();
				if(keyCode == leftKey) spieler.setMovingLeft(true);
				if(keyCode == upKey) spieler.setMovingUp(true);
//				if(keyCode == downKey) spieler.setMovingLeft(true);
				if(keyCode == rightKey) spieler.setMovingRight(true);
			}
		});
	}
	
}
