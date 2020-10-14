package model;

/*
RULES TO IMPLEMENT:
    - "From one perfect consonance to another perfect consonance one must proceed in contrary or oblique motion"
    - "From a perfect consonance to an imperfect consonance one may proceed in any of the three motions"
    - "From an imperfect consonance to a perfect consonance one must proceed in contrary or oblique motion"
    - "From one imperfect consonance to another perfect consonance one may proceed in any of the three motions"

    1. Check that both voices are completely full
    2. Check that all intervals are consonant
    3. Check that the starting and ending intervals are perf
    4. Check that no transitions which lead to perf are parallel
 */

import exceptions.*;

import java.util.ArrayList;

public class FirstSpeciesValidator {
    private Voice v0;
    private Voice v1;

    public FirstSpeciesValidator(Composition cmp) {}

    public boolean validate(Composition cmp) {
        Voice v0 = cmp.getVoice(0);
        Voice v1 = cmp.getVoice(1);

        try {
            boolean voicesFull = isVoiceFull(v0) && isVoiceFull(v1);
            boolean allIntervalsConsonant = areAllIntervalsConsonant(v0, v1);
            boolean firstLastIntervalPerfect = isFirstIntervalPerfect(v0, v1) && isLastIntervalPerfect(v0, v1);
            boolean noIntervalsToPerfectParallel = areNoIntervalsToPerfectParallel(v0, v1);
        } catch (VoiceNotFullException notFull) {
            return false;
        } catch (NotAllIntervalsConsonantException notAllConsonant) {
            return false;
        } catch (FirstIntervalNotPerfectException firstNotPerfect) {
            return false;
        } catch (LastIntervalNotPerfectException lastNotPerfect) {
            return false;
        } catch (ParallelToPerfectException parallelToPerfect) {
            return false;
        }

        return true;
    }

    private boolean isVoiceFull(Voice v) throws VoiceNotFullException {
        for (int i = 0; i < v.size(); i++) {
            if (v.getNote(i).isRest()) {
                throw new VoiceNotFullException();
            }
        }
        return true;
    }

    private boolean areAllIntervalsConsonant(Voice v0, Voice v1) throws NotAllIntervalsConsonantException {
        ArrayList<Interval> intervals = generateIntervals(v0, v1);

        for (Interval i : intervals) {
            if (!i.isConsonant() && !i.isPerfect()) {
                throw new NotAllIntervalsConsonantException();
            }
        }

        return true;
    }

    private boolean isFirstIntervalPerfect(Voice v0, Voice v1) throws FirstIntervalNotPerfectException {
        Interval fi = new Interval(v0.getNote(0), v1.getNote(0));

        if (!fi.isPerfect()) {
            throw new FirstIntervalNotPerfectException();
        }

        return true;
    }

    private boolean isLastIntervalPerfect(Voice v0, Voice v1) throws LastIntervalNotPerfectException {
        int lastIndex = v0.size() - 1;
        Interval li = new Interval(v0.getNote(lastIndex), v1.getNote(lastIndex));

        if (!li.isPerfect()) {
            throw new LastIntervalNotPerfectException();
        }

        return true;
    }

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

        return false;
    }

    // REQUIRES: v0 and v1 are the same size
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