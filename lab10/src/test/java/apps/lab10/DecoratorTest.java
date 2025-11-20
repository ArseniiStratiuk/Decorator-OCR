package apps.lab10;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DecoratorTest {

    // Mock implementation of Document for testing
    static class MockDocument implements Document {
        @Override
        public String parse() {
            return "Mock Parsed Text";
        }
    }

    @Test
    void testTimedDocument() {
        Document mockDoc = new MockDocument();
        Document timedDoc = new TimedDocument(mockDoc);
        
        String result = timedDoc.parse();
        
        assertEquals("Mock Parsed Text", result);
    }

    @Test
    void testCachedDocument() {
        // This test verifies that CachedDocument delegates to the wrapped document
        // when not in cache (first run logic).
        // Note: We are not testing the actual DB persistence here to keep the test 
        // independent of the file system/DB state in this simple example, 
        // but in a real scenario we would use an in-memory DB or mock the SQLiteInjector.
        
        Document mockDoc = new MockDocument();
        // Use a unique path to avoid collision with real data
        String uniquePath = "test/path/" + System.currentTimeMillis(); 
        
        Document cachedDoc = new CachedDocument(mockDoc, uniquePath);
        
        String result = cachedDoc.parse();
        assertEquals("Mock Parsed Text", result);
    }
}
