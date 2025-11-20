package apps.lab10;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import java.io.File;

public class TesseractOCR {
    public static String extractText(String filePath) {
        Tesseract tesseract = new Tesseract();
        // The path to the tessdata directory.
        // You need to download the tessdata folder and provide the path to it.
        // For example, if you placed it in a 'tessdata' folder at the root of your project:
        tesseract.setDatapath("tessdata"); 
        tesseract.setLanguage("eng"); // Set the language to English

        try {
            return tesseract.doOCR(new File(filePath));
        } catch (TesseractException e) {
            e.printStackTrace();
            return null;
        }
    }
}
