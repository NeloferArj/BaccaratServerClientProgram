import java.io.Serializable;
import java.util.ArrayList;

public class BaccaratInfo implements Serializable {
	
	/**
	 * 
	 */

	//CLIENT
	
	public BaccaratInfo() {
		
	}
	
	private static final long serialVersionUID = 1L;
	public int server; //holds the port number
	public int amount; //amount bid by client
	public String biddingChoice; //bidding choice of client
	public String ip; //ip adresss entered by client
	
	
	ArrayList<String> playerHand;
	ArrayList<String> bankerHand;
	double amountWon;
	String whoWonAtucally;
	String playerexCard;
	String bankerexCard;
	boolean ifplayer; //true if player got an extra card
	boolean ifbanker; //true if banker got an extra card 
	int newRound; //current round number
	boolean dropped = false; //true if client dropped
	
	public BaccaratInfo(int portnum, String adress) {
		// TODO Auto-generated constructor stub
		server = portnum;
		ip = adress;
		playerHand = new ArrayList<String>();
		bankerHand =  new ArrayList<String>();
		amountWon = -99;
		whoWonAtucally = null;
		playerexCard = null;
		bankerexCard = null;
		newRound = 1;
	}


		public int getServer() {
	      return server ;
	   }

	   public void setServer(int num) {
	      server = num;
	   }

	   public int getAmount() {
	      return amount;
	   }

	   public void setAmount(int num) {
	      amount = num;
	   }
	   
	   public boolean getDropped() {
           return dropped;
       }

       public void setDropped(boolean val) {
           dropped = val;

       }

	   public String getBiddingChoice() {
		      return biddingChoice;
		}

	   public void setbiddingChoice(String option) {
		   biddingChoice = option;
	   }
	   
	   public String getIp() {
		  return ip;
				  
	   }
	   
	   public void setIp(String adress) {
		   ip = adress;
	   }
	  

	   public double getamountWon() {
	      return amountWon;
	   }

	   public void setAmountWon(double num) {
		   amountWon = num;
	   }
	   
	   public String getwhoWon() {
		      return whoWonAtucally;
		}

	   public void setwhoWonAtucally(String option) {
		   whoWonAtucally = option;
	   }
	   
	   public String getexCard1() {
		   return playerexCard;
	   }
				  
	   
	   public void setexCard1(String val) {
		   playerexCard = val;	  
	   }
	   
	   public String getexCard2() {
		   return bankerexCard;
	   }
				  
	   
	   public void setexCard2(String val) {
		   bankerexCard = val;
				  
	   }
	   
	   public ArrayList<String> getplayerHand() {
		   return playerHand;
	   }
				 
	   public void setplayerHand(ArrayList<String> hand) {
		   playerHand = hand;
				  
	   }
	   
	   public ArrayList<String> getbankerHand() {
		   return bankerHand;
	   }
	   
	   public void setbankerHand(ArrayList<String> hand) {
		   bankerHand = hand;
				  
	   }
	   
	
	   public boolean getifPlayer() {
		   return ifplayer;
	   }
				  
	   
	   public void setifPlayer(boolean val) {
		   ifplayer = val;
				  
	   }
	   
	   public boolean getifBanker() {
		   return ifbanker;
	   }
				  
	   
	   public void setifBanker(boolean val) {
		   ifbanker = val;
				  
	   }
	   
		public int getRound() {
		      return newRound ;
		   }

	   public void setRound(int num) {
		   newRound = num;
	   }
	   
		

}