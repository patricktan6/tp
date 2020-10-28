package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class AddWeightCommand extends Command {
    public static final String COMMAND_WORD = "weight";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds user's weight (in kg) to fitNUS. "
            + "Parameters: "
            + PREFIX_WEIGHT + "WEIGHT\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_WEIGHT + "68";

    public static final String MESSAGE_SUCCESS = "Weight added: %d kg";
    public static final String MESSAGE_INVALID_WEIGHT = "This is not a valid weight";

    private final int weight;

    /**
     * Creates an ExerciseAddCommand to add the specified {@code Exercise}
     */
    public AddWeightCommand(int weight) {
        requireNonNull(weight);
        this.weight = weight;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (weight < 0) {
            throw new CommandException(MESSAGE_INVALID_WEIGHT);
        }

        model.addWeight(weight);
        return new CommandResult(String.format(MESSAGE_SUCCESS, weight));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddWeightCommand // instanceof handles nulls
                && this.weight == ((AddWeightCommand) other).weight);
    }
}
