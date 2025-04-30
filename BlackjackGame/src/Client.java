import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
    	ObjectInputStream inStream = null;
    	ObjectOutputStream outStream = null;
    	
    	try {
    		Socket socket = new Socket("localhost", 5555);
    		
    		inStream = new ObjectInputStream(socket.getInputStream());
    		outStream = new ObjectOutputStream(socket.getOutputStream());
    	} catch (IOException except) {
    		except.printStackTrace();
    	}
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
