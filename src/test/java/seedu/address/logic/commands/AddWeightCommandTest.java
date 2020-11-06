package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.FitNus;
import seedu.address.model.ReadOnlyFitNus;
import seedu.address.model.person.Weight;
import seedu.address.testutil.WeightBuilder;

public class AddWeightCommandTest {

    @Test
    public void constructor_nullWeight_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddWeightCommand(null));
    }

    @Test
    public void execute_weightAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingWeightAdded modelWeightStub = new ModelStubAcceptingWeightAdded();
        Weight validWeight = new WeightBuilder().build();

        CommandResult commandResult = new AddWeightCommand(validWeight).execute(modelWeightStub);

        assertEquals(String.format(AddWeightCommand.MESSAGE_SUCCESS, validWeight), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validWeight), modelWeightStub.weightAdded);
    }

    /**
     * A Model stub that always accept the weight being added.
     */
    private class ModelStubAcceptingWeightAdded extends ModelStub {
        final ArrayList<Weight> weightAdded = new ArrayList<>();

        @Override
        public void addWeight(Weight weight) {
            requireNonNull(weight);
            weightAdded.add(weight);
        }

        @Override
        public ReadOnlyFitNus getFitNus() {
            return new FitNus();
        }
    }

}
