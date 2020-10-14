package model;

import java.util.ArrayList;

public class FirstSpeciesComposition extends Composition {
    private static final int VOICE_COUNT = 2;

    public FirstSpeciesComposition(int size) {
        super.validator = new FirstSpeciesValidator(this);

        super.voices = new ArrayList<Voice>();
        for (int i = 0; i < VOICE_COUNT; i++) {
            super.voices.add(new Voice(size));
        }
    }

    // REQUIRES: 1 <= voiceNum <= number of available voices
    //           voice is under its note limit
    // MODIFIES: this
    // EFFECTS: add note to specified voice
    @Override
    public void addNote(int voiceNum, Note note) {
        getVoice(voiceNum).addNote(note);
    }

    // REQUIRES: a note exists in specified position of specified voice
    // MODIFIES: this
    // EFFECTS: replace specified note with rest
    @Override
    public void removeNote(int voiceNum, int position) {
        getVoice(voiceNum).removeNote(position);
    }

    // REQUIRES: all voices have the same size
    //           there must be >= 1 voices
    // EFFECTS: return the maximum amount of notes allowed per voice
    public int size() {
        return super.voices.get(0).size();
    }
}