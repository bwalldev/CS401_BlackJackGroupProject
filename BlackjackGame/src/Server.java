import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
	
	// Stores players who are logged in
	private static List<Player> loggedInPlayers = new ArrayList<>();
	
	public static void main(String[] args) {
		
	}
	
	// -------------- ClientHandler Class ----------------------------------------------------

	class ClientHandler implements Runnable {
		private final Socket clientSocket;
		
		public ClientHandler(Socket socket) {
			this.clientSocket = socket;
		}
		

		@Override
		public void run() {
			ObjectOutputStream outStream = null;
			ObjectInputStream inStream = null;
			
			try {
				outStream = new ObjectOutputStream(clientSocket.getOutputStream());
				inStream = new ObjectInputStream(clientSocket.getInputStream());
				
				
				while (true) {
					Message incomingMessage = (Message) inStream.readObject();
					
					switch (incomingMessage.getType()) {
						
						case LOGIN:
							String username = incomingMessage.getFrom();
							String password = incomingMessage.getText();
							
							boolean authenticated = false;
							
							// Reads from "database" by line separated into parts
							try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
								
								String line;
								
								while ((line = reader.readLine()) != null) {
									String[] parts = line.split(" ");
									if (parts.length == 3 && parts[0].equals(username) && parts[1].equals(password)) {
										int balance = Integer.parseInt(parts[2]);
										
										// Get Logged in Player list, if username isn't logged in create a new player
										if (getLoggedInPlayer(username) == null) {
											Player newPlayer = new Player(username, password);
											Server.loggedInPlayers.add(newPlayer);
											
											Message success = new Message(MessageType.LOGIN, username, password, "Login successful.", null, null);
						                    outStream.writeObject(success);
						                    
						                 // Set to true because login matched
											authenticated = true;
										} else {
											// Reject logins because username isn't null
											Message alreadyLoggedIn = new Message(MessageType.LOGIN, username, password, "Already logged in.", null, null);
											outStream.writeObject(alreadyLoggedIn);
										}
										
										
										break;
									}
								}
								
								// No login from file check , send failure message
								if (!authenticated) {
						            Message fail = new Message(MessageType.LOGIN, username, password, "Invalid username or password.", null, null);
						            outStream.writeObject(fail);
						        }

						    } catch (IOException e) {
						        e.printStackTrace();
						    }
							
							break;
						case LOGOUT:
							
							return;
						case JOIN_TABLE:
							
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
							
					}
				}
				
				
			} catch (IOException | ClassNotFoundException except) {
				except.printStackTrace();
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
		
		// Create a player list for table later
	}



	@Override
	public void run() {
	}
}
