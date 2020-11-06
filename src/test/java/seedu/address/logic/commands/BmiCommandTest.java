package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.FitNus;
import seedu.address.model.ReadOnlyFitNus;

public class BmiCommandTest {

    @Test
    public void execute_bmiViewedSuccessful() throws Exception {
        ModelStubWithBmi modelBmiStub = new ModelStubWithBmi();

        CommandResult commandResult = new BmiCommand().execute(modelBmiStub);

        assertEquals(String.format(BmiCommand.MESSAGE_SUCCESS, modelBmiStub.getBmi()),
                commandResult.getFeedbackToUser());

    }

    @Test
    public void execute_bmiNotAvailable_throwCommandException() throws Exception {
        ModelStubWithInvalidBmi modelInvalidBmiStub = new ModelStubWithInvalidBmi();

        BmiCommand bmiCommand = new BmiCommand();

        assertThrows(CommandException.class, BmiCommand.MESSAGE_INVALID_BMI, () ->
                bmiCommand.execute(modelInvalidBmiStub));
    }

    /**
     * A Model stub that has bmi.
     */
    private class ModelStubWithBmi extends ModelStub {
        final double bmi = 20.6;

        @Override
        public double getBmi() {
            return this.bmi;
        }

        @Override
        public ReadOnlyFitNus getFitNus() {
            return new FitNus();
        }
    }

    /**
     * A Model stub that has invalid bmi.
     */
    private class ModelStubWithInvalidBmi extends ModelStub {
        final double bmi = Double.NaN;

        @Override
        public double getBmi() {
            return this.bmi;
        }

        @Override
        public ReadOnlyFitNus getFitNus() {
            return new FitNus();
        }
    }

}
