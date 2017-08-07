/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.*;
import javax.swing.border.Border;
import javax.xml.soap.Text;


/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
	String name;

    public void run() {
        // NOTE : recall that the 'final' keyword notes immutability even for local variables.

        // Top-level frame in which game components live
        // Be sure to change "TOP LEVEL FRAME" to the name of your game
        final JFrame frame = new JFrame("Blackjack");
        frame.setLocation(300, 300);
        

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Running...");
        status_panel.add(status);

        // Main playing area
        final GameCourt court = new GameCourt(status);
        frame.add(court, BorderLayout.CENTER);
        
        // NewGame button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);
        

        // Note here that when we add an action listener to the reset button, we define it as an
        // anonymous inner class that is an instance of ActionListener with its actionPerformed()
        // method overridden. When the button is pressed, actionPerformed() will be called.
        final JButton leave_game = new JButton("Leave Game");
        leave_game.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	BufferedReader in = null;
				try {
					in = new BufferedReader(new FileReader("smallDictionary.txt"));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					System.out.println("Can't find smalldictionary");
					e1.printStackTrace();
				}
								           	
				
            	String name= JOptionPane.showInputDialog("Please input your name for the high winnings board!");
            	name = name.trim();
            	Winnings addWin = new Winnings(name, court.getWinnings());
            	
				/*
				 * take name, read out ordered pairs into a collection, 
				 * add to the collection properly, iterate through and write out 
				 */				
			
               ArrayList<Winnings> winningsList = new ArrayList<Winnings>();
                
               try { 
                	String s = in.readLine();
                	while (s != null) {
                		int length = s.length();
                		if (length == 0) {
                			throw new IOException("Blank line in file");
                		}
                		for (int i = 0; i < length; i++) {
                			Winnings input = null;
                			if (s.charAt(i) == ' ') {
                				System.out.println("found a space in the input file");
                				String readName = s.substring(0, i);
                				String wonInit = s.substring(i+1);
                				int won = Integer.parseInt(wonInit);
                				input = new Winnings(readName, won);
	          				} 
                			if (input != null) {
                				winningsList.add(input);
                				}
                			}
                		s = in.readLine();	  
          		  }  
                	in.close();                	
                }
               
                catch (IOException e1) {
                	e1.printStackTrace();
                	}
               
               winningsList.add(addWin);
               Collections.sort(winningsList);
               
               BufferedWriter out = null;
				try {
					out = new BufferedWriter(new FileWriter("smallDictionary.txt"));
				} catch (IOException e1) {
               	System.out.println("exception thrown in read");
					e1.printStackTrace();
				}
				               
                try {
                	for (Winnings w: winningsList) {
                		out.write(w.getName() + " " + w.getWinnings() + "\n");
    					System.out.println(w.getName() + " " + w.getWinnings());
                	}
					out.close();
					
				} catch (IOException e1) {
                	System.out.println("exception thrown in write");
					e1.printStackTrace();
				} 
                
                String boardText = "Name      Winnings" + "\n";
                
                for (Winnings line: winningsList) {
                	boardText = boardText + line.getName() + "      " +  line.getWinnings() + "\n";
                	System.out.println(line.getName() + "      " +  line.getWinnings());
                }
                
                JOptionPane.showMessageDialog(frame, boardText);

            	System.exit(0);
            }
        });
         
        control_panel.add(leave_game);
        
        final JLabel winnings = new JLabel(" Winnings: $" + court.getWinnings()+ " ");
        Border w_border = BorderFactory.createLineBorder(Color.black);
        Border insideBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border comp_border = BorderFactory.createCompoundBorder(w_border, insideBorder);
        winnings.setOpaque(true);
        winnings.setBorder(comp_border);
        winnings.setBackground(Color.white);
        
        final JButton new_game = new JButton("New Game");
        new_game.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
                BufferedReader in = null;
				try {
					in = new BufferedReader(new FileReader("smallDictionary.txt"));
				} catch (FileNotFoundException e1) {
					System.out.println("Can't find smalldictionary");
					e1.printStackTrace();
				}
								           	
				
            	String name= JOptionPane.showInputDialog("Please input your name for the high winnings board!");
            	name = name.trim();
            	Winnings addWin = new Winnings(name, court.getWinnings());
            	
				/*
				 * take name, read out ordered pairs into a collection, 
				 * add to the collection properly, iterate through and write out 
				 */	
            	
            	
			
               ArrayList<Winnings> winningsList = new ArrayList<Winnings>();
                
               try { 
                	String s = in.readLine();
                	System.out.println("s (String line) is " + s);
                	while (s != null) {
                    	System.out.println("inside for loop, s is not null");
                		int length = s.length();
                		if (length == 0) {
                		throw new IOException("Blank line in file");
                		}
                		for (int i = 0; i < length; i++) {
                			Winnings input = null;
                			if (s.charAt(i) == ' ') {
                				System.out.println("found a space in the input file");
                				String readName = s.substring(0, i);
                				String wonInit = s.substring(i+1);
                				int won = Integer.parseInt(wonInit);
                				input = new Winnings(readName, won);
	          				} 
                			if (input != null) {
                				winningsList.add(input);
                				}
                			}
                		s = in.readLine();	  
          		  }  
                	in.close();                	
                }
               
                catch (IOException e1) {
                	e1.printStackTrace();
                	}
               
               winningsList.add(addWin);
               Collections.sort(winningsList);
               
               BufferedWriter out = null;
				try {
					out = new BufferedWriter(new FileWriter("smallDictionary.txt"));
				} catch (IOException e1) {
               	System.out.println("exception thrown in read");
					e1.printStackTrace();
				}
				
                
                try {
                	for (Winnings w: winningsList) {
                		System.out.println("Attempting to write");
                		out.write(w.getName() + " " + w.getWinnings() + "\n");
    					System.out.println(w.getName() + " " + w.getWinnings());
                	}
					out.close();
					
				} catch (IOException e1) {
                	System.out.println("exception thrown in write");
					e1.printStackTrace();
				} 
                
                String boardText = "Name      Winnings" + "\n";
                
                for (Winnings line: winningsList) {
                	boardText = boardText + line.getName() + "      " +  line.getWinnings() + "\n";
                	System.out.println(line.getName() + "      " +  line.getWinnings());
                }
                
                JOptionPane.showMessageDialog(frame, boardText);

                court.newGame();
        		winnings.setText("Winnings: $" + court.getWinnings());
        		System.out.println(court.getWinnings());

            }
        });
        control_panel.add(new_game);
        
 
        final JButton new_round = new JButton("New Round");
        new_round.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.reset();
            }
        });
        control_panel.add(new_round);
        
        final JButton instructions = new JButton("Instructions");
        instructions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String instruction_message = "Welcome to BlackJack! This game follows the rules of traditional Blackjack,\n"
            			+ "where you first must bet any multiple of $5, $25, or $100 (as long as you have \n"
            			+ "enough money). After you bet, click the Deal button to deal the cards and start the round. \n"
            			+ "Once you given your cards, you can either choose to Double Down (double your bet and \n"
            			+ "recieve just one more card), Hit (get an additional card), or Stand (keep your hand and \n"
            			+ "allow the dealer to take their turn). You can choose to hit as long as you haven't yet broken \n"
            			+ "(gone over 21). Below are some helpful reminders of the game's rules!: \n"
            			+ "- The dealer must hit until they have above 17. \n"
            			+ "- An ace can be an 11 or a 1. \n"
            			+ "- Face cards add a value of 10 to your hand. \n "
            			+ "- If you get Blackjack (21), you win double what you bet! \n"
            			+ "- You win the round if your hand has a higher value than the dealer's. \n"
            			+ "- Tip: Assume the dealer's hidden card is 10 and take action based on that.";
            			
                JOptionPane.showMessageDialog(frame, instruction_message);

            }
        });
        control_panel.add(instructions);
        
        
        final JPanel user_panel = new JPanel();
        frame.add(user_panel, BorderLayout.EAST);
        user_panel.setLayout(new BoxLayout(user_panel, BoxLayout.Y_AXIS));
 
        final JButton deal = new JButton("Deal");
        deal.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		court.deal();
        	}
        });
        user_panel.add(deal); 
        
        final JButton hit = new JButton("Hit");
        hit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		court.hit();
        		winnings.setText("Winnings: $" + court.getWinnings());
        	}
        });
        user_panel.add(hit);
        
        final JButton stand = new JButton("Stand");
        stand.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		court.stand();
        		winnings.setText("Winnings: $" + court.getWinnings());

        	}
        });
        user_panel.add(stand);
        
        
        final JButton bet5 = new JButton("Bet $5");
        bet5.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		court.bet(5);
        		winnings.setText("Winnings: $" + court.getWinnings());
        	}
        });
        
 
        final JPanel bet_panel = new JPanel();
        frame.add(bet_panel, BorderLayout.SOUTH);
        
        bet_panel.add(winnings);
        bet_panel.add(bet5);
        
        final JButton bet25 = new JButton("Bet $25");
        bet25.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		court.bet(25);
        		winnings.setText("Winnings: $" + court.getWinnings());
        	}
        });
        bet_panel.add(bet25);
        
        
        final JButton bet100 = new JButton("Bet $100");
        bet100.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		court.bet(100);
        		winnings.setText("Winnings: $" + court.getWinnings());
        	}
        });
        bet_panel.add(bet100);
        
        final JButton doubleDown = new JButton("Double Down");
        doubleDown.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		court.doubleDown();
        		winnings.setText("Winnings: $" + court.getWinnings());
        	}
        });
        bet_panel.add(doubleDown);
        
        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start game
        court.reset();
    }

    /**
     * Main method run to start and run the game. Initializes the GUI elements specified in Game and
     * runs it. IMPORTANT: Do NOT delete! You MUST include this in your final submission.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}