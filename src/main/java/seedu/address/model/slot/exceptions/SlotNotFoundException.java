package seedu.address.model.slot.exceptions;

public class SlotNotFoundException extends RuntimeException {

    private static final String MESSAGE = "The slot does not exist in the timetable.";

    public SlotNotFoundException() {
        super(MESSAGE);
    }
}
