/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smashbros;

/**
 * Spieler. Wie der Name schon sagt. Extendet SpielObjekt
 * @see SpielObjekt
 * @author fre.riedmann
 */
public class Spieler extends SpielObjekt {
	
	private boolean isMovingLeft, isMovingUp, isMovingDown, isMovingRight;
	
	public Spieler(float x, float y, float width, float height) {
		super(x, y, width, height, true);
	}
	
	@Override 
	public void update(long time) {
		super.update(time);
		if(isMovingUp()) jump();
		if(isMovingLeft()) posX -= 10;
		if(isMovingRight()) posX += 10;
//		if(isMovingDown) 
	}
	
	public void jump() {
		velY += 4;
		setMovingUp(false);
	}

	public boolean isMovingLeft() {
		return isMovingLeft;
	}

	public void setMovingLeft(boolean isMovingLeft) {
		this.isMovingLeft = isMovingLeft;
	}

	public boolean isMovingUp() {
		return isMovingUp;
	}

	public void setMovingUp(boolean isMovingUp) {
		this.isMovingUp = isMovingUp;
	}

	public boolean isMovingRight() {
		return isMovingRight;
	}

	public void setMovingRight(boolean isMovingRight) {
		this.isMovingRight = isMovingRight;
	}
}
