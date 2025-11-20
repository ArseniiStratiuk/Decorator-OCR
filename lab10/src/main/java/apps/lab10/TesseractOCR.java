package apps.lab10;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import java.io.File;

public class TesseractOCR {
    public static String extractText(String filePath) {
        Tesseract tesseract = new Tesseract();
        
        // Only use environment variable
        String tessDataPath = getTessDataPathFromEnv();
        if (tessDataPath != null) {
            System.out.println("Using tessdata path: " + tessDataPath);
            tesseract.setDatapath(tessDataPath);
        } else {
            System.err.println("ERROR: TESSDATA_PREFIX environment variable not set!");
            System.err.println("Please set TESSDATA_PREFIX to the Tesseract installation directory (e.g., D:\\tesseract-ocr)");
            return "Error: TESSDATA_PREFIX environment variable not set.";
        }
        
        tesseract.setLanguage("eng"); // Set the language to English

        try {
            return tesseract.doOCR(new File(filePath));
        } catch (TesseractException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        } catch (Error e) {
            System.err.println("Critical Tesseract Error: " + e.getMessage());
            return "Error: Tesseract failed to initialize. Check if 'eng.traineddata' exists in the tessdata path.";
        }
    }

    private static String getTessDataPathFromEnv() {
        String envPath = System.getenv("TESSDATA_PREFIX");
        if (envPath == null) {
            return null;
        }
        
        // Check if envPath already points to tessdata folder
        if (envPath.endsWith("tessdata")) {
            // If it points to tessdata, use the parent directory
            File tessdataDir = new File(envPath);
            if (tessdataDir.exists() && tessdataDir.isDirectory()) {
                return tessdataDir.getParent();
            }
        } else {
            // If it points to parent directory, verify tessdata exists
            File tessdataDir = new File(envPath, "tessdata");
            if (tessdataDir.exists() && tessdataDir.isDirectory()) {
                return envPath;
            }
        }
        
        return null;
    }
}
