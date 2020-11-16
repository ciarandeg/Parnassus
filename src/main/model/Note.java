package model;

import java.util.Observable;
import java.util.Observer;

// Represents a single musical note, with pitch and length
public class Note extends Observable {
    public static final int REST = 20;
    private int pitch; // Midi pitch vals

    public Note() {
        this.pitch = REST;
    }

    public Note(int pitch) {
        this.pitch = pitch;
    }

    public boolean isRest() {
        return this.pitch == REST;
    }

    public int getPitch() {
        return this.pitch;
    }

    public void setPitch(int pitch) {
        this.pitch = pitch;
        setChanged();
        notifyObservers();
    }

    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
        setChanged();
        notifyObservers();
    }
}
