package seedu.address.model.person.exceptions;

public class SlotOverlapDurationException extends RuntimeException {

    private static final String MESSAGE = "Operation would result in overlapping durations in slots.";

    public SlotOverlapDurationException() {
        super(MESSAGE);
    }
}
