/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smashbros;

import java.awt.Graphics;

/**
 * Superklasse, alle Objekte im Spiel extenden diese Klasse
 * @see Spieler
 * @author fre.riedmann
 */
public class SpielObjekt {
    
    float velX, velY, posX, posY, width, height;
    private float gravity;
    Hitbox hitbox;
    
    public SpielObjekt(float x, float y, float width, float height, boolean hasHitbox) {
        this.posX = x;
        this.posY = y;
        this.width = width;
        this.height = height;
		if(hasHitbox) hitbox = new Hitbox(x, y, width, height);
    }
    
    
    
    /**
     * updated das spielobjekt (beschleunigungen usw)
     * @param time Zeit seit dem letzten update in millisekunden
     */
    public void update(long time) {
    	if(time<=0) return;
        //MINUS gravity, weil die koordinaten nach unten hin ja kleiner werden (sonst würde alles nach oben fallen)
        this.velY += gravity*(time/1000f);   //1000/time damit sekunden rauskommen
        this.posX += velX;
        this.posY += velY;
        if(hitbox!=null) {
        	hitbox.setPosX(posX);
			hitbox.setPosY(posY);
        }
    }
    
    public void update(long time, boolean aufBoden) {
    	if(time<=0) return;
        //MINUS gravity, weil die koordinaten nach unten hin ja kleiner werden (sonst würde alles nach oben fallen)
        if(!aufBoden) this.velY += gravity*(time/1000f);   //1000/time damit sekunden rauskommen
        this.posX += velX;
        this.posY += velY;
        if(hitbox!=null) {
        	hitbox.setPosX(posX);
			hitbox.setPosY(posY);
        }
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
    
    public float getPosY() {
        return this.posY;
    }
    
    public float getVelX() {
        return this.velX;
    }
    
    public float getVelY() {
        return this.velY;
    }
    
}
