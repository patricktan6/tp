package seedu.address.model.person.exceptions;

public class DuplicateDailyCalorieException extends RuntimeException {
    public DuplicateDailyCalorieException() {
        super("There is already a log for today's calorie!");
    }

}
