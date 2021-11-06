import java.util.ArrayList;

import com.sun.glass.ui.Application;

public class BaccaratGame {
	
	 static double currentBet ; //amount client bid
	 double totalWinnings; // number of wins since game started
	 static String bet; //client's bidding choice
	 static double amountWon; //amount client won
	 static String winner; // result of the game
	
	//evaluating Winnings after both hands are evaluated
	public static double evaluateWinnings() 
	{
		//covert client's bidding choice and game result to lower case
		String str1 = bet.toLowerCase();
		String str2 = winner.toLowerCase();

		if(str1.equals(str2) == true)
		{
	 		
			if(str1.equals("player")  == true)
			{
				amountWon = currentBet*2;
			}
			else if(str1.equals("banker")  == true)
			{
				amountWon = currentBet*.95;
			}
			else if(str1.equals("draw")  == true)
			{
				amountWon = currentBet*8 +currentBet;
			}
			
		}
		else
		{
			amountWon = 0;
		}		
		return amountWon;

	}

}
