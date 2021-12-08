package api.issues.exceptions;

public class IssueNotFoundException extends RuntimeException {
    
    public IssueNotFoundException(Long id) {
        super("Could not find issue " + id);
    }
}
