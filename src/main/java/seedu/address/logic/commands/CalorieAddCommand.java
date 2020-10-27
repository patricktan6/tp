package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds a DailyCalorie to fitNUS.
 */
public class CalorieAddCommand extends Command {

    public static final String COMMAND_WORD = "calorie_add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds your caloric intake to today's sum. "
            + "Parameters: "
            + PREFIX_CALORIE + "CALORIE_AMOUNT "
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CALORIE + "1000 ";

    public static final String MESSAGE_SUCCESS = "Calories successfully added: %1$s" + "\nToday's calories are: ";

    private final int toAdd;

    /**
     * Creates an CalorieAddCommand to add the specified {@code DailyCalorie}
     */
    public CalorieAddCommand(int calories) {
        toAdd = calories;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.addCalories(toAdd);
        int todayCalories = model.getCalories();
        return new CommandResult(String.format(MESSAGE_SUCCESS + todayCalories, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CalorieAddCommand // instanceof handles nulls
                && toAdd == (((CalorieAddCommand) other).toAdd));
    }
}

