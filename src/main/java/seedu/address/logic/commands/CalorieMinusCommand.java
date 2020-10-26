package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds an Routine to fitNUS.
 */
public class CalorieMinusCommand extends Command {

    public static final String COMMAND_WORD = "calorie_minus";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deducts an amount from today's sum. "
            + "Parameters: "
            + PREFIX_CALORIE + "CALORIE_AMOUNT "
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CALORIE + "1000 ";

    public static final String MESSAGE_SUCCESS = "Calories successfully deducted: %1$s" + "\nToday's calories are: ";
    public static final String MESSAGE_FAILURE = "You are deducting a sum that is greater than today's calories!";

    private final int toDeduct;

    /**
     * Creates an RoutineAddCommand to add the specified {@code Routine}
     */
    public CalorieMinusCommand(int calories) {
        toDeduct = calories;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        int todayCalories = model.getCalories();

        if (todayCalories < toDeduct) {
            throw new CommandException(MESSAGE_FAILURE);
        }
        model.minusCalories(toDeduct);
        todayCalories -= toDeduct;
        return new CommandResult(String.format(MESSAGE_SUCCESS + todayCalories, toDeduct));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CalorieMinusCommand // instanceof handles nulls
                && toDeduct == (((CalorieMinusCommand) other).toDeduct));
    }
}

