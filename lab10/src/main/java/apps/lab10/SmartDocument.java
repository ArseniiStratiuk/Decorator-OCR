package apps.lab10;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class SmartDocument implements Document {
    public String filePath;

    @Override
    @SneakyThrows
    public String parse() {
        return TesseractOCR.extractText(filePath);
    }
}