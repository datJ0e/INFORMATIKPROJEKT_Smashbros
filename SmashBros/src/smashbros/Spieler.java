/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smashbros;

import java.awt.Graphics;

/**
 * Spieler. Wie der Name schon sagt. Extendet SpielObjekt
 * @see SpielObjekt
 * @author fre.riedmann
 */
public class Spieler extends SpielObjekt {
	
	private boolean isMovingLeft, isMovingUp, isMovingDown, isMovingRight;
	private boolean aufBoden;
	private Spiel spiel;
	
	public Spieler(Spiel spiel, float x, float y, float width, float height) {
		super(x, y, width, height, true);
		super.setGravity(5f);
		this.spiel = spiel;
	}
	
	@Override 
	public void update(long time) {
		super.update(time, aufBoden);
			if(isMovingUp()) jump();
			if(isMovingLeft()) posX -= 1;
			if(isMovingRight()) posX += 1;
//			if(isMovingDown)
		
		if(aufBoden && velY>0) velY = 0;
		boolean anyIntersection = false;
		for(Boden b : spiel.getAlleBodens()) {
			if(this.hitbox.intersects(b.hitbox)) anyIntersection = true;
		}
		aufBoden = anyIntersection;
	}
	
	@Override
    public void draw(Graphics g) {
        g.fillRect((int)posX, (int)posY, (int)width, (int)height);
        if(hitbox!=null) hitbox.draw(g);
    }
	
	public void jump() {
		if(!aufBoden) return;
		velY -= 4;
		setMovingUp(false);
	}

	public boolean isMovingLeft() {
		return isMovingLeft;
	}

	public void setMovingLeft(boolean isMovingLeft) {
		System.out.println("Spieler is moving left: " + isMovingLeft);
		this.isMovingLeft = isMovingLeft;
	}

	public boolean isMovingUp() {
		return isMovingUp;
	}

	public void setMovingUp(boolean isMovingUp) {
		System.out.println("Spieler is moving up: " + isMovingUp);
		this.isMovingUp = isMovingUp;
	}

	public boolean isMovingRight() {
		return isMovingRight;
	}

	public void setMovingRight(boolean isMovingRight) {
		System.out.println("Spieler is moving right: " + isMovingRight);
		this.isMovingRight = isMovingRight;
	}

	public boolean isAufBoden() {
		return aufBoden;
	}

	public void setAufBoden(boolean aufBoden) {
		this.aufBoden = aufBoden;
	}
}
