import java.util.ArrayList;
import java.util.List;

public class Dealer extends Player {
	
	public Dealer(Card one, Card two) {
		super();	
		addCard(one);
		addCard(two);
	}
	
	public List<Card> takeTurn(Deck deck) {
		while (this.getValue() < 17) {
			Card c = deck.dealCard();
			addCard(c);
			System.out.println("Dealer value: " + this.getValue());
		}
	
		return this.getHand();
	}
}
