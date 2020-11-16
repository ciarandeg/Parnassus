package ui.graphics;

import model.Note;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class GraphicalNote extends JSpinner implements Observer {
    private static final int SPINNER_DEFAULT = 60;
    private static final int SPINNER_MIN = 20;
    private static final int SPINNER_MAX = 127;
    private static final int SPINNER_INC = 1;
    private static final Font FONT = new Font("NotoSansMono Nerd Font", Font.BOLD, 24);

    private Note note;
    private Listener listener;

    public GraphicalNote(Note note) {
        super(new SpinnerNumberModel(SPINNER_DEFAULT,
                                    SPINNER_MIN,
                                    SPINNER_MAX,
                                    SPINNER_INC));
        addChangeListener(listener = new Listener());
        setFont(FONT);

        this.note = note;
        note.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        setValue(note.getPitch());
    }

    public void setPitch(int pitch) {
        note.setPitch(pitch);
    }

    private class Listener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            int pitch = (int) getValue();
            setPitch(pitch);
        }
    }
}