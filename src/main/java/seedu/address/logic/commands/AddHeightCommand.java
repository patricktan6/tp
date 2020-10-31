package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEIGHT;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class AddHeightCommand extends Command {
    public static final String COMMAND_WORD = "height";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds user's height (in cm) to fitNUS. "
            + "Parameters: "
            + PREFIX_HEIGHT + "HEIGHT\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_HEIGHT + "172.5";

    public static final String MESSAGE_SUCCESS = "Height added: %.2f cm";
    public static final String MESSAGE_INVALID_HEIGHT = "This is not a valid height";

    private final double height;

    /**
     * Creates an AddHeightCommand to add the specified height in centimetres.
     */
    public AddHeightCommand(double height) {
        requireNonNull(height);
        this.height = height;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (height < 0) {
            throw new CommandException(MESSAGE_INVALID_HEIGHT);
        }

        model.addHeight(height);
        return new CommandResult(String.format(MESSAGE_SUCCESS, height));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddHeightCommand // instanceof handles nulls
                && this.height == ((AddHeightCommand) other).height);
    }
}
