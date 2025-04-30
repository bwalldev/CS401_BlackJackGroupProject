import java.util.ArrayList;
import java.util.Timer;

public class Game {
    private ArrayList<Player> players;
    private Dealer dealer;
    private Shoe shoe;
    private int currentPlayer;
    private Timer timer;
    private int maxPlayers;

    public Game(ArrayList<Player> players, Dealer dealer, int maxPlayers) {
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
	++currentPlayer;
	if (currentPlayer >= players.size()) {
		dealerTurn();
	} else {
	    Player player = players.get(currentPlayer);
	    if (player.getHand().isBusted() || player.hasStayed()) { //has stayed is in player class no need to use getHand() made this mistake too many times
		    nextPlayerTurn();
	    } else {
		System.out.println("It's now " + player.getName() + "'s turn.");
	    }
	}
    }
	
    public void dealerTurn() {
	System.out.println("Dealer's turn!");
	//dealer take turn
	while (dealer.getHand().getHandValue() < 12) {
	    dealer.getHand().addCardToHand(card); //must complete the method for addCardToHand for daler class
	    System.out.println("Dealer draws a card. Hand value is now: " + dealer.getHand().getHandValue());
	    
	}
	if (dealer.getHand().isBusted()) {
	    System.out.println("Dealer busted!");
	} else {
	    System.out.println("Dealer stays at: " + dealer.getHand().getHandValue());
	}
	
	roundEnd();
    }
	
    public void roundEnd() {
	//show what the dealer's score is at the end of each round but not the other players
	int dealerScore = dealer.getHandValue();
	System.out.println("Dealer's score: " + dealerScore);
	    for (Player player : players) {
		int playerScore = player.getHandValue();
		if(player.getHand().isBusted()) {
		    System.out.println(player.getName() + " has busted. :( Dealer wins.");
		} else if (delaer.getHand.isBusted()) {
		    System.out.println("Dealer has busted!");
		} else if (playerScore > dealerScore) {
		    System.out.println(player.getName() + " wins!!!");
		} else if (playerScore == dealerScore) {
		    System.out.println("It's a tie!!");
		} else if {
		    System.out.println(player.getName() + " loses hand :( .");
		}
	  
    }
	
    public void hit() {
	Player player = players.get(currentPlayer);
	player.addCard(shoe.draw());
	System.out.prinlnplayer.getName() + "hits and now has: " + player.getHandValue());
	    if(player.getHand().isBusted()) {
	    	System.out.println(player.getName() + " busted!");
		nextPlayerTurn();
	    }
    }

    public void stay() {
	Player player = player.get(currentPlayer);
	//player has stayed = true
	player.stay();
	System.out.println(player.getName() + " stays at: " + player.getHand().getHandValue());
	nextPlayerTurn();
    }
}
