import soot.*;
import soot.jimple.AssignStmt;
import soot.jimple.internal.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class MRTransformer extends BodyTransformer {
    private LogWriter logWriter;
    private ArrayList<MRVisitorData> visitor_data;
    public MRTransformer() {
        this.logWriter = new LogWriter(this.getClass().getSimpleName(), AnalysisConstants.DEBUG);
        this.visitor_data = new ArrayList<>();
    }

    @Override
    protected void internalTransform(Body body, String s, Map<String, String> map) {
        final PatchingChain<Unit> units = body.getUnits();
        SootMethod m = body.getMethod();
        logWriter.write_out(m.getName());
        MRVisitor visitor = new MRVisitor();
        for (Iterator<Unit> iter = units.snapshotIterator(); iter.hasNext(); ) {
            final Unit u = iter.next();
            u.apply(visitor);
        }
        this.visitor_data.add(new MRVisitorData(m, visitor.get_assignments()));
    }

    private void print_ops(Value op1, Value op2) {
        logWriter.write_out("\tOp1: " + op1.getClass().getSimpleName() + " " + op1.getType().toString());
        logWriter.write_out("\tOp2: " + op2.getClass().getSimpleName() + " " + op2.getType().toString());
    }

    void print_possible_transforms() {
        for(MRVisitorData vdata : visitor_data) {
            ArrayList<AssignStmt> tmp = vdata.get_assignments();
            for(AssignStmt stmt : tmp) {
                Value rop = stmt.getRightOp();
                Value lop = stmt.getLeftOp();
                if(rop instanceof JAddExpr) {
                    logWriter.write_out("JAddExpr: " + rop.toString());
                    Value op1 = ((JAddExpr) rop).getOp1();
                    Value op2 = ((JAddExpr) rop).getOp2();
                    print_ops(op1, op2);
                } else if(rop instanceof JSubExpr) {
                    logWriter.write_out("JSubExpr: " + rop.toString());
                    Value op1 = ((JSubExpr) rop).getOp1();
                    Value op2 = ((JSubExpr) rop).getOp2();
                    print_ops(op1, op2);
                } else if(rop instanceof JMulExpr) {
                    logWriter.write_out("JMulExpr: " + rop.toString());
                    Value op1 = ((JMulExpr) rop).getOp1();
                    Value op2 = ((JMulExpr) rop).getOp2();
                    print_ops(op1, op2);
                } else if(rop instanceof JDivExpr) {
                    logWriter.write_out("JDivExpr: " + rop.toString());
                    Value op1 = ((JDivExpr) rop).getOp1();
                    Value op2 = ((JDivExpr) rop).getOp2();
                    print_ops(op1, op2);
                } else if(rop instanceof JNeExpr) {
                    logWriter.write_out("JNeExpr: " + rop.toString());
                    Value op1 = ((JNeExpr) rop).getOp1();
                    Value op2 = ((JNeExpr) rop).getOp2();
                    print_ops(op1, op2);
                } else if(rop instanceof JEqExpr) {
                    logWriter.write_out("JEqExpr: " + rop.toString());
                    Value op1 = ((JEqExpr) rop).getOp1();
                    Value op2 = ((JEqExpr) rop).getOp2();
                    print_ops(op1, op2);
                } else if(rop instanceof JGeExpr) {
                    logWriter.write_out("JGeExpr: " + rop.toString());
                    Value op1 = ((JGeExpr) rop).getOp1();
                    Value op2 = ((JGeExpr) rop).getOp2();
                    print_ops(op1, op2);
                } else if(rop instanceof JGtExpr) {
                    logWriter.write_out("JGtExpr: " + rop.toString());
                    Value op1 = ((JGtExpr) rop).getOp1();
                    Value op2 = ((JGtExpr) rop).getOp2();
                    print_ops(op1, op2);
                } else if(rop instanceof JLeExpr) {
                    logWriter.write_out("JLeExpr: " + rop.toString());
                    Value op1 = ((JLeExpr) rop).getOp1();
                    Value op2 = ((JLeExpr) rop).getOp2();
                    print_ops(op1, op2);
                } else if(rop instanceof JLtExpr) {
                    logWriter.write_out("JLtExpr: " + rop.toString());
                    Value op1 = ((JLtExpr) rop).getOp1();
                    Value op2 = ((JLtExpr) rop).getOp2();
                    print_ops(op1, op2);
                } else if(rop instanceof JStaticInvokeExpr) {
                    logWriter.write_out("JStaticInvokeExpr: " + rop.toString());
                } else if(rop instanceof JVirtualInvokeExpr) {
                    logWriter.write_out("JVirtualInvokeExpr: " + rop.toString());
                } else {
                    logWriter.write_out("Unknown: " + rop.toString());
                }
            }
        }
    }
}