import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MyTest {

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	
	static BaccaratDealer game;
	
	static ArrayList<Card> deck2 =  new ArrayList<Card>();
	static ArrayList<Card> deck3 =  new ArrayList<Card>();
	
	static ArrayList<Card> playerHand =  new ArrayList<Card>();
	static ArrayList<Card> bankerHand =  new ArrayList<Card>();
	
	static ArrayList<Card> playerHand2 =  new ArrayList<Card>();
	static ArrayList<Card> bankerHand2 =  new ArrayList<Card>();
	
	static ArrayList<String> newHand =  new ArrayList<String>();

	
	@BeforeEach
	public void Todo()
	{
		game = new BaccaratDealer();
		deck2.clear();
		playerHand.clear();
		bankerHand.clear();
		bankerHand2.clear();
		playerHand2.clear();
	}
	
//TEST FOR BACCARATDEALER
	
	@Test 
	//testing if RandNum returns a random number
	public void testRandNum1()
	{
		int[] arr1 = new int[10];
		for(int i = 0; i<10; i++)
		{
			// should return an array of random number between 0 and 52
			arr1[i] = BaccaratDealer.RandNum(52);
		}
		
		int[] arr2 = new int[10];
		for(int i = 0; i<10; i++)
		{
			arr2[i] = BaccaratDealer.RandNum(52);
		}
  		assertNotEquals(arr1,arr2, "RandNum does not return random numbers");
	}
	
	@Test 
	//testing if RandNum returns a random number
	public void testRandNum2()
	{
		int[] arr1 = new int[10];
		for(int i = 0; i<10; i++)
		{
			// should return an array of random number between 0 and 52
			arr1[i] = BaccaratDealer.RandNum(52);
		}
		
		int[] arr2 = new int[10];
		for(int i = 0; i<10; i++)
		{
			arr2[i] = BaccaratDealer.RandNum(52);
		}
  		assertNotNull(arr1, "RandNum returns nulls");
  		assertNotNull(arr2, "RandNum returns nulls");
	}

	
	@Test
	//test if deck was populated with 52 cards
 	public void testDeck1()
 	{
		deck2 = BaccaratDealer.generateDeck();
  		assertEquals(52,BaccaratDealer.deckSize(deck2), "Incorrect size of deck");

 	}
	
	@Test
	//test if deck was populated with 52 cards
 	public void testDeck2()
 	{
		deck2 = BaccaratDealer.generateDeck();
  		assertNotNull(deck2, "deck is null");
 	}

	@Test
	//displaying deck to see if all the cards are correct
 	public void dispDeck()
 	{
		deck2 = BaccaratDealer.generateDeck();
		int count = 1;
		 for(int i = 0; i<=51; i++)
		 {
		 System.out.println(Integer.toString(count) +". " +deck2.get(i).getSuit() + " " + Integer.toString(deck2.get(i).getVal()));
		 count ++;
		 }
	
		assertNotEquals(deck2.get(4).getVal(),deck2.get(5).getVal(), "deck contains out repeating vlaues");
		assertNotEquals(deck2.get(7).getVal(),deck2.get(9).getVal(), "deck contains out repeating vlaues");
		assertNotEquals(deck2.get(35).getVal(),deck2.get(50).getVal(), "deck contains out repeating vlaues");
		assertNotEquals(deck2.get(25).getVal(),deck2.get(44).getVal(), "deck contains out repeating vlaues");
		assertNotEquals(deck2.get(16).getVal(),deck2.get(35).getVal(), "deck contains out repeating vlaues");
 	}
	
	@Test
	//testing if deal hand method correclty deals two cards
 	public void testDealHand()
 	{
		deck2 = BaccaratDealer.generateDeck();
		playerHand = BaccaratDealer.dealHand(deck2);
		bankerHand = BaccaratDealer.dealHand(deck2);
		playerHand2 = BaccaratDealer.dealHand(deck2);
		bankerHand2 = BaccaratDealer.dealHand(deck2);
		
		assertNotEquals(playerHand,playerHand2, "different hands are not dealt");
		assertNotEquals(bankerHand2,bankerHand, "different hands are not dealt");
		assertNotEquals(bankerHand2,playerHand, "different hands are not dealt");
		assertNotEquals(bankerHand,playerHand2, "different hands are not dealt");

	   System.out.println("player card: " + playerHand.get(0).getSuit() + Integer.toString(playerHand.get(0).getVal())+ " , " + playerHand.get(1).getSuit() + Integer.toString(playerHand.get(1).getVal()));  
	  System.out.println("banker card: " + bankerHand.get(0).getSuit() +Integer.toString(bankerHand.get(0).getVal())+ " , " + bankerHand.get(1).getSuit()+ Integer.toString(bankerHand.get(1).getVal()));
		 
 	}
	
	@Test
	//testing if deal hand method correclty deals two cards
 	public void testDealHand1()
 	{
		deck2 = BaccaratDealer.generateDeck();
		playerHand = BaccaratDealer.dealHand(deck2);
		bankerHand = BaccaratDealer.dealHand(deck2);
		playerHand2 = BaccaratDealer.dealHand(deck2);
		bankerHand2 = BaccaratDealer.dealHand(deck2);
		
		assertNotNull(playerHand, "deal hand deals out null cards");
		assertNotNull(playerHand2, "deal hand deals out null cards");
		assertNotNull(bankerHand2, "deal hand deals out null cards");
		assertNotNull(bankerHand, "deal hand deals out null cards");
 	}
	
	@Test
	//testing draw one method
 	public void testdrawOne1()
 	{
		Card card1 = new Card(null,0);
		Card card2 = new Card(null,0);
		Card card3 = new Card(null,0);
		Card card4 = new Card(null,0);
		
		deck2 = BaccaratDealer.generateDeck();
		card1 = BaccaratDealer.drawOne(deck2);
		card2 = BaccaratDealer.drawOne(deck2);
		card3 = BaccaratDealer.drawOne(deck2);
		card4 = BaccaratDealer.drawOne(deck2);

		assertNotNull(card2.getVal(), "draw one deals out the null values");
		assertNotNull(card3.getVal(), "draw one deals out the null values");
		assertNotNull(card1.getVal(), "draw one deals out the same cards");
 	}

	@Test
	//testing draw one method
 	public void testdrawOne2()
 	{
		Card card1 = new Card(null,0);
		Card card2 = new Card(null,0);
		Card card3 = new Card(null,0);
		Card card4 = new Card(null,0);
		
		deck2 = BaccaratDealer.generateDeck();
		card1 = BaccaratDealer.drawOne(deck2);
		card2 = BaccaratDealer.drawOne(deck2);
		card3 = BaccaratDealer.drawOne(deck2);
		card4 = BaccaratDealer.drawOne(deck2);

		assertNotNull(card1, "draw one deals out the null card");
		assertNotNull(card2, "draw one deals out the null cards");
		assertNotNull(card3, "draw one deals out the null cards");
		assertNotNull(card4, "draw one deals out the null cards");
 	}
	
	
	@Test 
	//testing the shuffle deck method
	public void testShuffle1()
	{
		deck2 = BaccaratDealer.generateDeck();
		deck3 = BaccaratDealer.shuffleDeck(deck2);
  		assertEquals(52,BaccaratDealer.deckSize(deck3), "Incorrect value");
  		assertEquals(52,BaccaratDealer.deckSize(deck2), "Incorrect value");
		assertNotEquals("should not be equal",deck2.get(7).getSuit(),deck3.get(7).getSuit());
		
	}
	@Test 
	//testing the shuffle deck method
	public void testShuffle2()
	{
		deck2 = BaccaratDealer.generateDeck();
		deck3 = BaccaratDealer.shuffleDeck(deck2);	
		assertNotNull(deck3, "shuffle methond returns a null deck" );	
	}
	@Test
	//test if deck size is correct
 	public void testDeckSize1()
 	{
		deck2 = BaccaratDealer.generateDeck();
  		assertEquals(52,BaccaratDealer.deckSize(deck2), "Incorrect size of deck");
 	}
	@Test
	//test if deck size returns null
 	public void testDeckSize2()
 	{
		deck2 = BaccaratDealer.generateDeck();
  		assertNotNull(BaccaratDealer.deckSize(deck2), "Incorrect size of deck");

 	}

// Testing BaccaratGame  
	//test if cards are evaluated correctly 
 	public void testEvaluateWinnings()
 	{
		deck2 = BaccaratDealer.generateDeck();
		BaccaratGame.currentBet = 45; //chagining currentbet to static
		BaccaratGame.bet = "player"; //chagining currentbet to static
		BaccaratGame.winner = "player";
		double amountWon = BaccaratGame.evaluateWinnings(); 

  		assertEquals(90,amountWon, "Incorrect amount won");
 	}
 	  
 	//test if cards are evaluated correctly 
  	public void testEvaluateWinnings2()
  	{
 		deck2 = BaccaratDealer.generateDeck();
 		BaccaratGame.currentBet = 85; //chagining currentbet to static
 		BaccaratGame.bet = "banker"; //chagining currentbet to static
 		BaccaratGame.winner = "Banker";
 		double amountWon = BaccaratGame.evaluateWinnings(); 

   		assertEquals(80.75,amountWon, "Incorrect amount won");
  	}
  	
  	 // Testing BaccaratGame  
 	//test if cards are evaluated correctly 
  	public void testEvaluateWinnings3()
  	{
 		deck2 = BaccaratDealer.generateDeck();
 		BaccaratGame.currentBet = 22; //chagining currentbet to static
 		BaccaratGame.bet = "DRAW"; //chagining currentbet to static
 		BaccaratGame.winner = "draw";
 		double amountWon = BaccaratGame.evaluateWinnings(); 

   		assertEquals(198,amountWon, "Incorrect amount won");
  	}
  	
 	 // Testing BaccaratGame  
	//test if cards are evaluated correctly 
 	public void testEvaluateWinning4()
 	{
		deck2 = BaccaratDealer.generateDeck();
		BaccaratGame.currentBet = 22; //chagining currentbet to static
		BaccaratGame.bet = "DRAW"; //chagining currentbet to static
		BaccaratGame.winner = "banker";
		double amountWon = BaccaratGame.evaluateWinnings(); 

  		assertEquals(0,amountWon, "Incorrect amount won");
 	}
 	
	 // Testing BaccaratGame  
	//test if cards are evaluated correctly 
	public void testEvaluateWinning5()
	{
		deck2 = BaccaratDealer.generateDeck();
		BaccaratGame.currentBet = 44; //chagining currentbet to static
		BaccaratGame.bet = "DRAW"; //chagining currentbet to static
		BaccaratGame.winner = "player";
		double amountWon = BaccaratGame.evaluateWinnings(); 

 		assertEquals(0,amountWon, "Incorrect amount won");
	}
	
	 // Testing BaccaratGame  
	//test if cards are evaluated correctly 
	public void testEvaluateWinning6()
	{
		deck2 = BaccaratDealer.generateDeck();
		BaccaratGame.currentBet = 15; //chagining currentbet to static
		BaccaratGame.bet = "banker"; //chagining currentbet to static
		BaccaratGame.winner = "player";
		double amountWon = BaccaratGame.evaluateWinnings(); 

		assertEquals(0,amountWon, "Incorrect amount won");
	}
	
 // Testing BaccaratGame logic
	public void testWhoWon1()
	{
		deck2 = BaccaratDealer.generateDeck();
		playerHand = BaccaratDealer.dealHand(deck2);
		bankerHand = BaccaratDealer.dealHand(deck2);
		assertNotNull(BaccaratGameLogic.whoWon(playerHand,bankerHand), "Incorrect wineer");
	}
	
	 // Testing BaccaratGame logic
	public void testWhoWon2()
	{
		
		deck2 = BaccaratDealer.generateDeck();
		playerHand = BaccaratDealer.dealHand(deck2);
		playerHand.get(0).setVal(5);
		playerHand.get(1).setVal(4);
		bankerHand = BaccaratDealer.dealHand(deck2);
		assertEquals("player",BaccaratGameLogic.whoWon(playerHand,bankerHand), "Incorrect winner");
	}
		
	 // Testing BaccaratGame logic
	public void testWhoWon3()
	{
		deck2 = BaccaratDealer.generateDeck();
		playerHand = BaccaratDealer.dealHand(deck2);
		bankerHand = BaccaratDealer.dealHand(deck2);
		playerHand.get(0).setVal(5);
		playerHand.get(1).setVal(4);
		bankerHand.get(0).setVal(5);
		bankerHand.get(1).setVal(4);
		bankerHand = BaccaratDealer.dealHand(deck2);
		assertEquals("Banker",BaccaratGameLogic.whoWon(playerHand,bankerHand), "Incorrect winner");
	}
	
	 // Testing BaccaratGame logic
	public void testEvalBanker()
	{
		deck2 = BaccaratDealer.generateDeck();
		bankerHand = BaccaratDealer.dealHand(deck2);
		bankerHand.get(0).setVal(5);
		bankerHand.get(1).setVal(4);
		Card card2 = new Card(null,0);
		card2 = BaccaratDealer.drawOne(deck2);
		assertEquals(false,BaccaratGameLogic.evaluateBankerDraw(bankerHand, card2), "Incorrect eval banker method");
	}
	
	 // Testing BaccaratGame logic
	public void testEvalBanker2()
	{
		deck2 = BaccaratDealer.generateDeck();
		bankerHand = BaccaratDealer.dealHand(deck2);
		bankerHand.get(0).setVal(13);
		bankerHand.get(1).setVal(12);
		Card card2 = new Card(null,0);
		card2 = BaccaratDealer.drawOne(deck2);
		assertEquals(true,BaccaratGameLogic.evaluateBankerDraw(bankerHand, card2), "Incorrect eval banker method");
	}
	
	 // Testing BaccaratGame logic
	public void testEvalBanker3()
	{
		deck2 = BaccaratDealer.generateDeck();
		bankerHand = BaccaratDealer.dealHand(deck2);
		bankerHand.get(0).setVal(5);
		bankerHand.get(1).setVal(3);
		Card card2 = new Card(null,0);
		card2 = BaccaratDealer.drawOne(deck2);
		assertEquals(false,BaccaratGameLogic.evaluateBankerDraw(bankerHand, card2), "Incorrect eval banker method");
	}
	
	 // Testing BaccaratGame logic
	public void testEvalplayer()
	{
		deck2 = BaccaratDealer.generateDeck();
		playerHand = BaccaratDealer.dealHand(deck2);
		playerHand.get(0).setVal(1);
		playerHand.get(1).setVal(3);
		assertEquals(true,BaccaratGameLogic.evaluatePlayerDraw(bankerHand), "Incorrect eval player method");
	}
	
	 // Testing BaccaratGame logic
	public void testEvalplayer2()
	{
		deck2 = BaccaratDealer.generateDeck();
		playerHand = BaccaratDealer.dealHand(deck2);
		playerHand.get(0).setVal(13);
		playerHand.get(1).setVal(12);
		assertEquals(true,BaccaratGameLogic.evaluatePlayerDraw(bankerHand), "Incorrect eval player method");
	}
	
	 // Testing BaccaratGame logic
	public void testEvalplayer3()
	{
		deck2 = BaccaratDealer.generateDeck();
		playerHand = BaccaratDealer.dealHand(deck2);
		playerHand.get(0).setVal(3);
		playerHand.get(1).setVal(3);
		assertEquals(false,BaccaratGameLogic.evaluatePlayerDraw(bankerHand), "Incorrect eval player method");
	}
	
	 // Testing BaccaratGame logic
	public void testEvalplayer4()
	{
		deck2 = BaccaratDealer.generateDeck();
		playerHand = BaccaratDealer.dealHand(deck2);
		playerHand.get(0).setVal(3);
		playerHand.get(1).setVal(4);
		assertEquals(false,BaccaratGameLogic.evaluatePlayerDraw(bankerHand), "Incorrect eval player method");
	}
	
	 // Testing BaccaratGame logic
	public void testEvalplayer5()
	{
		deck2 = BaccaratDealer.generateDeck();
		playerHand = BaccaratDealer.dealHand(deck2);
		playerHand.get(0).setVal(4);
		playerHand.get(1).setVal(4);
		assertEquals(false,BaccaratGameLogic.evaluatePlayerDraw(bankerHand), "Incorrect eval player method");
		assertEquals(true,BaccaratGameLogic.playerWon,"player should have has a natural win");
	}
	
	
	@Test
//testing card class 
 	public void testCard1()
 	{
		Card card1 = new Card(null,0);
		Card card2 = new Card(null,0);
		Card card3 = new Card(null,0);
		Card card4 = new Card(null,0);
		
		deck2 = BaccaratDealer.generateDeck();
		card1 = BaccaratDealer.drawOne(deck2);
		card2 = BaccaratDealer.drawOne(deck2);
		card3 = BaccaratDealer.drawOne(deck2);
		card4 = BaccaratDealer.drawOne(deck2);

		assertNotNull(card1, "card is null ");
		assertNotNull(card2, "card is null ");
		assertNotNull(card3, "card is null");
		assertNotNull(card4,"card is null ");
 	}
	
	@Test
	
 	public void testCard2()
 	{
		Card card1 = new Card(null,0);
		Card card2 = new Card(null,0);
		
		deck2 = BaccaratDealer.generateDeck();
		card1 = BaccaratDealer.drawOne(deck2);
		card1.setVal(10);
		card2 = BaccaratDealer.drawOne(deck2);
		card2.setVal(9);
		
		assertEquals(10,card1.getVal(), "card value is not correct");
		assertEquals(9,card2.getVal(), "card value is not correct");
 	}

//Testing Baccarat info
	@Test
 	public void BaccaratInfoTest()
 	{
		BaccaratInfo gamelogic = new BaccaratInfo(55,"23.4.2.1");
		assertEquals(55,gamelogic.getServer(), "Incorrect port");
		assertEquals("23.4.2.1",gamelogic.getIp(), "Incorrect IP adress");

 	}
	
	@Test
 	public void BaccaratInfoTest2()
 	{
		BaccaratInfo gamelogic = new BaccaratInfo(55,"23.4.2.1");
		gamelogic.setIp("24.3.2");
		gamelogic.setServer(9999);
		assertEquals(9999,gamelogic.getServer(), "Incorrect port");
		assertEquals("24.3.2",gamelogic.getIp(), "Incorrect IP adress");
		
		
		gamelogic.setAmount(88);
		assertEquals(88,gamelogic.getAmount(), "Incorrect amount");
		
		gamelogic.setDropped(true);
		assertEquals(true,gamelogic.getDropped(), "Incorrect client dropped");
		
		gamelogic.setbiddingChoice("player");
		assertEquals("player",gamelogic.getBiddingChoice(), "Incorrect bidding choice");

		gamelogic.setAmountWon(45);
		assertEquals(45,gamelogic.getamountWon(), "Incorrect amount");
		
		gamelogic.setwhoWonAtucally("banker");
		assertEquals("banker",gamelogic.getwhoWon(), "Incorrect winner");

	
		gamelogic.setexCard1("banker");
		assertEquals("banker",gamelogic.getexCard1(), "Incorrect card value");
		
		gamelogic.setexCard2("banker");
		assertEquals("banker",gamelogic.getexCard2(), "Incorrect card value");
		
		gamelogic.setRound(3);
		assertEquals(3,gamelogic.getRound(), "Incorrect round value");
		
		gamelogic.setifBanker(true);
		assertEquals(true,gamelogic.getifBanker(), "Incorrect, banker extra card");
		
		gamelogic.setifPlayer(true);
		assertEquals(true,gamelogic.getifPlayer(), "Incorrect, player extra card");
		
		deck2 = BaccaratDealer.generateDeck();

		playerHand = BaccaratDealer.dealHand(deck2);
		newHand = Server.convertHand(playerHand); 
		gamelogic.setbankerHand(newHand);
		assertEquals(newHand,gamelogic.getbankerHand(), "Incorrect, player extra card");
		
		playerHand = BaccaratDealer.dealHand(deck2);
		newHand = Server.convertHand(playerHand); 
		gamelogic.setplayerHand(newHand);
		assertEquals(newHand,gamelogic.getplayerHand(), "Incorrect, player extra card");
 	}
	
	
// Testing server methods
	@Test
	//test if deck was converted to strings from cards
 	public void testConverHand()
 	{
		deck2 = BaccaratDealer.generateDeck();
		playerHand = BaccaratDealer.dealHand(deck2);
	    System.out.println("player card: " + playerHand.get(0).getSuit() + Integer.toString(playerHand.get(0).getVal())+ " , " + playerHand.get(1).getSuit() + Integer.toString(playerHand.get(1).getVal()));  
		newHand = Server.convertHand(playerHand);
		System.out.println("new card: " + newHand.get(0) + " , " + newHand.get(1));  
  		assertEquals(newHand.get(0),playerHand.get(0).getSuit() + Integer.toString(playerHand.get(0).getVal()), "Incorrect value");
  		assertEquals(newHand.get(1),playerHand.get(1).getSuit() + Integer.toString(playerHand.get(1).getVal()), "Incorrect value");
 	}
	
	@Test
	//test if deck was converted to strings from cards
 	public void testConverHand2()
 	{
		deck2 = BaccaratDealer.generateDeck();
		playerHand = BaccaratDealer.dealHand(deck2);
		newHand = Server.convertHand(playerHand);
		assertNotNull(newHand.get(0), "null after card conversion");
		assertNotNull(newHand.get(1), "null after card conversion");
 	}

}
