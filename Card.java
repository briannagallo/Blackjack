
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * A game object displayed using an image.
 * 
 * Note that the image is read from the file when the object is constructed, and that all objects
 * created by this constructor share the same image data (i.e. img is static). This is important for
 * efficiency: your program will go very slowly if you try to create a new BufferedImage every time
 * the draw method is invoked.
 */
public class Card extends GameObj {
    public static final int HEIGHT = 70;
    public static final int WIDTH = 55;
    public static final int INIT_POS_X = 45;
    public static final int INIT_POS_Y = 200;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 0;

    private BufferedImage img;
    
    private String name;
    private String suit;
    private String rank;
    private int value;
    private boolean isAce = false;

    public Card(int courtWidth, int courtHeight, String rank, String suit) {
        super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, WIDTH, HEIGHT, courtWidth, courtHeight);

        this.rank = rank;
        this.suit = suit;   
        
        
        if (rank.equals("jack")
        		|| rank.equals("king") 
        		|| rank.equals("10") 
        		|| rank.equals("queen")) {
        	this.value = 10;
        	this.name = rank + "_of_" + suit + ".png";
        }
        else if (rank.equals("ace")) {
        	this.isAce = true;
        	this.name = "ace_of_" + suit + ".png";
        	this.value = 1;
        }
        else {
        	this.value = Integer.parseInt(rank);
        	this.name = this.value + "_of_" + suit + ".png";
        }

        try {
            if (img == null) {
                img = ImageIO.read(new File(name));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }
    
    public int getValue() {
    	return this.value;
    }
    
    
    public String getRank() {
    	return this.rank;
    }
    
    public String getSuit() {
    	return this.suit;
    }
    
    public void setHighAce() {
    	if (this.isAce) {
    		this.value = 11;
    	}
    }
    
    public void setLowAce() {
    	if (this.isAce) {
    		this.value = 1;
    	}
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, this.getPx(), this.getPy(), this.getWidth(), this.getHeight(), null);
    }
}
