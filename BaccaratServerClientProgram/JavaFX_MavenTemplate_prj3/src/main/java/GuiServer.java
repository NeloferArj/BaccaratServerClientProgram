
import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
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

//first run this program and enter the port number as 5555, then go run the guiclient and enter client number 127.0.0.1, after you click connect, the server show show the client 
// has connected, bidding choice is either draw, player or banker
public class GuiServer extends Application{

	
	TextField s1,s2,s3,s4;
	Button serverChoice;
	Button exitButton;
	HashMap<String, Scene> sceneMap;
	GridPane grid;
	VBox buttonBox;
	VBox clientBox;
	Scene startScene;
	BorderPane startPane;
	Server serverConnection;	
	
//	Client clientConnection;
	
	ListView<String> serverLog;
	ListView<String> listItems;
	BaccaratInfo data2;

	static int port; // user picks what port to listen to
	Label l1 = new Label("Enter port number and click 'Listen to this port'");
	TextField t1 = new TextField(); //holds port number
	Button b1; 
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {		
		// TODO Auto-generated method stub
		primaryStage.setTitle("Baccarat Game");
		
		//START button
		this.serverChoice = new Button("START GAME");
		this.serverChoice.setStyle("-fx-pref-width: 500px");
		this.serverChoice.setStyle("-fx-pref-height: 100px");
	
        serverChoice.setDisable(true);
        
		b1 = new Button("Listen to this port");
		b1.setOnAction(e->{
	         port = Integer.valueOf(t1.getText());
	     	l1.setText("Click 'Start Game' and please wait!");
	         serverChoice.setDisable(false);

		});
	
	
		this.serverChoice.setOnAction(e->{ 
			primaryStage.setScene(sceneMap.get("server"));
		primaryStage.setTitle("This is the Server");
		serverConnection = new Server(data -> {
		Platform.runLater(()->{
		listItems.getItems().add(data.toString());
		});
		
		},port);
	
		});
			
		this.buttonBox = new VBox(10, serverChoice,l1,t1,b1);
		
		//initial stage
		startPane = new BorderPane();
		startPane.setPadding(new Insets(70));
		startPane.setCenter(buttonBox);
		
		startScene = new Scene(startPane, 400,400);
		
		listItems = new ListView<String>();		
		exitButton = new Button("Turn off Server");
		exitButton.setOnAction( new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				primaryStage.close();
			}
			
		});

		sceneMap = new HashMap<String, Scene>();
		sceneMap.put("server",  createServerGui());

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
	public Scene createServerGui() {
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(20));
		pane.setStyle( "-fx-background-image: url(" +
                "'https://pressstart.vip/images/uploads/assets/bluemoon.png'" +
            "); " +
            "-fx-background-size: cover;");
		pane.setCenter(listItems);
		pane.setBottom(exitButton);
		return new Scene(pane, 500, 500);	
	}

}
