import java.util.ArrayList;

class AnalysisUtils {
    static ArrayList<Benchmark> get_benchmark_table() {
        ArrayList<Benchmark> lst = new ArrayList<>();
        lst.add(new Benchmark("puma", AnalysisConstants.PUMA_CLASSES));
        lst.add(new Benchmark("brown", AnalysisConstants.BROWN_CLASSES));
        lst.add(new Benchmark("pigmix2", AnalysisConstants.PIGMIX2_CLASSES));
        return lst;
    }
}
