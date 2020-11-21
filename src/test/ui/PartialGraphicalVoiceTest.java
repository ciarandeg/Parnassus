package ui;

import exceptions.GraphicalVoiceOutOfBoundsException;
import model.Voice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.graphics.PartialGraphicalVoice;

import static org.junit.jupiter.api.Assertions.fail;

public class PartialGraphicalVoiceTest {
    private Voice voice;
    private PartialGraphicalVoice gVoice;

    @Test
    public void testPartialGraphicalVoiceException() {
        voice = new Voice(4);
        try {
            gVoice = new PartialGraphicalVoice(voice, 4, 2);
            fail();
        } catch (GraphicalVoiceOutOfBoundsException e) {
        }

        try {
            gVoice = new PartialGraphicalVoice(voice, 3, 4);
        } catch (GraphicalVoiceOutOfBoundsException e) {
            fail();
        }
    }
}
