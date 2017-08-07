=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: bgallo
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. Collections- Multiple ArrayLists were used in the implementation of this game. An ArrayList was used firstly
  to create a deck of cards that is initially populated with 52 unique cards and can then be decremented with either a 
  deal two hands or deal a single card function. An ArrayList was used because the size of the deck changes size each time
  a card is dealt, and this is a list because the order matters, depending on how the deck was shuffled. Access is only
  needed from the first (0th) index each time. An ArrayList was also used for the Player's (User and dealer's) hand for
  similar reasons. The Players' hands change size throughout the game. 

  2. Subtyping/Inheritance- This game implements a Player class that acts as a Superclass for both a User and Dealer
  class. The Player class implements methods that both the user and the dealer share, such as getValue, getHand, add
  a Card to the hand, hasBlackJack, and setBlackJack. These are common properties of the game players, regardless of
  the player being the dealer or the user. The User class diverges from the Player class in that while it extends Player, 
  also includes all betting, doubling down, and handling of money functionality, because Users have money but dealers don't.
  Unlike the Dealer, the users can either win, lose, or tie. On the other hand, the Dealer extends PLayer as well, but adds on 
  method that the User does not-- takeTurn. Specific to BlackJack, the dealer has set rules that he/she must follow when it is
  their turn. The Dealer is playing to beat the User but he/she can only pick another card when their hand has a value 
  less than 17. If above, they must pass up their turn. 

  3. I/O- At the end of the game when the User chooses to either quit or restart a new game, the User types their name
  into a text pop up and their winnings are subsequently written to a .txt file and added to a high winnings list. The
  high winnings list (Name and Winnings) are then read into the game and displayed in a message pop up, in order of highest
  winnings to lowest. The winnings are read each time and stored in an arrayList of Winnings (class implemented) that 
  can be sorted and added to easily.

  4. JUnit Testing- My game incorporate JUnit testing for the main game state for the User and Dealer class, where the
  functionality for both the User and Dealer class were tested for game situations such as the user betting more or less
  than his/her current amount of money, the user attempting to double down, how the add Card method handles adding cards
  with the special Blackjack Ace functionality (where an Ace can either be valued at 11 or 1), and how the Dealer takes
  his turn given different hand values. All the rules of the game are independent of the GUI and could therefore easily 
  be tested. It also makes sense to have extensive JUnit testing, since the bulk of the game operates independently from 
  the GUI.

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  
Card: This class implements a GameObj card, which is essentially a class that contains 
the ranking and suit of a given card, as well as its Blackjack value (10 if a face card, 1 or 11 if ace). 
In addition, by concatenating both the String rank and String suit (passed into the constructor) of a card,
the card's corresponding unique image can be created and subsequently assigned
 to the card in the constructor. The card class is used directly in the deck class as well as in
 the Player class, where a player holds a hand of Card(s).

Deck: The deck class implements a standard 52 card deck of Cards (class: Card).
Upon instantiating a deck, 52 unique cards are generated randomly and added
to an ArrayList deck. The deck of cards has methods including deal hand and 
deal a single card and there is one deck of cards generated per round of the game,
 and that deck is used to add cards to the Dealer and User's hands.

Player: The player class creates a blackjack player and is called to have a particular hand
and that hand can be added to. The player class also stores the hand's total value (summation
of all the cards' values). While not directly used in this game, the Player class is extended 
in both the User and Dealer class (below).

User: The user class implements any functionality that BlackJack allows a player (not dealer)
of the game to do, including betting, doubling down, and deciding whether or not to hit (add a card)
or stand. This class also can react to winning, losing, or tying, including methods that change the 
user's total winnings based on if they won lost or tied the game. It takes into the constructor the
amount of money the user has and calls the super method, Player, in the constructor as well.

Dealer: The dealer class extends the Player class and is instantiated by passing two initial card
values into the constructor, as well as calling the superclass of Player in the constructor. The dealer
is an automated aspect of BlackJack and is not controlled by the user. As such, its method taketurn() 
causes the dealer object to add a card to its hand until its value is above a certain threshold. a Dealer
is instantiated in the game only after a User has been created and the user has bet, when the user chooses
to start the game by clicking the Deal Cards button. 

Winnings: This class is used solely for the high winnings board that incorporates I/O. The Winnings
class implements the Comparable interface (used to later order the Winnings on the high winnings board.
It is pretty straightforward, Winnings has two fields (an int winnings and String name) and there are basic
getters for both name and winnings. The constructor for Winnings takes in both a String name and int winnings (money). 
The comparable method is implemented by comparing the winnings between two Winnings objects.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?

At first I struggled to incorporate four required game concepts, however I realized that
the dealer and user had many of the same required methods and fields, so I realized I
could create a Player superclass that both the user and dealer could extend.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?

The private state is properly encapsulated because the private fields can not be altered without
using a setter function (if implemented). One thing I might change is that the game requires the win/tie/lose
states to be determined in the GameCourt, as opposed to having a class or function inside the user classes that determine
if a user has won, lost, or tied. I don't think I would refactor if given the chance because the game works well and
does not require a lot of redundant code, and extra features can be added to the game relatively easily.  

========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.

Card images: https://opengameart.org/content/playing-cards-vector-png
Blackjack board image: http://www.highlimitslots.com/blackjack-games/

