package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Exercise;
import seedu.address.model.person.Lesson;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

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
     * Returns an unmodifiable view of the lessons list.
     * This list will not contain any duplicate lessons.
     */
    ObservableList<Lesson> getLessonList();

}
