package seedu.address.logic.parser.routines;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROUTINE;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.routines.RoutineDeleteExerciseCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Exercise;
import seedu.address.model.person.Name;
import seedu.address.model.person.Routine;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new RoutineDeleteExerciseCommand object
 */
public class RoutineDeleteExerciseCommandParser implements Parser<RoutineDeleteExerciseCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RoutineDeleteExerciseCommand
     * and returns a RoutineDeleteExerciseCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RoutineDeleteExerciseCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ROUTINE, PREFIX_EMAIL);

        if (!arePrefixesPresent(argMultimap, PREFIX_ROUTINE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RoutineDeleteExerciseCommand.MESSAGE_USAGE));
        }

        Name routineName = ParserUtil.parseName(argMultimap.getValue(PREFIX_ROUTINE).get());

        Name exerciseName = ParserUtil.parseName(argMultimap.getValue(PREFIX_EMAIL).get());
        Routine routine = new Routine(routineName);
        Set<Tag> tagList = new HashSet<>();
        Exercise exercise = new Exercise(exerciseName, tagList);

        return new RoutineDeleteExerciseCommand(routine, exercise);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
