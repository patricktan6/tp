package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.person.CalorieLog;
import seedu.address.model.person.DailyCalorie;
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
    private double height = Double.NaN;
    private double weight = Double.NaN;
    private final Timetable timetable;
    private final CalorieLog calorieLog;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     * among constructors.
     */
    {
        persons = new UniquePersonList();
        exercises = new UniqueExerciseList();
        routines = new UniqueRoutineList();
        lessons = new UniqueLessonList();
        timetable = new Timetable();
        calorieLog = new CalorieLog();
    }

    public AddressBook() {
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// user-level operations

    /**
     * Adds the height of the user.
     *
     * @param height the height of the user.
     */
    public void addHeight(double height) {
        this.height = height;
    }

    /**
     * Adds the weight of the user.
     *
     * @param weight the weight of the user.
     */
    public void addWeight(double weight) {
        this.weight = weight;
    }

    public double getBmi() {
        return this.weight / Math.pow((this.height / 100.0), 2);
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
     * Replaces the contents of exercise list with {@code exercises}.
     * {@code exercises} must not contain duplicate exercises.
     */
    public void setExercises(List<Exercise> exercises) {
        this.exercises.setExercises(exercises);
    }

    /**
     * Replaces the contents of routines list with {@code routines}.
     * {@code routines} must not contain duplicate routines.
     */
    public void setRoutines(List<Routine> routines) {
        this.routines.setRoutines(routines);
    }

    /**
     * Replaces the contents of lesson list with {@code lessons}.
     * {@code lessons} must not contain duplicate lessons.
     */
    public void setLessons(List<Lesson> lessons) {
        this.lessons.setLessons(lessons);
    }

    /**
     * Replaces the contents of slot list with {@code slots}.
     * {@code slots} must not contain duplicate slots or overlapping slots.
     */
    public void setSlots(List<Slot> slots) {
        timetable.setSlots(slots);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setExercises(newData.getExerciseList());
        setLessons(newData.getLessonList());
        setRoutines(newData.getRoutineList());
        setSlots(newData.getSlotList());
        addCalorieEntries(newData.getDailyCalorieList());
        addHeight(newData.getHeight());
        addWeight(newData.getWeight());
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
     *
     * @param slot The slot to be checked.
     * @return true if the slot is already occupied in the timetable.
     */
    public boolean hasSlot(Slot slot) {
        requireNonNull(slot);
        return timetable.hasSlot(slot);
    }

    /**
     * Returns true if the slot has overlapping duration with another slot in the timetable.
     *
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
     * Replaces the given exercise {@code target} in the list with {@code editedExercise}.
     * {@code target} must exist in FitNUS.
     * The exercise identity of {@code editedExercise} must not be the same as another existing exercise in FitNUS.
     */
    public void setExercise(Exercise target, Exercise editedExercise) {
        requireNonNull(editedExercise);

        exercises.setExercise(target, editedExercise);
        routines.setExercise(target, editedExercise);
    }

    /**
     * Replaces the given lesson {@code target} in the list with {@code editedLesson}.
     * {@code target} must exist in FitNUS.
     * The lesson identity of {@code editedLesson} must not be the same as another existing lesson in FitNUS.
     */
    public void setLesson(Lesson target, Lesson editedLesson) {
        requireNonNull(editedLesson);

        lessons.setLesson(target, editedLesson);
        timetable.setSlot(target, editedLesson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    /**
     * Removes {@code key} from {@code fitNUS}.
     * {@code key} must exist in fitNUS.
     */
    public void removeExercise(Exercise key) {
        exercises.remove(key);
        routines.deleteExercise(key);
    }

    /**
     * Removes {@code key} from {@code fitNUS}.
     * {@code key} must exist in fitNUS.
     */
    public void removeLesson(Lesson key) {
        lessons.remove(key);
        timetable.deleteSlot(key);
    }

    /**
     * Removes {@code key} from {@code fitNUS}.
     * {@code key} must exist in fitNUS.
     */
    public void removeRoutine(Routine key) {
        routines.remove(key);
        timetable.deleteSlot(key);
    }

    /**
     * Removes {@code key} from timetable in {@code fitNUS}.
     * {@code key} must exist in timetable.
     */
    public void removeSlotFromTimetable(Slot key) {
        timetable.deleteSlot(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Routine> getRoutineList() {
        return routines.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Exercise> getExerciseList() {
        return exercises.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Lesson> getLessonList() {
        return lessons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Slot> getSlotList() {
        return timetable.getSlotList();
    }

    @Override
    public ObservableList<DailyCalorie> getDailyCalorieList() {
        return calorieLog.asUnmodifiableObservableList();
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getWeight() {
        return weight;
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
     *
     * @param index index that is input by user.
     * @return False if out of bounds.
     */
    public boolean checkBounds(int index) {
        return index > 0 && index <= routines.checkSize();
    }

    /**
     * Lists out all of the Routines that fitNUS has.
     *
     * @return String containing all the Routines.
     */
    public String listRoutines() {
        return routines.listRoutines();
    }

    /**
     * Adds an existing Exercise in fitNUS to an existing Routine.
     *
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

    /**
     * Adds a Slot to the Timetable in fitNUS.
     *
     * @param slot The slot to be added.
     */
    public void addSlotToTimetable(Slot slot) {
        timetable.addSlot(slot);
    }

    /**
     * Retrieves the Lesson object from UniqueLessonList that the user specified.
     *
     * @param lesson Lesson object that the user wants.
     * @return Lesson object that exists within fitNUS that the user is looking for.
     */
    public Lesson retrieveLesson(Lesson lesson) {
        return lessons.retrieveLesson(lesson);
    }

    /**
     * Retrieves the Routine object from UniqueRoutineList that the user specified.
     *
     * @param routine Routine object that the user wants.
     * @return Routine object that exists within fitNUS that the user is looking for.
     */
    public Routine retrieveRoutine(Routine routine) {
        return routines.retrieveRoutine(routine);
    }

    /**
     * Deletes an existing Exercise in fitNUS from an existing Routine.
     *
     * @param r Existing Routine.
     * @param e Existing Exercise.
     */
    public void deleteExerciseToRoutine(Routine r, Exercise e) {
        requireNonNull(r);
        requireNonNull(e);

        Exercise retrievedExercise = exercises.retrieveExercise(e);
        Routine retrievedRoutine = routines.retrieveRoutine(r);
        routines.deleteExerciseFromRoutine(retrievedRoutine, retrievedExercise);
    }

    /**
     * Adds calories into today's calorie log.
     * @param calories The amount of calories that the user wants to add.
     */
    public void addCalories(int calories) {
        calorieLog.addCalories(calories);
    }

    /**
     * Deducts calories in today's calorie log.
     * @param calories The amount of calories that the user wants to deduct.
     */
    public void minusCalories(int calories) {
        calorieLog.minusCalories(calories);
    }

    /**
     * Retrieves the number of calories for today.
     * @return Number of calories for today.
     */
    public int getCalories() {
        return calorieLog.getCalories();
    }

    /**
     * Checks if calorie log currently contains a certain daily calorie entry.
     * @param dailyCalorie Specific Daily Calorie object that the user wants to find.
     * @return True if calorie log contains what the user is looking for.
     */
    public boolean hasDailyCalorie(DailyCalorie dailyCalorie) {
        requireNonNull(dailyCalorie);
        return calorieLog.contains(dailyCalorie);
    }

    /**
     * Adds all the daily calorie entries into the calorie log.
     * @param entries Collection of all the entries of daily calorie.
     */
    public void addCalorieEntries(List<DailyCalorie> entries) {
        calorieLog.setCalorieLog(entries);
    }
}
