package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddHeightCommand;
import seedu.address.logic.commands.AddWeightCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExerciseAddCommand;
import seedu.address.logic.commands.ExerciseDeleteCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.LessonAddCommand;
import seedu.address.logic.commands.LessonDeleteCommand;
import seedu.address.logic.commands.LessonListCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListExercisesCommand;
import seedu.address.logic.commands.RoutineAddExerciseCommand;
import seedu.address.logic.commands.RoutineCreateCommand;
import seedu.address.logic.commands.RoutineListCommand;
import seedu.address.logic.commands.RoutineViewCommand;
import seedu.address.logic.commands.TimetableAddSlotCommand;
import seedu.address.logic.parser.exceptions.ParseException;

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

        case ListExercisesCommand.COMMAND_WORD:
            return new ListExercisesCommand();

        case RoutineCreateCommand.COMMAND_WORD:
            return new RoutineCreateCommandParser().parse(arguments);

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

        case TimetableAddSlotCommand.COMMAND_WORD:
            return new TimetableAddSlotCommandParser().parse(arguments);

        case AddHeightCommand.COMMAND_WORD:
            return new AddHeightCommandParser().parse(arguments);

        case AddWeightCommand.COMMAND_WORD:
            return new AddWeightCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
