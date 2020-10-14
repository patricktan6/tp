package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROUTINE;

import java.util.stream.Stream;

import seedu.address.logic.commands.RoutineCreateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Routine;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class RoutineCreateCommandParser implements Parser<RoutineCreateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RoutineCreateCommand
     * and returns an RoutineCreateCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RoutineCreateCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ROUTINE);

        if (!arePrefixesPresent(argMultimap, PREFIX_ROUTINE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RoutineCreateCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_ROUTINE).get());

        Routine routine = new Routine(name);

        return new RoutineCreateCommand(routine);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
