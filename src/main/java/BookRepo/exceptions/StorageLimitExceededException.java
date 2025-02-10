package BookRepo.exceptions;

public class StorageLimitExceededException extends BookRepoException {
    public StorageLimitExceededException() {
        super("Cannot save more than 100 books.");
    }
}
