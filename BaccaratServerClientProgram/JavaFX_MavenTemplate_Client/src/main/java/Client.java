import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class Client extends Thread{
	Socket socketClient;
	
	ObjectOutputStream out;
	ObjectInputStream in;
	String ip;
	int port;
	int amount;
	String biddingChoice;
	int currentWinnings;
	private Consumer<Serializable> callback;
	public BaccaratInfo gamedata;
	

	public Client(Consumer<Serializable> call, String ip, int port) {
		// TODO Auto-generated constructor stub
		callback = call;
		this.ip = ip;
		this.port = port;	
		currentWinnings = 0;
		gamedata = new BaccaratInfo(port,ip);
	}
	
		public void run() {
			
			try {
			System.out.println("FROM CLIENT");
	 		System.out.println("ip: " + ip);
			System.out.println("port: " + Integer.toString(port));
			socketClient= new Socket(ip,port); // 127.0.0.1 //5555
			
			
		    out = new ObjectOutputStream(socketClient.getOutputStream());
		    in = new ObjectInputStream(socketClient.getInputStream());
		    socketClient.setTcpNoDelay(true);
			}
			catch(Exception e) {}
			
			while(true) {
				 
				try {
				gamedata =(BaccaratInfo) in.readObject(); //sending to server instance of info class
				
				callback.accept("-------- ROUND: " + Integer.toString(gamedata.getRound()) +" --------");// dipaly
			
			
				//info from BaccaratInfo
				ArrayList<String> playerHand = gamedata.getplayerHand();
				ArrayList<String> bankerHand = gamedata.getbankerHand();
				double won = gamedata.getamountWon();
				String whoWon = gamedata.getwhoWon();
				String excardPlayer = gamedata.getexCard1();
				String excardBanker = gamedata.getexCard2();
				boolean ifPlayer = gamedata.getifPlayer();
				boolean ifBanker = gamedata.getifBanker();
		
		    	//storing all messages into a array	    	
		    	ArrayList<String> Hand = new ArrayList<String>();
				Hand.add("Player Hand: " + playerHand.get(0) + " , " + playerHand.get(1) + "    |    Banker Hand: " + bankerHand.get(0) + " , " + bankerHand.get(1));
				if(ifPlayer) //player got an extra car
				{
					Hand.add("Player's extra card: " + excardPlayer);  
				}
				if(ifBanker) //banker got an extra card
				{
					Hand.add("Banker's extra card: " + excardBanker); 
				}
				if(won == 0)
			    	{
			    		currentWinnings--;
			    		Hand.add("Result: Sorry, you bet " + gamedata.getBiddingChoice() + "!    |    you lost: $" + Integer.toString(gamedata.getAmount()));
			    		if(currentWinnings <=0)
			    		{
			    			Hand.add("Number of wins: You have no wins yet");
			    		}
			    		else
			    		{
			    			Hand.add("Number of wins: You have " + Integer.toString(currentWinnings) + " wins");
			    		}
			    	}
		    	else
		    	{
		    		currentWinnings++;
		    		Hand.add("Result: Congrats you bet " + gamedata.getBiddingChoice() + "!    |    you win $" + Double.toString(won));
		    		if(currentWinnings <=0)
		    		{
		    			Hand.add("Number of wins: You have no wins in total");
		    		}
		    		else
		    		{
		    			Hand.add("Number of wins: You have " + Integer.toString(currentWinnings) + " wins in total");
		    		}
		    	}
				
				// displaying on client GUI 
				for(int j=0; j<Hand.size(); j++) 
					{
						final int number = j;
						PauseTransition pause = new PauseTransition(Duration.seconds(j));
						pause.setOnFinished(e->{
							callback.accept(Hand.get(number)); 
						});	
						pause.play();
					}
				
				}
				catch(Exception e) {}
			}
		
	    }
		
		public void send(BaccaratInfo data) {
		
			try {
				out.writeObject(data);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
