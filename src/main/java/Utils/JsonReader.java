package Utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class JsonReader {
    public List<HashMap<String, String>> getJsonToMap() throws IOException {
        String jsonData = FileUtils.readFileToString(new File(System.getProperty("user.dir")
                +"/src/main/resources/PurchaseOrder.json"), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonData, new TypeReference<List<HashMap<String, String>>>() {
        });
    }
}
