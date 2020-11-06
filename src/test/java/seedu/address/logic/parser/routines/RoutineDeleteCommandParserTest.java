package seedu.address.logic.parser.routines;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.routines.RoutineDeleteCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the RoutineDeleteCommand code. The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class RoutineDeleteCommandParserTest {
    private final RoutineDeleteCommandParser parser = new RoutineDeleteCommandParser();

    @Test
    public void parse_validArgs_returnsRoutineDeleteCommand() {
        assertParseSuccess(parser, "1", new RoutineDeleteCommand(Index.fromOneBased(1)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(
                parser, "a", MESSAGE_INVALID_INDEX);
    }
    @Test
    public void parse_missingArgs_throwsParseException() {
        String expectedMessage =
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RoutineDeleteCommand.MESSAGE_USAGE);
        assertParseFailure(
                parser, "", expectedMessage);
    }

}
