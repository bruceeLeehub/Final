package Non_Terminal_Symbols_SysY;

import Parcel_My_Type.*;
import Table.*;
import Word_Analyze.*;

public class FuncType {
    private final RegKey regKey;
    public static String name_funcType = "<FuncType>";

    public RegKey getRegKey(){
        return this.regKey;
    }

    public static FuncType analyse(IdentifySymbol identifySymbol, My_DataType dataType) {
        Symbol symbol = identifySymbol.get_CurrentSym();
        RegKey regKey = symbol.getRegKey();
        boolean judge = (regKey == RegKey.VOIDTK || regKey == RegKey.INTTK);
        if (judge) {
            identifySymbol.getASymbol();
            dataType.dataType = (regKey == RegKey.INTTK) ? DataType.INT_DATA : DataType.VOID_DATA;

            identifySymbol.addStr(name_funcType);
        }

        return new FuncType(regKey);
    }

    public FuncType(RegKey regKey){
        this.regKey = regKey;
    }
}
