package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROUTINE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Exercise;
import seedu.address.model.person.Routine;

/**
 * Adds an Routine to fitNUS.
 */
public class RoutineAddExerciseCommand extends Command {

    public static final String COMMAND_WORD = "routine_add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an Exercise to a Routine in fitNUS. "
            + "Parameters: "
            + PREFIX_ROUTINE + "ROUTINE_NAME "
            + PREFIX_EMAIL + "EXERCISE_NAME"
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ROUTINE + "Leg Day Session "
            + PREFIX_EMAIL + "Squats ";

    public static final String MESSAGE_SUCCESS = "Exercise added to Routine: %1$s";
    public static final String MESSAGE_MISSING_ROUTINE = "This routine does not exist in fitNUS";
    public static final String MESSAGE_MISSING_EXERCISE = "This exercise does not exist in fitNUS";

    private final Routine routineToAdd;
    private final Exercise exerciseToAdd;

    /**
     * Creates an RoutineAddCommand to add the specified {@code Routine}
     */
    public RoutineAddExerciseCommand(Routine routine, Exercise exercise) {
        requireNonNull(routine);
        routineToAdd = routine;
        exerciseToAdd = exercise;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasRoutine(routineToAdd)) {
            throw new CommandException(MESSAGE_MISSING_ROUTINE);
        } else if (!model.hasExercise(exerciseToAdd)) {
            throw new CommandException(MESSAGE_MISSING_EXERCISE);
        }

        model.addExerciseToRoutine(routineToAdd, exerciseToAdd);
        return new CommandResult(String.format(String.format(MESSAGE_SUCCESS, routineToAdd), exerciseToAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoutineAddExerciseCommand // instanceof handles nulls
                && routineToAdd.equals(((RoutineAddExerciseCommand) other).routineToAdd))
                && exerciseToAdd.equals(((RoutineAddExerciseCommand) other).exerciseToAdd);
    }
}

