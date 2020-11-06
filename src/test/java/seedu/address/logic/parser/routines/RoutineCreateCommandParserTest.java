package seedu.address.logic.parser.routines;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalRoutines.LEG_DAY;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.routines.RoutineCreateCommand;

public class RoutineCreateCommandParserTest {
    private final RoutineCreateCommandParser parser = new RoutineCreateCommandParser();

    @Test
    public void parse_allRoutineFieldsPresent_success() {

        // whitespace only preamble
        assertParseSuccess(parser, " r/Leg Day", new RoutineCreateCommand(LEG_DAY));
    }

    @Test
    public void parse_compulsoryRoutineFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RoutineCreateCommand.MESSAGE_USAGE);

        // missing routine prefix
        assertParseFailure(parser, "Leg Day", expectedMessage);
    }

    @Test
    public void parse_invalidRoutineValue_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RoutineCreateCommand.MESSAGE_USAGE);

        // invalid Routine
        assertParseFailure(parser, PREAMBLE_WHITESPACE + LEG_DAY + "&", expectedMessage);
        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + "Leg Day",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RoutineCreateCommand.MESSAGE_USAGE));
    }
}
