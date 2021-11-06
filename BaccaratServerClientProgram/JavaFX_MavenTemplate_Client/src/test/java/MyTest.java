//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import org.junit.jupiter.api.DisplayName;
//
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ValueSource;
//
//import javafx.application.Platform;
//import javafx.scene.control.ListView;
//
//class MyTest {
//	
//	static Client client1;
//	static String ip;
//	static int port;
//
//	static ListView<String> clientLog = new ListView<String>();
//
//	@BeforeEach
//	public void Todo()
//	{
//		port = 5555;
//		ip = "127.0.01";
//		client1 = new Client(data->{
//	        Platform.runLater(()->{
//	        	clientLog.getItems().add(data.toString());
//	        	//if data.
//	        	//if(data.contains("update cards")
//	                 });
//	        },ip, port);
//	}
//	
//	
//	
//
//	@Test
//	void test() {
//  		assertEquals(5555,Client.port, "Incorrect value");
//	}
//		
//		
//	}
