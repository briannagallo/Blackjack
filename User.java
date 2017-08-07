import java.util.ArrayList;
import java.util.List;

public class User extends Player {
	private int money;
	private int amt_bet;
	
	
	public User(int money) {
		super();
		this.money = money;	
	}
	
	public void createHand(Card one, Card two) {
		addCard(one);
		addCard(two);
		if (this.getValue() == 21) this.setBlackJack();
		
	}
	
	
	public int lost() {
		return this.money;	
	}
	
	public int tie() {
		this.money = this.money + this.amt_bet;
		return this.money;
	}
	
	public int won() {
		if (this.hasBlackJack()) {
			this.money = this.money + 3*this.amt_bet;
			return this.money;
		}
		else {
			this.money = this.money + this.amt_bet + this.amt_bet;
			return this.money;
		}
	}
	
	public boolean bet(int amount) {
		if (this.money - amount >= 0) {
			this.amt_bet = amount;
			this.money = this.money - this.amt_bet;
			return true;
		}
		else {
			return false;			
		}	
	}
	
	public int getMoney() {
		return this.money;
	}
	
	public boolean doubleDown() {
		if (this.getHand() == null) return false;
		else if (this.getHand().size() == 2) {
			boolean doubled = this.bet(this.amt_bet);
			this.amt_bet = 2*amt_bet;
			
			return doubled;
		}
		else {
			return false;
		}
	}
	
}
