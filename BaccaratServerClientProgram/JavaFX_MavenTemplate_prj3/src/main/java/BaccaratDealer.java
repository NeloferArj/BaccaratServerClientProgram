import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BaccaratDealer {
	
	
   static ArrayList<Card> deck =  new ArrayList<Card>();
	
   public static int RandNum(int num)
   {
		Random random = new Random();
		int randIndex = 0;
		while (true){
			randIndex = random.nextInt(num+1);
		    if(randIndex !=0) break;
		}
		return randIndex;
    }

//	generateDeck will generate a new standard 52 card deck where each card is an
//	instance of the Card class in the ArrayList<Card> deck.
	public static ArrayList<Card> generateDeck() {
		String [] suits = {"Clubs" , "Diamonds" , "Hearts" , "Spades"}; //4 suits in a deck
		for(int i = 0; i<4; i++)
		{
			for(int j =1; j<=13; j++)
			{
				Card newCard = new Card(suits[i],j);
				deck.add(newCard);
			}
		}
		return deck;
	}


	
//	dealHand will deal two cards and return them in an ArrayList<Card>.
	public static ArrayList<Card> dealHand(ArrayList<Card> deck)
	{
		
		ArrayList<Card> hand = new ArrayList<Card>();
		hand.add(drawOne(deck));
		hand.add(drawOne(deck));
		return hand;
	}
	
//	drawOne will deal a single card and return it.
	public static Card drawOne(ArrayList<Card> deck) {
		//get 2 cards
		int index = RandNum(51); // get a value between 0 to 51, which are indices of ArrayList<Card> deck;
		return deck.get(index);
	}
	
//	shuffleDeck will create a new deck of 52 cards and “shuffle”; randomize the cards in ArrayList<Card>.
	public static ArrayList<Card>  shuffleDeck(ArrayList<Card> deck)
	{
		deck.clear();
		generateDeck();
		Collections.shuffle(deck); //shuffles deck
		return deck;
	}
	
//	deckSize will just return how many cards are in this.deck at any given time.
	public static int deckSize(ArrayList<Card> deck)
	{
		return deck.size();
	}
}
