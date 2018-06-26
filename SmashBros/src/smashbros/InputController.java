package smashbros.gameplay;

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
	
	public void newControll(Spieler spieler, int leftKey, int upKey, int downKey, int rightKey, int attackKey) {
		frame.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if(keyCode == leftKey) spieler.setMovingLeft(false);
				else if(keyCode == upKey) spieler.setMovingUp(false);
//				else if(keyCode == downKey) spieler.setMovingLeft(true);
				else if(keyCode == rightKey) spieler.setMovingRight(false);
				System.out.println("released key code: " + (int)e.getKeyChar() + "(tricky).. real: " + e.getKeyCode());
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if(keyCode == leftKey) spieler.setMovingLeft(true);
				else if(keyCode == upKey) spieler.setMovingUp(true);
//				else if(keyCode == downKey) spieler.setMovingLeft(true);
				else if(keyCode == rightKey) spieler.setMovingRight(true);
				else if(keyCode == attackKey) spieler.attack();
				
				System.out.println("pressed key code: " + (int)e.getKeyChar() + "(tricky).. real: " + e.getKeyCode());
			}
		});
	}
	
}
