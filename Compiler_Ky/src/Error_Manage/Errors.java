package Error_Manage;

import Non_Terminal_Symbols_SysY.*;
import Parcel_My_Type.My_Int;
import Table.SymTable;
import Table.TableEntry;
import Word_Analyze.Symbol;

import java.util.ArrayList;

public class Errors {
    public static ArrayList<String> errorPrintList = new ArrayList<>();
    public Errors() {
    }

    public static boolean checkFormatStr(Symbol sym, My_Int numOfExpExpected) {
        char[] str = sym.get_IdentName().toCharArray();
        boolean ret = true;
        numOfExpExpected.my_Int = 0;
        for (int i = 0; i < str.length && ret; i++) {
            if (str[i] == '%') {
                i++;
                if(i < str.length) {
                    ret = str[i] == 'd';
                    if(ret) {
                        numOfExpExpected.my_Int++;
                    }
                }else {
                    ret = false;
                    break;
                }
            }else if(str[i] == '\\'){
                i++;
                ret = str[i] == 'n';
            }else if(str[i] == '\"' || str[i] == '!' || str[i] == ' ' || str[i] >= '(' && str[i] <= '~'){
                continue;
            }else{
                ret = false;
            }
        }
        if(!ret) {
            errorPrintList.add(sym.getRow_Idx() + " a");
        }
        return ret;
    }

    public static void checkIfDupDef(boolean isFunc, Symbol sym){
        CompUnit.isNameDuplicateDef = SymTable.currentTableContainName(isFunc, sym.get_IdentName());
        if (CompUnit.isNameDuplicateDef) {
            Errors.add_ErrorOutPut(sym.getRow_Idx() + " b");
        }
    }

    public static void checkNameUndefined(boolean isFunc, Symbol sym){
        boolean isDefined = SymTable.NameContainedInAllTable(isFunc, sym.get_IdentName());
        if(!isDefined) {
            Errors.add_ErrorOutPut(sym.getRow_Idx() + " c");
        }
    }

    public static void checkParamNumMatched(Symbol sym, Integer numOfParamsActually){
        int numOfParamsExpected = SymTable.get_ExpectedParamsNum(sym.get_IdentName());
        if(numOfParamsExpected != numOfParamsActually) {
            Errors.add_ErrorOutPut(sym.getRow_Idx() + " d");
        }
    }

    public static void checkRParamsMatched(TableEntry funcTE, TableEntry actuallyTE, Symbol sym, int paramSeq){
        boolean isMatched;
        if(paramSeq > funcTE.get_ParamSize()){
            return;
        }
        TableEntry expectedTE = funcTE.get_ParamsList().get(paramSeq - 1);
        isMatched = expectedTE.get_DType() == actuallyTE.get_DType();
        isMatched &= expectedTE.get_Dims() == actuallyTE.get_Dims();
        if(!isMatched) {
            Errors.add_ErrorOutPut(sym.getRow_Idx() + " e");
        }
    }

    public static void checkAssignValueToConst(Symbol sym){
        TableEntry te = SymTable.get_SymByNameInAllTable(false, sym.get_IdentName());
        if(te != null && te.isConst()) {
            Errors.add_ErrorOutPut(sym.getRow_Idx() + " h");
        }
    }

    public static void add_ErrorOutPut(String str){
        errorPrintList.add(str);
    }
}
