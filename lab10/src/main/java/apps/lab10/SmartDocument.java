package apps.lab10;

import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class SmartDocument implements Document {
    public String filePath;

    @Override
    @SneakyThrows
    public String parse() {
        try (ImageAnnotatorClient vision = ImageAnnotatorClient.create()) {
            ByteString imgBytes = ByteString.readFrom(new FileInputStream(filePath));

            Image img = Image.newBuilder().setContent(imgBytes).build();
            Feature feat = Feature.newBuilder().setType(Feature.Type.TEXT_DETECTION).build();
            AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
                    .addFeatures(feat)
                    .setImage(img)
                    .build();
            List<AnnotateImageRequest> requests = new ArrayList<>();
            requests.add(request);

            BatchAnnotateImagesResponse response = vision.batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.getResponsesList();

            StringBuilder result = new StringBuilder();
            for (AnnotateImageResponse res : responses) {
                if (res.hasError()) {
                    System.out.format("Error: %s%n", res.getError().getMessage());
                    return "";
                }
                result.append(res.getFullTextAnnotation().getText());
            }
            return result.toString();
        }
    }
}