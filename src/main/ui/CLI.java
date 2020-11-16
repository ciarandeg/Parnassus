package ui;

import exceptions.*;
import model.Composition;
import model.Note;
import model.Validator;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class CLI {
    public static void main(String[] args) {

        System.out.println("Welcome to Parnassus!");

        session();

        System.out.println("See you later!");
    }

    // EFFECTS: a session where the user can load a composition, have it validated, and save their work once it's done
    //          once finished, user can choose to create a new session to work on another composition
    public static void session() {
        Composition cmp;
        cmp = promptLoadComposition();

        promptValidateComposition(cmp);

        promptSaveComposition(cmp);

        System.out.println("Would you like to restart with a new composition?");
        if (promptYesNo()) {
            session();
        }
    }

    // EFFECTS: Let user load composition either from external file or through manual entry
    //          return loaded composition
    private static Composition promptLoadComposition() {
        Composition cmp;

        System.out.println("Would you like to load an existing composition?");
        if (promptYesNo()) {
            cmp = promptLoadFromFile();
        } else {
            cmp = promptNoteEntry();
        }
        return cmp;
    }

    // EFFECTS: Prompt user for path to JSON file describing a composition
    //          return the loaded composition
    private static Composition promptLoadFromFile() {
        Composition cmp = new Composition(1); // must initialize because of try/catch block
        Scanner scn = new Scanner(System.in);

        boolean validPath = false;
        while (!validPath) {
            JsonReader jsr;
            System.out.printf("Enter path to json file: ");
            String src = scn.nextLine();
            jsr = new JsonReader(src);
            try {
                cmp = jsr.read();
                validPath = true;
            } catch (IOException e) {
                System.out.println("Invalid path! Please try again.");
            }
        }
        return cmp;
    }

    // EFFECTS: Let user manually enter pitch information for their composition
    //          return the resulting composition
    private static Composition promptNoteEntry() {
        Composition cmp;
        Scanner scn = new Scanner(System.in);

        System.out.printf("Enter the number of notes you want per voice: ");
        cmp = new Composition(scn.nextInt());

        System.out.printf("NOTE: Parnassus uses MIDI integers for pitch.\n");
        System.out.printf("60 is middle C, and 61 is middle C#. 0 is used for rest.\n\n");

        System.out.printf("Now you can begin note entry for voice 1.\n");

        for (int v = 0; v < 2; v++) {
            for (int i = 0; i < cmp.getVoice(0).size(); i++) {
                System.out.printf("Please enter the pitch of voice %d note %d: ", v + 1, i + 1);
                Note n = new Note(scn.nextInt());
                cmp.addNote(v, n);
            }
            if (v == 0) {
                System.out.printf("\nOnto the next voice!\n\n");
            }
        }
        System.out.printf("\nNote entry complete.\n");

        return cmp;
    }

    // EFFECTS: validate user's composition and provide feedback
    private static void promptValidateComposition(Composition cmp) {
        System.out.printf("Now Parnassus will validate your composition and output any compositional errors:\n\n");

        if (validateComposition(cmp)) {
            System.out.println("Your counterpoint is flawless! I have nothing more to teach you. Have good day!");
        } else {
            System.out.println("Your counterpoint is incorrect.");
        }
    }

    // EFFECTS: offer to save user's composition to an external file
    private static void promptSaveComposition(Composition cmp) {
        Scanner scn = new Scanner(System.in);

        System.out.println("Would you like to save your composition?");
        if (promptYesNo()) {
            System.out.printf("Enter the path to new file: ");
            JsonWriter jsw = new JsonWriter(scn.nextLine());
            try {
                jsw.open();
                jsw.write(cmp);
                jsw.close();
            } catch (FileNotFoundException e) {
                System.out.println("There was an error writing to file.");
            }
        }
    }

    // EFFECTS: allow user to answer yes or no to a question, return true or false accordingly
    private static boolean promptYesNo() {
        Scanner scn = new Scanner(System.in);

        while (true) {
            System.out.printf("Enter yes or no: ");
            String answer = scn.nextLine();
            if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")) {
                return true;
            } else if (answer.equalsIgnoreCase("no") || answer.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.printf("That's not a valid answer. Please try again.\n\n");
            }
        }
    }

    // EFFECTS: attempt to validate cmp, provide feedback on counterpoint errors if an exception is thrown
    private static boolean validateComposition(Composition cmp) {
        try {
            cmp.validate();
            return true;
        } catch (VoiceNotFullException e) {
            System.out.println("One or more voices are not filled with notes!");
            return false;
        } catch (NotAllIntervalsConsonantException e) {
            System.out.println("One or more intervals are not consonant!");
            return false;
        } catch (FirstIntervalNotPerfectException e) {
            System.out.println("The first interval is not a perfect consonance!");
            return false;
        } catch (LastIntervalNotPerfectException e) {
            System.out.println("The last interval is not a perfect consonance!");
            return false;
        } catch (ParallelToPerfectException e) {
            System.out.println("One or more perfect consonances are approached by parallel motion!");
            return false;
        }
    }
}
