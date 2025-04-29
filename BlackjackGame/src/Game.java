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
	System.out.println("Dealer's turn!");
	//dealer take turn
	while (dealer.getHand().getHandValue() < 12) {
	    
	    System.out.println("Dealer draws a card. Hand value is now: " + dealer.getHand().getHandValue());
	    
	}
	if (dealer.getHand().isBusted()) {
	    System.out.println("Dealer busted!");
	} else {
	    System.out.println("Dealer stays at: " + dealer.getHand().getHandValue());
	}
	
	roundEnd();
    }
	
    public void playerTurn() {
		
    }
	
    public void roundEnd() {
		
    }
    public void hit() {

    }

    public void stay() {
	Player player = players.get(currentPlayer);
	//player has stayed = true
	System.out.println(player.getName() + " stays at: " + player.getHand();
	nextPlayerTurn();
    }
}
