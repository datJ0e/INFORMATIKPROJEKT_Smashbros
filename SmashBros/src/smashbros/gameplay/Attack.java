package smashbros.gameplay;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Attack extends SpielObjekt {
	
	private Spieler caster;   //der spieler der die attacke "gecastet" hat
	private float relXToPlayer, relYToPlayer;
	private int direction;
	private int age, maxAge;
	public boolean isOld = false;
	private Image image = null;
	
	public Attack(Spiel spiel, Spieler spieler, float relXToPlayer, float relYToPlayer, float width, float height, int maxAge, int DIRECTION) {
		super(spiel, spieler.getPosX()+relXToPlayer, spieler.getPosY()+relYToPlayer, width, height, true);
		this.relXToPlayer = relXToPlayer;
		this.relYToPlayer = relYToPlayer;
		this.setCaster(spieler);
		this.direction = DIRECTION;
		this.maxAge = maxAge;
		load();
	}
	
	public void load() {
		try {
//			image = ImageIO.read(ResourceLoader.load("ArenaFull.jpg"));
			image = ImageIO.read(new File("K:\\Klasse 11\\Informatik-Projekt\\SmashBros\\Grafik\\BenutzteGrafik\\explosion1.png"));
			image = toCompatibleImage(createResizedCopy(image, (int)width, (int)height, false));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		isOld = true;
	}
	
	
	@Override
    public void draw(Graphics g) {
//		g.setColor(Color.CYAN);
//        g.fillRect((int)posX, (int)posY, (int)width, (int)height);
//        if(hitbox!=null) hitbox.draw(g);
		g.drawImage(image, (int)posX, (int)posY, null);
    }
	
	
	public static Attack createDefaultAttack(Spiel spiel, Spieler spieler, int direction) {
		int attackWidth = spiel.getFenster().getWidth()/(1600/30);
		int relYToPlayer = spiel.getFenster().getHeight()/(900/20);
		float relX = direction==Direction.DIRECTION_LEFT?-attackWidth : spieler.getHitbox().width;
		return new Attack(spiel, spieler, relX, relYToPlayer, attackWidth, attackWidth, 6, direction);
	}

	public Spieler getCaster() {
		return caster;
	}

	public void setCaster(Spieler caster) {
		this.caster = caster;
	}
	
	
	
	
	
	BufferedImage createResizedCopy(Image originalImage, int scaledWidth, int scaledHeight, boolean preserveAlpha) {
        System.out.println("resizing...");
        int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
        Graphics2D g = scaledBI.createGraphics();
        if (preserveAlpha) {
            g.setComposite(AlphaComposite.Src);
        }
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null); 
        g.dispose();
        return scaledBI;
    }
	
	private BufferedImage toCompatibleImage(BufferedImage image) {
	    GraphicsConfiguration gfxConfig = GraphicsEnvironment.
	        getLocalGraphicsEnvironment().getDefaultScreenDevice().
	        getDefaultConfiguration();

	    //falls das bild schon optimiert ist
	    if (image.getColorModel().equals(gfxConfig.getColorModel()))
	        return image;

	    //neues leeres bild erstellen das optimiert ist
	    BufferedImage newImage = gfxConfig.createCompatibleImage(
	            image.getWidth(), image.getHeight(), image.getTransparency());
	    	
	    Graphics2D g2d = newImage.createGraphics();

	    //neues bild zeichnen
	    g2d.drawImage(image, 0, 0, null);
	    g2d.dispose();
	    
	    return newImage; 
	}
}
