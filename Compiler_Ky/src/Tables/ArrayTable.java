package Tables;

import java.util.ArrayList;

public class ArrayTable {
    private static ArrayList<ArrayTableEntry> ArrTable;

    public static int createAnEntry(ArrayList<Integer> upperBounds){
        ArrayTableEntry arrayTableEntry = new ArrayTableEntry(upperBounds);
        ArrTable.add(arrayTableEntry);
        int size = ArrTable.size();
        return size - 1;
    }

    public static ArrayList<ArrayTableEntry> getArrTable(){
        return ArrTable;
    }

    public static void createArrTable(){
        ArrTable = new ArrayList<>();
    }

    public static int createAnEntry(ArrayList<Integer> upperBounds, ArrayList<Integer> constArray){
        ArrTable.add(new ArrayTableEntry(upperBounds, constArray));
        return ArrTable.size() - 1;
    }

}
