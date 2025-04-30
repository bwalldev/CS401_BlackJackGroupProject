import java.util.ArrayList;

public class Lobby {
    private ArrayList<Table> tables;
    private int maxTables;

    public Lobby() {
        this.tables = new ArrayList<Table>();
        this.maxTables = 3;
    }

    public void addTable(Dealer dealer) {
        if(tables.size() < maxTables) {
            tables.add(new Table(dealer));
        }
    }

    public void removeTable(Table table) {
        tables.remove(table);
    }
}
  
