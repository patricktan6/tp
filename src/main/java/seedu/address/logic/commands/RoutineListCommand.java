package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class RoutineListCommand extends Command {

    public static final String COMMAND_WORD = "routine_list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows all routines in fitNUS.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_LIST_MESSAGE = "All routines in fitNUS: ";

    @Override
    public CommandResult execute(Model model) {

        String routineList = model.listRoutines();
        return new CommandResult(SHOWING_LIST_MESSAGE + "\n" + routineList);
    }
}
