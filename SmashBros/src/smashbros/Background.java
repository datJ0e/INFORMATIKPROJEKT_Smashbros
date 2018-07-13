package smashbros;

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Background extends Canvas {

	private static final long serialVersionUID = 1L;
	BufferedImage image;
	int width,height;
	
	public Background(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		load();
	}
	
	public void load() {
		try {
			image = ImageIO.read(new File("K:\\Klasse 11\\Informatik-Projekt\\SmashBros\\Grafik\\ArenaBG.png"));
			image = toCompatibleImage(createResizedCopy(image, width, height, false));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, this);
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
