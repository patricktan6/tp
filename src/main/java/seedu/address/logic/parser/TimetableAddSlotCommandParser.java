package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROUTINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.stream.Stream;

import seedu.address.logic.commands.TimetableAddSlotCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Day;
import seedu.address.model.person.Duration;
import seedu.address.model.person.Name;
import seedu.address.model.person.Routine;
import seedu.address.model.person.Slot;

/**
 * Parses input arguments and creates a new TimetableAddSlotCommand object
 */
public class TimetableAddSlotCommandParser implements Parser<TimetableAddSlotCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RoutineAddExerciseCommand
     * and returns an RoutineAddExerciseCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TimetableAddSlotCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ROUTINE, PREFIX_DAY, PREFIX_TIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_ROUTINE, PREFIX_DAY, PREFIX_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    TimetableAddSlotCommand.MESSAGE_USAGE));
        }

        Name routineName = ParserUtil.parseName(argMultimap.getValue(PREFIX_ROUTINE).get());

        Routine routine = new Routine(routineName);
        Day day = ParserUtil.parseDay(argMultimap.getValue(PREFIX_DAY).get());
        Duration duration = ParserUtil.parseDuration(argMultimap.getValue(PREFIX_TIME).get());

        Slot slot = new Slot(routine, day, duration);

        return new TimetableAddSlotCommand(slot);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
