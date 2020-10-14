package exceptions;

public class VoiceNotFullException extends Exception {
    public VoiceNotFullException() {
        System.out.println("One or more voices are not filled with notes!");
    }
}
