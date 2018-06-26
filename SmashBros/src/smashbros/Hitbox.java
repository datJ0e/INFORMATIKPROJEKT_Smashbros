package smashbros;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Helferklasse die ganz viel rechnet.
 * Wird benutzt um zu schauen ob SpielObjekte sich berÃ¼hren
 * @author fre.riedmann
 */

public class Hitbox {
	
	float posX, posY, width, height;
	boolean boden = false;   //wenn boden true ist, dann zählen nur kollisionen bei denen die hitbox von oben kommt
	
	public Hitbox(float x, float y, float width, float height) {
		this.posX = x;
		this.posY = y;
		this.width = width;
		this.height = height;
	}
	
	public boolean intersectsRightOf(Hitbox hb) {
		float myMaxX = this.posX + this.width;
		float myLowX = myMaxX;
		float myMaxY = this.posY + this.height;
		float myLowY = this.posY;
		float thatMaxX = hb.getX() + hb.getWidth();
		float thatLowX = hb.getX();
		float thatMaxY = hb.getY() + hb.getHeight();
		float thatLowY = hb.getY();
		
		return intersectsXandY(myMaxX, myLowX, myMaxY, myLowY, thatMaxX, thatLowX, thatMaxY, thatLowY);
	}
	
	public boolean intersectsBottomOf(Hitbox hb) {
		float myMaxX = this.posX + this.width;
		float myLowX = this.posX;
		float myMaxY = this.posY + this.height;
		float myLowY = this.posY;
		float thatMaxX = hb.getX() + hb.getWidth();
		float thatLowX = hb.getX();
		float thatMaxY = hb.getY() + hb.getHeight();
		float thatLowY = thatMaxY;   //tut so als ob nur der boden eine hitbox wäre
		
		return intersectsXandY(myMaxX, myLowX, myMaxY, myLowY, thatMaxX, thatLowX, thatMaxY, thatLowY);
	}
	
	public boolean intersectsTopOf(Hitbox hb) {
		float myMaxX = this.posX + this.width;
		float myLowX = this.posX;
		float myMaxY = this.posY + this.height;
		float myLowY = this.posY;
		float thatMaxX = hb.getX() + hb.getWidth();
		float thatLowX = hb.getX();
		float thatMaxY = hb.getY();   //tut so als ob nur die "decke" eine hitbox wäre
		float thatLowY = hb.getY();
		
		return intersectsXandY(myMaxX, myLowX, myMaxY, myLowY, thatMaxX, thatLowX, thatMaxY, thatLowY);
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
		
		return intersectsXandY(myMaxX, myLowX, myMaxY, myLowY, thatMaxX, thatLowX, thatMaxY, thatLowY);
		
//		boolean intersectsX = (myLowX < thatLowX && myMaxX > thatLowX) || (myLowX > thatLowX && myMaxX < thatMaxX) || (myLowX < thatMaxX && myMaxX > thatMaxX);
//		boolean intersectsY = (myLowY < thatLowY && myMaxY > thatLowY) || (myLowY > thatLowY && myMaxY < thatMaxY) || (myLowY < thatMaxY && myMaxY > thatMaxY);
		
//		if(boden) intersectsY = (intersectsY && (thatMaxY<=myMaxY));
//		if(intersectsX && intersectsY) System.out.println("INTESECTION: tly: " + thatLowY + ", mmy: " + myMaxY);
//		return intersectsX && intersectsY;
	}
	
	public boolean intersectsXandY(float myMaxX, float myLowX, float myMaxY, float myLowY, float thatMaxX, float thatLowX, float thatMaxY, float thatLowY) {
		boolean intersectsX = (myLowX <= thatLowX && myMaxX >= thatLowX) || (myLowX >= thatLowX && myMaxX <= thatMaxX) || (myLowX <= thatMaxX && myMaxX >= thatMaxX);
		boolean intersectsY = (myLowY <= thatLowY && myMaxY >= thatLowY) || (myLowY >= thatLowY && myMaxY <= thatMaxY) || (myLowY <= thatMaxY && myMaxY >= thatMaxY);
		return intersectsX && intersectsY;
	}
	
	
	public void draw(Graphics g) {
		Color c = new Color(1f, 1f, 0f, 1f);
		g.setColor(c);
        g.drawRect((int)posX, (int)posY, (int)width, (int)height);
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
	
	public void setIsBoden(boolean isBoden) {
		boden = isBoden;
	}
}
