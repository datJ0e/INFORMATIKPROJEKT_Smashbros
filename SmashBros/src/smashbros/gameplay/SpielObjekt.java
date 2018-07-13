/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smashbros.gameplay;

import java.awt.Graphics;

/**
 * Superklasse, alle Objekte im Spiel extenden diese Klasse
 * @see Spieler
 * @author fre.riedmann
 */
public class SpielObjekt {
    
    float velX, velY, posX, posY, width, height;
    private float gravity;
    private boolean aufBoden = false;
    private Spiel spiel;
    Hitbox hitbox = null;
    
    public SpielObjekt(Spiel spiel, float x, float y, float width, float height, boolean hasHitbox) {
        this.posX = x;
        this.posY = y;
        this.width = width;
        this.height = height;
        this.setSpiel(spiel);
		if(hasHitbox) hitbox = new Hitbox(x, y, width, height);
    }
    
    
    
    /**
     * updated das spielobjekt (beschleunigungen usw)
     * @param time Zeit seit dem letzten update in millisekunden
     */
    public void update(long time) {
    	update(time, isAufBoden());
    }
    
    public void update(long time, boolean aufBoden) {
    	if(time<=0) return;
        //MINUS gravity, weil die koordinaten nach unten hin ja kleiner werden (sonst würde alles nach oben fallen)
        if(!aufBoden) this.velY += gravity*(float)(time/1000f);   //1000/time damit sekunden rauskommen
        moveRelative(velX, velY);
        checkBodenCollision();
        if(hitbox!=null) {
        	hitbox.setPosX(posX);
			hitbox.setPosY(posY);
        }
    }
    
	public void moveRelative(float x, float y) {
		float movementStep = 0.2f;
		if(x>0) {
			for(float dx = 0f; dx <= x; dx+=movementStep) {
				posX += movementStep;
				hitbox.setPosX(posX);
			}
		}
		if(y>0) {
			for(float dy = 0f; dy <= y; dy+=movementStep) {
				if(!checkBodenCollision()) posY += movementStep;
				hitbox.setPosY(posY);
//				 break;
			}
		}
		if(x<0) {
			for(float dx = 0f; dx >= x; dx-=movementStep) {
				posX -= movementStep;
				hitbox.setPosX(posX);
			}
		}
		if(y<0) {
			for(float dy = 0f; dy >= y; dy-=movementStep) {
				posY -= movementStep;
				hitbox.setPosY(posY);
//				if(checkBodenCollision()) break;
			}
		}
	}
	
	public boolean checkBodenCollision() {
		boolean anyIntersection = false;
		for(Boden b : getSpiel().getAlleBodens()) {
			if(b.intersectsWithMe(this.hitbox)) anyIntersection = true;
		}
		aufBoden = anyIntersection;
		return isAufBoden();
	}
    
    public void addVelocities(float velX, float velY) {
        this.velX += velX;
        this.velY += velY;
    }
    
    public void draw(Graphics g) {
        g.fillRect((int)posX, (int)posY, (int)width, (int)height);
        if(hitbox!=null) hitbox.draw(g);
    }
    
    
    public boolean intersects(Hitbox hb) {
    	return this.hitbox.intersects(hb);
    }
    
    /*
    * Getter und Setter
    */
    public void setGravity(float gravity) {
        this.gravity = gravity;
    }
    
    public float getPosX() {
        return this.posX;
    }
    
    public void setPosX(float posX) {
    	this.posX = posX;
    	this.hitbox.posX = posX;
    }
    
    public float getPosY() {
        return this.posY;
    }
    
    public void setPosY(float posY) {
    	this.posY = posY;
    	this.hitbox.posY = posY;
    }
    
    public float getVelX() {
        return this.velX;
    }
    
    public float getVelY() {
        return this.velY;
    }
    
	public boolean isAufBoden() {
		return aufBoden;
	}
	
	public Hitbox getHitbox() {
		return hitbox;
	}



	public Spiel getSpiel() {
		return spiel;
	}



	public void setSpiel(Spiel spiel) {
		this.spiel = spiel;
	}
    
}
