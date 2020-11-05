package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.FitNus;
import seedu.address.model.person.Exercise;
import seedu.address.model.person.Name;
import seedu.address.model.person.Routine;
import seedu.address.model.tag.Tag;

/**
 * A utility class containing a list of {@code Routine} objects to be used in tests.
 */
public class TypicalRoutines {

    public static final Routine LEG_DAY = new Routine(new Name("Leg Day"));
    public static final Routine UPPER_BODY = new Routine(new Name("Upper Body"));

    private TypicalRoutines() {
    } // prevents instantiation

    /**
     * Returns an {@code FitNus} with all the typical persons.
     */
    public static FitNus getTypicalFitNus() {
        FitNus ab = new FitNus();
        for (Routine routine : getTypicalRoutines()) {
            ab.addRoutine(routine);
        }
        return ab;
    }


    /**
     * Returns an {@code FitNus} with all the typical persons.
     */
    public static FitNus getPopulatedFitNus() {
        FitNus ab = new FitNus();
        Set<Tag> typicalTags = new HashSet<Tag>();
        typicalTags.add(new Tag("Dumbbell"));
        Exercise squats = new Exercise(new Name("Squats"), new HashSet<>());
        Exercise bicep = new Exercise(new Name("Bicep Curls"), typicalTags);

        if (!LEG_DAY.hasExercise(squats)) {
            LEG_DAY.addExercise(squats);
        }

        if (!UPPER_BODY.hasExercise(bicep)) {
            UPPER_BODY.addExercise(bicep);
        }

        ab.addRoutine(LEG_DAY);
        ab.addRoutine(UPPER_BODY);
        return ab;
    }
    public static List<Routine> getTypicalRoutines() {
        return new ArrayList<Routine>(Arrays.asList(LEG_DAY, UPPER_BODY));
    }

}
