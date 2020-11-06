package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.FitNus;
import seedu.address.model.ReadOnlyFitNus;
import seedu.address.model.person.Height;
import seedu.address.testutil.HeightBuilder;

public class AddHeightCommandTest {

    @Test
    public void constructor_nullHeight_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddHeightCommand(null));
    }

    @Test
    public void execute_heightAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingHeightAdded modelHeightStub = new ModelStubAcceptingHeightAdded();
        Height validHeight = new HeightBuilder().build();

        CommandResult commandResult = new AddHeightCommand(validHeight).execute(modelHeightStub);

        assertEquals(String.format(AddHeightCommand.MESSAGE_SUCCESS, validHeight), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validHeight), modelHeightStub.heightAdded);
    }

    /**
     * A Model stub that always accept the height being added.
     */
    private class ModelStubAcceptingHeightAdded extends ModelStub {
        final ArrayList<Height> heightAdded = new ArrayList<>();

        @Override
        public void addHeight(Height height) {
            requireNonNull(height);
            heightAdded.add(height);
        }

        @Override
        public ReadOnlyFitNus getFitNus() {
            return new FitNus();
        }
    }

}
