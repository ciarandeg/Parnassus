package model;

// Represents a musical composition of arbitrary length with an arbitrary number of voices
public abstract class Composition {
    protected Validator validator;

    public abstract void addNote(int voiceNum, Note note);

    public abstract void removeNote(int voiceNum, int position);

    public void validate() {
        validator.validate(this);
    }
}
