import java.util.ArrayList;

public class Dealer extends Player {

 
  
   //constructor
    public Dealer(String username, String password) {
        super(username, password, 0);
    }
   
   //getter methods
    public int getHandValue() {
	return this.getHand().getHandTotal();
    }
   
    public void hitPlayer(Player player, Card card) {
	player.addCardToHand(card);
    }
   
    public void startGame(Table table) {
	this.getHand().clearHand();
        dealStartingCard(table);
    }
   
    public void dealStartingCard(Table table) {
        ArrayList<Player> players = table.getPlayers();   
	for (int i = 0; i < players.size(); i++) {
	    Player player = players.get(i);   
	    Card firstCard = table.getGame().getShoe().getCard();
	    Card secondCard = table.getGame().getShoe().getCard();
	    player.setHand(firstCard, secondCard);
	}   
	Card dealerCard1 = table.getGame().getShoe().getCard();
	Card dealerCard2 = table.getGame().getShoe().getCard();
	this.setHand(dealerCard1, dealerCard2);
    }

    public void clearDealerHand() {
        hand.clearHand();
    }
   
}


