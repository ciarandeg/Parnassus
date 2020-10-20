package model;

import exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidatorTest {
    private static final int CMP_SIZE = 4;

    private Composition cmp;
    private Validator val;
    private Note n1;

    @BeforeEach
    public void init() {
        cmp = new Composition(CMP_SIZE);
        val = new Validator();
        n1 = new Note(60);
    }

    @Test
    public void validateEmptyVoiceTest() {
        try {
            assertFalse(val.validate(cmp));
        } catch (VoiceNotFullException e) {
        } catch (NotAllIntervalsConsonantException e) {
            fail();
        } catch (FirstIntervalNotPerfectException e) {
            fail();
        } catch (LastIntervalNotPerfectException e) {
            fail();
        } catch (ParallelToPerfectException e) {
            fail();
        }
    }

    @Test
    public void validateFirstVoicePartiallyEmptyTest() {
        for (int i = 0; i < CMP_SIZE; i++) {
            cmp.addNote(1, n1);
        }
        cmp.addNote(0, n1);
        try {
            assertFalse(val.validate(cmp));
        } catch (VoiceNotFullException e) {
        } catch (NotAllIntervalsConsonantException e) {
            fail();
        } catch (FirstIntervalNotPerfectException e) {
            fail();
        } catch (LastIntervalNotPerfectException e) {
            fail();
        } catch (ParallelToPerfectException e) {
            fail();
        }
    }

    @Test
    public void validateSecondVoicePartiallyEmptyTest() {
        for (int i = 0; i < CMP_SIZE; i++) {
            cmp.addNote(0, n1);
        }
        cmp.addNote(1, n1);
        try {
            assertFalse(val.validate(cmp));
        } catch (VoiceNotFullException e) {
        } catch (NotAllIntervalsConsonantException e) {
            fail();
        } catch (FirstIntervalNotPerfectException e) {
            fail();
        } catch (LastIntervalNotPerfectException e) {
            fail();
        } catch (ParallelToPerfectException e) {
            fail();
        }
    }

    @Test
    public void validateFullVoicesTest() {
        for (int i = 0; i < CMP_SIZE; i++) {
            cmp.addNote(0, n1);
            cmp.addNote(1, n1);
        }
        try {
            assertTrue(val.validate(cmp));
        } catch (VoiceNotFullException e) {
        } catch (NotAllIntervalsConsonantException e) {
        } catch (FirstIntervalNotPerfectException e) {
        } catch (LastIntervalNotPerfectException e) {
        } catch (ParallelToPerfectException e) {
        }
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

        try {
            assertFalse(val.validate(cmp));
        } catch (VoiceNotFullException e) {
            fail();
        } catch (NotAllIntervalsConsonantException e) {
        } catch (FirstIntervalNotPerfectException e) {
            fail();
        } catch (LastIntervalNotPerfectException e) {
            fail();
        } catch (ParallelToPerfectException e) {
            fail();
        }
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

        try {
            assertFalse(val.validate(cmp));
        } catch (VoiceNotFullException e) {
            fail();
        } catch (NotAllIntervalsConsonantException e) {
        } catch (FirstIntervalNotPerfectException e) {
            fail();
        } catch (LastIntervalNotPerfectException e) {
            fail();
        } catch (ParallelToPerfectException e) {
            fail();
        }
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

        try {
            assertTrue(val.validate(cmp));
        } catch (VoiceNotFullException e) {
            fail();
        } catch (NotAllIntervalsConsonantException e) {
            fail();
        } catch (FirstIntervalNotPerfectException e) {
            fail();
        } catch (LastIntervalNotPerfectException e) {
            fail();
        } catch (ParallelToPerfectException e) {
            fail();
        }
    }

    @Test
    public void validateFirstIntervalNotPerfect() {
        cmp.addNote(0, new Note(62));
        cmp.addNote(0, new Note(65));
        cmp.addNote(0, new Note(64));
        cmp.addNote(0, new Note(62));

        cmp.addNote(1, new Note(77));
        cmp.addNote(1, new Note(74));
        cmp.addNote(1, new Note(76));
        cmp.addNote(1, new Note(81));

        try {
            assertFalse(val.validate(cmp));
        } catch (VoiceNotFullException e) {
            fail();
        } catch (NotAllIntervalsConsonantException e) {
            fail();
        } catch (FirstIntervalNotPerfectException e) {
        } catch (LastIntervalNotPerfectException e) {
            fail();
        } catch (ParallelToPerfectException e) {
            fail();
        }
    }

    @Test
    public void validateLastIntervalNotPerfect() {
        cmp.addNote(0, new Note(62));
        cmp.addNote(0, new Note(65));
        cmp.addNote(0, new Note(64));
        cmp.addNote(0, new Note(62));

        cmp.addNote(1, new Note(74));
        cmp.addNote(1, new Note(74));
        cmp.addNote(1, new Note(76));
        cmp.addNote(1, new Note(77));

        try {
            assertFalse(val.validate(cmp));
        } catch (VoiceNotFullException e) {
            fail();
        } catch (NotAllIntervalsConsonantException e) {
            fail();
        } catch (FirstIntervalNotPerfectException e) {
            fail();
        } catch (LastIntervalNotPerfectException e) {
        } catch (ParallelToPerfectException e) {
            fail();
        }
    }

    @Test
    public void validateFirstLastIntervalsNotPerfect() {
        cmp.addNote(0, new Note(62));
        cmp.addNote(0, new Note(65));
        cmp.addNote(0, new Note(64));
        cmp.addNote(0, new Note(62));

        cmp.addNote(1, new Note(77));
        cmp.addNote(1, new Note(74));
        cmp.addNote(1, new Note(76));
        cmp.addNote(1, new Note(81));

        boolean firstPerf = true;
        boolean lastPerf = true;

        try {
            assertTrue(val.validate(cmp));
        } catch (VoiceNotFullException e) {
            fail();
        } catch (NotAllIntervalsConsonantException e) {
            fail();
        } catch (FirstIntervalNotPerfectException e) {
        } catch (LastIntervalNotPerfectException e) {
        } catch (ParallelToPerfectException e) {
            fail();
        }
    }

    @Test
    public void validateFirstLastIntervalsPerfect() {
        cmp.addNote(0, new Note(62));
        cmp.addNote(0, new Note(65));
        cmp.addNote(0, new Note(64));
        cmp.addNote(0, new Note(62));

        cmp.addNote(1, new Note(74));
        cmp.addNote(1, new Note(74));
        cmp.addNote(1, new Note(76));
        cmp.addNote(1, new Note(81));

        try {
            assertTrue(val.validate(cmp));
        } catch (VoiceNotFullException e) {
            fail();
        } catch (NotAllIntervalsConsonantException e) {
            fail();
        } catch (FirstIntervalNotPerfectException e) {
            fail();
        } catch (LastIntervalNotPerfectException e) {
            fail();
        } catch (ParallelToPerfectException e) {
            fail();
        }
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

        try {
            assertFalse(val.validate(cmp));
        } catch (VoiceNotFullException e) {
            fail();
        } catch (NotAllIntervalsConsonantException e) {
            fail();
        } catch (FirstIntervalNotPerfectException e) {
            fail();
        } catch (LastIntervalNotPerfectException e) {
            fail();
        } catch (ParallelToPerfectException e) {
        }
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

        try {
            assertFalse(val.validate(cmp));
        } catch (VoiceNotFullException e) {
            fail();
        } catch (NotAllIntervalsConsonantException e) {
            fail();
        } catch (FirstIntervalNotPerfectException e) {
            fail();
        } catch (LastIntervalNotPerfectException e) {
            fail();
        } catch (ParallelToPerfectException e) {
        }
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

        try {
            assertTrue(val.validate(cmp));
        } catch (VoiceNotFullException e) {
            fail();
        } catch (NotAllIntervalsConsonantException e) {
            fail();
        } catch (FirstIntervalNotPerfectException e) {
            fail();
        } catch (LastIntervalNotPerfectException e) {
            fail();
        } catch (ParallelToPerfectException e) {
            fail();
        }
    }
}