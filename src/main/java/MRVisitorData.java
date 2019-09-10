import soot.SootMethod;
import soot.Value;
import soot.jimple.AssignStmt;
import java.util.ArrayList;

class MRVisitorData {
    private SootMethod method;
    private ArrayList<AssignStmt> assignments;
    private LogWriter logWriter;

    MRVisitorData(SootMethod method, ArrayList<AssignStmt> assignments) {
        this.method = method;
        this.assignments = assignments;
        this.logWriter = new LogWriter(this.getClass().getSimpleName(), AnalysisConstants.DEBUG);
    }

    SootMethod get_method() {
        return method;
    }

    ArrayList<AssignStmt> get_assignments() {
        return assignments;
    }

    void print_data() {
        logWriter.write_out("Method: " + method.getName());
        for(AssignStmt stmt : assignments) {
            Value rop = stmt.getRightOp();
            logWriter.write_out("\t" + rop.getClass());
        }
    }
}
