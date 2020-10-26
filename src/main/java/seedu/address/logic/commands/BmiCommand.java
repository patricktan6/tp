package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class BmiCommand extends Command {
    public static final String COMMAND_WORD = "bmi";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views the user's BMI. "
            + "\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Your BMI is %.2f.";
    public static final String MESSAGE_INVALID_BMI = "Your BMI is not available. "
            + "Please make sure that you have updated your height and weight.";

    /**
     * Creates a BmiCommand to get the BMI Index.
     */
    public BmiCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        double bmi = model.getBmi();
        if (Double.isNaN(bmi)) {
            throw new CommandException(MESSAGE_INVALID_BMI);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, bmi));
    }

    @Override
    public boolean equals(Object other) {
        return other == this; // short circuit if same object
    }

}
