package framework.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.InputStream;

public final class TestDataReader {

    private static final ObjectMapper mapper = new ObjectMapper();
    private final JsonNode rootNode;

    private final Logger logger = LoggerUtils.getLogger(TestDataReader.class);

    public TestDataReader(String fileName) {
        try (InputStream is = getClass().getClassLoader()
                .getResourceAsStream("testdata/" + fileName)) {

            if (is == null) {
                throw new RuntimeException("Test data file not found: " + fileName);
            }
            rootNode = mapper.readTree(is);
            logger.info("Loaded test data file: {}", fileName);

        } catch (IOException e) {
            logger.error("Failed to read test data file: {}", fileName, e);
            throw new RuntimeException("Failed to read test data file: " + fileName, e);
        }
    }

    public String getString(String path) {
        String[] parts = path.split("\\.");
        JsonNode node = rootNode;
        for (String part : parts) {
            node = node.path(part);
        }
        if (node.isMissingNode()) {
            String msg = "Test data path not found: " + path;
            logger.error(msg);
            throw new RuntimeException(msg);
        }
        String value = node.asText();
        logger.info("Accessed test data [{}] = {}", path, value);
        return value;
    }
}
