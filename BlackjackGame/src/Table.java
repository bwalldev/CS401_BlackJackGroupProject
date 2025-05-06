import java.util.ArrayList;

public class Table {
    private ArrayList<Player> players;
    private Dealer dealer;
    private int maxPlayers;
    private int maxDealers;
    private Game game;
    private Shoe shoe;

    public Table() {
	this.players = new ArrayList<>();
	this.dealer = null;
	this.maxPlayers = 6; //6 Players maximum per table
	this.maxDealers = 1; //1 Dealer maximum per table
	this.shoe = new Shoe(3);
	this.game = new Game(this.players, this.dealer, this.maxPlayers);
    }

    public ArrayList<Player> getPlayers() {
    	return players;
    }
    
    public int getNumPlayers() {
    	return players.size();
    }
    
    public void addPlayer(Player player) {
    	if (this.players.size() < this.maxPlayers) {
    		players.add(player);
    	}
    }
    
    public void setGame(Game game) {
    	this.game = game;
    }

    public Dealer getDealer() {
    	return this.dealer;
    }

    public int getMaxPlayers() {
    	return maxPlayers;
    }

    public int getMaxDealers() {
    	return maxDealers;
    }
    
    public Card hitPlayer() {
    	return this.shoe.getCard();
    }
    
    public Game getGame() {
    	return this.game;
    }
    
    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }
    
    public void removePlayer(Player p) {
        this.players.remove(p);
    }

    public boolean isEmpty() {
    	return players.isEmpty();
    }
}
