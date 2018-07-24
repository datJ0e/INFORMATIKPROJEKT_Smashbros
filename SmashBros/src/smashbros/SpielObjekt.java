/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smashbros;

/**
 * @author fre.riedmann
 */
public class SpielObjekt {
    
    private float velX, velY, posX, posY;
    private float gravity;
    
    public SpielObjekt(float x, float y) {
        this.posX = x;
        this.posY = y;
    }
    
    /**
     * updated das spielobjekt (beschleunigungen usw)
     * @param time Zeit seit dem letzten update in millisekunden
     */
    public void update(long time) {
        this.velY -= gravity;   //MINUS gravity, weil die koordinaten nach unten hin ja kleiner werden (sonst wÃ¼rde alles nach oben fallen)
        this.posX += velX;
        this.posY += velY;
    }
    
    public void addVelocities(float velX, float velY) {
        this.velX += velX;
        this.velY += velY;
    }
    
    //Getter und Setter
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
