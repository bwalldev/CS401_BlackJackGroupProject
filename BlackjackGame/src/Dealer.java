public class Dealer {
	//Attributes 
   private int id;
   private String username;
   private String Password;
   private Hand hand;
 
   
   //constructor
   public Dealer(int id, String username, String password) {
	   this.id = 0;
	   this.username = "Dealer";
	   this.Password = "password";
	   this.hand = new Hand();
	  }
   
   //getter methods
   public int getID() {
	   return id;
   }
   
   public String getUsername() {
	   return username;
   }
	  
   public String getPassword() {
	   return Password;
   }
   
   public Hand getHand() {
	   return hand;
   }
   
   public int getHandvalue() {
	   return hand.getHandTotal();
   }
   
   public void addCardToHand(Card newcard) {
	   hand.addCard(newcard);
   }
   
   public void startGame() {
	   hand = new Hand();
	   dealStartingCard();
   }
   
//   public void startRound() {
//	   
//   }
   
   public void dealStartingCard(){
	   
   }
}
