import soot.*;
import soot.options.Options;

import java.util.ArrayList;

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

        // Options.v().set_exclude(AnalysisConstants.EXCLUDES);

        String base_cp = "%s;" + AnalysisConstants.RT_DIR;

        ArrayList<Benchmark> benchmarks = AnalysisUtils.get_benchmark_table();

        MRTransformer transformer = new MRTransformer();
        PackManager.v().getPack("jtp").add(new Transform("jtp.transformer", transformer));

        for(Benchmark b : benchmarks) {
            logWriter.write_out("Starting benchmark suite " + b.get_name());
            transformer.reset_transformer();
            String[] program_lst = b.get_programs();
            String[] new_args = new String[args.length + 2];
            for(int j = 0; j < args.length; j++) {
                new_args[j] = args[j];
            }
            new_args[args.length] = String.format(base_cp, b.get_dir());
            // TODO: loop through programs instead of statically calling the first one.
            new_args[args.length + 1] = program_lst[0];
            // TODO: fix timer nonsense, internal timers have already been started and need to be reset on each run.
            soot.Main.main(new_args);
            transformer.print_possible_transforms();
            // Timers.v().totalTimer.end(); // NOPE
        }
        String outputDir = SourceLocator.v().getOutputDir();
        long endTime   = System.currentTimeMillis();
        System.out.println("INFO: Total running time: " + ((float)(endTime - startTime) / 1000) + " sec");
    }
}

