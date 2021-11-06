import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GuiClient extends Application {

	static Client clientConnection;;
	static ListView<String> clientLog = new ListView<String>();
	Button b1,b2,b3,b4, exitb;

	static String ip;
	static int port;
	static int amountBet;
	static String biddingOn;
	
	Label l1 = new Label("Enter IP adress: ");
	Label l2 = new Label("Enter port number: ");
	Label l3 = new Label("Enter bet amount: ");
	Label l4 = new Label("Enter bidding choice: ");
	Label l5 = new Label("click 'Connect' and please wait!");

    TextField t1 = new TextField(); //holds ip
    TextField t2 = new TextField(); //holds port
    TextField t3 = new TextField(); //holds bet amount
    TextField t4 = new TextField(); // holds bidding choice
    
    BorderPane borderPane = new BorderPane(); Scene startScene;
	BorderPane startPane;
	BorderPane winPane;
	BorderPane losePane;
	HashMap<String, Scene> sceneMap;


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("This is the Client");

		//exit button
		exitb = new Button("Exit");
		exitb.setOnAction(e -> {
		clientConnection.gamedata.setDropped(true);
		clientConnection.send(clientConnection.gamedata);
		Platform.exit();
		});
		
		//bet button
		b3 = new Button("Bet");
		t3.setDisable(true);
		t4.setDisable(true);
        b3.setDisable(true);
		b3.setOnAction(e->{
	         amountBet = Integer.valueOf(t3.getText());
	         biddingOn = t4.getText();
			clientConnection.gamedata.setbiddingChoice(biddingOn);
			clientConnection.gamedata.setAmount(amountBet);
			clientConnection.gamedata.setIp(ip);
			clientConnection.gamedata.setServer(port);
			clientConnection.send(clientConnection.gamedata);
	        b3.setDisable(true);
	        b4.setDisable(false);
			
		});
		
		//connect button
		b1 = new Button("Connect");
		b1.setStyle("-fx-pref-width: 500px");
		b1.setStyle("-fx-pref-height: 100px");
		b1.setOnAction(e->{
			 ip = t1.getText();
	         port = Integer.valueOf(t2.getText());
			connectHelper();
			primaryStage.setScene(sceneMap.get("server"));
			t3.setDisable(false);
			t4.setDisable(false);
	        b3.setDisable(false);
		});
		
		//new round button
		b4 = new Button("New Round");
        b4.setDisable(true);
		b4.setOnAction(e->{
	         amountBet = Integer.valueOf(t3.getText());
	         biddingOn = t4.getText();
			clientConnection.gamedata.setbiddingChoice(biddingOn);
			clientConnection.gamedata.setAmount(amountBet);
			clientConnection.gamedata.setIp(ip);
			clientConnection.gamedata.setServer(port);
			clientConnection.gamedata.setRound(clientConnection.gamedata.getRound()+1);
			clientConnection.send(clientConnection.gamedata);

		});
		
		//Initial stage
		VBox vbox1 = new VBox(b1);
		VBox vbox2 = new VBox(5,l5,l1,t1,l2,t2);
		startPane = new BorderPane();
		startPane.setPadding(new Insets(70));
		startPane.setCenter(vbox1);
		startPane.setBottom(vbox2);
		
		startScene = new Scene(startPane, 400,400);
		sceneMap = new HashMap<String, Scene>();
		sceneMap.put("server",  createClientGui());

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
		
		primaryStage.setScene(startScene);
		primaryStage.show();
	}
	
	//second stage
	public Scene createClientGui() {
		HBox hbox2 = new HBox(5,exitb,b3,b4);
		VBox clientBox = new VBox(5,l3,t3,l4,t4);
		clientBox.setStyle("-fx-background-color: lightblue");
		borderPane.setTop(hbox2); // top of window
		borderPane.setBottom(clientBox);

		borderPane.setCenter(clientLog);
		borderPane.setPadding(new Insets(20));
		borderPane.setStyle( "-fx-background-image: url(" +
                "'https://pressstart.vip/images/uploads/assets/bluemoon.png'" +
            "); " +
            "-fx-background-size: cover;");
		return new Scene(borderPane, 550, 550);
	}

	public void connectHelper()
	{
		try {
	clientConnection = new Client(data->{
        Platform.runLater(()->{
        	clientLog.getItems().add(data.toString());
                 });
        },ip, port); //sending info to client constructor
		}
	     catch (Exception e) {
			e.printStackTrace();
		}
		clientConnection.start();
	}

}
