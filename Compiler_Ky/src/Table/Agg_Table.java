package Table;
import java.util.TreeMap;

public class Agg_Table {
    private final TreeMap<String, TableEntry> attrTable;
    private final TreeMap<String, TableEntry> funcTable;
    public Agg_Table(){
        this.attrTable = new TreeMap<>();
        this.funcTable = new TreeMap<>();
    }

    public TreeMap<String, TableEntry> get_FuncTable(){
        return funcTable;
    }

    public TreeMap<String, TableEntry> get_RightTable(boolean isFunc){
        if(!isFunc) {
            return get_AttrTable();
        }
        else {
            return get_FuncTable();
        }
    }

    public TreeMap<String, TableEntry> get_AttrTable(){
        return attrTable;
    }
}
