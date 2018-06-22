package smashbros;

import java.awt.Color;
import java.awt.Graphics;

public class Boden extends SpielObjekt {
	
	Spiel spiel;
	
	
	public Boden(Spiel spiel, float x, float y, float width) {
		super(spiel, x, y, width, 20, true);
		this.spiel = spiel;
		super.hitbox.setIsBoden(true);
	}
	
	
	public boolean intersectsWithMe(Hitbox hb) {
		return (hb.intersectsTopOf(this.hitbox) && (hb.getY()+hb.getHeight()<=this.hitbox.posY+10));
	}
	
	@Override
	public void update(long time) {
		velX = 0;
		velY = 0;
	}
	
	@Override
    public void draw(Graphics g) {
		Color c = new Color(81, 43, 10, 255);
		g.setColor(c);
        g.fillRect((int)posX, (int)posY, (int)width, (int)height);
        if(hitbox!=null) hitbox.draw(g);
    }

}
