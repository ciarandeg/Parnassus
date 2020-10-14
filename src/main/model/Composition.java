package model;

import java.util.ArrayList;

public class Composition {
    private static final int VOICE_COUNT = 2;

    private Validator validator;
    private ArrayList<Voice> voices;

    // Represents a first-species counterpoint composition of arbitrary size
    public Composition(int size) {
        this.validator = new Validator();

        this.voices = new ArrayList<Voice>();
        for (int i = 0; i < VOICE_COUNT; i++) {
            this.voices.add(new Voice(size));
        }
    }

    // REQUIRES: 1 <= voiceNum <= number of available voices
    //           voice is under its note limit
    // MODIFIES: this
    // EFFECTS: add note to specified voice
    public void addNote(int voiceNum, Note note) {
        getVoice(voiceNum).addNote(note);
    }

    // REQUIRES: a note exists in specified position of specified voice
    // MODIFIES: this
    // EFFECTS: replace specified note with rest
    public void removeNote(int voiceNum, int position) {
        getVoice(voiceNum).removeNote(position);
    }

    // REQUIRES: all voices have the same size
    //           there must be >= 1 voices
    // EFFECTS: return the maximum amount of notes allowed per voice
    public int size() {
        return this.voices.get(0).size();
    }

    // REQUIRES: voiceNum <= number of voices present
    // EFFECTS: return voice specified by voiceNum
    public Voice getVoice(int voiceNum) {
        return voices.get(voiceNum);
    }
}