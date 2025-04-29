import java.util.ArrayList;
import java.util.Timer;

public class Game {
	private ArrayList<Player> players;
	private Dealer dealer;
	private Shoe shoe;
	private int currentPlayer;
	private Timer timer;
	private int maxPlayers;

	public Game(ArrayList<Player> players, Dealer dealer, int maxPlayers){
		this.players = players;
		this.dealer = dealer;
		this.maxPlayers = maxPlayers;
		this.shoe = new Shoe(3); // 3 decks in the shoe, can change if we decide 
		this.currentPlayer = 0;
		this.timer = new Timer();
	}

	public Shoe getShoe() {
		return this.shoe;
	}

	public void nextPlayerTurn() {
	}
	
	public void dealerTurn() {
	}
	
	public void playerTurn() {
		
	}
	
	public void roundEnd() {
		
	}
	public void hit() {

	}

	public void stay() {
		Players player = players.get(currentPlayer);
	}
}
