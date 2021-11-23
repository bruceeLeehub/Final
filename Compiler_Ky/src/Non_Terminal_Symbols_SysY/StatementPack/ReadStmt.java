package Non_Terminal_Symbols_SysY.StatementPack;

import Non_Terminal_Symbols_SysY.*;
import Tables.*;

public class ReadStmt extends Stmt {
    private LVal lVal;
    private int read_Int;

    @Override
    public void genCode() {
        lVal.genCode(null, true);   // to be written
        Code.addCode(CodeType.RDI);
    }

    public ReadStmt(LVal lVal) {
        this.lVal = lVal;
        this.read_Int = 0;
    }
}
