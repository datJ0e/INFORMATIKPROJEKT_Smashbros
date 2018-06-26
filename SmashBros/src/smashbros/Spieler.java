/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smashbros.gameplay;

import java.awt.Graphics;

/**
 * Spieler. Wie der Name schon sagt. Extendet SpielObjekt
 * @see SpielObjekt
 * @author fre.riedmann
 */
public class Spieler extends SpielObjekt {
	
	private boolean isMovingLeft, isMovingUp, isMovingDown, isMovingRight;
	private int maxAirJumps = 2;   //wie oft man in der luft nochmal springen kann
	private int currentAirJumps = 0;   //wie oft der spieler in der luft schon gepsrungen ist
	private int damageTaken = 0;
	private int facingDirection = 0;
	
	public Spieler(Spiel spiel, float x, float y, float width, float height) {
		super(spiel, x, y, width, height, true);
		super.setGravity(5f);
	}
	
	@Override 
	public void update(long time) {
		super.update(time);
			if(isMovingUp()) jump();
//			float relX = 0, relY = 0;
			if(isMovingLeft()) {
				velX = -2.5f;
				facingDirection = Direction.DIRECTION_LEFT;
			} else if(isMovingRight()) {
				velX = 2.5f;
				facingDirection = Direction.DIRECTION_RIGHT;
			}
			if(super.isAufBoden()) {
				currentAirJumps = 0;
				if(velY>0)velY=0;
			}
			if(!isMovingLeft() && !isMovingRight()) velX = (int)(velX*0.9);
//			moveRelative(relX, relY);
//			if(isMovingDown)
		
//		if(aufBoden && velY>0) velY = 0;
//		boolean anyIntersection = false;
//		for(Boden b : spiel.getAlleBodens()) {
//			if(this.hitbox.intersects(b.hitbox)) anyIntersection = true;
//		}
//		aufBoden = anyIntersection;
	}
	
	@Override
    public void draw(Graphics g) {
        g.fillRect((int)posX, (int)posY, (int)width, (int)height);
        if(hitbox!=null) hitbox.draw(g);
    }
	

	
	public void jump() {
		if(super.isAufBoden() || currentAirJumps < maxAirJumps) {
			if(!super.isAufBoden()) currentAirJumps++;
			velY = -4.5f;
			setMovingUp(false);
		}
	}

	public boolean isMovingLeft() {
		return isMovingLeft;
	}
	
	public void attack() {
		switch(facingDirection) {
			case Direction.DIRECTION_LEFT:
				attackLeft();
				break;
			case Direction.DIRECTION_RIGHT:
				attackRight();
				break;
		}
	}

	public void attackLeft() {
		super.getSpiel().getAttacks().add(Attack.createDefaultAttack(super.getSpiel(), this, Direction.DIRECTION_LEFT));
	}
	
	public void attackRight() {
		super.getSpiel().getAttacks().add(Attack.createDefaultAttack(super.getSpiel(), this, Direction.DIRECTION_RIGHT));
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
	
	public void addDamage(int damage) {
		setDamageTaken(getDamageTaken() + damage);
	}

	public int getDamageTaken() {
		return damageTaken;
	}

	public void setDamageTaken(int damageTaken) {
		this.damageTaken = damageTaken;
	}

//	public boolean isAufBoden() {
//		return aufBoden;
//	}
//
//	public void setAufBoden(boolean aufBoden) {
//		this.aufBoden = aufBoden;
//	}
}
