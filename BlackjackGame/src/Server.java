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
	
	public static void main(String[] args) {
		// What Client Sockets will connect to
		ServerSocket serverSocket = null;
		
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
							String username = incomingMessage.getUsername();
							String password = incomingMessage.getPassword();
							
							boolean authenticated = false;
							
							// Reads from "database" by line separated into parts
							try (BufferedReader reader = new BufferedReader(new FileReader("src\\users.txt"))) {
								
								String line;
								
								while ((line = reader.readLine()) != null) {
									String[] parts = line.split(",");
									if (parts.length == 3 && parts[0].equals(username) && parts[1].equals(password)) {
										int balance = Integer.parseInt(parts[2]);
										
										// Get Logged in Player list, if username isn't logged in create a new player
										if (getLoggedInPlayer(username) == null) {
											Player newPlayer = new Player(username, password, balance);
											Server.loggedInPlayers.add(newPlayer);
											
											Message success = new Message(MessageType.LOGIN, username, password, "Login successful.", null, null);
						                    outStream.writeObject(success);
						                    
						                 // Set to true because login matched
											authenticated = true;
											break;
										} else {
											// Reject logins because username isn't null
											Message alreadyLoggedIn = new Message(MessageType.LOGIN, username, password, "Already logged in.", null, null);
											outStream.writeObject(alreadyLoggedIn);
											authenticated = true;
											break;
										}
									}
								}
								
								// No login from file check , send failure message
								if (!authenticated) {
						            Message fail = new Message(MessageType.LOGIN, username, password, "Invalid username or password. entered " + username + ", " + password, null, null);
						            outStream.writeObject(fail);
						        }

						    } catch (IOException e) {
						        e.printStackTrace();
						    }
							
							break;
						case LOGOUT:
							Message logoutMessage = new Message(MessageType.LOGOUT, null, null, "You've been logged out", null, null);
							
							outStream.writeObject(logoutMessage);
							
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
			} catch (EOFException e) {
				System.out.println("A Client has disconnected");
			} catch (SocketException e) {
				System.out.println("A Client has closed the program without disconnecting.");
			} catch (IOException | ClassNotFoundException except) {
				except.printStackTrace();
			} finally {
				// Making sure to close the Socket, input, and output streams to the Client
				try {
					if (inStream != null)
						inStream.close();
					
					if (outStream != null) {
						outStream.close();
						clientSocket.close();
					}
				} catch (IOException except) {
					except.printStackTrace();
				}
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
	}
}
