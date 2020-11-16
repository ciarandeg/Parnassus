package persistence;

import model.Composition;
import model.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// CITATION: this class is adapted from JsonWriterTest.java in CPSC 210's JSON Serialization Demo:
//           https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

class JsonWriterTest {
    private static final int CompositionSize = 4;
    private Composition cmp;

    @BeforeEach
    void init() {
        cmp = new Composition(CompositionSize);
    }

    @Test
    void testWriterInvalidFile() {
        try {
            Composition cmp = new Composition(4);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyComposition() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyComposition.json");
            writer.open();
            writer.write(cmp);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyComposition.json");
            cmp = reader.read();

            for (int i = 0; i < CompositionSize; i++) {
                assertEquals(cmp.getVoice(0).getNote(i).getPitch(), Note.REST);
            }

            for (int i = 0; i < CompositionSize; i++) {
                assertEquals(cmp.getVoice(1).getNote(i).getPitch(), Note.REST);
            }
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
