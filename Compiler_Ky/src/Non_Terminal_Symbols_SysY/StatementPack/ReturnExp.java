package Non_Terminal_Symbols_SysY.StatementPack;

import Non_Terminal_Symbols_SysY.*;
import Tables.*;

public class ReturnExp extends Stmt {
    public Exp exp;

    @Override
    public void genCode() {
        if (exp == null) {
            Code.addCode(CodeType.RET, BlockStmt.block_Layers);
        } else {
            exp.genCode(null);
            Code.addCode(CodeType.VRE, BlockStmt.block_Layers);
        }
    }

    public ReturnExp(Exp exp) {
        this.exp = exp;
    }
}
