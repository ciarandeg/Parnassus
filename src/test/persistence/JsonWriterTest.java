package persistence;

import model.Composition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.
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
                assertEquals(cmp.getVoice(0).getNote(i).getPitch(), 0);
            }

            for (int i = 0; i < CompositionSize; i++) {
                assertEquals(cmp.getVoice(1).getNote(i).getPitch(), 0);
            }
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
