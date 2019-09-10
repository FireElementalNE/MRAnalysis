import soot.PackManager;
import soot.SourceLocator;
import soot.Transform;
import soot.Transformer;
import soot.options.Options;

public class Main {
    public static void main(String[] args) {
        LogWriter logWriter = new LogWriter(Main.class.getSimpleName(), AnalysisConstants.DEBUG);
        long startTime = System.currentTimeMillis();

        // Code hooks the RTAAnalysis then launches Soot, which traverses
        // all classes and creates and stores the appropriate constraints.

        Options.v().set_output_format(Options.output_format_jimple);
        Options.v().set_allow_phantom_refs(true);
        Options.v().set_ignore_resolution_errors(true);
        Options.v().set_whole_program(true);
        Options.v().set_verbose(true);

        Options.v().set_exclude(AnalysisConstants.EXCLUDES);

        MRTransformer transformer = new MRTransformer();

        PackManager.v().getPack("jtp").add(new Transform("jtp.transformer", transformer));
        soot.Main.main(args);

        transformer.print_possible_transforms();

        String outputDir = SourceLocator.v().getOutputDir();

        long endTime   = System.currentTimeMillis();
        System.out.println("INFO: Total running time: " + ((float)(endTime - startTime) / 1000) + " sec");

    }
}

