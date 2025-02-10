package BookRepo.exceptions;

public class StorageException extends BookRepoException {
    public StorageException(String message, Throwable cause) {
        super(message);
        initCause(cause);
    }
}
