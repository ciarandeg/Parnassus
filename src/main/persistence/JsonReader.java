package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Composition;
import model.Note;
import org.json.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

// CITATION: this class is adapted from JsonReader.java in CPSC 210's JSON Serialization Demo:
//           https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a reader that reads composition from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads composition from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Composition read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseComposition(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses composition from JSON object and returns it
    private Composition parseComposition(JSONObject jsonObject) {
        JSONArray voiceArray = jsonObject.getJSONArray("voices");
        JSONArray v0 = voiceArray.getJSONArray(0);
        JSONArray v1 = voiceArray.getJSONArray(1);
        assertEquals(v0.length(), v1.length());
        Composition cmp = new Composition(v0.length());

        for (int i = 0; i < v0.length(); i++) {
            Note n = new Note(v0.getInt(i));
            cmp.addNote(0, n);
        }
        for (int i = 0; i < v1.length(); i++) {
            Note n = new Note(v1.getInt(i));
            cmp.addNote(1, n);
        }

        return cmp;
    }

    public String getSource() {
        return this.source;
    }
}
