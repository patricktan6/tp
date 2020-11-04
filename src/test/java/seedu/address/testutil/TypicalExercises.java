package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Exercise;
import seedu.address.model.person.Name;

public class TypicalExercises {

    public static final Exercise BENCH_PRESS = new Exercise(new Name("Bench Press"), new HashSet<>());
    public static final Exercise SQUATS = new Exercise(new Name("Squats"), new HashSet<>());

    private TypicalExercises() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical exercises.
     */
    public static AddressBook getTypicalFitNus() {
        AddressBook ab = new AddressBook();
        for (Exercise exercise : getTypicalExercises()) {
            ab.addExercise(exercise);
        }
        return ab;
    }

    public static List<Exercise> getTypicalExercises() {
        return new ArrayList<>(Arrays.asList(BENCH_PRESS, SQUATS));
    }
}
