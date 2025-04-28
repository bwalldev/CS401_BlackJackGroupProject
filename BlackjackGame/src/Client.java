import java.net.Socket;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Client {
	
	public static void main(String[] args) {
		
		String username = "";
		String password = "";
		
		Scanner scan = new Scanner(System.in);
		
		
		System.out.println("enter a username:");
		username = scan.nextLine();
		
		System.out.println("enter a password:");
		password = scan.nextLine();
		
		//TODO: dont make this localhost
		try (Socket socket = new Socket("localhost", 1234)) {
			
			// create a ObjectInputStream so we can read data from it.
			ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
			// create a ObjectoutputStream so we can read data from it.
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

			//send a login request. given one text field the username and password are seperated by the "|" character, this is temporary.
    		Message sendMsg = new Message(username +"|"+password,"login");
    		objectOutputStream.writeObject(sendMsg);
			objectOutputStream.flush();
		
		
		
		
		
		
		
		
			//catch stuff
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//this catch is likely relevant later
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
}
