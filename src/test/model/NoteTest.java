package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Observable;
import java.util.Observer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoteTest {
    private static final int TEST_PITCH = 73;
    private Note note;

    @BeforeEach
    public void init() {
        note = new Note();
    }

    @Test
    public void testNotePitch() {
        assertEquals(note.getPitch(), Note.REST);
        note.setPitch(TEST_PITCH);
        assertEquals(note.getPitch(), TEST_PITCH);
    }

    @Test
    public void testNoteObserver() {
        assertEquals(note.countObservers(), 0);
        note.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
            }
        });
        assertEquals(note.countObservers(), 1);
    }
}
