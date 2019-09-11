class Benchmark {

    private String name;
    private String dir;
    private String[] programs;

    Benchmark(String name, String[] programs) {
        this.programs = programs;
        this.name = name;
        this.dir = String.format(SecretConstants.PROGRAM_DIR, name);
    }

    String[] get_programs() {
        return programs;
    }

    String get_name() {
        return name;
    }

    String get_dir() {
        return dir;
    }
}
