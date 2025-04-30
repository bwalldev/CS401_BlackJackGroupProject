import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private Socket socket;                  
    private BufferedReader input;          
    private PrintWriter output;            
                    

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
