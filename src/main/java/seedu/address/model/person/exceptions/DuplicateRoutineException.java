package seedu.address.model.person.exceptions;

public class DuplicateRoutineException extends RuntimeException {
    public DuplicateRoutineException() {
        super("Operation would result in duplicate routines.");
    }
}
