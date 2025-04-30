import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
	    try {
            // Create server socket on port 12336
            ServerSocket serverSocket = new ServerSocket(12336);
            System.out.println("Server is running...");

            //listening for clients
            while (true) {
                Socket clientSocket = serverSocket.accept(); 
                System.out.println("Client connected!");

                //new thread to handle the client
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
		
	}
	
	// -------------- ClientHandler Class ----------------------------------------------------

	class ClientHandler implements Runnable {
		private final Socket clientSocket;
		
		public ClientHandler(Socket socket) {
			this.clientSocket = socket;
		}
		

		public void run() {
			try (
				 BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	             PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true)
	            		 ){
				
				
			} catch (IOException except) {
				except.printStackTrace();
			}
		}
		
	}


//	public void run() {
//	}
}
