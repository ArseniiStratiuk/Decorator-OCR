package apps.lab10;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TimedDocument implements Document {
    private Document document;

    @Override
    public String parse() {
        long start = System.nanoTime();
        String result = document.parse();
        long end = System.nanoTime();
        long duration = (end - start) / 1_000_000; // convert to milliseconds
        System.out.println("Parsing took: " + duration + "ms");
        return result;
    }
}
