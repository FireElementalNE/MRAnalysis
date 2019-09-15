import soot.jimple.AbstractStmtSwitch;
import soot.jimple.AssignStmt;
import soot.jimple.IfStmt;

import java.util.ArrayList;

class MRVisitor extends AbstractStmtSwitch {
    private LogWriter logWriter;
    private ArrayList<AssignStmt> assignments;
    private ArrayList<IfStmt> ifstmts;

    MRVisitor() {
        this.assignments = new ArrayList<>();
        this.ifstmts = new ArrayList<>();
        this.logWriter = new LogWriter(this.getClass().getSimpleName(), AnalysisConstants.DEBUG);
    }

    @Override
    public void caseAssignStmt(AssignStmt stmt) {
        assignments.add(stmt);
        super.caseAssignStmt(stmt);
    }
    @Override
    public void caseIfStmt(IfStmt stmt) {
        ifstmts.add(stmt);
        super.caseIfStmt(stmt);
    }

    ArrayList<IfStmt> get_ifstmts() {
        return ifstmts;
    }

    ArrayList<AssignStmt> get_assignments() {
        return this.assignments;
    }
}