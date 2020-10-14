package exceptions;

public class FirstIntervalNotPerfectException extends Exception {
    public FirstIntervalNotPerfectException() {
        System.out.println("The first interval is not a perfect consonance!");
    }
}
