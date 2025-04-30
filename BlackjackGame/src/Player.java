
public class Player {
    private final int id;
    private static int idCount = 0;
    private String username;
    private String password;
    private int balance;
    protected Hand hand;
    private int currBet;
    private boolean stayed;
   
	
	
    Player(String username, String password) {
	this.id = Player.idCount++;
	this.username = username;
	this.password = password;
	this.balance = 0;
	this.hand = new Hand();
	this.currBet = 0;
	this.stayed = false;
    }
	
    public int getID() {
	return this.id;
    }
	
    public String getUsername() {
	return this.username;
    }
	
    public String getPassword() {
	return this.password;
    }
	
    public int getBalance() {
	return this.balance;
    }
	
    public Hand getHand() {
	return this.hand;
    }
  
    public int getHandValue() {
	return this.getHand().getHandTotal();
    }
    
    public int getCurrBet() {
	return this.currBet;
    }
	
    public void setBalance(int balance) {
	if (balance >= 0)
	    this.balance = balance;
    }
	
    public void setHand(Card card1, Card card2) {
	if (card1 != null && card2 != null)
	    hand = new Hand(card1, card2);
    }
	
    public void setCurrBet(int amount) {
	if (amount <= this.balance)
	    this.currBet = amount;
    }
	
    public void withdrawalMoney(int amount) {
	if (amount <= this.balance)
	    this.balance -= amount;
    }
	
    public void depositMoney(int amount) {
	if (amount >= 1)
	    this.balance += amount;
    }
	
    public void addCardToHand(Card newcard) {
        this.getHand().addCard(newcard);  
    }

    public boolean hasStayed() {
        return stayed;
    }

    public void stay() {
        stayed = true;
    }

    public void clearPlayerHand() {
	hand.clearHand();	//clear cards from the players hand 
	stayed = false;		//reset stayed flag to false as per in the constructor	
    }
}
