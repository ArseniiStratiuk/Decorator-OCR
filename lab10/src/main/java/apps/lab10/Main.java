package apps.lab10;

public class Main {
    public static void main(String[] args) {
        // Replace with a real image path on your computer
        String filePath = "test_image.png"; 

        System.out.println("--- First Run (Should hit API) ---");
        Document smartDoc = new SmartDocument(filePath);
        // Wrap with caching (needs path for DB key) and timing
        Document cachedDoc = new CachedDocument(smartDoc, filePath);
        Document timedDoc = new TimedDocument(cachedDoc);
        
        System.out.println(timedDoc.parse());

        System.out.println("\n--- Second Run (Should hit Cache) ---");
        // Create a new chain to prove persistence
        Document cachedDoc2 = new CachedDocument(smartDoc, filePath);
        Document timedDoc2 = new TimedDocument(cachedDoc2);
        
        System.out.println(timedDoc2.parse());
    }
}
