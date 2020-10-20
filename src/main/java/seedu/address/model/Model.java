package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Exercise;
import seedu.address.model.person.Lesson;
import seedu.address.model.person.Person;
import seedu.address.model.person.Routine;
import seedu.address.model.person.Slot;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Exercise> PREDICATE_SHOW_ALL_EXERCISES = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Routine> PREDICATE_SHOW_ALL_ROUTINES = unused -> true;

    Predicate<Lesson> PREDICATE_SHOW_ALL_LESSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Deletes the given exercise.
     * The exercise must exist in fitNUS.
     */
    void deleteExercise(Exercise target);

    /**
     * Deletes the given lesson.
     * The lesson must exist in fitNUS.
     */
    void deleteLesson(Lesson target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns an unmodifiable view of the filtered exercise list
     */
    ObservableList<Exercise> getFilteredExerciseList();

    /** Returns an unmodifiable view of the filtered lesson list */
    ObservableList<Lesson> getFilteredLessonList();

    /**
     * Returns an unmodifiable view of the filtered slot list
     */
    ObservableList<Slot> getFilteredSlotList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Updates the filter of the filtered exercise list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredExerciseList(Predicate<Exercise> predicate);

    /**
     * Updates the filter of the filtered lesson list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredLessonList(Predicate<Lesson> predicate);

    /**
     * Returns true if a exercise with the same details as {@code exercise} exists in fitNUS.
     */
    boolean hasExercise(Exercise exercise);

    /**
     * Adds the given exercise.
     * {@code exercise} must not already exist in fitNUS.
     */
    void addExercise(Exercise exercise);

    /**
     * Adds the given routine.
     * {@code routine} must not already exist in fitNUS.
     */
    void addRoutine(Routine routine);

    /**
     * Returns true if a routine with the same identity as {@code routine} exists in fitNUS.
     */
    boolean hasRoutine(Routine r);

    /**
     * Adds the given exercise to the given routine.
     * {@code r} must already exist in fitNUS.
     * {@code e} must already exist in fitNUS.
     */
    void addExerciseToRoutine(Routine r, Exercise e);

    /**
     * List out all the routines in fitNUS.
     */
    String listRoutines();

    /**
     * Updates the filter of the filtered routine list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredRoutineList(Predicate<Routine> predicate);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deleteRoutine(Routine target);

    boolean hasSlot(Slot slot);
    boolean hasOverlappingSlot(Slot slot);
    void addSlotToTimetable(Slot slot);
    void deleteSlotFromTimetable(Slot target);

    void addHeight(int height);

    void addWeight(int weight);

    /**
     * Returns true if a lesson with the same details as {@code lesson} exists in timetable.
     */
    boolean hasLesson(Lesson lesson);

    /**
     * Adds the given lesson.
     * {@code lesson} must not already exist in timetable.
     */
    void addLesson(Lesson lesson);

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Routine> getFilteredRoutineList();

    /**
     * Retrieves the Lesson object from UniqueLessonList that the user specified.
     * @param lesson Lesson object that the user wants.
     * @return Lesson object that exists within fitNUS that the user is looking for.
     */
    Lesson retrieveLesson(Lesson lesson);

    /**
     * Retrieves the Routine object from UniqueRoutineList that the user specified.
     * @param routine Routine object that the user wants.
     * @return Routine object that exists within fitNUS that the user is looking for.
     */
    Routine retrieveRoutine(Routine routine);

    /**
     * Deletes the specified exercise from an existing routine.
     * {@code routine} must already exist within fitNUS.
     * {@code exercise} must already exist within fitNUS.
     */
    void deleteExerciseToRoutine(Routine routine, Exercise exercise);

    void viewRoutine(Routine routineToView);
}
