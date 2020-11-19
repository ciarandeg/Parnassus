package model;

import java.util.ArrayList;
import java.util.Iterator;

// Represents a single voice of a composition, containing notes and rests
public class Voice implements Iterable<Note> {
    private ArrayList<Note> notes;

    public Voice(int size) {
        notes = new ArrayList<Note>();
        for (int i = 0; i < size; i++) {
            notes.add(new Note());
        }
    }

    // MODIFIES: this
    // EFFECTS: replace earliest rest with new note
    //          if voice is full, do nothing
    public void addNote(Note note) {
        for (int i = 0; i < notes.size(); i++) {
            Note n = notes.get(i);
            if (n.isRest()) {
                notes.set(i, note);
                return;
            }
        }
    }

    // REQUIRES: there is a note at position
    // MODIFIES: this
    // EFFECTS: set note to rest
    public void removeNote(int index) {
        notes.set(index, new Note());
    }

    // REQUIRES: there is a note at position
    // EFFECTS: return note at position
    public Note getNote(int index) {
        return notes.get(index);
    }

    // EFFECTS: return number of notes in voice (excluding rests)
    public int getNoteCount() {
        int i = 0;
        for (Note n : notes) {
            if (!n.isRest()) {
                i++;
            }
        }
        return i;
    }

    // EFFECTS: return maximum possible number of notes in voice
    public int size() {
        return notes.size();
    }

    public ArrayList<Note> getNoteArrayList() {
        return notes;
    }

    @Override
    public Iterator<Note> iterator() {
        return notes.iterator();
    }
}
