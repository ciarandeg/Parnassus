package ui.graphics;

import model.Note;
import model.Voice;

import javax.swing.*;
import java.awt.*;

public class GraphicalVoice extends JPanel {
    private Voice voice;

    public GraphicalVoice(Voice voice) {
        super(new GridLayout(1, voice.getNoteCount()));
        this.voice = voice;
        for (Note n : this.voice) {
            add(new GraphicalNote(n));
        }
    }
}
