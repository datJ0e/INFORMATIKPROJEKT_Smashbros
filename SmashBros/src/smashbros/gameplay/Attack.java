package smashbros.gameplay;

import java.awt.Color;
import java.awt.Graphics;

public class Attack extends SpielObjekt {
	
	private Spieler caster;   //der spieler der die attacke "gecastet" hat
	private float relXToPlayer, relYToPlayer;
	private int direction;
	private int age, maxAge;
	public boolean isOld = false;
	
	public Attack(Spiel spiel, Spieler spieler, float relXToPlayer, float relYToPlayer, float width, float height, int maxAge, int DIRECTION) {
		super(spiel, spieler.getPosX()+relXToPlayer, spieler.getPosY()+relYToPlayer, width, height, true);
		this.relXToPlayer = relXToPlayer;
		this.relYToPlayer = relYToPlayer;
		this.setCaster(spieler);
		this.direction = DIRECTION;
		this.maxAge = maxAge;
	}
	
	@Override
	public void update(long time) {
		super.setPosX(getCaster().getPosX()+relXToPlayer);
		super.setPosY(getCaster().getPosY()+relYToPlayer);
		age++;
		isOld = age>=maxAge;
	}
	
	public void hit(Spieler spieler) {
		spieler.addDamage(7);
		float punchVel = (float) (Math.sqrt((spieler.getDamageTaken())*Math.tanh(spieler.getDamageTaken()*0.1))*direction);   //*direction, damit bei direction_left punchVel nach links geht
		spieler.velX += punchVel;
		System.out.println("hit: " + punchVel + ", direction: " + direction);
	}
	
	
	@Override
    public void draw(Graphics g) {
		g.setColor(Color.CYAN);
        g.fillRect((int)posX, (int)posY, (int)width, (int)height);
        if(hitbox!=null) hitbox.draw(g);
    }
	
	
	public static Attack createDefaultAttack(Spiel spiel, Spieler spieler, int direction) {
		int attackWidth = 30;
		float relX = direction==Direction.DIRECTION_LEFT?-attackWidth : spieler.getHitbox().width;
		return new Attack(spiel, spieler, relX, 20, attackWidth, attackWidth, 6, direction);
	}

	public Spieler getCaster() {
		return caster;
	}

	public void setCaster(Spieler caster) {
		this.caster = caster;
	}
}
