package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FirstSpeciesCompositionTest {
    private FirstSpeciesComposition cmp;
    private Note n1;
    private Note n2;
    private Voice v0;
    private Voice v1;

    @BeforeEach
    public void init() {
        cmp = new FirstSpeciesComposition(8);
        n1 = new Note(60);
        n2 = new Note(72);
        v0 = cmp.getVoice(0);
        v1 = cmp.getVoice(1);
    }

    @Test
    public void testAddNote() {
        cmp.addNote(0, n1);
        assertEquals(v0.getNoteCount(), 1);
        assertEquals(v0.getNote(0), n1);

        cmp.addNote(0, n2);
        assertEquals(v0.getNoteCount(), 2);
        assertEquals(v0.getNote(1), n2);

        cmp.addNote(1, n2);
        assertEquals(v1.getNoteCount(), 1);
        assertEquals(v1.getNote(0), n2);

        cmp.addNote(1, n1);
        assertEquals(v1.getNoteCount(), 2);
        assertEquals(v1.getNote(1), n1);
    }

    @Test
    public void testRemoveNote() {
        assertEquals(v0.getNoteCount(), 0);
        cmp.addNote(0, n1);
        assertEquals(v0.getNoteCount(), 1);
        cmp.addNote(0, n2);
        assertEquals(v0.getNoteCount(), 2);
        cmp.removeNote(0, 0);
        assertEquals(v0.getNoteCount(), 1);
        assertEquals(v0.getNote(1), n2);
    }
}