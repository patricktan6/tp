package seedu.address.logic.commands.routines;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ROUTINES;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class RoutineListCommand extends Command {

    public static final String COMMAND_WORD = "list_routines";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows all routines in fitNUS.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_LIST_MESSAGE = "All routines in fitNUS: ";

    @Override
    public CommandResult execute(Model model) {

        model.updateFilteredRoutineList(PREDICATE_SHOW_ALL_ROUTINES);
        return new CommandResult(SHOWING_LIST_MESSAGE);
    }
}
