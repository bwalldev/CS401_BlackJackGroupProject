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
    	  Message loginMessage = new Message(MessageType.LOGIN, username, password, 0, "login", "Client", null, -1);
    	  
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
    
    public String sendLogoutMessage(String username, String password) {
  	  Message logoutMessage = new Message(MessageType.LOGOUT, username, password, 0, "logout", "Client", null, -1);
  	  
  	  try {
			this.outStream.writeObject(logoutMessage);
			this.outStream.flush();
			
			Message serverMessage = (Message) inStream.readObject();
			
			this.loggedIn = true;
			
			return serverMessage.getText();
		  } catch (IOException | ClassNotFoundException except) {
			except.printStackTrace();
		  }
  	  
  	  return "Logout Unsuccessful";
  }
    
    public String sendHitMessage(String username, Card card) {
    	Message hitMessage = new Message(MessageType.HIT, username, null, 0, "Hit", "Client", card, -1);
    	
    	try {
    		this.outStream.writeObject(hitMessage);
    		this.outStream.flush();
    		
    		Message serverMessage = (Message) inStream.readObject();
    		
    		return serverMessage.getText();
    	} catch (IOException | ClassNotFoundException except) {
    		except.printStackTrace();
    	}
    	
    	return "Hit";
    }
    
    public String sendStayMessage(String username) {
    	
    	Message stayMessage = new Message(MessageType.STAY, username, null, 0, "Stay", "Client", null, -1);
	
    	try {
    		this.outStream.writeObject(stayMessage);
    		this.outStream.flush();
		
    		Message serverMessage = (Message) inStream.readObject();
		
    		return serverMessage.getText();
    	} catch (IOException | ClassNotFoundException except) {
    		except.printStackTrace();
    	}
	
    	return "Stay";
    }
    
    public String sendJoinTableMessage(String username, int tableID) {
    	Message joinMessage = new Message(MessageType.JOIN_TABLE, username, null, 0, "Join Table", "Client", null, tableID);
    	
    	try {
    		this.outStream.writeObject(joinMessage);
    		this.outStream.flush();
    		
    		Message serverMessage = (Message) inStream.readObject();
    		
    		return serverMessage.getText();
    		
    	} catch (IOException | ClassNotFoundException except) {
    		except.printStackTrace();
    	}
    	return "Joined Table";
    }
    
    public String sendLeaveTableMessage(String username, int tableID) {
    	Message leaveMessage = new Message(MessageType.LEAVE_TABLE, username, null, 0, "Leave Table", "Client", null, tableID);
    	
    	try {
    		this.outStream.writeObject(leaveMessage);
    		this.outStream.flush();
    		
    		Message serverMessage = (Message) inStream.readObject();
    		
    		return serverMessage.getText();
    		
    	} catch (IOException | ClassNotFoundException except) {
    		except.printStackTrace();
    	}
    	return "Left Table";
    }
    
    public String sendDepositMessage(String username, int balance) {
    	Message depositMessage = new Message(MessageType.DEPOSIT, username, null, balance, "Deposit", "Client", null, -1);
    	
    	try {
    		this.outStream.writeObject(depositMessage);
    		this.outStream.flush();
    		
    		Message serverMessage = (Message) inStream.readObject();
    		
    		return serverMessage.getText();
    		
    	} catch (IOException | ClassNotFoundException except) {
    		except.printStackTrace();
    	}
    	return "Deposited";
    }
    
    public String sendWithdrawalMessage(String username, int balance) {
    	Message withdrawMessage = new Message(MessageType.WITHDRAWAL, username, null, balance, "Withdraw", "Client", null, -1);
    	
    	try {
    		this.outStream.writeObject(withdrawMessage);
    		this.outStream.flush();
    		
    		Message serverMessage = (Message) inStream.readObject();
    		
    		return serverMessage.getText();
    		
    	} catch (IOException | ClassNotFoundException except) {
    		except.printStackTrace();
    	}
    	return "Withdrew";
    }
    
    
    
    public int getTableCountMessage() {
    	Message tableCountMessage = new Message(MessageType.TABLE_COUNT, null, null, 0, null, "Client", null, -1);
    	
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
             if (this.socket != null && this.socket.isClosed() == false)
            	 this.socket.close();
             System.out.println("Disconnected! ");
    	}
    	catch(IOException e) {
            System.out.println("Error Disconnecting! " + e.getMessage());
    	}	
       
    }
}
