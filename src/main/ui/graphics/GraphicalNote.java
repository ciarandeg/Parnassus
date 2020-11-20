package ui.graphics;

import model.Note;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

// A graphical representation of a Note, displayed as a JSpinner
public class GraphicalNote extends JSpinner implements Observer {
    private static final Font FONT = new Font("NotoSansMono Nerd Font", Font.BOLD, 24);

    private Note note;
    private Listener listener;

    // EFFECTS: construct a JSpinner representing a Note
    public GraphicalNote(Note note) {
        super(new NoteSpinnerModel());
        addChangeListener(listener = new Listener());
        setFont(FONT);

        this.note = note;
        note.addObserver(this);
    }

    // EFFECTS: set Spinner value to current note pitch
    @Override
    public void update(Observable o, Object arg) {
        setValue(note.getPitch());
    }

    // EFFECTS: if value is Integer, convert from midi to note name and pass through to super.setValue()
    //          else pass through to super.setValue()
    @Override
    public void setValue(Object value) {
        if (value instanceof Integer) {
            super.setValue(midiToNoteName((Integer) value));
        } else {
            super.setValue(value);
        }
    }

    // EFFECTS: set note pitch to pitch
    public void setPitch(int pitch) {
        note.setPitch(pitch);
    }

    // REQUIRES: midi is either valid midi pitch or rest (21 <= midi <= 127 || midi == Note.REST)
    // EFFECTS: convert midi value to note name (as specified in NoteSpinnerModel.java)
    private String midiToNoteName(int midi) {
        if (midi == Note.REST) {
            return NoteSpinnerModel.REST;
        }

        int octave = (midi - 12) / 12;
        String noteName = NoteSpinnerModel.NOTE_NAMES[(midi + 12) % 12] + octave;

        return noteName;
    }

    // REQUIRES: name is valid note name (as specified in NoteSpinnerModel.java)
    // EFFECTS: convert name to midi value
    private int noteNameToMidi(String name) {
        if (name.equals(NoteSpinnerModel.REST)) {
            return Note.REST;
        }

        int octave = Integer.parseInt(name.substring(name.length() - 1));
        name = name.substring(0, name.length() - 1);

        for (int i = 0; i < NoteSpinnerModel.NOTE_NAMES.length; i++) {
            if (name.equals(NoteSpinnerModel.NOTE_NAMES[i])) {
                return i + 12 + (12 * octave);
            }
        }

        return Note.REST;
    }

    private class Listener implements ChangeListener {
        // EFFECTS: set note pitch to current JSpinner value
        @Override
        public void stateChanged(ChangeEvent e) {
            setPitch(noteNameToMidi((String) getValue()));
        }
    }
}