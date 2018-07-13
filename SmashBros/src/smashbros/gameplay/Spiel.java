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
	private int deaths = 0;
	
	public Spieler(Spiel spiel, float x, float y, float width, float height) {
		super(spiel, x, y, width, height, true);
		super.setGravity(getSpiel().getFenster().getHeight()/(900/(5f)));
	}
	
	@Override 
	public void update(long time) {
		super.update(time);
			if(isMovingUp()) jump();
//			float relX = 0, relY = 0;
			if(isMovingLeft()) {   //falls nach links gedrückt wurde, nach links laufen
				if(super.isAufBoden()) {
					velX = getSpiel().getFenster().getWidth()/(1600/(-3.5f));
				} else {   //falls in der Luft, nur ein bisschen entgegensteuern
					if(velX>-2) velX -= 0.5f;
				}
				facingDirection = Direction.DIRECTION_LEFT;
			} else if(isMovingRight()) {   //falls nach rechts gedrückt wurde, nach rechts laufen
				if(super.isAufBoden()) {
					velX = getSpiel().getFenster().getWidth()/(1600/(3.5f));
				} else {   //falls in der Luft, nur ein bisschen entgegensteuern
					if(velX<2) velX += getSpiel().getFenster().getWidth()/(1600/(0.5f));
				}
				facingDirection = Direction.DIRECTION_RIGHT;
			}
			if(super.isAufBoden()) {
				currentAirJumps = 0;
				if(velY>0)velY=0;
			}
			if(super.isAufBoden() && !isMovingLeft() && !isMovingRight()) velX = (float)(velX*0.8f);
			if(Math.abs(velX) < 0.1) velX = 0;
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
			velY = getSpiel().getFenster().getHeight()/(900/(-4.5f));
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
	
	public void die() {
		setDeaths(getDeaths() + 1);
		this.posY = 10;
		this.posX = getSpiel().getFenster().getWidth()/2 + this.width/2;
		this.velX = 0;
		this.velY = 3;
		damageTaken = 0;
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

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

//	public boolean isAufBoden() {
//		return aufBoden;
//	}
//
//	public void setAufBoden(boolean aufBoden) {
//		this.aufBoden = aufBoden;
//	}
}
