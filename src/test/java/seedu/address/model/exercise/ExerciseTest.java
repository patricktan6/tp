package seedu.address.model.exercise;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalExercises.BENCH_PRESS;
import static seedu.address.testutil.TypicalExercises.SQUATS;

import org.junit.jupiter.api.Test;

public class ExerciseTest {

    @Test
    public void isSameExercise() {
        // same object -> returns true
        assertTrue(BENCH_PRESS.isSameExercise(BENCH_PRESS));
        assertTrue(SQUATS.isSameExercise(SQUATS));

        // null -> returns false
        assertFalse(BENCH_PRESS.isSameExercise(null));
        assertFalse(SQUATS.isSameExercise(null));
    }
}
