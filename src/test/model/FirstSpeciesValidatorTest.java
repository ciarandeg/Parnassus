package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FirstSpeciesValidatorTest {
    private static final int CMP_SIZE = 4;

    private FirstSpeciesComposition cmp;
    private Note n1;

    @BeforeEach
    public void init() {
        cmp = new FirstSpeciesComposition(CMP_SIZE);
        n1 = new Note(60);
    }

    @Test
    public void validateEmptyVoiceTest() {
        assertFalse(cmp.validate());
    }

    @Test
    public void validateOneVoicePartiallyEmptyTest() {
        for (int i = 0; i < CMP_SIZE; i++) {
            cmp.addNote(0, n1);
        }
        cmp.addNote(1, n1);
        assertFalse(cmp.validate());
    }

    @Test
    public void validateFullVoicesTest() {
        for (int i = 0; i < CMP_SIZE; i++) {
            cmp.addNote(0, n1);
            cmp.addNote(1, n1);
        }
        assertTrue(cmp.validate());
    }

    @Test
    public void validateNoIntervalsConsonantTest() {
        cmp.addNote(0, new Note(62));
        cmp.addNote(0, new Note(65));
        cmp.addNote(0, new Note(64));
        cmp.addNote(0, new Note(62));

        cmp.addNote(1, new Note(68));
        cmp.addNote(1, new Note(67));
        cmp.addNote(1, new Note(69));
        cmp.addNote(1, new Note(72));

        assertFalse(cmp.validate());
    }

    @Test
    public void validateSomeIntervalsConsonantTest() {
        cmp.addNote(0, new Note(62));
        cmp.addNote(0, new Note(65));
        cmp.addNote(0, new Note(64));
        cmp.addNote(0, new Note(62));

        cmp.addNote(1, new Note(69));
        cmp.addNote(1, new Note(67));
        cmp.addNote(1, new Note(71));
        cmp.addNote(1, new Note(72));

        assertFalse(cmp.validate());
    }

    @Test
    public void validateAllIntervalsConsonantTest() {
        cmp.addNote(0, new Note(60));
        cmp.addNote(0, new Note(60));
        cmp.addNote(0, new Note(60));
        cmp.addNote(0, new Note(60));

        cmp.addNote(1, new Note(72));
        cmp.addNote(1, new Note(77));
        cmp.addNote(1, new Note(79));
        cmp.addNote(1, new Note(84));

        assertTrue(cmp.validate());
    }

    @Test
    public void validateStartIntervalNotPerfect() {
        cmp.addNote(0, new Note(62));
        cmp.addNote(0, new Note(65));
        cmp.addNote(0, new Note(64));
        cmp.addNote(0, new Note(62));

        cmp.addNote(1, new Note(77));
        cmp.addNote(1, new Note(74));
        cmp.addNote(1, new Note(76));
        cmp.addNote(1, new Note(81));

        assertFalse(cmp.validate());
    }

    @Test
    public void validateEndIntervalNotPerfect() {
        cmp.addNote(0, new Note(62));
        cmp.addNote(0, new Note(65));
        cmp.addNote(0, new Note(64));
        cmp.addNote(0, new Note(62));

        cmp.addNote(1, new Note(74));
        cmp.addNote(1, new Note(74));
        cmp.addNote(1, new Note(76));
        cmp.addNote(1, new Note(77));

        assertFalse(cmp.validate());
    }

    @Test
    public void validateStartEndIntervalsPerfect() {
        cmp.addNote(0, new Note(62));
        cmp.addNote(0, new Note(65));
        cmp.addNote(0, new Note(64));
        cmp.addNote(0, new Note(62));

        cmp.addNote(1, new Note(74));
        cmp.addNote(1, new Note(74));
        cmp.addNote(1, new Note(76));
        cmp.addNote(1, new Note(81));

        assertTrue(cmp.validate());
    }

    @Test
    public void validateAllIntervalsToPerfectAreParallelTest() {
        cmp.addNote(0, new Note(62));
        cmp.addNote(0, new Note(65));
        cmp.addNote(0, new Note(64));
        cmp.addNote(0, new Note(62));

        cmp.addNote(1, new Note(74));
        cmp.addNote(1, new Note(74));
        cmp.addNote(1, new Note(76));
        cmp.addNote(1, new Note(74));

        assertFalse(cmp.validate());
    }

    @Test
    public void validateSomeIntervalsToPerfectAreParallelTest() {
        cmp.addNote(0, new Note(62));
        cmp.addNote(0, new Note(65));
        cmp.addNote(0, new Note(64));
        cmp.addNote(0, new Note(62));

        cmp.addNote(1, new Note(69));
        cmp.addNote(1, new Note(72));
        cmp.addNote(1, new Note(76));
        cmp.addNote(1, new Note(81));

        assertFalse(cmp.validate());
    }

    @Test
    public void validateNoIntervalsToPerfectAreParallelTest() {
        cmp.addNote(0, new Note(62));
        cmp.addNote(0, new Note(65));
        cmp.addNote(0, new Note(67));
        cmp.addNote(0, new Note(62));

        cmp.addNote(1, new Note(74));
        cmp.addNote(1, new Note(72));
        cmp.addNote(1, new Note(67));
        cmp.addNote(1, new Note(74));

        assertTrue(cmp.validate());
    }
}