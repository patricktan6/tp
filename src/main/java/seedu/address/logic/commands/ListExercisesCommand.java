package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EXERCISES;

import seedu.address.model.Model;

/**
 * Lists all exercises in fitNUS to the user.
 */
public class ListExercisesCommand extends Command {

    public static final String COMMAND_WORD = "list_exercises";

    public static final String MESSAGE_SUCCESS = "Listed all exercises";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredExerciseList(PREDICATE_SHOW_ALL_EXERCISES);
        model.updateFilteredPersonList(unused -> false);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
