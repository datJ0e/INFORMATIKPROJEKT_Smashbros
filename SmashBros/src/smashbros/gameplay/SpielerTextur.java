package smashbros;

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import smashbros.gameplay.Direction;

public class SpielerTextur extends Canvas {

	private static final long serialVersionUID = 1L;
//	BufferedImage image;
	int width,height;
	int x = 0, y = 0;
	int spriteCounter = 0;
	Image[] walkSpritesRight = null;
	Image[] walkSpritesLeft = null;
	Image imageIdleRight = null;
	Image imageIdleLeft = null;
	boolean walking = false;
	int lookingDirection = Direction.DIRECTION_LEFT;
	boolean istSpielerZwei = false;
	
	public SpielerTextur(int width, int height, boolean spielerZwei) {
		super();
		this.width = width;
		this.height = height;
		this.istSpielerZwei = spielerZwei;
		load();
	}
	
	public void load() {
		BufferedImage image;
		walkSpritesRight = new Image[11];
		walkSpritesLeft = new Image[11];
		for(int i = 0; i <= 10; i++) {
			try {
				String url = "K:\\Klasse 11\\Informatik-Projekt\\SmashBros\\Grafik\\BenutzteGrafik\\" + (istSpielerZwei?"1":"") + "Walk" + i + ".png";
				System.out.println("Laden...: " + url);
				image = ImageIO.read(new File(url));
				image = toCompatibleImage(createResizedCopy(image, width, height, false));
				walkSpritesRight[i] = image;
				walkSpritesLeft[i] = mirror(image);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			BufferedImage image2;
			image2 = ImageIO.read(new File("K:\\Klasse 11\\Informatik-Projekt\\SmashBros\\Grafik\\BenutzteGrafik\\" + (istSpielerZwei?"1":"") + "Boi.png"));
			image2 = toCompatibleImage(createResizedCopy(image2, width, height, false));
			imageIdleRight = image2;
			imageIdleLeft = mirror(image2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		if(walking) {
			if(lookingDirection == Direction.DIRECTION_LEFT) {
				g.drawImage(walkSpritesLeft[spriteCounter], x, y, this);
			} else {
				g.drawImage(walkSpritesRight[spriteCounter], x, y, this);
			}
			spriteCounter ++;
			spriteCounter = spriteCounter%11;
		} else {
			if(lookingDirection == Direction.DIRECTION_LEFT) {
				g.drawImage(imageIdleLeft, x, y, this);
			} else {
				g.drawImage(imageIdleRight, x, y, this);
			}
		}
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setWalking(boolean walking) {
		this.walking = walking;
	}
	
	public void setDirection(int direction) {
		this.lookingDirection = direction;
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
	
	private BufferedImage mirror(BufferedImage image) {
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-image.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		image = op.filter(image, null);
		return image;
	}
	
}
