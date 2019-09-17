import soot.SootMethod;
import soot.Value;
import soot.jimple.AssignStmt;
import soot.jimple.IfStmt;
import soot.jimple.internal.*;

import java.util.ArrayList;

class AnalysisUtils {

    static final LogWriter logWriter = new LogWriter(AnalysisUtils.class.getName(), AnalysisConstants.DEBUG);

    static ArrayList<Benchmark> get_benchmark_table() {
        ArrayList<Benchmark> lst = new ArrayList<>();
        lst.add(new Benchmark("puma", AnalysisConstants.PUMA_DIR, AnalysisConstants.PUMA_CLASSES));
        lst.add(new Benchmark("brown", AnalysisConstants.BROWN_DIR, AnalysisConstants.BROWN_CLASSES));
        lst.add(new Benchmark("pigmix2", AnalysisConstants.PIGMIX2_DIR, AnalysisConstants.PIGMIX2_CLASSES));
        lst.add(new Benchmark("pegasus", AnalysisConstants.PEGASUS_DIR, AnalysisConstants.PEGASUS_CLASSES));
        return lst;
    }

    private static String parse_value_type(Value value) {
        String msg;
        if (value instanceof JAddExpr) {
            msg = "JAddExpr: " + value.toString();
        } else if (value instanceof JSubExpr) {
            msg = "JSubExpr: " + value.toString();
        } else if (value instanceof JMulExpr) {
            msg = "JMulExpr: " + value.toString();
        } else if (value instanceof JDivExpr) {
            msg = "JDivExpr: " + value.toString();
        } else if (value instanceof JNeExpr) {
            msg = "JNeExpr: " + value.toString();
        } else if (value instanceof JEqExpr) {
            msg = "JEqExpr: " + value.toString();
        } else if (value instanceof JGeExpr) {
            msg = "JGeExpr: " + value.toString();
        } else if (value instanceof JGtExpr) {
            msg = "JGtExpr: " + value.toString();
        } else if (value instanceof JLeExpr) {
            msg = "JLeExpr: " + value.toString();
        } else if (value instanceof JLtExpr) {
            msg = "JLtExpr: " + value.toString();
        } else if (value instanceof JStaticInvokeExpr) {
            StringBuilder sb = new StringBuilder();
            sb.append("JStaticInvokeExpr: ").append(value.toString());
            JStaticInvokeExpr staticInvokeExpr = (JStaticInvokeExpr) value;
            SootMethod sm = staticInvokeExpr.getMethod();
            if (sm.getDeclaringClass().getName().equals(AnalysisConstants.MATH_LIB)) {
                sb.append(" -----> ").append("Found Math Call: ").append(sm.getName());
            }
            else if(sm.getDeclaringClass().getName().equals(AnalysisConstants.STRING_LIB)) {
                sb.append(" -----> ").append("Found String Call: ").append(sm.getName());
            }
            msg = sb.toString();
        } else if (value instanceof JVirtualInvokeExpr) {
            StringBuilder sb = new StringBuilder();
            msg = "JVirtualInvokeExpr: " + value.toString();
            sb.append("JVirtualInvokeExpr: ").append(value.toString());
            JVirtualInvokeExpr virtualInvokeExpr = (JVirtualInvokeExpr) value;
            SootMethod sm = virtualInvokeExpr.getMethod();
            if (sm.getDeclaringClass().getName().equals(AnalysisConstants.MATH_LIB)) {
                sb.append(" -----> ").append("Found Math Call: ").append(sm.getName());
            }
            else if(sm.getDeclaringClass().getName().equals(AnalysisConstants.STRING_LIB)) {
                sb.append(" -----> ").append("Found String Call: ").append(sm.getName());
            }
            msg = sb.toString();
        } else {
            msg = "Unknown: " + value.toString();
        }
        return msg;
    }

    static String parse_assignment(AssignStmt stmt) {
        Value rop = stmt.getRightOp();
        Value lop = stmt.getLeftOp(); // ???
        return parse_value_type(rop);
    }

    static String parse_ifstmt(IfStmt stmt) {
        Value cond = stmt.getCondition();
        return parse_value_type(cond);
    }

}
