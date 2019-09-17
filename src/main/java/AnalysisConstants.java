import java.util.ArrayList;
import java.util.Arrays;

class AnalysisConstants {
    static final boolean DEBUG = true;
    private static final String[] EXCLUDES_INTERNAL = new String[] { "jdk.*" };
    static final String[] PUMA_CLASSES = new String[] {
            "AdjList",
            "Classification",
            "Cluster",
            "ClusterWritable",
            "FileCountPair",
            "HistogramMovies",
            "HistogramRatings",
            "InvertedIndex",
            "Kmeans",
            "RankedInvertedIndex",
            "SelfJoin",
            "SequenceCount",
            "TermVectorPerHost",
            "WordCount"
    };
    static final String[] BROWN_CLASSES = new String[] {
            "brown.edu.brown.cs.mapreduce.benchmarks.Benchmark1",
            "brown.edu.brown.cs.mapreduce.benchmarks.Benchmark2",
            "brown.edu.brown.cs.mapreduce.benchmarks.Benchmark3",
            "brown.edu.brown.cs.mapreduce.benchmarks.Benchmark3A",
            "brown.edu.brown.cs.mapreduce.benchmarks.Benchmark4",
            "brown.edu.brown.cs.mapreduce.benchmarks.CompositeKey",
            "brown.edu.brown.cs.mapreduce.benchmarks.DummyJob",
            "brown.edu.brown.cs.mapreduce.benchmarks.Grep",
            "brown.edu.brown.cs.mapreduce.benchmarks.OrBenchmark2",
            "brown.edu.brown.cs.mapreduce.benchmarks.OrBenchmark3",
            "brown.edu.brown.cs.mapreduce.benchmarks.Sort"
    };
    static final String[] PIGMIX2_CLASSES = new String[] {
            "L1",
            "L10",
            "L11",
            "L12",
            "L13",
            "L14",
            "L15",
            "L16",
            "L17",
            "L2",
            "L3",
            "L4",
            "L5",
            "L6",
            "L7",
            "L8",
            "L9",
            "Library"
    };
    static final String[] PEGASUS_CLASSES = new String[] {
            // "pegasus.BitShuffleCoder",
            // "pegasus.BlockElem",
            "pegasus.ConCmpt",
            "pegasus.ConCmptBlock",
            "pegasus.ConCmptIVGen",
            "pegasus.DegDist",
            "pegasus.EdgeType",
            "pegasus.FMBitmask",
            "pegasus.GIMV",
            "pegasus.Hadi",
            "pegasus.HadiBlock",
            "pegasus.HadiIVGen",
            "pegasus.HadiResultInfo",
            "pegasus.HadiUtils",
            "pegasus.JoinTablePegasus",
            "pegasus.L1norm",
            "pegasus.L1normBlock",
            "pegasus.MatvecUtils",
            "pegasus.MinMaxInfo",
            "pegasus.NormalizeVector",
            "pegasus.PagerankBlock",
            "pegasus.PagerankInitVector",
            "pegasus.PagerankNaive",
            "pegasus.PagerankPrep",
            "pegasus.PegasusUtils",
            "pegasus.RWRBlock",
            "pegasus.RWRNaive",
            "pegasus.ResultInfo",
            "pegasus.Saxpy",
            "pegasus.SaxpyBlock",
            "pegasus.SaxpyTextoutput",
            "pegasus.ScalarMult",
            "pegasus.VectorElem",
            "pegasus.matvec"
    };


    static final String RT_DIR = SecretConstants.RT_DIR;
    static final String PUMA_DIR = String.format(SecretConstants.PROGRAM_DIR, "puma");
    static final String BROWN_DIR = String.format(SecretConstants.PROGRAM_DIR, "brown");
    static final String PIGMIX2_DIR = String.format(SecretConstants.PROGRAM_DIR, "pigmix2");
    static final String PEGASUS_DIR = String.format(SecretConstants.PROGRAM_DIR, "pegasus");

    static final ArrayList<String> EXCLUDES = new ArrayList<>(Arrays.asList(EXCLUDES_INTERNAL));
    static final String MATH_LIB = "java.lang.Math";
    static final String STRING_LIB = "java.lang.String";

    static final String ASSIGNMENTS_HEADER = "\tAssignments\n\t-------------------------------------------------\n";
    static final String IFSTMTS_HEADER = "\tIf Statements\n\t-------------------------------------------------\n";

}

