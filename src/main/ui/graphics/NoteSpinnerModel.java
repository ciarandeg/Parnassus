package ui.graphics;

import javax.swing.*;
import java.util.Arrays;

// A SpinnerListModel that displays note names
public class NoteSpinnerModel extends SpinnerListModel {
    public static final String REST = "REST";
    public static final String[] NOTE_NAMES = {"C", "Db", "D", "Eb", "E", "F", "F#", "G", "Ab", "A", "Bb", "B"};
    public static final String[] NOTE_RANGE =
            {
            REST,
            "A0", "Bb0", "B0",
            "C1", "Db1", "D1", "Eb1", "E1", "F1", "F#1", "G1", "Ab1", "A1", "Bb1", "B1",
            "C2", "Db2", "D2", "Eb2", "E2", "F2", "F#2", "G2", "Ab2", "A2", "Bb2", "B2",
            "C3", "Db3", "D3", "Eb3", "E3", "F3", "F#3", "G3", "Ab3", "A3", "Bb3", "B3",
            "C4", "Db4", "D4", "Eb4", "E4", "F4", "F#4", "G4", "Ab4", "A4", "Bb4", "B4",
            "C5", "Db5", "D5", "Eb5", "E5", "F5", "F#5", "G5", "Ab5", "A5", "Bb5", "B5",
            "C6", "Db6", "D6", "Eb6", "E6", "F6", "F#6", "G6", "Ab6", "A6", "Bb6", "B6",
            "C7", "Db7", "D7", "Eb7", "E7", "F7", "F#7", "G7", "Ab7", "A7", "Bb7", "B7",
            "C8", "Db8", "D8", "Eb8", "E8", "F8", "F#8", "G8", "Ab8", "A8", "Bb8", "B8",
            "C9", "Db9", "D9", "Eb9", "E9", "F9", "F#9", "G9", "Ab9"
            };

    public NoteSpinnerModel() {
        super(Arrays.asList(NOTE_RANGE));
    }
}
