package BookRepo.exceptions;

public class MissingRequiredBookFieldsException extends BookRepoException {
    public MissingRequiredBookFieldsException() {
        super("Title, author, and genre are required fields.");
    }
}
