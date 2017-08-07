
import java.util.ArrayList;
import java.util.List;

public class Player {
	private int value;
	private boolean hasBlackJack;
	private List<Card> cards;
	
	public Player() {
		this.hasBlackJack = false;
		cards = new ArrayList<Card>();
		if (this.value == 21) this.hasBlackJack = true;
		else this.hasBlackJack = false;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public List<Card> getHand() {
		
		return this.cards;
	}
	
	public void addCard(Card c) {
		this.cards.add(c);
		if (c.getValue() == 1) {
			if ((this.value + 1) == 21) {
				System.out.println("Ace was made to be value 1,  got blackjack");
				this.value = this.value + c.getValue();
				this.hasBlackJack = true;
			}
			else if ((this.value + 11) == 21) {
				System.out.println("Ace was made to be value 11, got blackjack!");
				c.setHighAce();
				this.value = this.value + c.getValue();
				this.hasBlackJack = true;	
			}
			else if ((this.value + 11) < 21) {
				System.out.println("Ace was made to be 11 because it didn't go over 21!");
				c.setHighAce();
				this.value = this.value + c.getValue();
			}
			else {
				System.out.println("Ace was made to be 1, because otherwise it exceeds 21!");
				this.value = this.value + c.getValue();
			}
		}
		boolean containsAce = false;
		int aceHandValue = 1;
		int aceIndex = 100;
		for (Card card: cards) {
			if (card.getRank().equals("ace")) {
				containsAce = true;
				aceIndex = cards.indexOf(card);
				aceHandValue = card.getValue();
			}
		}
		
		if (containsAce && (aceHandValue == 11) && (aceIndex != cards.indexOf(c))) {
			if ((this.value + c.getValue() > 21)) {
				cards.get(aceIndex).setLowAce();
				this.value = this.value - 10;
				this.value = this.value + c.getValue();
			}
			else {
				this.value = this.value + c.getValue();
			}

		}
		else if (!c.getRank().equals("ace")) {
			this.value = this.value + c.getValue();
			if (this.value == 21) {
				this.hasBlackJack = true;
			}
		}
	}
	
	public boolean hasBlackJack() {
		return this.hasBlackJack;
	}
	
	public void setBlackJack() {
		this.hasBlackJack = true;
	}
	
}
