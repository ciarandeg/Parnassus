package model;

import exceptions.*;

import java.util.ArrayList;

// Represents the benevolent teacher Aloys, who informs you about your errors through Socratic dialogue
public class Validator {
    private Voice v0;
    private Voice v1;

    public Validator() {}

    // EFFECTS: validate that cmp fulfills all the rules of first-species counterpoint
    //          return true if the composition is valid, throw an exception otherwise
    public boolean validate(Composition cmp) throws VoiceNotFullException, NotAllIntervalsConsonantException,
            FirstIntervalNotPerfectException, LastIntervalNotPerfectException, ParallelToPerfectException {
        Voice v0 = cmp.getVoice(0);
        Voice v1 = cmp.getVoice(1);

        boolean firstVoiceFull = isVoiceFull(v0);
        boolean secondVoiceFull = isVoiceFull(v1);
        boolean allIntervalsConsonant = areAllIntervalsConsonant(v0, v1);
        boolean firstIntervalPerfect = isFirstIntervalPerfect(v0, v1);
        boolean lastIntervalPerfect = isLastIntervalPerfect(v0, v1);
        boolean noIntervalsToPerfectParallel = areNoIntervalsToPerfectParallel(v0, v1);

        return true;
    }

    // EFFECTS: return true if v contains its maximum number of notes, with no rests
    //          false otherwise
    private boolean isVoiceFull(Voice v) throws VoiceNotFullException {
        for (Note n : v) {
            if (n.isRest()) {
                throw new VoiceNotFullException();
            }
        }
        return true;
    }

    // REQUIRES: both voices are full
    // EFFECTS: return true if all harmonic intervals between a pair of voices are consonant or perfect
    //          return false otherwise
    private boolean areAllIntervalsConsonant(Voice v0, Voice v1) throws NotAllIntervalsConsonantException {
        ArrayList<Interval> intervals = generateIntervals(v0, v1);

        for (Interval i : intervals) {
            if (!i.isConsonant() && !i.isPerfect()) {
                throw new NotAllIntervalsConsonantException();
            }
        }

        return true;
    }

    // REQUIRES: both voices are full
    // EFFECTS: return true if the first harmonic interval between a pair of voices is a perfect consonance
    //          return false otherwise
    private boolean isFirstIntervalPerfect(Voice v0, Voice v1) throws FirstIntervalNotPerfectException {
        Interval fi = new Interval(v0.getNote(0), v1.getNote(0));

        if (!fi.isPerfect()) {
            throw new FirstIntervalNotPerfectException();
        }

        return true;
    }

    // REQUIRES: both voices are full
    // EFFECTS: return true if the last harmonic interval between a pair of voices is a perfect consonance
    //          return false otherwise
    private boolean isLastIntervalPerfect(Voice v0, Voice v1) throws LastIntervalNotPerfectException {
        int lastIndex = v0.size() - 1;
        Interval li = new Interval(v0.getNote(lastIndex), v1.getNote(lastIndex));

        if (!li.isPerfect()) {
            throw new LastIntervalNotPerfectException();
        }

        return true;
    }

    // REQUIRES: both voices are full
    // EFFECTS: return true if all perfect consonances between two voices are approached by non-parallel motion
    //          return false otherwise
    private boolean areNoIntervalsToPerfectParallel(Voice v0, Voice v1) throws ParallelToPerfectException {
        ArrayList<Interval> intervals = generateIntervals(v0, v1);
        ArrayList<Integer> perfectIndices = new ArrayList<Integer>();

        for (Interval ivl : intervals) {
            int i = intervals.indexOf(ivl);

            if (ivl.isPerfect() && i != 0) {
                perfectIndices.add(i);
            }
        }

        for (int i : perfectIndices) {
            Note v0n0 = v0.getNote(i - 1);
            Note v0n1 = v0.getNote(i);
            Note v1n0 = v1.getNote(i - 1);
            Note v1n1 = v1.getNote(i);
            Motion m0 = new Motion(v0n0, v0n1);
            Motion m1 = new Motion(v1n0, v1n1);

            if ((m0.isUp() && m1.isUp()) || (m0.isDown() && m1.isDown())) {
                throw new ParallelToPerfectException();
            }
        }

        return true;
    }

    // REQUIRES: v0 and v1 are the same size, and both are full
    // EFFECTS: generate a list of the intervals between two voices
    public ArrayList<Interval> generateIntervals(Voice v0, Voice v1) {
        ArrayList<Note> n0 = v0.getNoteArrayList();
        ArrayList<Note> n1 = v1.getNoteArrayList();
        ArrayList<Interval> intervals = new ArrayList<Interval>();

        for (int i = 0; i < n0.size(); i++) {
            intervals.add(new Interval(n0.get(i), n1.get(i)));
        }

        return intervals;
    }
}