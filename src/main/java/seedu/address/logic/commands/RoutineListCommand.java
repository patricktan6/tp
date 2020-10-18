package seedu.address.logic.commands;

import seedu.address.model.Model;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ROUTINES;

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
        model.updateFilteredExerciseList(unused -> false);
        model.updateFilteredPersonList(unused -> false);
        model.updateFilteredRoutineList(PREDICATE_SHOW_ALL_ROUTINES);
        return new CommandResult(SHOWING_LIST_MESSAGE + "\n" + routineList);
    }
}
