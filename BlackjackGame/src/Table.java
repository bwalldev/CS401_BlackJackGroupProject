import java.util.ArrayList;

public class Table {
  private ArrayList<Player> players;
  private Dealer dealer;
  private int maxPlayers;
  private int maxDealers;
  private Game game;

  public Table(Dealer dealer) {
    this.players = new ArrayList<>();
    this.dealer = dealer;
    this.maxPlayers = 6; //6 Players maximum per table
    this.maxDealers = 1; //1 Dealer maximum per table
  }

  public ArrayList<Player> getPlayers() {
    return players;
  }

  public Dealer getDealer() {
    return dealer;
  }

  public int getMaxPlayers() {
    return maxPlayers;
  }

  public int getMaxDealers() {
    return maxDealers;
  }
  
  public Game getGame() {
	  return this.game;
  }

  public boolean isEmpty() {
    return players.isEmpty();
  }
    
}
