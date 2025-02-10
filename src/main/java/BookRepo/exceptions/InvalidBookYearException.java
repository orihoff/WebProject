package BookRepo.exceptions;

public class InvalidBookYearException extends BookRepoException {
    public InvalidBookYearException(int minYear) {
        super("Year cannot be less than " + minYear + ".");
    }
}
