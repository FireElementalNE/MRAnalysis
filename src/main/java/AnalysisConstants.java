import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class AnalysisConstants {
    static final boolean DEBUG = true;
    private static final String[] EXCLUDES_INTERNAL = new String[] { "jdk.*" };
    static final String[] PUMA_CLASSES = new String[] {
            "InvertedIndex",
            "Kmeans",
            "WordCount",
            "Classification",
            "HistogramMovies"
    };
    static final String[] BROWN_CLASSES = new String[] {
            // TODO: Cannot find the classes in the paper
            "edu.brown.cs.mapreduce.benchmarks.Grep"
    };
    static final String[] PIGMIX2_CLASSES = new String[] {
            "L3",
            "L6",
            "L8",
            "L12",
            "L15",
            "L16",
            "L17"
    };

    static final String RT_DIR = SecretConstants.RT_DIR;

    static final ArrayList<String> EXCLUDES = new ArrayList<>(Arrays.asList(EXCLUDES_INTERNAL));
    static final String MATH_LIB = "java.lang.Math";


}
