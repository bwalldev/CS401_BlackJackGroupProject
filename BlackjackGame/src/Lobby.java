import java.util.ArrayList;

public class Lobby {
    private ArrayList<Table> tables;
    private int maxTables;

    public Lobby() {
        this.tables = new ArrayList<>();
        this.maxTables = 3;
    }

    public void addTable(Table table) {
      if(tables.size() < maxTables) {
          tables.add(table);
      }
    }

    public void removeTable(Table table) {
        tables.remove(table);
    }
}
  
