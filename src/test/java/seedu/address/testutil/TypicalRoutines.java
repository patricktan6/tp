package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Exercise;
import seedu.address.model.person.Name;
import seedu.address.model.person.Routine;
import seedu.address.model.tag.Tag;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalRoutines {

    public static final Routine LEG_DAY = new Routine(new Name("Leg Day"));
    public static final Routine UPPER_BODY = new Routine(new Name("Upper Body"));

    private TypicalRoutines() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Routine routine : getTypicalRoutines()) {
            ab.addRoutine(routine);
        }
        return ab;
    }

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getPopulatedAddressBook() {
        AddressBook ab = new AddressBook();
        for (Routine routine : getRoutinesWithExercise()) {
            ab.addRoutine(routine);
        }
        return ab;
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
