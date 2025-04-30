import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Game {
    private Card card;
    private ArrayList<Player> players;
    private Dealer dealer;
    private Shoe shoe;
    private int currentPlayer;
    private Timer timer;
    private TimerTask turnTimeoutTask;
    private int maxPlayers;

    public Game(ArrayList<Player> players, Dealer dealer, int maxPlayers) {
	this.players = players;
	this.dealer = dealer;
	this.maxPlayers = maxPlayers;
	this.shoe = new Shoe(3); // 3 decks in the shoe, can change if we decide 
	this.currentPlayer = 0;
	this.timer = new Timer();
    }
    private void startTurnTimer() {
	if(Timer != null) {
	    timer.cancel(); //stop timer if there is no time left
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
		System.out.println("It's now " + player.getUsername() + "'s turn.");
	    }
	}
    }
	
    public void dealerTurn() {
	System.out.println("Dealer's turn!");
	//dealer take turn
	while (dealer.getHandValue() < 12) { //The max card value is 11
	    dealer.getHand().addCard(card); //must complete the method for addCard for dealer class
	    System.out.println("Dealer draws a card. Hand value is now: " + dealer.getHandValue());
	}
	if (dealer.getHand().isBusted()) {
	    System.out.println("Dealer busted!");
	} else {
	    System.out.println("Dealer stays at: " + dealer.getHandValue());
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
		System.out.println(player.getUsername() + " has busted. :( Dealer wins.");
	    } else if (dealer.getHand().isBusted()) {
		System.out.println("Dealer has busted!");
	    } else if (playerScore > dealerScore) {
		System.out.println(player.getUsername() + " wins!!!");
	    } else if (playerScore == dealerScore) {
		System.out.println("It's a tie!!");
	    } else {
		System.out.println(player.getUsername() + " loses hand :( .");
	    }
	}

	//Reset game for next round
	for(Player player : players) {
	    //clear player's hand
	}
	//clear player's hand
	currentPlayer = 0; //set the player counter to 0.
	System.out.println("Round ended. Ready for next round.");
	  
    }
	
    public void hit() {
	Player player = players.get(currentPlayer);
	player.addCardToHand(shoe.getCard());
	System.out.println(player.getUsername() + "hits and now has: " + player.getHandValue());
	    if(player.getHand().isBusted()) {
	    	System.out.println(player.getUsername() + " busted!");
		nextPlayerTurn();
	    }
    }

    public void stay() {
	Player player = players.get(currentPlayer);
	//player has stayed = true
	player.stay();
	System.out.println(player.getUsername() + " stays at: " + player.getHandValue());
	nextPlayerTurn();
    }
}
