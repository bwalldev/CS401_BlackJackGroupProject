import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		
		ServerSocket server = null;

		System.out.println("server running");

		try {

			// server is listening on port 1234
			server = new ServerSocket(1234);
			server.setReuseAddress(true);

			// running infinite loop for getting
			// client request
			while (true) {

				// socket object to receive incoming client
				// requests
				Socket client = server.accept();

				// Displaying that new client is connected
				// to server
				System.out.println("New client connected");

				// create a new thread object
				ClientHandler clientSock
					= new ClientHandler(client);

				// This thread will handle the client
				// separately
				new Thread(clientSock).start();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (server != null) {
				try {
					server.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
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
				
			} catch (IOException except) {
				except.printStackTrace();
			}
		}
		
	}



//	@Override
//	public void run() {
//	}
}
