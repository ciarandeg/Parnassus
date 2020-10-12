package model;

// Represents a single musical note, with pitch and length
public class Note {
    private static final int REST_PITCH = 0;
    private int pitch; // Midi pitch vals (middle C = 60)
    private int length; // Denominator of note value (8th note is represented by 8)

    public Note() {
        this.pitch = REST_PITCH;
        this.length = 1;
    }

    public Note(int pitch) {
        this.pitch = pitch;
        this.length = 1;
    }

    public Note(int pitch, int length) {
        this.pitch = pitch;
        this.length = length;
    }

    public boolean isRest() {
        return this.pitch == REST_PITCH;
    }
}
