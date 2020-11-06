package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_TAG_EASY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalExercises.BENCH_PRESS;
import static seedu.address.testutil.TypicalLessons.GES1028;
import static seedu.address.testutil.TypicalLessons.getTypicalFitNus;
import static seedu.address.testutil.TypicalRoutines.LEG_DAY;
import static seedu.address.testutil.TypicalRoutines.UPPER_BODY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.calorie.DailyCalorie;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.exceptions.DuplicateLessonException;
import seedu.address.model.person.Body;
import seedu.address.model.person.Height;
import seedu.address.model.person.Name;
import seedu.address.model.person.Weight;
import seedu.address.model.routine.Routine;
import seedu.address.model.routine.exceptions.DuplicateRoutineException;
import seedu.address.model.slot.Slot;
import seedu.address.testutil.LessonBuilder;

public class FitNusTest {

    private final FitNus fitNus = new FitNus();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), fitNus.getLessonList());
        assertEquals(Collections.emptyList(), fitNus.getExerciseList());
        assertEquals(Collections.emptyList(), fitNus.getRoutineList());
        assertEquals(Collections.emptyList(), fitNus.getSlotList());
        assertEquals(Collections.emptyList(), fitNus.getDailyCalorieList());

        //Body comes pre-filled up so assertions are different.
        List<Body> bodyList = new ArrayList<>();
        Body newBody = new Body();
        newBody.setHeight(new Height(160));
        newBody.setWeight(new Weight(45));
        bodyList.add(newBody);
        assertEquals(bodyList, fitNus.getBody());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> fitNus.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyFitNus_replacesData() {
        FitNus newData = getTypicalFitNus();
        fitNus.resetData(newData);
        assertEquals(newData, fitNus);
    }

    //Lesson tests

    @Test
    public void resetData_withDuplicateLessons_throwsDuplicateLessonException() {
        // Two lessons with the same identity fields
        Lesson editedGes1028 = new LessonBuilder(GES1028).withTags(VALID_LESSON_TAG_EASY).build();
        List<Lesson> newLessons = Arrays.asList(GES1028, editedGes1028);
        FitNusLessonsStub newData = new FitNusLessonsStub(newLessons);

        assertThrows(DuplicateLessonException.class, () -> fitNus.resetData(newData));
    }

    @Test
    public void hasLesson_nullLesson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> fitNus.hasLesson(null));
    }

    @Test
    public void hasLesson_lessonNotInFitNus_returnsFalse() {
        assertFalse(fitNus.hasLesson(GES1028));
    }

    @Test
    public void hasLesson_lessonInFitNus_returnsTrue() {
        fitNus.addLesson(GES1028);
        assertTrue(fitNus.hasLesson(GES1028));
    }

    @Test
    public void hasLesson_lessonWithSameIdentityFieldsInFitNus_returnsTrue() {
        fitNus.addLesson(GES1028);
        Lesson editedGes1028 = new LessonBuilder(GES1028).withTags(VALID_LESSON_TAG_EASY)
                .build();
        assertTrue(fitNus.hasLesson(editedGes1028));
    }

    @Test
    public void getLessonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> fitNus.getLessonList().remove(0));
    }

    //Routine tests

    @Test
    public void resetData_withDuplicateRoutines_throwsDuplicateRoutineException() {
        // Two Routines with the same identity fields
        Routine copyRoutine = new Routine(new Name("Leg Day"));
        List<Routine> newRoutines = Arrays.asList(LEG_DAY, copyRoutine);
        FitNusRoutinesStub newData = new FitNusRoutinesStub(newRoutines);

        assertThrows(DuplicateRoutineException.class, () -> fitNus.resetData(newData));
    }

    @Test
    public void hasRoutine_nullRoutine_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> fitNus.hasRoutine(null));
    }

    @Test
    public void hasRoutine_missingRoutine_throwsNullPointerException() {
        assertFalse(fitNus.hasRoutine(LEG_DAY));
    }

    @Test
    public void hasRoutine_routineInFitNus_returnsTrue() {
        fitNus.addRoutine(LEG_DAY);
        assertTrue(fitNus.hasRoutine(LEG_DAY));
    }

    @Test
    public void hasRoutine_routineWithSameIdentityFieldsInFitNus_returnsTrue() {
        fitNus.addRoutine(LEG_DAY);
        Routine copyRoutine = new Routine(new Name("Leg Day"));
        assertTrue(fitNus.hasRoutine(copyRoutine));
    }

    @Test
    public void getRoutineList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> fitNus.getRoutineList().remove(0));
    }

    @Test
    public void addExerciseToRoutine_success() {
        FitNus fitNus = new FitNus();
        fitNus.addExercise(BENCH_PRESS);
        fitNus.addRoutine(LEG_DAY);
        Exercise newExercise = new Exercise(new Name("Amazing"), new HashSet<>());
        fitNus.addExerciseToRoutine(LEG_DAY, newExercise);

        assertTrue(fitNus.getRoutineList().get(0).hasExercise(newExercise));
    }

    @Test
    public void setExercise_successForRoutine() {
        fitNus.addExercise(BENCH_PRESS);
        LEG_DAY.addExercise(BENCH_PRESS);
        fitNus.addRoutine(LEG_DAY);
        Exercise copyExercise = new Exercise(new Name("Bench"), new HashSet<>());
        fitNus.setExercise(BENCH_PRESS, copyExercise);

        assertFalse(fitNus.getRoutineList().get(0).hasExercise(BENCH_PRESS));
        assertTrue(fitNus.getRoutineList().get(0).hasExercise(copyExercise));
    }

    @Test
    public void deleteExercise_successForRoutine() {
        FitNus fitNus = new FitNus();
        fitNus.addExercise(BENCH_PRESS);
        fitNus.addRoutine(LEG_DAY);
        assertTrue(fitNus.getRoutineList().get(0).hasExercise(BENCH_PRESS));

        fitNus.removeExercise(BENCH_PRESS);

        assertFalse(fitNus.getRoutineList().get(0).hasExercise(BENCH_PRESS));
        assertFalse(fitNus.hasExercise(BENCH_PRESS));

    }

    @Test
    public void removeRoutine_successForRoutine() {
        fitNus.addExercise(BENCH_PRESS);
        LEG_DAY.addExercise(BENCH_PRESS);
        fitNus.addRoutine(LEG_DAY);
        assertTrue(fitNus.hasExercise(BENCH_PRESS));
        assertTrue(fitNus.hasRoutine(LEG_DAY));

        fitNus.removeRoutine(LEG_DAY);

        assertTrue(fitNus.hasExercise(BENCH_PRESS));
        assertFalse(fitNus.hasRoutine(LEG_DAY));
    }

    @Test
    public void deleteExerciseFromRoutine_successForRoutine() {
        fitNus.addExercise(BENCH_PRESS);
        LEG_DAY.addExercise(BENCH_PRESS);
        fitNus.addRoutine(LEG_DAY);
        assertTrue(fitNus.getRoutineList().get(0).hasExercise(BENCH_PRESS));

        fitNus.deleteExerciseFromRoutine(LEG_DAY, BENCH_PRESS);

        assertFalse(fitNus.getRoutineList().get(0).hasExercise(BENCH_PRESS));
        assertTrue(fitNus.hasExercise(BENCH_PRESS));
    }

    @Test
    public void retrieveRoutine_sucess() {
        fitNus.addRoutine(LEG_DAY);

        assertEquals(fitNus.retrieveRoutine(LEG_DAY), LEG_DAY);
    }

    @Test
    public void retrieveRoutine_invalidRoutine() {
        fitNus.addRoutine(LEG_DAY);

        assertNotEquals(fitNus.retrieveRoutine(LEG_DAY), UPPER_BODY);
    }

    /**
     * A stub ReadOnlyFitNus whose lesson list can violate interface constraints.
     */
    private static class FitNusLessonsStub implements ReadOnlyFitNus {
        private final ObservableList<Exercise> exercises = FXCollections.observableArrayList();
        private final ObservableList<Lesson> lessons = FXCollections.observableArrayList();
        private final ObservableList<Routine> routines = FXCollections.observableArrayList();
        private final ObservableList<Slot> slots = FXCollections.observableArrayList();
        private final ObservableList<DailyCalorie> calorieLog = FXCollections.observableArrayList();
        private final ObservableList<Body> body = FXCollections.observableArrayList();

        FitNusLessonsStub(Collection<Lesson> lessons) {
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

    /**
     * A stub ReadOnlyFitNus whose activity list can violate interface constraints.
     */
    private static class FitNusRoutinesStub implements ReadOnlyFitNus {
        private final ObservableList<Exercise> exercises = FXCollections.observableArrayList();
        private final ObservableList<Lesson> lessons = FXCollections.observableArrayList();
        private final ObservableList<Routine> routines = FXCollections.observableArrayList();
        private final ObservableList<Slot> slots = FXCollections.observableArrayList();
        private final ObservableList<DailyCalorie> calorieLog = FXCollections.observableArrayList();
        private final ObservableList<Body> body = FXCollections.observableArrayList();

        FitNusRoutinesStub(Collection<Routine> routines) {
            this.routines.setAll(routines);
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
