package apps.lab10;

public class Main {
    public static void main(String[] args) {
        String filePath = "Decorator-OCR-main/lab10/src/main/java/apps/lab10/testocr.png"; 

        System.out.println("--- First Run (Should hit API) ---");
        Document smartDoc = new SmartDocument(filePath);
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
