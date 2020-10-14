package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.person.Exercise;
import seedu.address.model.person.Lesson;
import seedu.address.model.person.Person;
import seedu.address.model.person.Routine;
import seedu.address.model.person.Slot;
import seedu.address.model.person.Timetable;
import seedu.address.model.person.UniqueExerciseList;
import seedu.address.model.person.UniqueLessonList;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.UniqueRoutineList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueExerciseList exercises;
    private final UniqueRoutineList routines;
    private final UniqueLessonList lessons;
    private final Timetable timetable;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        exercises = new UniqueExerciseList();
        routines = new UniqueRoutineList();
        lessons = new UniqueLessonList();
        timetable = new Timetable();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns true if an exercise with the same identity as {@code exercise} exists in fitNUS.
     */
    public boolean hasExercise(Exercise exercise) {
        requireNonNull(exercise);
        return exercises.contains(exercise);
    }

    /**
     * Returns true if a routine with the same identity as {@code routine} exists in fitNUS.
     */
    public boolean hasRoutine(Routine r) {
        requireNonNull(r);
        return routines.contains(r);
    }

    /**
     * Returns true if a lesson with the same identity as {@code lesson} exists in the timetable.
     */
    public boolean hasLesson(Lesson lesson) {
        requireNonNull(lesson);
        return lessons.contains(lesson);
    }

    /**
     * Returns true if the slot is already occupied in the timetable.
     * @param slot The slot to be checked.
     * @return true if the slot is already occupied in the timetable.
     */
    public boolean hasSlot(Slot slot) {
        requireNonNull(slot);
        return timetable.hasSlot(slot);
    }

    /**
     * Returns true if the slot has overlapping duration with another slot in the timetable.
     * @param slot The slot to be checked.
     * @return true if the slot has overlapping duration with another slot in the timetable.
     */
    public boolean hasOverlappingDurationInSlot(Slot slot) {
        requireNonNull(slot);
        return timetable.hasOverlapDuration(slot);
    }

    /**
     * Adds an exercise to fitNUS.
     * The exercise must not already exist in fitNUS.
     */
    public void addExercise(Exercise e) {
        exercises.add(e);
    }

    /**
     * Adds a lesson to the timetable.
     * The lesson must not already exist in the timetable.
     */
    public void addLesson(Lesson e) {
        lessons.add(e);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    public void addRoutine(Routine routine) {
        routines.add(routine);
    }

    public void addSlotToTimetable(Slot slot) {
        timetable.addSlot(slot);
    }

    public String viewRoutine(int index) {
        return routines.viewRoutine(index);
    }
    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }

    /**
     * Checks the index given is within the bounds of Routine.
     * @param index index that is input by user.
     * @return False if out of bounds.
     */
    public boolean checkBounds(int index) {
        requireNonNull(index);
        return index > 0 && index <= routines.checkSize();
    }

    /**
     * Lists out all of the Routines that fitNUS has.
     * @return String containing all the Routines.
     */
    public String listRoutines() {
        return routines.listRoutines();
    }

    /**
     * Adds an existing Exercise in fitNUS to an existing Routine.
     * @param r Existing Routine.
     * @param e Existing Exercise.
     */
    public void addExerciseToRoutine(Routine r, Exercise e) {
        requireNonNull(r);
        requireNonNull(e);

        Exercise retrievedExercise = exercises.retrieveExercise(e);
        Routine retrievedRoutine = routines.retrieveRoutine(r);
        routines.addExercise(retrievedRoutine, retrievedExercise);
    }
}
