package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.DailyCalorie;
import seedu.address.model.person.Exercise;
import seedu.address.model.person.Lesson;
import seedu.address.model.person.Person;
import seedu.address.model.person.Routine;
import seedu.address.model.person.RoutineNameContainsKeywordsPredicate;
import seedu.address.model.person.Slot;
import seedu.address.model.person.SlotDayPredicate;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Exercise> filteredExercises;
    private final FilteredList<Routine> filteredRoutine;
    private final FilteredList<Lesson> filteredLessons;
    private final FilteredList<Slot> filteredSlots;
    private final FilteredList<DailyCalorie> filteredDailyCalories;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredExercises = new FilteredList<>(this.addressBook.getExerciseList());
        filteredRoutine = new FilteredList<>(this.addressBook.getRoutineList());
        filteredLessons = new FilteredList<>(this.addressBook.getLessonList());
        filteredSlots = new FilteredList<>(this.addressBook.getSlotList());
        filteredDailyCalories = new FilteredList<>(this.addressBook.getDailyCalorieList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void deleteExercise(Exercise target) {
        addressBook.removeExercise(target);
    }

    @Override
    public void deleteLesson(Lesson target) {
        addressBook.removeLesson(target);
    }

    @Override
    public void deleteRoutine(Routine target) {
        addressBook.removeRoutine(target);
    }

    @Override
    public void deleteSlotFromTimetable(Slot target) {
        addressBook.removeSlotFromTimetable(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public void addExercise(Exercise exercise) {
        addressBook.addExercise(exercise);
        updateFilteredExerciseList(PREDICATE_SHOW_ALL_EXERCISES);
    }

    @Override
    public void setExercise(Exercise target, Exercise editedExercise) {
        requireAllNonNull(target, editedExercise);
        addressBook.setExercise(target, editedExercise);
    }


    @Override
    public void addRoutine(Routine routine) {
        addressBook.addRoutine(routine);
        updateFilteredRoutineList(PREDICATE_SHOW_ALL_ROUTINES);
        updateFilteredExerciseList(PREDICATE_SHOW_ALL_EXERCISES);
    }

    @Override
    public void addCalories(int calories) {
        addressBook.addCalories(calories);
        updateFilteredCalorieLog(PREDICATE_SHOW_ALL_LOGS);
    }

    @Override
    public void updateFilteredCalorieLog(Predicate<DailyCalorie> predicate) {
        requireNonNull(predicate);
        filteredDailyCalories.setPredicate(predicate);
    }

    @Override
    public void minusCalories(int calories) {
        addressBook.minusCalories(calories);
    }

    @Override
    public int getCalories() {
        return addressBook.getCalories();
    }

    @Override
    public ObservableList<DailyCalorie> getFilteredDailyCalorie() {
        return filteredDailyCalories;
    }

    @Override
    public boolean hasRoutine(Routine r) {
        requireNonNull(r);
        return addressBook.hasRoutine(r);
    }

    @Override
    public void addExerciseToRoutine(Routine r, Exercise e) {
        addressBook.addExerciseToRoutine(r, e);
        updateFilteredRoutineList(PREDICATE_SHOW_ALL_ROUTINES);
        updateFilteredExerciseList(PREDICATE_SHOW_ALL_EXERCISES);
    }

    @Override
    public void deleteExerciseToRoutine(Routine r, Exercise e) {
        addressBook.deleteExerciseToRoutine(r, e);
        updateFilteredRoutineList(PREDICATE_SHOW_ALL_ROUTINES);
        updateFilteredExerciseList(PREDICATE_SHOW_ALL_EXERCISES);
    }

    @Override
    public boolean hasExercise(Exercise exercise) {
        requireNonNull(exercise);
        return addressBook.hasExercise(exercise);
    }

    @Override
    public String listRoutines() {
        updateFilteredRoutineList(PREDICATE_SHOW_ALL_ROUTINES);
        updateFilteredExerciseList(PREDICATE_SHOW_ALL_EXERCISES);
        return addressBook.listRoutines();
    }

    @Override
    public void addHeight(double height) {
        addressBook.addHeight(height);
    }

    @Override
    public void addWeight(double weight) {
        addressBook.addWeight(weight);
    }

    @Override
    public double getBmi() {
        return addressBook.getBmi();
    }

    /**
     * Adds a Lesson into fitNUS.
     *
     * @param lesson Lesson object that is to be added to fitNUS.
     */
    public void addLesson(Lesson lesson) {
        addressBook.addLesson(lesson);
        updateFilteredLessonList(PREDICATE_SHOW_ALL_LESSONS);
    }

    @Override
    public void setLesson(Lesson target, Lesson editedLesson) {
        requireAllNonNull(target, editedLesson);

        addressBook.setLesson(target, editedLesson);
    }

    @Override
    public boolean hasLesson(Lesson lesson) {
        requireNonNull(lesson);
        return addressBook.hasLesson(lesson);
    }

    @Override
    public boolean hasSlot(Slot slot) {
        requireNonNull(slot);
        return addressBook.hasSlot(slot);
    }

    @Override
    public boolean hasOverlappingSlot(Slot slot) {
        requireNonNull(slot);
        return addressBook.hasOverlappingDurationInSlot(slot);
    }

    @Override
    public void addSlotToTimetable(Slot slot) {
        addressBook.addSlotToTimetable(slot);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public Lesson retrieveLesson(Lesson lesson) {
        return addressBook.retrieveLesson(lesson);
    }

    @Override
    public Routine retrieveRoutine(Routine routine) {
        return addressBook.retrieveRoutine(routine);
    }

    @Override
    public void viewRoutine(Routine r) {
        String argument = r.getName().fullName;
        String[] nameKeywords = argument.split("\\s+");
        filteredRoutine.setPredicate(
                new RoutineNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)
        ));
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Routine> getFilteredRoutineList() {
        return filteredRoutine;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Exercise} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Exercise> getFilteredExerciseList() {
        return filteredExercises;
    }


    /**
     * Returns an unmodifiable view of the list of {@code Lesson} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Lesson> getFilteredLessonList() {
        return filteredLessons;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<DailyCalorie> getFilteredDailyCalorieList() {
        return filteredDailyCalories;
    }

    @Override
    public ObservableList<Slot> getFilteredSlotList() {
        return filteredSlots;
    }

    @Override
    public ObservableList<Slot> getFilteredSlotList(String day) {
        SlotDayPredicate predicate = new SlotDayPredicate(Arrays.asList(day));

        ObservableList<Slot> slotObservableList = this.addressBook.getSlotList();
        FilteredList<Slot> filteredSlotsByDay = new FilteredList<>(slotObservableList);

        requireNonNull(predicate);
        filteredSlotsByDay.setPredicate(predicate);
        return filteredSlotsByDay;
    }

    @Override
    public void updateFilteredRoutineList(Predicate<Routine> predicate) {
        requireNonNull(predicate);
        filteredRoutine.setPredicate(predicate);
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public void updateFilteredExerciseList(Predicate<Exercise> predicate) {
        requireNonNull(predicate);
        filteredExercises.setPredicate(predicate);
    }

    @Override
    public void updateFilteredLessonList(Predicate<Lesson> predicate) {
        requireNonNull(predicate);
        filteredLessons.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
