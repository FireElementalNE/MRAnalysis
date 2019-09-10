import java.util.ArrayList;
import java.util.Arrays;

public class AnalysisConstants {
    public static final boolean DEBUG = true;
    private static final String[] EXCLUDES_INTERNAL = new String[] { "jdk.*" };
    static final ArrayList<String> EXCLUDES = new ArrayList<>(Arrays.asList(EXCLUDES_INTERNAL));
}
