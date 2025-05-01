import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private Socket socket;
    ObjectInputStream inStream;
	ObjectOutputStream outStream;
	boolean loggedIn;
    
    public Client() throws IOException {
    	ObjectInputStream inStream = null;
    	ObjectOutputStream outStream = null;
    	Socket socket = null;
    	this.loggedIn = false;
    }

    //connect the client to the server on localhost
    public boolean connectToServer(String ipAddress, int port) {
       try {
    	   this.socket= new Socket (ipAddress, port);
    	   
    	   this.inStream = new ObjectInputStream (socket.getInputStream());
    	   this.outStream = new ObjectOutputStream(socket.getOutputStream());
    	   
    	   return true;
       } catch (IOException e) {
    	   System.out.println("connection faild! " +  e.getMessage());
    	   
    	   return false;
       }
    }

   // send message to server
    public String sendLoginMessage(String username, String password) {
    	  Message loginMessage = new Message(MessageType.LOGIN, username, password, "login", "Client", null);
    	  
    	  try {
			outStream.writeObject(loginMessage);
			outStream.flush();
			
			Message serverMessage = (Message) inStream.readObject();
			
			return serverMessage.getText();
		  } catch (IOException | ClassNotFoundException except) {
			except.printStackTrace();
		  }
    	  
    	  return "Login Unsuccessful";
    }
    

    //disconnects from server
    public void disconnect() {
    	try {
    		 if (this.inStream != null) 
    			 this.inStream.close();
             if (this.outStream != null) 
            	 this.outStream.close();
             System.out.println("Disconnected! ");
    	}
    	catch(IOException e) {
            System.out.println("Error Disconnecting! " + e.getMessage());
    	}	
       
    }
}
