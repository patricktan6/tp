package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.DailyCalorie;
import seedu.address.model.person.Exercise;
import seedu.address.model.person.Lesson;
import seedu.address.model.person.Person;
import seedu.address.model.person.Routine;
import seedu.address.model.person.Slot;

/**
 * Unmodifiable view of fitNUS
 */
public interface ReadOnlyFitNus {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the exercises list.
     * This list will not contain any duplicate exercises.
     */
    ObservableList<Exercise> getExerciseList();

    /**
     * Returns an unmodifiable view of the routines list.
     * This list will not contain any duplicate routines.
     */
    ObservableList<Routine> getRoutineList();

    /**
     * Returns an unmodifiable view of the lessons list.
     * This list will not contain any duplicate lessons.
     */
    ObservableList<Lesson> getLessonList();

    /**
     * Returns an unmodifiable view of the slots list.
     * This list will not contain any duplicate slots.
     */
    ObservableList<Slot> getSlotList();

    /**
     * Returns an unmodifiable view of the daily calorie list.
     * This list will not contain any duplicate daily calorie entries.
     */
    ObservableList<DailyCalorie> getDailyCalorieList();

    /**
     * Returns the user's height.
     */
    double getHeight();

    /**
     * Returns the user's weight.
     */
    double getWeight();
}
