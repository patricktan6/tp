package seedu.address.logic.commands.routines;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.person.RoutineNameContainsKeywordsPredicate;

/**
 * Finds and lists all routines in fitNUS whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindRoutinesCommand extends Command {

    public static final String COMMAND_WORD = "find_routines";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all routines whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " Lower";

    private final RoutineNameContainsKeywordsPredicate predicate;

    public FindRoutinesCommand(RoutineNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredRoutineList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ROUTINES_LISTED_OVERVIEW, model.getFilteredRoutineList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindRoutinesCommand // instanceof handles nulls
                && predicate.equals(((FindRoutinesCommand) other).predicate)); // state check
    }
}
