import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.*;


public class MyTests {
	
	@Test public void GetUserValueTest() {
		User user = new User(250);
		Card c1 = new Card(300,300, "10", "clubs");
		Card c2 = new Card(300,300, "10", "diamonds");
		Card c3 = new Card(300,300, "10", "clubs");
		user.addCard(c1);
		user.addCard(c2);
		user.addCard(c3);
		assertTrue(user.getValue() == 30);	
	}
	
	@Test public void UserBetsInBoundsTest() {
		User user = new User(250);
		user.bet(50);
		assertEquals(user.getMoney(), 200);
	}
	
	
	@Test public void UserBetsOutOfBoundsTest() {
		User user = new User(50);
		user.bet(100);
		assertEquals(user.getMoney(), 50);
	}
	
	@Test public void UserDoublesDownTest() {
		User user = new User(250);
		Card c1 = new Card(300,300, "10", "clubs");
		Card c2 = new Card(300,300, "10", "diamonds");
		user.addCard(c1);
		user.addCard(c2);
		user.bet(100);
		user.doubleDown();
		System.out.println(user.getMoney());
		assertEquals(user.getMoney(), 50);
	}
	
	@Test public void UserDoublesDownIllegallyTest() {
		User user = new User(150);
		Card c1 = new Card(300,300, "10", "clubs");
		Card c2 = new Card(300,300, "10", "diamonds");
		user.addCard(c1);
		user.addCard(c2);
		user.bet(100);
		user.doubleDown();
		assertEquals(user.getMoney(), 50);
	}
	
	@Test public void UserGetsBlackJackTest() {
		User user = new User(250);
		Card c1 = new Card(300,300, "10", "clubs");
		Card c2 = new Card(300,300, "10", "diamonds");
		Card c3 = new Card(300,300, "ace", "clubs");
		user.addCard(c1);
		user.addCard(c2);
		user.addCard(c3);
		assertTrue(user.hasBlackJack());	
	}	
	
	@Test public void PlayerGetsAceUnder21Test() {
		User user = new User(250);
		Card c1 = new Card(300,300, "ace", "clubs");
		Card c2 = new Card(300,300, "3", "diamonds");
		Card c3 = new Card(300,300, "2", "clubs");
		user.addCard(c1);
		user.addCard(c2);
		user.addCard(c3);
		assertTrue(user.getValue() == 16);	
	}
	
	@Test public void PlayerGetsAceOver21Test() {
		User user = new User(250);
		Card c1 = new Card(300,300, "10", "clubs");
		Card c2 = new Card(300,300, "5", "diamonds");
		Card c3 = new Card(300,300, "ace", "clubs");
		user.addCard(c1);
		user.addCard(c2);
		user.addCard(c3);
		assertTrue(user.getValue() == 16);	
	}
	
	@Test public void ChangeAceinHandTest() {
		User user = new User(250);
		Card c1 = new Card(300,300, "ace", "clubs");
		Card c2 = new Card(300,300, "2", "diamonds");
		Card c3 = new Card(300,300, "queen", "clubs");
		user.addCard(c1);
		user.addCard(c2);
		assertTrue(user.getValue() == 13);
		user.addCard(c3);
		assertTrue(user.getValue() == 13);	
	}
	
	
	@Test public void UserWinsTest() {
		User user = new User(250);
		user.bet(50);
		user.won();
		assertTrue(user.getMoney() == 300);
	}
	
	@Test public void UserWinsWithBlackJackTest() {
		User user = new User(250);
		user.setBlackJack();
		user.bet(50);
		user.won();
		assertTrue(user.getMoney() == 350);
	}
	
	@Test public void UserLosesTest() {
		User user = new User(250);
		user.bet(50);
		user.lost();
		assertTrue(user.getMoney() == 200);	
	}
	
	@Test public void UserTieTest() {
		User user = new User(250);
		user.bet(50);
		user.tie();
		assertTrue(user.getMoney() == 250);
	}
	

	@Test public void DealerNoTakeTurnTest() {
		Card c1 = new Card(300,300, "10", "clubs");
		Card c2 = new Card(300,300, "8", "diamonds");
		Dealer d = new Dealer(c1,c2);
		Deck deck = new Deck(300,300);
		d.takeTurn(deck);
		List<Card> cardList = new ArrayList<Card>();
		cardList.add(c1);
		cardList.add(c2);
		assertTrue(d.getHand().equals(cardList));
	}
	
	@Test public void DealerTakeTurnTest() {
		Card c1 = new Card(300,300, "2", "clubs");
		Card c2 = new Card(300,300, "8", "diamonds");
		Dealer d = new Dealer(c1,c2);
		Deck deck = new Deck(300,300);
		d.takeTurn(deck);
		List<Card> cardList = new ArrayList<Card>();
		cardList.add(c1);
		cardList.add(c2);
		assertFalse(d.getHand().equals(cardList));
	}
	
	@Test public void DeckRandomizedTest() {
		Deck d1 = new Deck(300,300);
		Card c = d1.dealCard();
		Card c1 = d1.dealCard();
		Card c2 = d1.dealCard();
		Deck d2 = new Deck(300,300);
		Card c3 = d2.dealCard();
		Card c4 = d2.dealCard();
		Card c5 = d2.dealCard();
		
		assertFalse(c.getRank().equals(c3.getRank()) && c.getSuit().equals(c3.getSuit()));
		assertFalse(c1.getRank().equals(c4.getRank()) && c1.getSuit().equals(c4.getSuit()));
		assertFalse(c2.getRank().equals(c5.getRank()) && c2.getSuit().equals(c5.getSuit()));	
	}	
	
}