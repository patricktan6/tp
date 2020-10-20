package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_NAME_CS2106;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_TAG_LECTURE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLessons.GES1028;
import static seedu.address.testutil.TypicalLessons.CS2106;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.LessonBuilder;

public class LessonTest {

    @Test
    public void asObservableLessonList_modifyLessonList_throwsUnsupportedOperationException() {
        Lesson lesson = new LessonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> lesson.getTags().remove(0));
    }

    @Test
    public void isSameLesson() {
        // same object -> returns true
        assertTrue(GES1028.isSameLesson(GES1028));

        // null -> returns false
        assertFalse(GES1028.isSameLesson(null));

        // different name -> returns false
        Lesson editedGES1028 = new LessonBuilder(GES1028).withName(VALID_LESSON_NAME_CS2106).build();
        assertFalse(GES1028.isSameLesson(editedGES1028));

        // same name, different tags -> returns true
        editedGES1028 = new LessonBuilder(GES1028).withTags(VALID_LESSON_TAG_LECTURE).build();
        assertTrue(GES1028.isSameLesson(editedGES1028));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Lesson GES1028Copy = new LessonBuilder(GES1028).build();
        assertTrue(GES1028.equals(GES1028Copy));

        // same object -> returns true
        assertTrue(GES1028.equals(GES1028));

        // null -> returns false
        assertFalse(GES1028.equals(null));

        // different type -> returns false
        assertFalse(GES1028.equals(5));

        // different person -> returns false
        assertFalse(GES1028.equals(CS2106));

        // different name -> returns false
        Lesson editedGES1028 = new LessonBuilder(GES1028).withName(VALID_LESSON_NAME_CS2106).build();
        assertFalse(GES1028.equals(editedGES1028));

        // different tags -> returns false
        editedGES1028 = new LessonBuilder(GES1028).withTags(VALID_LESSON_TAG_LECTURE).build();
        assertFalse(GES1028.equals(editedGES1028));
    }
}
