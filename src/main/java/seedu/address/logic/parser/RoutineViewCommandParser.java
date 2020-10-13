package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.RoutineCreateCommand;
import seedu.address.logic.commands.RoutineViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Routine;

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
