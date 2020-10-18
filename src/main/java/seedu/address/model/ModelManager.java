package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Exercise;
import seedu.address.model.person.Lesson;
import seedu.address.model.person.Person;
import seedu.address.model.person.Routine;
import seedu.address.model.person.Slot;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Exercise> filteredExercises;
    private final FilteredList<Lesson> filteredLessons;

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
        filteredLessons = new FilteredList<>(this.addressBook.getLessonList());
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
    public void addRoutine(Routine routine) {
        addressBook.addRoutine(routine);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public boolean hasRoutine(Routine r) {
        requireNonNull(r);
        return addressBook.hasRoutine(r);
    }

    @Override
    public void addExerciseToRoutine(Routine r, Exercise e) {
        addressBook.addExerciseToRoutine(r, e);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public boolean hasExercise(Exercise exercise) {
        requireNonNull(exercise);
        return addressBook.hasExercise(exercise);
    }

    @Override
    public String viewRoutine(int index) {
        requireNonNull(index);
        return addressBook.viewRoutine(index);
    }

    @Override
    public boolean checkBounds(int index) {
        requireNonNull(index);
        return addressBook.checkBounds(index);
    }

    @Override
    public String listRoutines() {
        return addressBook.listRoutines();
    }

    @Override
    public void addHeight(int height) {
        addressBook.addHeight(height);
    }

    @Override
    public void addWeight(int weight) {
        addressBook.addWeight(weight);
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


    //=========== Filtered Person List Accessors =============================================================

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
