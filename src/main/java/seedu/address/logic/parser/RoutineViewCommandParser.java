package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.stream.Stream;

import seedu.address.logic.commands.RoutineViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class RoutineViewCommandParser implements Parser<RoutineViewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RoutineViewCommand
     * and returns an RoutineViewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RoutineViewCommand parse(String args) throws ParseException {

        try {
            int index = Integer.parseInt(args.substring(1));
            return new RoutineViewCommand(index);
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RoutineViewCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
