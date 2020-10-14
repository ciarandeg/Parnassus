package model;

import java.util.ArrayList;

// Represents a musical composition of arbitrary length with an arbitrary number of voices
public abstract class Composition {
    protected FirstSpeciesValidator validator;
    protected ArrayList<Voice> voices;

    public abstract void addNote(int voiceNum, Note note);

    public abstract void removeNote(int voiceNum, int position);

    // EFFECTS: validate this, return true if valid, false otherwise
    public boolean validate() {
        return validator.validate(this);
    }

    // REQUIRES: voiceNum <= number of voices present
    // EFFECTS: return voice specified by voiceNum
    public Voice getVoice(int voiceNum) {
        return voices.get(voiceNum);
    }
}
