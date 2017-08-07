/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact with one another. Take
 * time to understand how the timer interacts with the different methods and how it repaints the GUI
 * on every tick().
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {

    // the state of the game logic
   /* private Square square; // the Black Square, keyboard control-- ME: TURN INTO cards,, 
    private Circle snitch; // the Golden Snitch, bounces- delete?
    private Poison poison; // the Poison Mushroom, doesn't move -- turn into buttons!
    */
    
    private User user;
    private Dealer dealer;
    private Deck deck;
    private int winnings = 250;
    private boolean canBet;
    private boolean canDeal;
    private boolean canHit;
    private List<Card> userhand;
    private List<Card> dealerhand;
    private boolean roundOver;
    private BufferedImage img;
    private BufferedImage boardImg;
    private boolean canShowSecond;
    private boolean showWinnings;
     

    public boolean playing = false; // whether the game is running 
    private JLabel status; // Current status text, i.e. "Running..."

    // Game constants
    public static final int COURT_WIDTH = 300;
    public static final int COURT_HEIGHT = 300;
    public static final int SQUARE_VELOCITY = 4;

    // Update interval for timer, in milliseconds
    public static final int INTERVAL = 35;

    public GameCourt(JLabel status) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        

        // The timer is an object which triggers an action periodically with the given INTERVAL. We
        // register an ActionListener with this timer, whose actionPerformed() method is called each
        // time the timer triggers. We define a helper method called tick() that actually does
        // everything that should be done in a single timestep.
        Timer timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.start(); // MAKE SURE TO START THE TIMER!

        // Enable keyboard focus on the court area.
        // When this component has the keyboard focus, key events are handled by its key listener.
        setFocusable(true);
        this.status = status;
    }
       
    public void newGame() {
    	this.winnings = 250;
    	reset();
    	/*
    	 * IO
    	 */
    }

    /**
     * (Re-)set the game to its initial state.
     */
    public void reset() {
    	
        try {
            if (img == null) {
                img = ImageIO.read(new File("back.png"));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        } 
        
        try {
            if (boardImg == null) {
                boardImg = ImageIO.read(new File("bj_board.jpg"));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        } 
    
    
        deck = new Deck(COURT_WIDTH, COURT_HEIGHT);
        
        user = new User(winnings);
        canDeal = false;
        canHit = false;
        canBet = true;
        userhand = null;
        dealerhand = null;
        roundOver = false;
        canShowSecond = false;
        showWinnings = false;
        
        playing = true;
        status.setText("Running...");
        

        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }
    
    public void deal() {
    	if (canDeal) {
    		Card[] dealt = deck.dealHand();
            user.createHand(dealt[0],dealt[2]);
            userhand = new ArrayList<Card>(user.getHand());
            dealer = new Dealer(dealt[1], dealt[3]);
            dealerhand = new ArrayList<Card>(dealer.getHand());
			canDeal = false;
    		canBet = false;
    		canHit = true;
    	}
    	else System.out.println("Sorry, you cannot deal right now!"); 	
    }
    
    public void bet(int amount) {
    	if (canBet && user.bet(amount) && !roundOver) {
    		canDeal = true;
    	}
    	winnings = user.getMoney();
    }
    
    public void doubleDown() {
    	if (!roundOver && user.doubleDown()) {
			System.out.println("User doubled down!");
			winnings = user.getMoney();
			Card c = deck.dealCard();
			user.addCard(c);
			userhand.add(c);
			stand();
    	}
    	else System.out.println("Sorry, you can only double down right after the cards have been dealt");
    }
    
    public void hit() {
		if (!roundOver && canHit) {
    		Card c = deck.dealCard();
    		user.addCard(c);
    		userhand.add(c);
    		if (user.getValue() > 21) {
    			int money = user.lost();
    			winnings = money;
    			System.out.println("User lost, over 21.");
    			roundOver = true; 			
    		}
    	}
    	 
    }
    
    public void stand() {
    	/*
    	 * reveal second card, go
    	 */
    	if (!roundOver) {
			canShowSecond = true;
        	canHit = false;
        	int money;
    		List<Card> dealercards = dealer.takeTurn(deck);
    		dealerhand = dealercards;
    		
    		if (dealer.getValue() > 21) {
    			money = user.won();
    			System.out.println("User won!");
    		}
    		
    		else if (dealer.getValue() == user.getValue()) {
    			money = user.tie();
    			System.out.println("User tied!");
			
    		}
    		
    		else if (dealer.getValue() < user.getValue()) {
    			money = user.won();
    			System.out.println("User won!");

    		}
    		
    		else {
    			money = user.lost();
    			System.out.println("User lost!");

    		}
    		winnings = money;
    		roundOver = true;
    	}
    }    
    public int getWinnings() {
    	return this.winnings;
    }
    
    public void highWinnings() {
    	showWinnings = true;
    }

    /**
     * This method is called every time the timer defined in the constructor triggers.
     */
    void tick() {
        if (playing) {
        	
            // update the display
            repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(boardImg, 0, 0, 475, 300, null);

        deck.draw(g);
        
        if (userhand != null) {
        	int counter = 0;
            for (Card c: userhand) {
            	c.setPx(100 + counter*30);
            	c.setPy(150);
            	counter ++;
            	c.draw(g);
            }
        }       
        if (dealerhand != null) {
        	int counter = 0;
            for (Card c: dealerhand) {
            	/*
            	 * hide second hard
            	 */
            	c.setPx(100 + counter*30);
            	c.setPy(35);
            	counter ++;
            	c.draw(g);
            	if (!canShowSecond) {
            		g.drawImage(img, 130, 35, 55, 70, null);
            	}
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}