package BookRepo.exceptions;

public class ExceedTitleLengthException extends BookRepoException {
    public ExceedTitleLengthException(int maxCharacters) {
        super("Title cannot exceed " + maxCharacters + " characters.");
    }
}
