package seedu.address.model.slot.exceptions;

public class SlotOverlapDurationException extends RuntimeException {

    private static final String MESSAGE = "Operation would result in overlapping durations in your timetable.";

    public SlotOverlapDurationException() {
        super(MESSAGE);
    }
}
