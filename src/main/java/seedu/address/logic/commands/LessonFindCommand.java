package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.LessonNameContainsKeywordsPredicate;

/**
 * Finds and lists all lessons in fitNUS whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class LessonFindCommand extends Command {

    public static final String COMMAND_WORD = "lesson_find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all lessons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " GES1035 geq cs";

    private final LessonNameContainsKeywordsPredicate predicate;

    public LessonFindCommand(LessonNameContainsKeywordsPredicate predicate) {
        assert(predicate != null);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredLessonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_LESSONS_LISTED_OVERVIEW, model.getFilteredLessonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LessonFindCommand // instanceof handles nulls
                && predicate.equals(((LessonFindCommand) other).predicate)); // state check
    }
}
