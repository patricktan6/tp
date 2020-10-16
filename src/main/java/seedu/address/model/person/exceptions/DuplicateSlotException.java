package seedu.address.model.person.exceptions;

public class DuplicateSlotException extends RuntimeException {

    private static final String MESSAGE = "Operation would result in duplicate slots.";

    public DuplicateSlotException() {
        super(MESSAGE);
    }
}
