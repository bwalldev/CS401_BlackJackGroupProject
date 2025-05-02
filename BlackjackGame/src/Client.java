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
    	this.inStream = null;
    	this.outStream = null;
    	this.socket = null;
    	this.loggedIn = false;
    }

    //connect the client to the server on localhost
    public boolean connectToServer(String ipAddress, int port) {
       try {
    	   this.socket= new Socket (ipAddress, port);
    	   
    	   this.outStream = new ObjectOutputStream(this.socket.getOutputStream());
    	   this.inStream = new ObjectInputStream (this.socket.getInputStream());
    	   
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
			this.outStream.writeObject(loginMessage);
			this.outStream.flush();
			
			Message serverMessage = (Message) inStream.readObject();
			
			this.loggedIn = true;
			
			return serverMessage.getText();
		  } catch (IOException | ClassNotFoundException except) {
			except.printStackTrace();
		  }
    	  
    	  return "Login Unsuccessful";
    }
    
    public int getTableCountMessage() {
    	Message tableCountMessage = new Message(MessageType.TABLE_COUNT, null, null, null, null, null);
    	
    	try {
    		this.outStream.writeObject(tableCountMessage);
    		
    		Message serverMessage = (Message) inStream.readObject();
    		
    		return Integer.parseInt(serverMessage.getText());
    	} catch(IOException | ClassNotFoundException except) {
    		except.printStackTrace();
    	}
    	
    	return 0;
    }
    
    public boolean getLoggedIn() {
    	return this.loggedIn;
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
