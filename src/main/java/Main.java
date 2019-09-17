import soot.G;
import soot.PackManager;
import soot.SourceLocator;
import soot.Transform;
import soot.jimple.AssignStmt;
import soot.jimple.IfStmt;
import soot.options.Options;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    private static LogWriter logWriter = new LogWriter(Main.class.getSimpleName(), AnalysisConstants.DEBUG);

    private static void write_transforms(String benchmark, String problem, MRTransformer transformer) {
        String filename = String.format("%s_%s.log", benchmark, problem);
        filename = AnalysisConstants.LOG_FOLDER + File.separator + filename;
        Path path = Paths.get(filename);
        try {
            BufferedWriter writer = Files.newBufferedWriter(path);
            ArrayList<MRVisitorData> vdata_lst = transformer.get_visitor_data();
            for (MRVisitorData vdata : vdata_lst) {
                writer.write("Method: " + vdata.get_method().getName() + "\n");
                ArrayList<AssignStmt> assignments = vdata.get_assignments();
                ArrayList<IfStmt> ifstmts = vdata.get_ifstmts();
                writer.write(AnalysisConstants.ASSIGNMENTS_HEADER);
                for (AssignStmt stmt : assignments) {
                    writer.write("\t\t"+ AnalysisUtils.parse_assignment(stmt) + "\n");
                }
                writer.write(AnalysisConstants.IFSTMTS_HEADER);
                for (IfStmt stmt : ifstmts) {
                    writer.write("\t\t"+ AnalysisUtils.parse_ifstmt(stmt) + "\n");
                }
            }
            writer.close();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        String log_path = System.getProperty("user.dir") + File.separator + AnalysisConstants.LOG_FOLDER;
        File log_folder = new File(log_path);
        boolean ret = true;
        System.out.println();
        if (Files.exists(Paths.get(log_path))) {
            log_folder.delete();
        }
        log_folder.mkdir();
        // Options.v().set_exclude(AnalysisConstants.EXCLUDES);
        String base_cp = "%s" + File.pathSeparator + AnalysisConstants.RT_DIR;
        ArrayList<Benchmark> benchmarks = AnalysisUtils.get_benchmark_table();
        ArrayList<String> nop_problems = new ArrayList<>();
        for (Benchmark b : benchmarks) {
            logWriter.write_out("Starting benchmark suite " + b.get_name());
            String[] program_lst = b.get_programs();
            String[] new_args = new String[args.length + 2];
            System.arraycopy(args, 0, new_args, 0, args.length);
            new_args[args.length] = String.format(base_cp, b.get_dir());
            for (String program : program_lst) {
                logWriter.write_out("Starting " + program + " in suite " + b.get_name());
                new_args[args.length + 1] = program;
                // soot settings.
                Options.v().set_output_format(Options.output_format_jimple);
                Options.v().set_allow_phantom_refs(true);
                Options.v().set_ignore_resolution_errors(true);
                Options.v().set_whole_program(true);
                Options.v().set_verbose(true);
                MRTransformer transformer = new MRTransformer();
                PackManager.v().getPack("jtp").add(new Transform("jtp.transformer", transformer));
                soot.Main.main(new_args);
                if (transformer.get_visitor_data().size() > 0) {
                    write_transforms(b.get_name(), program, transformer);
                } else {
                    String problem_name = String.format("%s_%s", program, b.get_name());
                    logWriter.write_out(problem_name + " is a NOP!");
                    nop_problems.add(problem_name);
                }
                logWriter.write_out("Finished " + program + " in suite " + b.get_name());
                // static soot reset
                G.v();
                G.reset();
            }
            logWriter.write_out("Finished benchmark suite " + b.get_name());
        }
        String outputDir = SourceLocator.v().getOutputDir();
        long endTime = System.currentTimeMillis();
        logWriter.write_out("INFO: Total running time: " + ((float) (endTime - startTime) / 1000) + " sec");
        logWriter.write_out("The Following programs are NOPs:");
        for (String s : nop_problems) {
            logWriter.write_out(s);
        }
    }
}

