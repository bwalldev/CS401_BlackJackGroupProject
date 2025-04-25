import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Server {
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
				
			} catch (IOException except) {
				except.printStackTrace();
			}
		}
		
	}



	@Override
	public void run() {
	}
}
