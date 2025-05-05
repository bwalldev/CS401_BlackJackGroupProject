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
	
	public static void main(String[] args) {
		// What Client Sockets will connect to
		ServerSocket serverSocket = null;
		
		// Test table
		tables.add(new Table(new Dealer("dealer1", "pass1")));
		
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
		
		private void handleJoinTable(Message incomingMessage) throws IOException {
			int tableID = incomingMessage.getTableID();
			
			// If Table is full, send a table full message
			if (tables.get(tableID).getPlayers().size() >= 6) {
				Message outMessage = new Message(MessageType.TABLE_FULL, "", "", 0, "Table 1 is full", "Server", null, tableID);
				
				this.outStream.writeObject(outMessage);
				this.outStream.flush();
			}
			// Table has an open seat
			else {
				Message outMessage = new Message(MessageType.JOIN_TABLE, incomingMessage.getUsername(), "", incomingMessage.getBalance(), "Joining Table 1", "Server", null, tableID);
				
				tables.get(tableID).addPlayer(new Player(incomingMessage.getUsername(), incomingMessage.getPassword(), incomingMessage.getBalance()));
				
				this.outStream.writeObject(outMessage);
				this.outStream.flush();
			}
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
	}
	
}
