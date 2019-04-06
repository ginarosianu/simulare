package Repository;

public class RepositoryException extends RuntimeException {
    public RepositoryException(String message) {
        super ("Repository exception ||| " + message);
    }
}
