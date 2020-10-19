package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindExercisesCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ExerciseNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindExercisesCommand object
 */
public class FindExercisesCommandParser implements Parser<FindExercisesCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindExercisesCommand
     * and returns a FindExercisesCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindExercisesCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindExercisesCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindExercisesCommand(new ExerciseNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
