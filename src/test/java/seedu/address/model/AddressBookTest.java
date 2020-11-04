package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_TAG_EASY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLessons.GES1028;
import static seedu.address.testutil.TypicalLessons.getTypicalFitNus;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Body;
import seedu.address.model.person.DailyCalorie;
import seedu.address.model.person.Exercise;
import seedu.address.model.person.Lesson;
import seedu.address.model.person.Routine;
import seedu.address.model.person.Slot;
import seedu.address.model.person.exceptions.DuplicateLessonException;
import seedu.address.testutil.LessonBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getLessonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyFitNus_replacesData() {
        AddressBook newData = getTypicalFitNus();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateLessons_throwsDuplicateLessonException() {
        // Two lessons with the same identity fields
        Lesson editedGes1028 = new LessonBuilder(GES1028).withTags(VALID_LESSON_TAG_EASY).build();
        List<Lesson> newLessons = Arrays.asList(GES1028, editedGes1028);
        AddressBookStub newData = new AddressBookStub(newLessons);

        assertThrows(DuplicateLessonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasLesson_nullLesson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasLesson(null));
    }

    @Test
    public void hasLesson_lessonNotInFitNus_returnsFalse() {
        assertFalse(addressBook.hasLesson(GES1028));
    }

    @Test
    public void hasLesson_lessonInFitNus_returnsTrue() {
        addressBook.addLesson(GES1028);
        assertTrue(addressBook.hasLesson(GES1028));
    }

    @Test
    public void hasLesson_lessonWithSameIdentityFieldsInFitNus_returnsTrue() {
        addressBook.addLesson(GES1028);
        Lesson editedGes1028 = new LessonBuilder(GES1028).withTags(VALID_LESSON_TAG_EASY).build();
        assertTrue(addressBook.hasLesson(editedGes1028));
    }

    @Test
    public void getLessonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getLessonList().remove(0));
    }

    /**
     * A stub ReadOnlyFitNus whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyFitNus {
        private final ObservableList<Exercise> exercises = FXCollections.observableArrayList();
        private final ObservableList<Lesson> lessons = FXCollections.observableArrayList();
        private final ObservableList<Routine> routines = FXCollections.observableArrayList();
        private final ObservableList<Slot> slots = FXCollections.observableArrayList();
        private final ObservableList<DailyCalorie> calorieLog = FXCollections.observableArrayList();
        private final ObservableList<Body> body = FXCollections.observableArrayList();

        AddressBookStub(Collection<Lesson> lessons) {
            this.lessons.setAll(lessons);
        }

        @Override
        public ObservableList<Exercise> getExerciseList() {
            return exercises;
        }

        @Override
        public ObservableList<Routine> getRoutineList() {
            return routines;
        }

        @Override
        public ObservableList<Lesson> getLessonList() {
            return lessons;
        }

        @Override
        public ObservableList<Slot> getSlotList() {
            return slots;
        }

        @Override
        public ObservableList<DailyCalorie> getDailyCalorieList() {
            return calorieLog;
        }

        @Override
        public ObservableList<Body> getBody() {
            return body;
        }
    }

}
