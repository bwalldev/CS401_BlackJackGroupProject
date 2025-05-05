import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class Server {
	// Stores players who are logged in
	private static List<Player> loggedInPlayers = new ArrayList<>();
	private static List<Dealer> loggedInDealers = new ArrayList<>();
	private static List<Table> tables = new ArrayList<Table>();
	private static List<ClientHandler> clientHandlers = new ArrayList<>();
	
	public static void main(String[] args) {
		// What Client Sockets will connect to
		ServerSocket serverSocket = null;
		
		// Test table
		for (int i = 0; i < 3; i++) {
		    tables.add(new Table());
		}
		
		try {
			// Initializing the ServerSocket and making sure that the port address can be used again if closed 
			serverSocket = new ServerSocket(7777);
			serverSocket.setReuseAddress(true);
			
			System.out.println("...Waiting for connection...\n");
			
			// Infinite loop that spawns new threads when a new Client connects to the ServerSocket
			while (true) {
				Socket socket = serverSocket.accept();
				
				// Creating a new handle to use for the new thread
				ClientHandler clientSocket = new ClientHandler(socket);
				
				new Thread(clientSocket).start();
			}
		} catch (IOException except) {
			except.printStackTrace();
		} finally {
			// Making sure to close the server's ServerSocket when the server closes
			if (serverSocket != null)
				try {
					serverSocket.close();
				}
				catch (IOException except) {
					except.printStackTrace();
				}
		}
	}
	
	// -------------- ClientHandler Class ----------------------------------------------------

	private static class ClientHandler implements Runnable {
		private final Socket clientSocket;
		private ObjectOutputStream outStream;
		private ObjectInputStream inStream;
		private String username = null;
		
		public ClientHandler(Socket socket) {
			this.clientSocket = socket;
			try {
		        this.outStream = new ObjectOutputStream(socket.getOutputStream());
		        this.inStream = new ObjectInputStream(socket.getInputStream());
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
			Server.clientHandlers.add(this);
		}

		@Override
		public void run() {
			
			try {
							
				while (true) {
					Message incomingMessage = (Message) this.inStream.readObject();
					handleMessage(incomingMessage);
					
				}
			} catch (EOFException e) {
				System.out.println("A Client has disconnected");
			} catch (SocketException e) {
				System.out.println("A Client has closed the program without disconnecting.");
				
				if (this.username != null) {
					Player player = getLoggedInPlayer(this.username);
					if (player != null) {
						Server.loggedInPlayers.remove(player);
						System.out.println("Logged Out Player: " + this.username);
					}
					
					Dealer dealer = getLoggedInDealer(this.username);
					if (dealer != null) {
						Server.loggedInDealers.remove(dealer);
						System.out.println("Logged Out Dealer: " + this.username);
					}
				}
				// try to add logout here
			} catch (IOException | ClassNotFoundException except) {
				except.printStackTrace();
			} finally {
				// Making sure to close the Socket, input, and output streams to the Client
				try {
					if (this.inStream != null) {
						this.inStream.close();	
					}
					
					if (this.outStream != null) {
						this.outStream.close();
					}
					if (this.clientSocket != null) {
						this.clientSocket.close();
					}
				} catch (IOException except) {
					except.printStackTrace();
				}
				Server.clientHandlers.remove(this);
			}
		}
		
		public void handleMessage(Message incomingMessage) {
			try {
				
				switch (incomingMessage.getType()) {
				
				case LOGIN:
					handleLogin(incomingMessage);
					break;
				case LOGOUT:
					handleLogout(incomingMessage);
					return;
				case JOIN_TABLE:
					handleJoinTable(incomingMessage);
					
					break;
				case LEAVE_TABLE:
					handleLeaveTable(incomingMessage);
					break;
				case CREATE_TABLE:
					handleCreateTable(incomingMessage);
					break;
				case CLOSE_TABLE:
					handleCloseTable(incomingMessage);
					break;
				case HIT:
					
					break;
				case STAY:
					
					break;
				case WITHDRAWAL:
					
					break;
				case DEPOSIT:
					
					break;
				case TABLE_COUNT:
					Message outMessage = new Message(MessageType.TABLE_COUNT, null, null, 0, String.valueOf(tables.size()), null, null, -1);
					
					this.outStream.writeObject(outMessage);
					
					break;
					
			}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		private ObjectOutputStream getStreamForUser(String username) {
			for (int i = 0; i < Server.clientHandlers.size(); i++) {
				ClientHandler channel = Server.clientHandlers.get(i);
				if (username.equals(channel.username)) {
					return channel.outStream;
				}
			}
			return null;
		}
		
		// Search loggedInPlayer list for player with the username
		private Player getLoggedInPlayer(String username) {
			for (int i = 0; i < Server.loggedInPlayers.size(); i++) {
				Player p = Server.loggedInPlayers.get(i);
				if (p.getUsername().equals(username)) {
					return p;
				}
			}
			return null;
		}
		
		private Dealer getLoggedInDealer(String username) {
			for (int i = 0; i < Server.loggedInDealers.size(); i++) {
				Dealer d = Server.loggedInDealers.get(i);
				if (d.getUsername().equals(username)) {
					return d;
				}
			}
			return null;
		}
		
		
		private void handleLogin(Message incomingMessage) throws IOException {
			this.username = incomingMessage.getUsername();
			String password = incomingMessage.getPassword();		
			
			
			// Reads from "database" by line separated into parts
			try (BufferedReader reader = new BufferedReader(new FileReader("src\\players.txt"))) {
				
				String line;
				
				while ((line = reader.readLine()) != null) {
					String[] parts = line.split(",");
					if (parts.length == 3 && parts[0].equals(username) && parts[1].equals(password)) {
						int balance = Integer.parseInt(parts[2]);
						
						// Get Logged in Player list, if username isn't logged in create a new player
						if (getLoggedInPlayer(username) == null) {
							Player newPlayer = new Player(username, password, balance);
							Server.loggedInPlayers.add(newPlayer);
							
							Message success = new Message(MessageType.LOGIN, username, password, balance, "Login successful.", null, null, -1 );
		                    this.outStream.writeObject(success);
		                    
		        
						} else {
							// Reject logins because username isn't null
							Message alreadyLoggedIn = new Message(MessageType.LOGIN, username, password, balance, "Already logged in.", null, null, -1);
							this.outStream.writeObject(alreadyLoggedIn);
							
						}
						return;
						//break;
					}
				}
				
		    } catch (IOException e) {
		        e.printStackTrace();
		    }

			
				try (BufferedReader reader = new BufferedReader(new FileReader("src\\dealers.txt"))) {
					String line;
					
					while ((line = reader.readLine()) != null ) {
						String[] parts = line.split(",");
						if (parts.length >= 2 && parts[0].equals(username) && parts[1].equals(password)) {
							
							if (getLoggedInDealer(username) == null) {
								Dealer newDealer = new Dealer(username, password);
								Server.loggedInDealers.add(newDealer);
								
								Message success = new Message(MessageType.LOGIN, username, password, 0, "Dealer login successful.", null, null, -1);
								this.outStream.writeObject(success);
								
							} else {
								Message alreadyLoggedIn = new Message(MessageType.LOGIN, username, password, 0, "Dealer already logged in.", null, null, -1);
								this.outStream.writeObject(alreadyLoggedIn);
							}
							
							return;
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			
				Message fail = new Message(MessageType.LOGIN, username, password, 0, "Invalid username or password.", null, null, -1);
				this.outStream.writeObject(fail);
		
		}
		
		private void handleLogout(Message incomingMessage) throws IOException {
			String username = incomingMessage.getUsername();
			
			Player player = getLoggedInPlayer(username);
			if (player != null) {
				Server.loggedInPlayers.remove(player);
				System.out.println("Player " + username + " has logged out.");
			}
			
			Dealer dealer = getLoggedInDealer(username);
			if (dealer != null) {
				Server.loggedInDealers.remove(dealer);
				System.out.println("Dealer " + username + " has logged out.");
			}
			
			Message logoutMessage = new Message(MessageType.LOGOUT, null, null, 0, "You've been logged out", null, null, -1);
			
			this.outStream.writeObject(logoutMessage);
		}
		
		private void handleJoinTable(Message incomingMessage) throws IOException {
			int tableID = incomingMessage.getTableID();
			Player player = getLoggedInPlayer(incomingMessage.getUsername());
			
			if (player == null) {
				return;
			}
			
			Table table = tables.get(tableID);
			
			if (player.getTableID() != -1) {
				Message alreadyJoined = new Message(MessageType.JOIN_TABLE, player.getUsername(), "", player.getBalance(), "Already in a table.", "Server", null, player.getTableID());
				this.outStream.writeObject(alreadyJoined);
				
				this.outStream.flush();
				return;
			}
			
			if (table.getDealer() == null) {
				Message outMessage = new Message(MessageType.NO_DEALER, "", "", 0, "No Dealer Present, Cannot Join.", "Server", null, tableID);
				
				this.outStream.writeObject(outMessage);
				this.outStream.flush();
				return;
			}
			
			// If Table is full, send a table full message
			if (table.getPlayers().size() >= 2) {
				Message outMessage = new Message(MessageType.TABLE_FULL, "", "", 0, "Table " + (tableID + 1) + " is full", "Server", null, tableID);
				
				this.outStream.writeObject(outMessage);
				this.outStream.flush();
				return;
			}
			
			// Table has an open seat
			
			table.addPlayer(player);
			player.setTableID(tableID);
			
			Message outMessage = new Message(MessageType.JOIN_TABLE, player.getUsername(), "", player.getBalance(), "Joined Table " + (tableID +1), "Server", null, tableID);
			this.outStream.writeObject(outMessage);
			
		}
		
		private void handleLeaveTable(Message incomingMessage) throws IOException {
			Player player = getLoggedInPlayer(incomingMessage.getUsername());
			int tableID = incomingMessage.getTableID();
			
			if (player == null || tableID < 0 || tableID >= tables.size()) {
				return;
			}
			
			Table table = tables.get(tableID);
			table.removePlayer(player);
			player.setTableID(-1);
			
			Message leaveTable = new Message(MessageType.LEAVE_TABLE, player.getUsername(), "", player.getBalance(), "You have left the table.", "Server", null, -1);
		    this.outStream.writeObject(leaveTable);
		    this.outStream.flush();
		}
		
		private void handleCreateTable(Message incomingMessage) throws IOException {
			Dealer dealer = getLoggedInDealer(incomingMessage.getUsername());
			
			if (dealer == null) {
				return;
			}
			
			for (int i = 0; i < tables.size(); i++) {
				Table table = tables.get(i);
				
				if (table.getDealer() == null) {
					table.setDealer(dealer);
					dealer.setTableID(i);
					
					
					Message tableCreated = new Message(MessageType.CREATE_TABLE, dealer.getUsername(), null, 0, "Table " + (i + 1) + " created.", "Server", null, i);
					this.outStream.writeObject(tableCreated);
					this.outStream.flush();
					return;
					
				}
				
			}
			Message failMessage = new Message(MessageType.CREATE_TABLE, dealer.getUsername(), null, 0, "Tables full.", "Server", null, -1);
			this.outStream.writeObject(failMessage);
			this.outStream.flush();
		}
		
		private void handleCloseTable(Message incomingMessage) throws IOException {
			Dealer dealer = getLoggedInDealer(incomingMessage.getUsername());
			int tableID = incomingMessage.getTableID();
			
			if (dealer == null || tableID < 0 || tableID >= tables.size()) {
				return;
			}
			
			Table table = tables.get(tableID);
			
			if (table.getDealer() != dealer) {
				return;
			}
			
			 List<Player> players = new ArrayList<>(table.getPlayers());
			 
			 for (int i = 0; i < players.size(); i++) {
				 Player p = players.get(i);
				 
				 table.removePlayer(p);
				 p.setTableID(-1);
				 
				 ObjectOutputStream playerOut = getStreamForUser(p.getUsername());
				 if (playerOut != null) {
					 Message kickMessage = new Message(MessageType.LEAVE_TABLE, p.getUsername(), "", p.getBalance(), "The dealer closed table. You will return to lobby", "Server", null, -1);
					 playerOut.writeObject(kickMessage);
					 playerOut.flush();
				 }
				 
			 }
			 
			 table.setDealer(null);
			 dealer.setTableID(-1);
			 
			 Message dealerMessage = new Message(MessageType.LEAVE_TABLE, dealer.getUsername(), "", 0, "Table closed. You will return to lobby", "Server", null, -1);
			 this.outStream.writeObject(dealerMessage);
			 this.outStream.flush();
			
		}
	}
	
}
