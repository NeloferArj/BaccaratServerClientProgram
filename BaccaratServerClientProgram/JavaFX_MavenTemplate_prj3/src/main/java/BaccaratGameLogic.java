import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class BaccaratGameLogic {
	
//	static ArrayList<Card> deck =  new ArrayList<Card>();

	static ArrayList<Card> hand1 = new ArrayList<Card>(); //player's hand
	static ArrayList<Card> hand2 = new ArrayList<Card>(); //banker's hand
	static int bankerVal = -99; //total points in banker's hand
	static int playerVal = -99; //total points in player's hand
	static Card playerCard = new Card(null, -99); //Additional card for player
	static Card bankerCard = new Card(null, -99); //Additional card for banker
	static int updatedPlayerVal = -99;
	static int updatedBankerVal = -99;
	
	static boolean bankerWon;
	static boolean playerWon;
	static boolean Draw;
	
	static boolean bankerEXcard;
	static boolean playerEXcard;

	static String whoWon  = "";
	
	static List<int[]> list1;
	
//	static String message = "";

//The method whoWon will evaluate two hands at the end of the game and return a string
//	depending on the winner: “Player”, “Banker”, “Draw”.
	public static String whoWon(ArrayList<Card> hand1, ArrayList<Card> hand2) {
		
		System.out.println("-----New Round-----");
		//reset values
		bankerVal = 0;
		playerVal = 0;
		updatedPlayerVal = 0;
		updatedBankerVal = 0;
		playerCard.setVal(0);
		bankerCard.setVal(0);
		
		// 1st evaluate player hand
		if(evaluatePlayerDraw(hand1) == true)
		{
			System.out.println("Evaluate player was true");
			playerCard = BaccaratDealer.drawOne(BaccaratDealer.deck); // deal new card
			
			System.out.println("Ex player card " + Integer.toString(playerCard.getVal()));
			if(playerCard.getVal() >= 10) { // if val is greater/equal to 10
				playerCard.setVal(0);
			}
			
			updatedPlayerVal = playerVal + playerCard.getVal(); //get new value of hand
			System.out.println("Updatedplayerval " + Integer.toString(updatedPlayerVal));
			if(updatedPlayerVal > 9) //if new total of hand points is > 9 then drop 1st value
			{
				updatedPlayerVal = getSecond(updatedPlayerVal);
				System.out.println("Updatedplayerval after drop 1st " + Integer.toString(updatedPlayerVal));
			}
			
		}
		else
		{
			if(playerWon == true) //natural win player
			{
				System.out.println("Player Won ");
				whoWon = "Player";
				return whoWon;
			}
		}
		
		//second evaluate banker
		System.out.println("befroe the evaluateBankerDraw");
		if(evaluateBankerDraw(hand2, playerCard))
		{
			System.out.println("Evaluate banker was true");
			bankerCard = BaccaratDealer.drawOne(BaccaratDealer.deck); //deal new card
			
			System.out.println("Ex banker card" + Integer.toString(bankerCard.getVal()));
			if(bankerCard.getVal() >= 10) {
				bankerCard.setVal(0);
			}
			
			updatedBankerVal = bankerVal + bankerCard.getVal(); //get new banker hand points
			System.out.println("Updatedbankerval " + Integer.toString(updatedBankerVal));
			if(updatedBankerVal > 9)
			{
				updatedBankerVal = getSecond(updatedBankerVal);
				System.out.println("Updatedbankerval after drop 1st " + Integer.toString(updatedBankerVal));
			}
		}
		else
		{
			if(bankerWon) //natural win banker
			{
				System.out.println("Banker Won");
				whoWon = "Banker";
				return whoWon;
			}
		}
		
		System.out.println("After updated Banker");
		
		// check if new banker hand or new player hand is closer to nine
		if(updatedBankerVal != updatedPlayerVal)
		{
			System.out.println("new Banker Val " + Integer.toString(updatedBankerVal) + "new player val " + Integer.toString(updatedPlayerVal));
			
			if(closer2Nine(updatedPlayerVal,updatedBankerVal))
			{
				whoWon = "Player";
				System.out.println("Player Won");
				return whoWon;
			}
			else
			{
				whoWon = "Banker";
				System.out.println("Banker Won");
				return whoWon;

			}
		}
		
		//either player or banker or both win a natural win
		if(bankerWon && playerWon)
		{
			whoWon = "Draw";
			System.out.println("game was Draw");
			return whoWon;
		}
		
		return whoWon;
	
	}

	
//	The method handTotal will take a hand and return how many points that hand is worth.
	public static int handTotal(ArrayList<Card> hand)
	{
		
		return 99;
	}
	
	// get the first digit of a given number
    public static int getSecond(int number) 
    { 
        // delete the last digit from the number until there is only one digit left 
    	return number%10;
    } 
    
    // check which number is closer to nine
    public static boolean closer2Nine(int val1, int val2)
    {
    	if(val1 == 9)
    	{
    		return true; // true indicates val1 is greater
    	}
    	else if(val2 == 9)
    	{
    		return false;
    	}
    	else if(val1 > val2)
    	{
    		return true;
    	}
    	
    	return false;
    }
    
//	The methods evaluateBankerDraw and evaluatePlayerDraw will return true if either one should be dealt a third card,
//			otherwise return false.
	public static boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard)
	{
		
		bankerVal = 0;	
		bankerWon = false;
		bankerEXcard = false;
		
		//the cards: 10s and face cards are worth zero points
		if(hand.get(0).getVal() >= 10)
		{
			hand.get(0).setVal(0);
		}
		if(hand.get(1).getVal() >= 10)
		{
			hand.get(1).setVal(0);
		}
		
		System.out.println("BankerCard1 " + Integer.toString(hand.get(0).getVal()) + " BankerCard2 " + Integer.toString(hand.get(1).getVal()));
		
		bankerVal = hand.get(0).getVal() + hand.get(1).getVal();
		System.out.println("BankerVal " + Integer.toString(bankerVal));
		
		//if the total value of the two cards is greater than 9, remove the 1st number of the total. For
		if(bankerVal > 9)
		{
			bankerVal = getSecond(bankerVal);
			System.out.println("BankerVal after dropping " + Integer.toString(bankerVal));
		}
		
		if(bankerVal == 8 || bankerVal == 9)
		{
			bankerWon = true; // banker has a natural win
			return false;
		}
		
		//If The Banker’s cards total 2 or less, The Banker gets one additional card.
		if(bankerVal <= 2 )
		{
			bankerEXcard = true;
			return true;
		}
		
		if(bankerVal == 7)
		{
			return false;
		}
		
		//The Bankers first two cards total 3, 4, 5, or 6, it depends on if The Player drew another
		//card and if so, the value of that card to determine if The Banker receives another card.
		if(bankerVal == 3  || bankerVal == 4 || bankerVal == 5 || bankerVal == 6)
		{
				System.out.println("playerCard value in banker: " + Integer.toString(playerCard.getVal()));
				if(bankerVal == 3)
				{
					if(playerCard.getVal() == 0 || playerCard.getVal() == 1 || playerCard.getVal() == 2 || playerCard.getVal() == 3 || playerCard.getVal() == 4 || playerCard.getVal() == 5 || playerCard.getVal() == 6 || playerCard.getVal() == 7 || playerCard.getVal() == 8 || playerCard.getVal() == 9)
					{
						bankerEXcard = true;
						System.out.println("Banker needs extra card");
						return true;
					}
				
				}
				else if(bankerVal == 4)
				{
					if(playerCard.getVal() == 2 || playerCard.getVal() == 3 || playerCard.getVal() == 4 || playerCard.getVal() == 5 || playerCard.getVal() == 6 || playerCard.getVal() == 7)
					{
						bankerEXcard = true;
						System.out.println("Banker needs extra card");
						return true;
					}

				}
				else if(bankerVal == 5)
				{
					if( playerCard.getVal() == 4 || playerCard.getVal() == 5 || playerCard.getVal() == 6 || playerCard.getVal() == 7)
					{
						bankerEXcard = true;
						System.out.println("Banker needs extra card");
						return true;
					}
				}
				else if(bankerVal == 6)
				{
					if(playerCard.getVal() == 6 || playerCard.getVal() == 7)
					{
						bankerEXcard = true;
						System.out.println("Banker needs extra card");
						return true;
					}
				}
				else {
					System.out.println("Banker doesnt need card");
					
					return false; //draw extra card or not
				}
			
		}
		
		System.out.println("Banker doesnt need card");
		
		return false; //draw extra card or not
	}
	
	
	public static boolean evaluatePlayerDraw(ArrayList<Card> hand)
	{
		playerVal = 0;
		
		playerWon = false;
		playerEXcard = false;

		//the cards: 10s and face cards are worth zero points
		if(hand.get(0).getVal() >= 10)
		{
			hand.get(0).setVal(0);
		}
		if(hand.get(1).getVal() >= 10)
		{
			hand.get(1).setVal(0);
		}
		
		System.out.println("PlayerCard1 " + Integer.toString(hand.get(0).getVal()) + " PlayerCard2 " + Integer.toString(hand.get(1).getVal()));
		
		playerVal = hand.get(0).getVal() + hand.get(1).getVal();
		System.out.println("PlayerVal " + Integer.toString(playerVal));
		
		if(playerVal > 9)
		{
			playerVal = getSecond(playerVal);
			System.out.println("PlayerVal after dropping " + Integer.toString(playerVal));
		}
		
		//The Player will go first: if hand totals to 5 or less, The Player gets one more card.
		if(playerVal <= 5)
		{
			playerEXcard = true;
			return true;
			
		}

		if(playerVal == 8 || playerVal == 9)
		{
			playerWon = true; // player has a natural win
			return false;
		}
		else if(playerVal == 6 || playerVal == 7)
		{
			return false;
		}
		
		return false; //draw extra card or not
	}

}
