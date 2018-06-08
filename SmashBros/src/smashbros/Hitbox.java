package smashbros;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Helferklasse die ganz viel rechnet.
 * Wird benutzt um zu schauen ob SpielObjekte sich berühren
 * @author fre.riedmann
 */

public class Hitbox {
	
	float posX, posY, width, height;
	
	public Hitbox(float x, float y, float width, float height) {
		this.posX = x;
		this.posY = y;
		this.width = width;
		this.height = height;
	}
	
	public boolean intersects(Hitbox hb) {
		float myMaxX = this.posX + this.width;
		float myLowX = this.posX;
		float myMaxY = this.posY + this.height;
		float myLowY = this.posY;
		float thatMaxX = hb.getX() + hb.getWidth();
		float thatLowX = hb.getX();
		float thatMaxY = hb.getY() + hb.getHeight();
		float thatLowY = hb.getY();
		
		boolean intersectsX = (myLowX < thatLowX && myMaxX > thatLowX) || (myLowX > thatLowX && myMaxX < thatMaxX);
		boolean intersectsY = (myLowY < thatLowY && myMaxY > thatLowY) || (myLowY > thatLowY && myMaxY < thatMaxY);
		
		return intersectsX && intersectsY;
	}
	
	
	public void draw(Graphics g) {
		Color c = new Color(0f, 1f, 0f, 0.5f);
		g.setColor(c);
        g.fillRect((int)posX, (int)posY, (int)width, (int)height);
    }
	
	
	//###################
	//## GETTER/SETTER ##
	//###################
	
	public void setPosX(float posX) {
		this.posX = posX;
	}
	
	public void setPosY(float posY) {
		this.posY = posY;
	}
	
	public float getX() {
		return posX;
	}
	
	public float getY() {
		return posY;
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
}
