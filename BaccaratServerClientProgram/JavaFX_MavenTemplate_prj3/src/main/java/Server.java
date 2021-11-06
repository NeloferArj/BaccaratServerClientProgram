import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

import javafx.application.Platform;
import javafx.scene.control.ListView;

public class Server{
	
	int count = 1;	
	ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
	TheServer server;
	private Consumer<Serializable> callback;
	
	String ip;
	int port;
	int amount;
	public static String biddingChoice;
	int currentWinnings;
	int portListen;
	
	Socket connection;
	
	
	public static BaccaratInfo clientTOserver;
	
	
	public Server(Consumer<Serializable> call, int serverPort) {
		callback = call;
		server = new TheServer();
		server.start();
		portListen = serverPort;
		// TODO Auto-generated constructor stub
	}
	

	public class TheServer extends Thread{
		
		public void run() {
		
			try(ServerSocket mysocket = new ServerSocket(portListen)){
		    System.out.println("Server is waiting for a client!");
		    callback.accept("Server is waiting for a client!");
		  
			
		    while(true) {
		
				ClientThread c = new ClientThread(mysocket.accept(), count);
				callback.accept("New client has connected to server: " + "client #" + count);
				callback.accept("Total number of clients: " + count);
				clients.add(c);
				c.start();
				
				count++;
				
			    }
			}//end of try
				catch(Exception e) {
					callback.accept("Server socket did not launch");
				}
			}//end of while
		}
	

		class ClientThread extends Thread{
		
			Socket connection;
			int count2;
			ObjectInputStream in;
			ObjectOutputStream out;
			
			ClientThread(Socket s, int count){
				this.connection = s;
				this.count2 = count;	
			}
			
			public void updateClients(BaccaratInfo gameinfo) {
				
				try {
					clients.get(count2-1).out.writeObject(gameinfo); 
					}
					
					catch(Exception e) {}
			}
			

			
			public void turnOffServer() throws IOException {
				connection.close();
			}
			
		
			public void run(){
					
				try {
					
					in = new ObjectInputStream(connection.getInputStream());
					out = new ObjectOutputStream(connection.getOutputStream());
					connection.setTcpNoDelay(true);	
				}
				catch(Exception e) {
					System.out.println("Streams not open");
				}
					
				 while(true) {
					    try {
					    	clientTOserver = (BaccaratInfo) in.readObject(); //Receiving
							amount = clientTOserver.getAmount();
							biddingChoice  = clientTOserver.getBiddingChoice();
						
							//print message on to server GUI
							callback.accept("Message from Client: Bet On [" + clientTOserver.getBiddingChoice() + "] Amount: [" + 
							Integer.toString(clientTOserver.getAmount()) + "] IP: [" + clientTOserver.getIp() +
							"] Port [" + Integer.toString(clientTOserver.getServer())+ "]");
							
				
							//check if client has dropped
							if(clientTOserver.getDropped() == true)
                            {
                                callback.accept("client was dropped");
                            }

							callback.accept("-------- ROUND: " + Integer.toString(clientTOserver.getRound()) +" --------");// dipaly
								
							ArrayList<Card> gameDeck =  new ArrayList<Card>();
							BaccaratDealer dealer = new BaccaratDealer();
							 gameDeck = dealer.generateDeck();
							 ArrayList<Card> playerHand = dealer.dealHand(gameDeck);
							 gameDeck = dealer.shuffleDeck(gameDeck);
							 ArrayList<Card> bankerHand = dealer.dealHand(gameDeck);
							 
					    	 
					    	 //storing hand in baccaratInfo class as strings
					    	 clientTOserver.setplayerHand(convertHand(playerHand));
					    	 clientTOserver.setbankerHand(convertHand(bankerHand));
					    	 
					    	 //storing values of each card in the hand 
					    	 int playerCard1 = playerHand.get(0).getVal();
					    	 int playerCard2 = playerHand.get(1).getVal();
					    	 int bankerCard1 = bankerHand.get(0).getVal();
					    	 int bankerCard2 = bankerHand.get(1).getVal();
					    	 
					    	 //displaying hands on to server
					    	 callback.accept("Player Hand: " + playerHand.get(0).getSuit() + Integer.toString(playerCard1)+ ", " + playerHand.get(1).getSuit() + Integer.toString(playerCard2) + "    |    Banker Hand: " + bankerHand.get(0).getSuit() +Integer.toString(bankerCard1)+ " , " + bankerHand.get(1).getSuit()+ Integer.toString(bankerCard2));
					    	 
					    	 //create new instance of game logic to call who won method, which evaluates both hands
					    	 BaccaratGameLogic gameLogic = new BaccaratGameLogic();
					    	 String atucallyWon = gameLogic.whoWon(playerHand,bankerHand);
					    	 
					    	 //booleans are ture or false depending on if player and banker gets an extra card
					    	 boolean playerexCard = gameLogic.playerEXcard;
					    	 boolean bankerexCard = gameLogic.bankerEXcard;
					    	 
					    	 if(playerexCard) //player got an extra card
						    	 {
					    		 	
						    		 Card excardplayer = BaccaratGameLogic.playerCard;
						    		 clientTOserver.setifPlayer(true);
						    		 clientTOserver.playerexCard = excardplayer.getSuit() + Integer.toString(excardplayer.getVal());
						    		 int newPlayerVal = gameLogic.updatedPlayerVal;
						    		 callback.accept("Extra card player: " +  excardplayer.getSuit() +Integer.toString(excardplayer.getVal()) + "    |    updated Player Hand Points: " + Integer.toString(newPlayerVal));
						    	 }
						    	 else
						    	 {
						    		 clientTOserver.setifPlayer(false);
						    		 int playerVal = gameLogic.playerVal;
						    		 callback.accept("Player Hand Points: " + Integer.toString(playerVal));
						    	 }
						    	 
						    	 if(bankerexCard) //banker got an extra card
						    	 {
						    		 Card excardbanker = BaccaratGameLogic.bankerCard;
						    		 clientTOserver.setifBanker(true);
						    		 clientTOserver.bankerexCard = excardbanker.getSuit() + Integer.toString(excardbanker.getVal());
						    		 int newBankerVal = gameLogic.updatedBankerVal;
						    		 callback.accept("Extra card banker: " + excardbanker.getSuit() + Integer.toString(excardbanker.getVal()) + "    |    updated Banker Hand Points: " + Integer.toString(newBankerVal));
						    	 }
						    	 else
						    	 {
						    		 clientTOserver.setifBanker(false);
						    		 int bankerVal = gameLogic.bankerVal;
						    		 callback.accept("Banker Hand Points: " + Integer.toString(bankerVal));
						    	 }
						    	 
						    	 
	//					    	callback.accept("Who won according to logic: " + atucallyWon);
						    	
						    	//create instance of BaccaratGame and call evaluteWinnings 
						    	BaccaratGame evalGame = new BaccaratGame();
						    	evalGame.currentBet = amount;
						    	evalGame.bet = biddingChoice;
						    	evalGame.winner = atucallyWon;
					    	 	double amountWon = evalGame.evaluateWinnings();
					    	 	
					    	 	//updating winning info in baccaratinfo class
						    	clientTOserver.whoWonAtucally = atucallyWon;	
						    	clientTOserver.amountWon = amountWon;
						    	
						    	if(amountWon == 0)
						    	{
						    		callback.accept("Result: Sorry, you bet " + clientTOserver.getBiddingChoice() + "! you lost: $" + Integer.toString(clientTOserver.getAmount()) + ", "+ clientTOserver.getwhoWon() + " wins");
						    	}
						    	else
						    	{
						    		callback.accept("Result: Congrats you bet " + clientTOserver.getBiddingChoice() + "! you win $" + Double.toString(amountWon));
						    	}
						    	updateClients(clientTOserver);
						    	}
						    
						    
						    catch(Exception e) {
						    	callback.accept("OOOOPPs...Something wrong with the socket from client: " + count2 + "....closing down!");
						    	callback.accept("Client #"+count2+" has left the server!");
						    	count2--;
						    	count--;
						    	clients.remove(this);
						    	break;
					    }
					}
				}//end of run
			
		}//end of client thread
		
		public static ArrayList<String> convertHand(ArrayList<Card> hand)
		{
			ArrayList<String> updatedCards = new ArrayList<String>();
			String card1 =  hand.get(0).getSuit() + Integer.toString(hand.get(0).getVal());
			String card2 = hand.get(1).getSuit() + Integer.toString(hand.get(1).getVal());
			updatedCards.add(card1);
			updatedCards.add(card2);
			return updatedCards;		
		}

}

