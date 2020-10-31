package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_NAME_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_NAME_CS2106;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_TAG_EASY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_TAG_LECTURE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Lesson;

/**
 * A utility class containing a list of {@code Lesson} objects to be used in tests.
 */
public class TypicalLessons {

    public static final Lesson GES1028 = new LessonBuilder().withName("GES1028")
            .withTags("auditorium").build();
    public static final Lesson GEH1030 = new LessonBuilder().withName("GEH1030")
            .withTags("music", "auditorium").build();
    public static final Lesson GET1011 = new LessonBuilder().withName("GET1011").build();
    public static final Lesson GER1000 = new LessonBuilder().withName("GER1000")
            .withTags("auditorium").build();
    public static final Lesson GEQ1000 = new LessonBuilder().withName("GEQ1000").build();
    public static final Lesson IS1103 = new LessonBuilder().withName("IS1103").build();
    public static final Lesson PC1141 = new LessonBuilder().withName("PC1141").build();

    // Manually added
    public static final Lesson MA1101R = new LessonBuilder().withName("MA1101R").build();
    public static final Lesson MA1521 = new LessonBuilder().withName("MA1521").build();

    // Manually added - Lesson's details found in {@code CommandTestUtil}
    public static final Lesson CS2030 = new LessonBuilder().withName(VALID_LESSON_NAME_CS2030)
            .withTags(VALID_LESSON_TAG_EASY).build();
    public static final Lesson CS2106 = new LessonBuilder().withName(VALID_LESSON_NAME_CS2106)
            .withTags(VALID_LESSON_TAG_LECTURE, VALID_LESSON_TAG_EASY).build();

    private TypicalLessons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical lessons.
     */
    public static AddressBook getTypicalFitNus() {
        AddressBook ab = new AddressBook();
        for (Lesson lesson : getTypicalLessons()) {
            ab.addLesson(lesson);
        }
        return ab;
    }

    public static List<Lesson> getTypicalLessons() {
        return new ArrayList<>(Arrays.asList(GES1028, GEH1030, GET1011, GER1000, GEQ1000, IS1103, PC1141));
    }
}
