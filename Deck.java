					
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;

/**
 * A game object displayed using an image.
 * 
 * Note that the image is read from the file when the object is constructed, and that all objects
 * created by this constructor share the same image data (i.e. img is static). This is important for
 * efficiency: your program will go very slowly if you try to create a new BufferedImage every time
 * the draw method is invoked.
 */
public class Deck extends GameObj {
    public static final String IMG_FILE = "back.png";
    public static final int HEIGHT = 70;
    public static final int WIDTH = 55;
    public static final int INIT_POS_X = 350;
    public static final int INIT_POS_Y = 35;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 0;
    
   
    private static BufferedImage img;
    private ArrayList<Card> deck;

    public Deck(int courtWidth, int courtHeight) {
        super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, WIDTH, HEIGHT, courtWidth, courtHeight);
    	this.deck = new ArrayList<Card>(52);
    	String[] rank_arr = {"4", "2", "3",
    	                     "ace", "5", "6", "7", "8", "9",
    	                     "10", "jack", "queen", "king"};
    	String[] suit_arr = {"clubs", "spades", "hearts", "diamonds"};
    	
    	for (int i = 0; i < rank_arr.length; i++) {
    		for (int j = 0; j < suit_arr.length; j++) {
    			Card card = new Card(courtWidth, courtHeight, rank_arr[i], suit_arr[j]);
    			deck.add(card);
    		}
    	}
		Collections.shuffle(deck);
    	
        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }
    
    public Card dealCard() {
    	Card c = deck.remove(0);
    	return c;
    }

    public Card[] dealHand() {
    	Card[] card_arr = new Card[4];
    	card_arr[0] = this.dealCard();
    	card_arr[2] = this.dealCard();
    	card_arr[1] = this.dealCard();
    	card_arr[3] = this.dealCard();
    	return card_arr;
    }
    
    @Override
    public void draw(Graphics g) {
        g.drawImage(img, this.getPx(), this.getPy(), this.getWidth(), this.getHeight(), null);
    }
}