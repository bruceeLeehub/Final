package Non_Terminal_Symbols_SysY.StatementPack;

import Non_Terminal_Symbols_SysY.*;

public class ExpStmt extends Stmt {
    public Exp exp;

    @Override
    public void genCode() {
        if (exp != null) {
            exp.genCode(null);
        }
    }

    public ExpStmt(Exp exp) {
        this.exp = exp;
    }
}
