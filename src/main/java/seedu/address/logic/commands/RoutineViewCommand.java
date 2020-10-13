package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ROUTINE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Routine;

/**
 * Adds an Routine to fitNUS.
 */
public class RoutineViewCommand extends Command {

    public static final String COMMAND_WORD = "routine_view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views a routine in fitNUS. "
            + "Parameters: "
            +"INDEX "
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + "3 ";

    public static final String MESSAGE_SUCCESS = "Routine requested: %1$s";
    public static final String MESSAGE_OUT_OF_BOUNDS_ROUTINE = "This routine index is out of bounds!";

    private final int toView;

    /**
     * Creates an RoutineAddCommand to add the specified {@code Routine}
     */
    public RoutineViewCommand(int index) {
        requireNonNull(index);
        toView = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.checkBounds(toView)) {
            throw new CommandException(MESSAGE_OUT_OF_BOUNDS_ROUTINE);
        } else {
            String routineMessage = model.viewRoutine(toView);
            return new CommandResult(String.format(MESSAGE_SUCCESS, routineMessage));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoutineViewCommand // instanceof handles nulls
        );
    }
}

