package ui;

import exceptions.*;
import model.Composition;
import model.Note;
import model.Validator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Parnassus!");

        while (true) {
            Scanner scn = new Scanner(System.in);
            Composition cmp;
            int voiceSize;

            System.out.printf("Enter the number of notes you want per voice: ");
            cmp = new Composition(voiceSize = scn.nextInt());

            System.out.printf("%d notes is an ideal size for a composition!\n\n", voiceSize);

            pitchFormatWarning();

            System.out.printf("Now you can begin note entry for voice 1.\n");
            noteEntry(cmp, voiceSize);

            System.out.printf("\nNote entry complete.\n");
            System.out.printf("Now Parnassus will validate your composition and output any compositional errors:\n\n");

            if (validate(cmp)) {
                System.out.println("Your counterpoint is flawless! I have nothing more to teach you. Have good day!");
                break;
            } else {
                if (!wrongAnswerPromptTryAgain()) {
                    System.out.println("Sorry to see you go! Please read up on your counterpoint before coming back!");
                    break;
                }
            }
        }
    }

    private static void pitchFormatWarning() {
        System.out.printf("NOTE: Parnassus uses MIDI integers for pitch.\n");
        System.out.printf("60 is middle C, and 61 is middle C#. 0 is used for rest.\n\n");
    }

    private static void noteEntry(Composition cmp, int voiceSize) {
        Scanner scn = new Scanner(System.in);

        for (int v = 0; v < 2; v++) {
            for (int i = 0; i < voiceSize; i++) {
                System.out.printf("Please enter the pitch of voice %d note %d: ", v + 1, i + 1);
                Note n = new Note(scn.nextInt());
                cmp.addNote(v, n);
            }
            if (v == 0) {
                System.out.printf("\nOnto the next voice!\n\n");
            }
        }

    }

    private static boolean validate(Composition cmp) {
        Validator vld = new Validator();

        try {
            vld.validate(cmp);
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

    private static boolean wrongAnswerPromptTryAgain() {
        Scanner scn = new Scanner(System.in);

        System.out.println("Your counterpoint is incorrect. Would you like to try again?");

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
}
