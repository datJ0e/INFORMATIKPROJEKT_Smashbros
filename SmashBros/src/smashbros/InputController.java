package smashbros;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

/**
 * Klasse die dafÃ¼r sorgt dass die Spieler sich bewegen wenn eine Taste gedrÃ¼ckt wird
 * @author fre.riedmann
 *
 */


public class InputController {
	
	private JFrame frame;
	
	public InputController(JFrame frame) {
		this.frame = frame;
	}
	
	public void newControll(Spieler spieler, int leftKey, int upKey, int downKey, int rightKey) {
		frame.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				System.out.println("typed key code: " + (int)e.getKeyChar());
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				int keyCode = (int)e.getKeyChar();
				if(keyCode == leftKey) spieler.setMovingLeft(false);
				if(keyCode == upKey) spieler.setMovingUp(false);
//				if(keyCode == downKey) spieler.setMovingLeft(true);
				if(keyCode == rightKey) spieler.setMovingRight(false);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = (int)e.getKeyChar();
				if(keyCode == leftKey) spieler.setMovingLeft(true);
				if(keyCode == upKey) spieler.setMovingUp(true);
//				if(keyCode == downKey) spieler.setMovingLeft(true);
				if(keyCode == rightKey) spieler.setMovingRight(true);
			}
		});
	}
	
}
