/** Measures repeated Core API calls after loading versus loading the data for every call. */
public final class Benchmark {
    private static volatile int sink;
    public static void main(String[] args) throws Exception {
        CoreModule cached = CoreModule.fromBundledData();
        for (int i = 0; i < 100; i++) sink += cached.displayResultsForEachEvent("women 10m platform", true).length();
        long start = System.nanoTime();
        for (int i = 0; i < 10000; i++) sink += cached.displayResultsForEachEvent("women 10m platform", true).length();
        long cachedTime = System.nanoTime() - start;
        start = System.nanoTime();
        for (int i = 0; i < 100; i++) sink += CoreModule.fromBundledData()
                .displayResultsForEachEvent("women 10m platform", true).length();
        long reloadedTime = System.nanoTime() - start;
        System.out.printf("Cached: %.3f microseconds/query (%d queries)%n", cachedTime / 10000.0 / 1000, 10000);
        System.out.printf("Reloaded: %.3f microseconds/query (%d queries)%n", reloadedTime / 100.0 / 1000, 100);
        System.out.printf("Speedup: %.1fx%n", (reloadedTime / 100.0) / (cachedTime / 10000.0));
    }
}
