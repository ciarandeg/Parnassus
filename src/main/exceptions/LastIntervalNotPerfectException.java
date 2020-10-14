package exceptions;

public class LastIntervalNotPerfectException extends Exception {
    public LastIntervalNotPerfectException() {
        System.out.println("The last interval is not a perfect consonance!");
    }
}
