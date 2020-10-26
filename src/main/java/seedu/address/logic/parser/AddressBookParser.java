package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddHeightCommand;
import seedu.address.logic.commands.AddWeightCommand;
import seedu.address.logic.commands.BmiCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExerciseAddCommand;
import seedu.address.logic.commands.ExerciseDeleteCommand;
import seedu.address.logic.commands.ExerciseEditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindExercisesCommand;
import seedu.address.logic.commands.FindLessonsCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.LessonAddCommand;
import seedu.address.logic.commands.LessonDeleteCommand;
import seedu.address.logic.commands.LessonEditCommand;
import seedu.address.logic.commands.LessonListCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListExercisesCommand;
import seedu.address.logic.commands.TimetableAddLessonCommand;
import seedu.address.logic.commands.TimetableAddRoutineCommand;
import seedu.address.logic.commands.TimetableDeleteSlotCommand;
import seedu.address.logic.commands.routines.FindRoutinesCommand;
import seedu.address.logic.commands.routines.RoutineAddExerciseCommand;
import seedu.address.logic.commands.routines.RoutineCreateCommand;
import seedu.address.logic.commands.routines.RoutineDeleteCommand;
import seedu.address.logic.commands.routines.RoutineDeleteExerciseCommand;
import seedu.address.logic.commands.routines.RoutineListCommand;
import seedu.address.logic.commands.routines.RoutineViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.routines.FindRoutinesCommandParser;
import seedu.address.logic.parser.routines.RoutineAddExerciseCommandParser;
import seedu.address.logic.parser.routines.RoutineCreateCommandParser;
import seedu.address.logic.parser.routines.RoutineDeleteCommandParser;
import seedu.address.logic.parser.routines.RoutineDeleteExerciseCommandParser;
import seedu.address.logic.parser.routines.RoutineViewCommandParser;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case FindExercisesCommand.COMMAND_WORD:
            return new FindExercisesCommandParser().parse(arguments);

        case FindLessonsCommand.COMMAND_WORD:
            return new FindLessonsCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ExerciseAddCommand.COMMAND_WORD:
            return new ExerciseAddCommandParser().parse(arguments);

        case ExerciseDeleteCommand.COMMAND_WORD:
            return new ExerciseDeleteCommandParser().parse(arguments);

        case ExerciseEditCommand.COMMAND_WORD:
            return new ExerciseEditCommandParser().parse(arguments);

        case ListExercisesCommand.COMMAND_WORD:
            return new ListExercisesCommand();

        case RoutineCreateCommand.COMMAND_WORD:
            return new RoutineCreateCommandParser().parse(arguments);

        case RoutineDeleteCommand.COMMAND_WORD:
            return new RoutineDeleteCommandParser().parse(arguments);

        case RoutineViewCommand.COMMAND_WORD:
            return new RoutineViewCommandParser().parse(arguments);

        case RoutineListCommand.COMMAND_WORD:
            return new RoutineListCommand();

        case RoutineAddExerciseCommand.COMMAND_WORD:
            return new RoutineAddExerciseCommandParser().parse(arguments);

        case LessonAddCommand.COMMAND_WORD:
            return new LessonAddCommandParser().parse(arguments);

        case LessonDeleteCommand.COMMAND_WORD:
            return new LessonDeleteCommandParser().parse(arguments);

        case LessonListCommand.COMMAND_WORD:
            return new LessonListCommand();

        case LessonEditCommand.COMMAND_WORD:
            return new LessonEditCommandParser().parse(arguments);

        case TimetableAddRoutineCommand.COMMAND_WORD:
            return new TimetableAddRoutineCommandParser().parse(arguments);

        case TimetableAddLessonCommand.COMMAND_WORD:
            return new TimetableAddLessonCommandParser().parse(arguments);

        case TimetableDeleteSlotCommand.COMMAND_WORD:
            return new TimetableDeleteSlotCommandParser().parse(arguments);

        case AddHeightCommand.COMMAND_WORD:
            return new AddHeightCommandParser().parse(arguments);

        case AddWeightCommand.COMMAND_WORD:
            return new AddWeightCommandParser().parse(arguments);

        case BmiCommand.COMMAND_WORD:
            return new BmiCommand();

        case RoutineDeleteExerciseCommand.COMMAND_WORD:
            return new RoutineDeleteExerciseCommandParser().parse(arguments);

        case FindRoutinesCommand.COMMAND_WORD:
            return new FindRoutinesCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
