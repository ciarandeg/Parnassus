package model;

// Represents a single musical note, with pitch and length
public class Note {
    private static final int REST_PITCH = 0;
    private int pitch; // Midi pitch vals (middle C = 60)

    public Note() {
        this.pitch = REST_PITCH;
    }

    public Note(int pitch) {
        this.pitch = pitch;
    }

    public boolean isRest() {
        return this.pitch == REST_PITCH;
    }

    public int getPitch() {
        return this.pitch;
    }
}
