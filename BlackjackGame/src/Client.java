import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private Socket socket;                  
    private BufferedReader input;          
    private PrintWriter output;
    
    public static void main(String[] args) {
    	ObjectInputStream inStream = null;
    	ObjectOutputStream outStream = null;
    	
    	Socket socket = null;
    	
    	try {
    		socket = new Socket("localhost", 7777);
    		
    		outStream = new ObjectOutputStream(socket.getOutputStream());
    		inStream = new ObjectInputStream(socket.getInputStream());
    	} catch (IOException except) {
    		except.printStackTrace();
    	}
    	
    	
	    	Message testMessage = new Message(MessageType.LOGIN, "bob", "ross", "TEXT", "Client", null);
	    	
	    	try {
				outStream.writeObject(testMessage);
				outStream.flush();
				
				Message response1 = (Message) inStream.readObject();
				System.out.println("Response 1: " + response1.getText());
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
	    	
	    	Message testMessageFail = new Message(MessageType.LOGIN, "test", "fail", "TEXT", "Client", null);
	    	
	    	try {
				outStream.writeObject(testMessageFail);
				outStream.flush();
				
				Message response2 = (Message) inStream.readObject();
				System.out.println("Response 2: " + response2.getText());
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
	    	
	    	Message serverMessage = null;
	    	
	    	try {
				serverMessage = (Message) inStream.readObject();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
	    	
	    	System.out.println(serverMessage.getText());
    	
    }

    //connect the client to the server on localhost
    public void connectToServer(int port) {
       try {
    	   socket= new Socket ("localhost", port);
    	   input = new BufferedReader (new InputStreamReader(socket.getInputStream()));
    	   output = new PrintWriter(socket.getOutputStream(), true);
       } catch (IOException e) {
    	   System.out.println("connection faild! " +  e.getMessage());
       }
    }

   // send message to server
    public void send(String message) {
    	if (output!= null)
    		output.println(message);  
    }
    
   // receives message from server
    public String receive(){
    	try {
    		if (input != null) {
    			return input.readLine();
    		}
    		else {
    			return null;
    		}
    	} catch (IOException e) {
    		System.out.println("receiving faild! " + e.getMessage());
    		return null;
    		
    	}  
    }

    //disconnects from server
    public void disconnect() {
    	try {
    		 if (input != null) input.close();
             if (output != null) output.close();
             System.out.println("Disconnected! ");
    	}
    	catch(IOException e) {
            System.out.println("Error Disconnecting! " + e.getMessage());
    	}	
       
    }
}
