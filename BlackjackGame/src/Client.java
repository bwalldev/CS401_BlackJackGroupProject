import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private Socket socket;                  
    private BufferedReader input;          
    private PrintWriter output;            
    private Player player;                 
    private Game game;                     

    //connect the client to the server
    public void connectToServer(String serverAddress, int port) {
       
    }

  
    public void send(String message) {
       
    }

    public String receive(){
    	
		return null;
        
    }

    //sends players action ( hit, stand)
    public void sendAction(String action) {
       
    }

    public void disconnect() {
       
    }
}
