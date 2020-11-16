package model;

import exceptions.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

// Represents a first-species counterpoint composition of arbitrary size
public class Composition implements Iterable<Voice> {
    private static final int VOICE_COUNT = 2;
    private ArrayList<Voice> voices;
    private Validator validator;

    // EFFECTS: create a VOICE_COUNT-voice composition with size notes per voice
    public Composition(int size) {
        this.voices = new ArrayList<Voice>();
        this.validator = new Validator(this);
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

    // REQUIRES: voiceNum <= number of voices present
    // EFFECTS: return voice specified by voiceNum
    public Voice getVoice(int voiceNum) {
        return voices.get(voiceNum);
    }

    // CITATION: this method is adapted from WorkRoom.toJson() in CPSC 210's JSON Serialization Demo:
    //           https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: convert composition data into a JSONObject, return it
    public JSONObject toJson() {
        JSONArray v0 = new JSONArray();
        JSONArray v1 = new JSONArray();
        JSONArray jsonVoices = new JSONArray();
        JSONObject json = new JSONObject();

        for (Note n : voices.get(0).getNoteArrayList()) {
            v0.put(n.getPitch());
        }

        for (Note n : voices.get(1).getNoteArrayList()) {
            v1.put(n.getPitch());
        }

        jsonVoices.put(v0);
        jsonVoices.put(v1);

        json.put("voices", jsonVoices);

        return json;
    }

    public boolean validate() throws NotAllIntervalsConsonantException, FirstIntervalNotPerfectException,
            ParallelToPerfectException, LastIntervalNotPerfectException, VoiceNotFullException {
        return validator.validate();
    }

    public Validator getValidator() {
        return validator;
    }

    @Override
    public Iterator<Voice> iterator() {
        return voices.iterator();
    }
}