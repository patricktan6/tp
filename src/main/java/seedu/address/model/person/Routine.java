package seedu.address.model.person;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Routine extends Activity {

    private final Set<Exercise> exercises;

    /**
     * Every field must be present and not null.
     */
    public Routine(Name name) {
        super(name);
        this.exercises = new HashSet<>();
    }

    public Set<Exercise> getExercises() {
        return this.exercises;
    }

    public void addExercise(Exercise newExercise) {
        this.exercises.add(newExercise);
    }

    public void deleteExercise(Exercise oldExercise) {
        this.exercises.remove(oldExercise);
    }

    /**
     * Returns true if both routines of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two routines.
     */
    @Override
    public boolean isSameActivity(Activity otherActivity) {
        if (otherActivity == this) {
            return true;
        }

        if (!(otherActivity instanceof Routine)) {
            return false;
        }

        Routine otherRoutine = (Routine) otherActivity;
        return otherRoutine.getName().equals(getName());
    }


    /**
     * Returns true if both routines have the same identity and data fields.
     * This defines a stronger notion of equality between two routines.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Routine)) {
            return false;
        }

        Routine otherPerson = (Routine) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getExercises().equals(getExercises());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, exercises);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());
        for (Exercise exercise : exercises) {
            builder.append("\n");
            builder.append(exercise.toString());
        }
        return builder.toString();
    }

}
