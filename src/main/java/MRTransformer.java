import soot.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class MRTransformer extends BodyTransformer {
    private ArrayList<MRVisitorData> visitor_data;
    private LogWriter logWriter = new LogWriter(this.getClass().getSimpleName(), AnalysisConstants.DEBUG);
    MRTransformer() {
        this.visitor_data = new ArrayList<>();
    }

    ArrayList<MRVisitorData> get_visitor_data() {
        return visitor_data;
    }

    @Override
    protected void internalTransform(Body body, String s, Map<String, String> map) {
        final PatchingChain<Unit> units = body.getUnits();
        SootMethod m = body.getMethod();
        // logWriter.write_out(m.getName());
        if(m.getName().equals("map") || m.getName().equals("reduce")) {
            logWriter.write_out(m.getName());
            MRVisitor visitor = new MRVisitor();
            for (Iterator<Unit> iter = units.snapshotIterator(); iter.hasNext(); ) {
                final Unit u = iter.next();
                u.apply(visitor);
            }
            this.visitor_data.add(new MRVisitorData(m, visitor));
        }
    }
}