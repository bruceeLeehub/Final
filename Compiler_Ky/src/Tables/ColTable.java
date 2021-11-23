package Tables;

import java.util.TreeMap;

public class ColTable{
    private int Ky_0 = 0;
    private int Ky_1 = 0;
    private int Ky_2 = 0;
    private int Ky_3 = 0;
    private int Ky_4 = 0;
    public TreeMap<String, TableEntry> funcTable;
    public TreeMap<String, TableEntry> attrTable;

    public int getKy_0() {
        return Ky_0;
    }

    public void setKy_0(int ky_0) {
        Ky_0 = ky_0;
    }

    public int getKy_4() {
        return Ky_4;
    }

    public void setKy_4(int ky_4) {
        Ky_4 = ky_4;
    }

    public int getKy_3() {
        return Ky_3;
    }

    public void setKy_3(int ky_3) {
        Ky_3 = ky_3;
    }

    public int getKy_2() {
        return Ky_2;
    }

    public void setKy_2(int ky_2) {
        Ky_2 = ky_2;
    }

    public int getKy_1() {
        return Ky_1;
    }

    public void setKy_1(int ky_1) {
        Ky_1 = ky_1;
    }

    public TreeMap<String, TableEntry> getTheRightTable(boolean isFunc){
        if(!isFunc) {
            return attrTable;
        }
        else {
            return funcTable;
        }
    }
    public ColTable(){
        this.funcTable = new TreeMap<>();
        this.attrTable = new TreeMap<>();
    }
}