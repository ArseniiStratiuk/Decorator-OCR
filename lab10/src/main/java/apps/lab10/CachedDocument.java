package apps.lab10;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CachedDocument implements Document {
    private Document document;
    private String path; // We need the path to use as a cache key

    @Override
    public String parse() {
        String cached = SQLiteInjector.get(path);
        if (cached != null && !cached.startsWith("Error:")) {
            System.out.println("Found in cache!");
            return cached;
        }
        
        System.out.println("Not in cache. Parsing...");
        String parsed = document.parse();
        if (!parsed.startsWith("Error:")) {
            SQLiteInjector.put(path, parsed);
        }
        return parsed;
    }
}
