import java.util.ArrayList;
import java.util.Arrays;

class AnalysisConstants {
    static final boolean DEBUG = true;
    private static final String[] EXCLUDES_INTERNAL = new String[] { "jdk.*" };
    static final ArrayList<String> EXCLUDES = new ArrayList<>(Arrays.asList(EXCLUDES_INTERNAL));
    static final String MATH_LIB = "java.lang.Math";
}
