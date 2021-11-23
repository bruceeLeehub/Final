import Tables.*;

import java.util.TreeMap;
import java.util.Scanner;
import java.util.ArrayList;


public class Interpreter_SysY {
    private static int basePtr = 0;
    private static int level = -1;
    private static int pc = 0;
    private static final Scanner scanner = new Scanner(System.in);
    private static final TreeMap<Integer/*lev*/, Integer/*base*/> display = new TreeMap<>();
    public static TreeMap<Integer, ArrayList<Integer>> disStack = new TreeMap<>();
    public static ArrayList<String> pcodeOutput = new ArrayList<>();

    public static void runInterpreter() {
//        System.out.println("gonna run code");
//        System.out.println("==================");
        while (pc < Code.codeSize() && pc >= 0) {
            runCode(Code.get_CodeType(pc), Code.get_X(pc), Code.get_Y(pc));
//            System.out.println(pc + "has runned");
        }
    }

    public static void runCode(CodeType codeType, int x, int y) {
        if (codeType == CodeType.INI) {
            runningINI();
        }else if (codeType == CodeType.MAI) {
            runningMAIN();
        }else if (codeType == CodeType.BKI) {
            runningBKI();
        }else if (codeType == CodeType.DBK) {
            runningDBK();
        }else if (codeType == CodeType.LDA) {
            runningLDA(x,y);
        }else if (codeType == CodeType.LOD) {
            runningLOD(x,y);
        }else if (codeType == CodeType.BRK) {
            runningBRK(x,y);
        }else if (codeType == CodeType.JMP) {
            runningJMP(y);
        }else if (codeType == CodeType.JMT) {
            runningJMT(y);
        }else if (codeType == CodeType.JMF) {
            runningJMF(y);
        }else if (codeType == CodeType.LDC) {
            runningLDC(y);
        }else if (codeType == CodeType.JMC) {
            runningJMC(y);
        }else if (codeType == CodeType.MKS) {
            runningMKS(y);
        }else if (codeType == CodeType.ADD) {
            runningADD();
        }else if (codeType == CodeType.SUB) {
            runningSUB();
        }else if (codeType == CodeType.MUL) {
            runningMUL();
        }else if (codeType == CodeType.DIV) {
            runningDIV();
        }else if (codeType == CodeType.MOD) {
            runningMOD();
        }else if (codeType == CodeType.MUS) {
            runningMUS();
        }else if (codeType == CodeType.NOT) {
            runningNOT();
        }else if (codeType == CodeType.ORR) {
            runningORR();
        }else if (codeType == CodeType.AND) {
            runningAND();
        }else if (codeType == CodeType.EQL) {
            runningEQL();
        }else if (codeType == CodeType.NEQ) {
            runningNEQ();
        }else if (codeType == CodeType.LSS) {
            runningLSS();
        }else if (codeType == CodeType.LEQ) {
            runningLEQ();
        }else if (codeType == CodeType.GRT) {
            runningGRT();
        }else if (codeType == CodeType.GEQ) {
            runningGEQ();
        }else if (codeType == CodeType.SWP) {
            runningSWP();
        }else if (codeType == CodeType.LAV) {
            runningLAV();
        }else if (codeType == CodeType.LCA) {
            runningLCA();
        }else if (codeType == CodeType.STO) {
            runningSTO();
        }else if (codeType == CodeType.VRE) {
            runningVRE(y);
        }else if (codeType == CodeType.CAL) {
            runningCAL();
        }else if (codeType == CodeType.EAT) {
            runningEAT(y);
        }else if (codeType == CodeType.RET) {
            runningRET(y);
        }else if (codeType == CodeType.WRF) {
            runningWRF(y);
        }else if (codeType == CodeType.RDI) {
            runningGETINT();
        }
    }

    public static void addValToDisStack(int lev, int value) {
        disStack.computeIfAbsent(lev, k -> new ArrayList<>());
        disStack.get(lev).add(value);
    }

    public static boolean swapped(int lev) {
        return disStack.get(lev) != null && disStack.get(lev).size() != 0;
    }

    public static int popValBeforeSwp(int lev) {
        int Ptr = disStack.get(lev).size() - 1;
        int val = disStack.get(lev).get(Ptr);
        disStack.get(lev).remove(Ptr);
        return val;
    }

    public static void runningINI() {
        pc += 1;
        int retValue = 0;
        MemoryDataStack.add_DataToTop(retValue);    // return value
        int RA = -1;
        MemoryDataStack.add_DataToTop(RA);    // RA
        int lev = 0;
        MemoryDataStack.add_DataToTop(lev);    // lev
        int DL = -1;
        MemoryDataStack.add_DataToTop(DL);    // DL
        int func_in_table = -1;
        MemoryDataStack.add_DataToTop(func_in_table);// func in tab
        level += 1;
        display.put(level, 0);
        basePtr = 0;
    }

    public static void runningMAIN() {
        pc += 1;
        int retValue = 0;
        MemoryDataStack.add_DataToTop(retValue);        // return value
        int RA = -1;
        MemoryDataStack.add_DataToTop(RA);       // RA
        int lev = 1;
        MemoryDataStack.add_DataToTop(lev);        // lev
        int DL = basePtr;
        MemoryDataStack.add_DataToTop(DL);  // DL
        int func_in_tab = -1;
        MemoryDataStack.add_DataToTop(func_in_tab);       // func in tab
        basePtr = MemoryDataStack.get_CurPtr() - 4;
        level++;
        display.put(level, basePtr);
    }

    public static void runningBKI() {
        pc += 1;
        int retValue = -1;
        MemoryDataStack.add_DataToTop(retValue);        // return value
        int RA = -1;
        MemoryDataStack.add_DataToTop(RA);       // RA
        int lev = level;
        MemoryDataStack.add_DataToTop(lev);        // lev
        int DL = basePtr;
        MemoryDataStack.add_DataToTop(DL);  // DL
        int func_in_tab = -1;
        MemoryDataStack.add_DataToTop(func_in_tab);       // func in tab
        basePtr = MemoryDataStack.get_CurPtr() - 4;
        level++;
        boolean isNull = (display.get(level) == null);
        if (!isNull) {   // it says that there is a value
            addValToDisStack(level, display.get(level));
        }
        display.put(level, basePtr);
    }

    public static void runningDBK() {
        pc += 1;
        boolean level_swapped = swapped(level);
        if (!level_swapped)
            display.remove(level);
        else
            display.put(level, popValBeforeSwp(level));

        level = MemoryDataStack.get_DataFromAdr(basePtr + 2);
        int preBase = basePtr;   // no need to leave a return value;
        basePtr = MemoryDataStack.get_DataFromAdr(basePtr + 3);
        MemoryDataStack.move_Ptr(preBase - 1);
    }


    public static void runningLDA(int x, int y) {
        pc += 1;
        int data = display.get(x) + y + 5;
        MemoryDataStack.add_DataToTop(data);
    }

    public static void runningLOD(int x, int y) {
        pc += 1;
        int addr = display.get(x) + y + 5;
        int value = MemoryDataStack.get_DataFromAdr(addr);
        MemoryDataStack.add_DataToTop(value);
    }

    public static void runningBRK(int x, int y) {
        int i = 0;
        while (i < x) {
            runningDBK();
            i += 1;
        }
//        for (int i = 0; i < x; i++) {
//            runDBK();
//        }
        pc = y;
    }

    public static void runningJMP(int dest) {
        pc = dest;
    }

    public static void runningLDC(int y) {
        pc += 1;
        MemoryDataStack.add_DataToTop(y);
    }

    public static void runningJMC(int dest) {
        pc += 1;
        if (MemoryDataStack.popOutdata() == 0)
            pc = dest;
    }

    public static void runningJMT(int dest){
        pc += 1;
        int value = MemoryDataStack.popOutdata();
        MemoryDataStack.add_DataToTop(value);
        if (value != 0)
            pc = dest;
    }

    public static void runningJMF(int dest){
        pc += 1;
        int value = MemoryDataStack.popOutdata();
        MemoryDataStack.add_DataToTop(value);
        if (value == 0)
            pc = dest;
    }

    public static void runningMKS(int y) {
        pc += 1;
        int retValue = 0;
        MemoryDataStack.add_DataToTop(retValue);    // r e t u r n v a l u e
        int RA = 0;
        MemoryDataStack.add_DataToTop(RA);    // R A
        int lev = level;
        MemoryDataStack.add_DataToTop(lev);    // l e v
        int DL = basePtr;
        MemoryDataStack.add_DataToTop(DL);    // D L

        MemoryDataStack.add_DataToTop(y);    // f u n c  i n  t a b
        basePtr = MemoryDataStack.get_CurPtr();
        basePtr -= 4;
    }


    public static void runningADD() {
        pc += 1;
        int result;
        int a2 = MemoryDataStack.popOutdata();
        int a1 = MemoryDataStack.popOutdata();
        result = a1 + a2;
        MemoryDataStack.add_DataToTop(result);
    }

    public static void runningSUB() {
        pc += 1;
        int result;
        int a2 = MemoryDataStack.popOutdata();
        int a1 = MemoryDataStack.popOutdata();
        result = a1 - a2;
        MemoryDataStack.add_DataToTop(result);
    }

    public static void runningMUL() {
        pc += 1;
        int result;
        int a2 = MemoryDataStack.popOutdata();
        int a1 = MemoryDataStack.popOutdata();
        result = a1 * a2;
        MemoryDataStack.add_DataToTop(result);
    }

    public static void runningDIV() {
        pc += 1;
        int result;
        int a2 = MemoryDataStack.popOutdata();
        int a1 = MemoryDataStack.popOutdata();
        result = a1 / a2;
        MemoryDataStack.add_DataToTop(result);
    }

    public static void runningMOD() {
        pc += 1;
        int result;
        int a2 = MemoryDataStack.popOutdata();
        int a1 = MemoryDataStack.popOutdata();
        result = a1 % a2;
        MemoryDataStack.add_DataToTop(result);
    }

    public static void runningMUS() {
        pc += 1;
        MemoryDataStack.add_DataToTop(-1 * MemoryDataStack.popOutdata());
    }

    public static void runningNOT() {
        pc += 1;
        MemoryDataStack.add_DataToTop((MemoryDataStack.popOutdata() == 0) ? 1 : 0);
    }

    public static void runningORR() {
        pc += 1;
        int a2 = MemoryDataStack.popOutdata();
        int a1 = MemoryDataStack.popOutdata();
        MemoryDataStack.add_DataToTop((a1 == 0 && a2 == 0) ? 0 : 1);
    }

    public static void runningAND() {
        pc += 1;
        int a2 = MemoryDataStack.popOutdata();
        int a1 = MemoryDataStack.popOutdata();
        MemoryDataStack.add_DataToTop((a1 == 0 || a2 == 0) ? 0 : 1);
    }

    public static void runningEQL() {
        pc += 1;
        int a2 = MemoryDataStack.popOutdata();
        int a1 = MemoryDataStack.popOutdata();
        MemoryDataStack.add_DataToTop((a1 == a2) ? 1 : 0);
    }

    public static void runningNEQ() {
        pc += 1;
        int a2 = MemoryDataStack.popOutdata();
        int a1 = MemoryDataStack.popOutdata();
        MemoryDataStack.add_DataToTop((a1 != a2) ? 1 : 0);
    }

    public static void runningLSS() {
        pc += 1;
        int a2 = MemoryDataStack.popOutdata(); // a2
        int a1 = MemoryDataStack.popOutdata(); // a1
        MemoryDataStack.add_DataToTop((a1 < a2) ? 1 : 0);
    }

    public static void runningLEQ() {
        pc += 1;
        int a2 = MemoryDataStack.popOutdata();
        int a1 = MemoryDataStack.popOutdata();
        MemoryDataStack.add_DataToTop((a1 <= a2) ? 1 : 0);
    }

    public static void runningGRT() {
        pc += 1;
        int a2 = MemoryDataStack.popOutdata();
        int a1 = MemoryDataStack.popOutdata();
        MemoryDataStack.add_DataToTop((a1 > a2) ? 1 : 0);
    }

    public static void runningGEQ() {
        pc += 1;
        int a2 = MemoryDataStack.popOutdata();
        int a1 = MemoryDataStack.popOutdata();
        MemoryDataStack.add_DataToTop((a1 >= a2) ? 1 : 0);
    }

    public static void runningSWP() {
        pc += 1;
        int a2 = MemoryDataStack.popOutdata();
        int a1 = MemoryDataStack.popOutdata();
        MemoryDataStack.add_DataToTop(a2);
        MemoryDataStack.add_DataToTop(a1);
    }

    public static void runningLAV() {
        pc += 1;
        int diff = MemoryDataStack.popOutdata();
        int adr = MemoryDataStack.popOutdata();
        //adr = MemoryDataStack.get_DataFromAdr(adr);
        int dest = adr + diff;
        int data = MemoryDataStack.get_DataFromAdr(dest);
        MemoryDataStack.add_DataToTop(data);
    }

    public static void runningLCA() {
        pc += 1;
        int offset = MemoryDataStack.popOutdata();
        int adr = MemoryDataStack.popOutdata();
        int value = ArrayTable.getArrTable().get(adr).getConst_Array().get(offset);
        MemoryDataStack.add_DataToTop(value);
    }

    public static void runningSTO() {
        pc += 1;
        int value = MemoryDataStack.popOutdata();
        int adr = MemoryDataStack.popOutdata();
        MemoryDataStack.saveDataToAdr(adr, value);
    }

    public static void runningVRE(int y) {
        int retVal = MemoryDataStack.popOutdata();
        for (int i = 0; i < y; i++) {
            runningDBK();
        }
        int adr = display.get(level);
        MemoryDataStack.saveDataToAdr(adr, retVal);

        // consider whether move return value or not
        pc = MemoryDataStack.get_DataFromAdr(basePtr + 1);
        boolean swapped = swapped(level);
        int preBase = MemoryDataStack.get_DataFromAdr(basePtr + 3);
        if (!swapped) {
            display.remove(level);
        } else
            display.put(level, popValBeforeSwp(level));

        level = MemoryDataStack.get_DataFromAdr(basePtr + 2);
        MemoryDataStack.move_Ptr(basePtr);
        // basePtr = display.get(lev);
        basePtr = preBase;
    }

    public static void runningCAL() {
        int adr = MemoryDataStack.get_DataFromAdr(basePtr + 4);
        ProgTableEntry progTableEntry = ProgTable.getProg_Entry(adr);
        String proName = progTableEntry.getProgName();
        TableEntry tableEntry = Table.getFuncTableEntry(proName);
        level = tableEntry.get_Level() + 1;
        boolean have_value = (display.get(level) != null);
        if (have_value) {   // it says that there is a value
            addValToDisStack(level, display.get(level));
        }
        display.put(level, basePtr);
        MemoryDataStack.saveDataToAdr(basePtr + 1, pc + 1);
        pc = tableEntry.get_Addr();
    }

    public static void runningEAT(int y) {
        pc += 1;
        for (int i = 0;i < y;i++) {
            MemoryDataStack.popOutdata();
        }
    }

    public static void runningRET(int y) {   // return without return value
        for (int i = 0;i < y;i++) {
            runningDBK();
        }
        // consider whether move return value or not
        pc = MemoryDataStack.get_DataFromAdr(basePtr + 1);
        boolean swapped = swapped(level);
        int preBase = MemoryDataStack.get_DataFromAdr(basePtr + 3);
        if (!swapped) {
            display.remove(level);
        } else
            display.put(level, popValBeforeSwp(level));

        level = MemoryDataStack.get_DataFromAdr(basePtr + 2);
        MemoryDataStack.move_Ptr(basePtr - 1);
        // basePtr = display.get(lev);
        basePtr = preBase;
    }

//    public static void VRE() {
//        // consider whether move return value or not
//        pc = MemoryDataStack.get_DataFromAdr(basePtr + 1);
//        int preBase = MemoryDataStack.get_DataFromAdr(basePtr + 3);
//        if (swapped(level)) {
//            int val = popValBeforeSwp(level);
//            display.put(level, val);
//        } else
//            display.remove(level);
//
//        level = MemoryDataStack.get_DataFromAdr(basePtr + 2);
//        MemoryDataStack.move_Ptr(basePtr);
//        // basePtr = display.get(lev);
//        basePtr = preBase;
//    }

    public static void runningWRF(int y) {
        pc += 1;
        ArrayList<Integer> expValues = new ArrayList<>();
        String[] stringContents = ConstStringTable.getString(y).split("%d");
        int length = stringContents.length;
        for (int i = 0; i < length - 1; i++) {
            expValues.add(MemoryDataStack.popOutdata());
        }
        String outString = stringContents[0];
        int size = expValues.size();
        for (int i = size - 1; i >= 0; i--) {
            String value = String.valueOf(expValues.get(i));
            outString = outString.concat(value);
            outString = outString.concat(stringContents[size - i]);
        }
        outString = outString.replaceAll("\\\\n", "\n");
        outString = outString.replaceAll("\"", "");
        pcodeOutput.add(outString);
    }

    public static void runningGETINT() {
        int value = scanner.nextInt();
        pc += 1;
        int adr = MemoryDataStack.popOutdata();
        MemoryDataStack.saveDataToAdr(adr, value);
    }
}
