import soot.SootMethod;
import soot.jimple.AssignStmt;
import soot.jimple.IfStmt;

import java.util.ArrayList;

class MRVisitorData {
    private SootMethod method;
    private ArrayList<AssignStmt> assignments;
    private ArrayList<IfStmt> ifstmts;
    private LogWriter logWriter;

    MRVisitorData(SootMethod method, MRVisitor visitor) {
        this.method = method;
        this.assignments = visitor.get_assignments();
        this.ifstmts = visitor.get_ifstmts();
        this.logWriter = new LogWriter(this.getClass().getSimpleName(), AnalysisConstants.DEBUG);
    }

    SootMethod get_method() {
        return method;
    }

    ArrayList<IfStmt> get_ifstmts() {
        return ifstmts;
    }

    ArrayList<AssignStmt> get_assignments() {
        return assignments;
    }
}
