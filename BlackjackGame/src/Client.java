import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Client {
    private Socket socket;
    private GUI gui;
    ObjectInputStream inStream;
	ObjectOutputStream outStream;
	boolean loggedIn;
    
    public Client(GUI gui) throws IOException {
    	this.gui = gui;
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
    public void sendLoginMessage(String username, String password) {
    	  Message loginMessage = new Message(MessageType.LOGIN, username, password, 0, "login", "Client", null, -1);
    	  
    	  try {
			this.outStream.writeObject(loginMessage);
			this.outStream.flush();
			
		  } catch (IOException except) {
			except.printStackTrace();
		  }
    	  
    	
    }
    
    public void sendLogoutMessage(String username, String password) {
  	  Message logoutMessage = new Message(MessageType.LOGOUT, username, password, 0, "logout", "Client", null, -1);
  	  
  	  try {
			this.outStream.writeObject(logoutMessage);
			this.outStream.flush();
			
		  } catch (IOException  except) {
			except.printStackTrace();
		  }
  	  
  }
    
    public void sendRequestHitMessage(String username, int tableID) {
    	Message requestMessage = new Message(MessageType.REQUEST_HIT, username, null, 0, "Player requested a hit", "Client", null, tableID);
    	
    	  try {
  			this.outStream.writeObject(requestMessage);
  			this.outStream.flush();
  			
  		  } catch (IOException  except) {
  			except.printStackTrace();
  		  }
    }
    
    public void sendHitMessage(String playerUsername, Card card, int tableID) {
    	Message hitMessage = new Message(MessageType.HIT, playerUsername, null, 0, "Hit", "Client", card, tableID);
    	
    	try {
    		this.outStream.writeObject(hitMessage);
    		this.outStream.flush();
    		
    	} catch (IOException except) {
    		except.printStackTrace();
    	}
    	
 
    }
    
    public void sendStayMessage(String username) {
    	
    	Message stayMessage = new Message(MessageType.STAY, username, null, 0, "Stay", "Client", null, -1);
	
    	try {
    		this.outStream.writeObject(stayMessage);
    		this.outStream.flush();
		
    	} catch (IOException  except) {
    		except.printStackTrace();
    	}
	
    }
    
    public void sendJoinTableMessage(String username, int tableID) {
    	Message joinMessage = new Message(MessageType.JOIN_TABLE, username, null, 0, "Join Table", "Client", null, tableID);
    	
    	try {
    		this.outStream.writeObject(joinMessage);
    		this.outStream.flush();	
    		
    	} catch (IOException  except) {
    		except.printStackTrace();
    	}
    
    }
    
    public void sendLeaveTableMessage(String username, int tableID) {
    	Message leaveMessage = new Message(MessageType.LEAVE_TABLE, username, null, 0, "Leave Table", "Client", null, tableID);
    	
    	try {
    		this.outStream.writeObject(leaveMessage);
    		this.outStream.flush();
    		
    	} catch (IOException except) {
    		except.printStackTrace();
    	}
    
    }
    
    public void sendCreateTableMessage(String username) {
    	Message createMessage = new Message(MessageType.CREATE_TABLE, username, null, 0, "Create Table", "Client", null, -1);
    	
    	try {
    		this.outStream.writeObject(createMessage);
    		this.outStream.flush();
    		
    	} catch (IOException except) {
    		except.printStackTrace();
    	}
    	
    }
    
    public void sendCloseTableMessage(String username, int tableID) {
    	Message closeMessage = new Message(MessageType.CLOSE_TABLE, username, null, 0, "Close Table", "Client", null, tableID);
    	
    	try {
    		this.outStream.writeObject(closeMessage);
    		this.outStream.flush();
    		
    	} catch (IOException except) {
    		except.printStackTrace();
    	}
    	
    }
    
    public void sendDepositMessage(String username, int balance) {
    	Message depositMessage = new Message(MessageType.DEPOSIT, username, null, balance, "Deposit", "Client", null, -1);
    	
    	try {
    		this.outStream.writeObject(depositMessage);
    		this.outStream.flush();
    		
    	} catch (IOException except) {
    		except.printStackTrace();
    	}
    	
    }
    
    public void sendWithdrawalMessage(String username, int balance) {
    	Message withdrawMessage = new Message(MessageType.WITHDRAWAL, username, null, balance, "Withdraw", "Client", null, -1);
    	
    	try {
    		this.outStream.writeObject(withdrawMessage);
    		this.outStream.flush();
    		
   
    	} catch (IOException except) {
    		except.printStackTrace();
    	}
  
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
    
    
    public void startListening() {
    	Thread listenerThread = new Thread(new Runnable() {
    		public void run() {
    			try {
    				while(true) {
    					Message msg = (Message) inStream.readObject();
    					handleServerMessage(msg);
    				}
    			} catch (IOException | ClassNotFoundException e) {
    				System.out.println("Disconnected from Server");
    			}
    		}
    	});
    	listenerThread.start();
    }
    
    private void handleServerMessage(Message msg) {
    	
    	switch (msg.getType()) {
    	case LOGIN:
    		
    		String loginStatus = msg.getText();
    		
    		if (loginStatus.equals("Login successful.")) {
    			this.loggedIn = true;
				gui.setPlayer(msg.getUsername(), msg.getPassword(), msg.getBalance());
				gui.showLobby();
			}
			else if (loginStatus.equals("Dealer login successful.")) {
				this.loggedIn = true;
				gui.setDealer(msg.getUsername(), msg.getPassword());
				gui.showLobby();
			} else {
				JOptionPane.showMessageDialog(null, loginStatus, "Login Unsuccessful", JOptionPane.ERROR_MESSAGE);
			}
    		
    		break;
    	case LOGOUT:
    		this.loggedIn = false;
    		
    		JOptionPane.showMessageDialog(null, "You have been logged out.", "Logout", JOptionPane.INFORMATION_MESSAGE);
    		
    		gui.setPlayer("", "", 0);
    		
    		gui.getCardLayout().show(gui.getMainPanel(), "login");
    		break;
    	case JOIN_TABLE:
    		gui.setTableID(msg.getTableID());
    		gui.showTable();
    		
    		gui.getCardLayout().show(gui.getMainPanel(), "table");
    		break;
    	case LEAVE_TABLE:
    		JOptionPane.showMessageDialog(null, msg.getText(), "Leave Table", JOptionPane.INFORMATION_MESSAGE);
    		gui.showLobby();
    		gui.getCardLayout().show(gui.getMainPanel(), "lobby");
    		break;
    		
    	case CREATE_TABLE:
    		if (msg.getTableID() != -1) {
    			gui.setTableID(msg.getTableID());
    			Table newTable = new Table();
      	        gui.setTable(newTable);
    			gui.showTable();
    		} else {
    			JOptionPane.showMessageDialog(null, msg.getText(), "Table Creation Failed", JOptionPane.ERROR_MESSAGE);
    		}
    		break;
    	case TABLE_FULL:
    		JOptionPane.showMessageDialog(null, "Table is full", "Join Table Error", JOptionPane.ERROR_MESSAGE);
    		
    		break;
    	case NO_DEALER:
    		JOptionPane.showMessageDialog(null, "No Dealer Present. Cannot Join", "No Dealer Present", JOptionPane.ERROR_MESSAGE);
    		
    		break;
    	case HIT:
    		Card card = msg.getCard();
   		 	//gui.getPlayer().addCardToHand(card); 

   		 	gui.addCardToPlayerHand(card);
   		 	
   		 	if (gui.getPlayer().getHandValue() > 21) {
   		 		JOptionPane.showMessageDialog(null, "YOU BUSTED! SORRY LOSER!", "HAHAHAHAHA", JOptionPane.INFORMATION_MESSAGE);
   		 	}
    		break;
    	case REQUEST_HIT:
    		gui.addHitRequest(msg.getUsername());
    		break;
    	case STAY:
    		break;
    	case WITHDRAWAL:
    		break;
    	case DEPOSIT:
    		break;
    	case TABLE_COUNT:
    		break;
		default:
			break;
    	}
    	
    }
}
