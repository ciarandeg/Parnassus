package persistence;

import model.Composition;
import model.Note;
import model.Voice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {
    private String src;
    private JsonReader jsr;
    private Composition cmp;

    @BeforeEach
    public void init () {
        src = "./data/testJsonReader.json";
        jsr = new JsonReader(src);
        try {
            cmp = jsr.read();
        } catch (IOException e) {
            fail("IO Exception!");
        }
    }

    @Test
    public void testJsonReaderConstructor() {
        assertEquals(jsr.getSource(), src);
    }

    @Test
    public void testJsonReaderRead() {
        Voice v0 = cmp.getVoice(0);
        Voice v1 = cmp.getVoice(1);

        assertEquals(v0.getNoteCount(), 4);
        assertEquals(v1.getNoteCount(), 4);

        ArrayList<Note> noteList = v0.getNoteArrayList();
        assert (noteList.addAll(v1.getNoteArrayList()));

        for (Note n : noteList) {
            assertEquals(noteList.indexOf(n) + 1, n.getPitch());
        }
    }
}
