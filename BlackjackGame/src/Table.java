
public class Table {
  private ArrayList<Player> players;
  private Dealer dealer;
  private int maxPlayers;
  private int maxDealers;
  private Game game;

  public Table(Dealer dealer) {
    this.plaeyrs = new ArrayList<>();
    this.dealer = dealer;
    this.maxPlayers = 6; //6 players maximum per table
    this.maxDealers = 2; //2 players maximum per table
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

  public boolean isEmpty() {
    return players.isEmpty();
  }
    
}
