package seedu.address.logic.parser.routines;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.routines.FindRoutinesCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.RoutineNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindExercisesCommand object
 */
public class FindRoutinesCommandParser implements Parser<FindRoutinesCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindExercisesCommand
     * and returns a FindExercisesCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindRoutinesCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindRoutinesCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindRoutinesCommand(new RoutineNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
