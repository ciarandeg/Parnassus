package model;

// Represents a single musical note, with pitch and length
public class Note {
    public static final int REST = 0;
    private int pitch; // Midi pitch vals (middle C = 60)

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
}
