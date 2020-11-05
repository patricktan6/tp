package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.FitNus;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.person.Name;
import seedu.address.model.routine.Routine;
import seedu.address.model.tag.Tag;

/**
 * A utility class containing a list of {@code Routine} objects to be used in tests.
 */
public class TypicalRoutines {

    public static final Routine LEG_DAY = new Routine(new Name("Leg Day"));
    public static final Routine UPPER_BODY = new Routine(new Name("Upper Body"));

    private TypicalRoutines() {} // prevents instantiation

    /**
     * Returns an {@code FitNus} with all the typical routines.
     */
    public static FitNus getTypicalFitNus() {
        FitNus fn = new FitNus();
        for (Routine routine : getTypicalRoutines()) {
            fn.addRoutine(routine);
        }
        return fn;
    }

    /**
     * Returns an {@code FitNus} with all the typical routines.
     */
    public static FitNus getPopulatedFitNus() {
        FitNus fn = new FitNus();
        for (Routine routine : getRoutinesWithExercise()) {
            fn.addRoutine(routine);
        }
        return fn;
    }
    public static List<Routine> getTypicalRoutines() {
        return new ArrayList<Routine>(Arrays.asList(LEG_DAY, UPPER_BODY));
    }
    public static List<Routine> getRoutinesWithExercise() {
        LEG_DAY.addExercise(new Exercise(new Name("Squats"), new HashSet<>()));
        Set<Tag> typicalTags = new HashSet<Tag>();
        typicalTags.add(new Tag("Dumbbell exercise"));
        UPPER_BODY.addExercise(new Exercise(new Name("Bicep Curls"), typicalTags));
        return new ArrayList<Routine>(Arrays.asList(LEG_DAY, UPPER_BODY));
    }
}
