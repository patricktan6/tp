package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.FitNus;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyFitNus;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.calorie.DailyCalorie;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Body;
import seedu.address.model.person.Height;
import seedu.address.model.person.Weight;
import seedu.address.model.routine.Routine;
import seedu.address.model.slot.Slot;
import seedu.address.testutil.WeightBuilder;

public class AddWeightCommandTest {

    @Test
    public void constructor_nullWeight_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddWeightCommand(null));
    }

    @Test
    public void execute_weightAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingWeightAdded modelWeightStub = new ModelStubAcceptingWeightAdded();
        Weight validWeight = new WeightBuilder().build();

        CommandResult commandResult = new AddWeightCommand(validWeight).execute(modelWeightStub);

        assertEquals(String.format(AddWeightCommand.MESSAGE_SUCCESS, validWeight), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validWeight), modelWeightStub.weightAdded);
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getFitNusFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFitNusFilePath(Path fitNusFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addExercise(Exercise exercise) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addRoutine(Routine routine) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasRoutine(Routine r) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addExerciseToRoutine(Routine r, Exercise e) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredRoutineList(Predicate<Routine> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteRoutine(Routine target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addHeight(Height height) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addWeight(Weight weight) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public double getBmi() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addLesson(Lesson lesson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Routine> getFilteredRoutineList() {
            return null;
        }

        @Override
        public void setFitNus(ReadOnlyFitNus newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyFitNus getFitNus() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasExercise(Exercise exercise) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasLesson(Lesson lesson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSlot(Slot slot) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasOverlappingSlot(Slot slot) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addSlotToTimetable(Slot slot) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteSlotFromTimetable(Slot slot) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteExercise(Exercise target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteLesson(Lesson target) {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public void setExercise(Exercise target, Exercise editedExercise) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setLesson(Lesson target, Lesson editedLesson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Lesson retrieveLesson(Lesson lesson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Routine retrieveRoutine(Routine routine) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteExerciseFromRoutine(Routine routine, Exercise exercise) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void viewRoutine(Routine routineToView) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addCalories(int calories) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void minusCalories(int calories) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<DailyCalorie> getFilteredDailyCalorie() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<DailyCalorie> getFilteredDailyCalorieList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getCalories() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Exercise> getFilteredExerciseList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Lesson> getFilteredLessonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Body> getFilteredBody() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Slot> getFilteredSlotList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Slot> getFilteredSlotList(String day) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredCalorieLog(Predicate<DailyCalorie> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredExerciseList(Predicate<Exercise> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredLessonList(Predicate<Lesson> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that always accept the weight being added.
     */
    private class ModelStubAcceptingWeightAdded extends ModelStub {
        final ArrayList<Weight> weightAdded = new ArrayList<>();

        @Override
        public void addWeight(Weight weight) {
            requireNonNull(weight);
            weightAdded.add(weight);
        }

        @Override
        public ReadOnlyFitNus getFitNus() {
            return new FitNus();
        }
    }

}
