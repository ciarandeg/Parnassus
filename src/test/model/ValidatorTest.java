package model;

import exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;

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
            // pass
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
            // pass
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
            // pass
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
    public void validateNoIntervalsConsonantTest() {
        readJson("./data/testNoIntervalsConsonantComposition.json");

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
        readJson("./data/testSomeIntervalsConsonantComposition.json");

        try {
            assertFalse(val.validate(cmp));
        } catch (VoiceNotFullException e) {
            fail();
        } catch (NotAllIntervalsConsonantException e) {
            // pass
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
        readJson("./data/testAllIntervalsConsonantComposition.json");

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
        readJson("./data/testFirstIntervalNotPerfectComposition.json");

        try {
            assertFalse(val.validate(cmp));
        } catch (VoiceNotFullException e) {
            fail();
        } catch (NotAllIntervalsConsonantException e) {
            fail();
        } catch (FirstIntervalNotPerfectException e) {
            // pass
        } catch (LastIntervalNotPerfectException e) {
            fail();
        } catch (ParallelToPerfectException e) {
            fail();
        }
    }

    @Test
    public void validateLastIntervalNotPerfect() {
        readJson("./data/testLastIntervalNotPerfectComposition.json");

        try {
            assertFalse(val.validate(cmp));
        } catch (VoiceNotFullException e) {
            fail();
        } catch (NotAllIntervalsConsonantException e) {
            fail();
        } catch (FirstIntervalNotPerfectException e) {
            fail();
        } catch (LastIntervalNotPerfectException e) {
            // pass
        } catch (ParallelToPerfectException e) {
            fail();
        }
    }

    @Test
    public void validateFirstLastIntervalsNotPerfect() {
        readJson("./data/testFirstLastIntervalsNotPerfectComposition.json");

        boolean firstPerf = true;
        boolean lastPerf = true;

        try {
            assertTrue(val.validate(cmp));
        } catch (VoiceNotFullException e) {
            fail();
        } catch (NotAllIntervalsConsonantException e) {
            fail();
        } catch (FirstIntervalNotPerfectException e) {
            // pass
        } catch (LastIntervalNotPerfectException e) {
            // pass
        } catch (ParallelToPerfectException e) {
            fail();
        }
    }

    @Test
    public void validateFirstLastIntervalsPerfect() {
        readJson("./data/testFirstLastIntervalsPerfectComposition.json");

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
        readJson("./data/testAllIntervalsToPerfectAreParallelComposition.json");

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
            // pass
        }
    }

    @Test
    public void validateSomeIntervalsToPerfectAreParallelTest() {
        readJson("./data/testSomeIntervalsToPerfectAreParallelComposition.json");

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
            // pass
        }
    }

    @Test
    public void validateNoIntervalsToPerfectAreParallelTest() {
        readJson("./data/testNoIntervalsToPerfectAreParallelComposition.json");

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

    // EFFECTS: point cmp to the composition described in src
    private void readJson(String src) {
        JsonReader jsr = new JsonReader(src);
        try {
            this.cmp = jsr.read();
        } catch (IOException e) {
            fail("The file failed to read/write for some reason");
        }
    }
}