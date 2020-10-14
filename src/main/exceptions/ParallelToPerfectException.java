package exceptions;

public class ParallelToPerfectException extends Exception {
    public ParallelToPerfectException() {
        System.out.println("One or more perfect consonances are approached by parallel motion!");
    }
}
