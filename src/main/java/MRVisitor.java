import soot.jimple.AbstractStmtSwitch;
import soot.jimple.AssignStmt;

import java.util.ArrayList;

class MRVisitor extends AbstractStmtSwitch {
    private LogWriter logWriter;
    private ArrayList<AssignStmt> assignments;

    MRVisitor() {
        this.assignments = new ArrayList<>();
        this.logWriter = new LogWriter(this.getClass().getSimpleName(), AnalysisConstants.DEBUG);
    }

    @Override
    public void caseAssignStmt(AssignStmt stmt) {
        assignments.add(stmt);
        super.caseAssignStmt(stmt);
    }

    ArrayList<AssignStmt> get_assignments() {
        return this.assignments;
    }
}