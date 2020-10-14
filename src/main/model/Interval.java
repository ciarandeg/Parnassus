package model;

import static java.lang.Math.abs;

public class Interval {
    enum ConsonanceLevel {
        DISSONANT,
        CONSONANT,
        PERFECT
    }

    public static final ConsonanceLevel[] CONSONANCE_VALUES = {
            ConsonanceLevel.PERFECT, // unison
            ConsonanceLevel.DISSONANT, // m2
            ConsonanceLevel.DISSONANT, // M2
            ConsonanceLevel.CONSONANT, // m3
            ConsonanceLevel.CONSONANT, // M3
            ConsonanceLevel.PERFECT, // P4
            ConsonanceLevel.DISSONANT, // +4
            ConsonanceLevel.PERFECT, // P5
            ConsonanceLevel.CONSONANT, // m6
            ConsonanceLevel.CONSONANT, // M6
            ConsonanceLevel.DISSONANT, // m7
            ConsonanceLevel.DISSONANT, // M7
    };

    int size;
    ConsonanceLevel consonance;

    public Interval(int size) {
        this.size = size;
        this.consonance = CONSONANCE_VALUES[this.size];
    }

    public Interval(Note n0, Note n1) {
        this.size = abs(n1.getPitch() - n0.getPitch()) % 12;
        this.consonance = CONSONANCE_VALUES[this.size];
    }

    // EFFECTS: return true if this is a dissonant interval, false otherwise
    public boolean isDissonant() {
        return this.consonance.equals(ConsonanceLevel.DISSONANT);
    }

    // EFFECTS: return true if this is a non-perfect consonant interval, false if dissonant or perfect
    public boolean isConsonant() {
        return this.consonance.equals(ConsonanceLevel.CONSONANT);
    }

    // EFFECTS: return true if this interval is a perfect consonance, false otherwise
    public boolean isPerfect() {
        return this.consonance.equals(ConsonanceLevel.PERFECT);
    }
}
