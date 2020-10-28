package seedu.address.model.calorie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.CalorieLog;
import seedu.address.model.person.DailyCalorie;
import seedu.address.model.person.exceptions.DailyCalorieNotFoundException;
import seedu.address.model.person.exceptions.DuplicateDailyCalorieException;

public class CalorieLogTest {

    private final LocalDate testDate1 = LocalDate.of(2020, 10, 28);
    private final LocalDate testDate2 = LocalDate.of(2020, 10, 26);
    private final DailyCalorie d1 = new DailyCalorie(testDate1);
    private final DailyCalorie d2 = new DailyCalorie(testDate1);
    private final DailyCalorie d3 = new DailyCalorie(testDate2);
    private final CalorieLog l1 = new CalorieLog();
    private final CalorieLog l2 = new CalorieLog();

    @Test
    public void equals_differentCalorieLog_returnsTrue() {
        assertTrue(l1.equals(l2));
    }

    @Test
    public void equals_differentCalorieLogWithDifferentDailyCalorie_returnsFalse() {
        l1.add(d1);
        assertFalse(l1.equals(l2));
    }

    @Test
    public void equals_differentCalorieLogWithSameDailyCalorie_returnsTrue() {
        l1.add(d1);
        l2.add(d1);
        assertTrue(l1.equals(l2));
    }

    @Test
    public void contains_nullCalorieLog_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> l1.contains(null));
    }

    @Test
    public void contains_dailyCalorieNotInList_returnsFalse() {
        assertFalse(l1.contains(d1));
    }

    @Test
    public void contains_dailyCalorieInList_returnsTrue() {
        l1.add(d1);
        assertTrue(l1.contains(d1));
    }

    @Test
    public void contains_dailyCalorieWithSameIdentityFieldsInList_returnsTrue() {
        l1.add(d1);
        assertTrue(l1.contains(d2));
    }

    @Test
    public void add_nulldailyCalorie_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> l1.add(null));
    }

    @Test
    public void add_duplicateDailyCalorie_throwsDuplicateDailyCalorieException() {
        l1.add(d1);
        assertThrows(DuplicateDailyCalorieException.class, () -> l1.add(d1));
    }

    @Test
    public void setDailyCalorie_nullTargetDailyCalorie_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> l1.setDailyCalorie(null, d1));
    }

    @Test
    public void setDailyCalorie_nullEditedDailyCalorie_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> l1.setDailyCalorie(d1, null));
    }

    @Test
    public void setDailyCalorie_targetDailyCalorieNotInList_throwsDailyCalorieNotFoundException() {
        assertThrows(DailyCalorieNotFoundException.class, () -> l1.setDailyCalorie(d1, d1));
    }

    @Test
    public void setDailyCalorie_editedDailyCalorieIsSameDailyCalorie_success() {
        l1.add(d1);
        l1.setDailyCalorie(d1, d1);
        CalorieLog expectedCalorieLog = new CalorieLog();
        expectedCalorieLog.add(d1);
        assertEquals(l1, expectedCalorieLog);
    }

    @Test
    public void setDailyCalorie_editedDailyCalorieHasSameIdentity_success() {
        l1.add(d1);
        l1.setDailyCalorie(d1, d2);
        CalorieLog expectedCalorieLog = new CalorieLog();
        expectedCalorieLog.add(d2);
        assertEquals(l1, expectedCalorieLog);
    }

    @Test
    public void setDailyCalorie_editedDailyCalorieHasDifferentIdentity_success() {
        l1.add(d1);
        l1.setDailyCalorie(d1, d2);
        CalorieLog expectedCalorieLog = new CalorieLog();
        expectedCalorieLog.add(d2);
        assertEquals(l1, expectedCalorieLog);
    }

    @Test
    public void setDailyCalorie_editedDailyCalorieHasNonUniqueIdentity_throwsDuplicateDailyCalorieException() {
        l1.add(d1);
        l1.add(d3);
        assertThrows(DuplicateDailyCalorieException.class, () -> l1.setDailyCalorie(d1, d3));
    }

    @Test
    public void remove_nullDailyCalorie_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> l1.remove(null));
    }

    @Test
    public void remove_dailyCalorieDoesNotExist_throwsDailyCalorieNotFoundException() {
        assertThrows(DailyCalorieNotFoundException.class, () -> l1.remove(d1));
    }

    @Test
    public void remove_existingDailyCalorie_removesDailyCalorie() {
        l1.add(d1);
        l1.remove(d1);
        CalorieLog expectedCalorieLog = new CalorieLog();
        assertEquals(expectedCalorieLog, l1);
    }

    @Test
    public void setDailyCalories_nullUniqueDailyCalorieList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> l1.setDailyCalories((CalorieLog) null));
    }

    @Test
    public void setDailyCalories_uniqueDailyCalorieList_replacesOwnListWithProvidedCalorieLog() {
        l1.add(d1);
        CalorieLog expectedCalorieLog = new CalorieLog();
        expectedCalorieLog.add(d2);
        l1.setDailyCalories(expectedCalorieLog);
        assertEquals(l1, expectedCalorieLog);
    }

    @Test
    public void setDailyCalories_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> l1.setDailyCalories((List<DailyCalorie>) null));
    }

    @Test
    public void setDailyCalories_list_replacesOwnListWithProvidedList() {
        l1.add(d1);
        List<DailyCalorie> dailyCalorieList = Collections.singletonList(d1);
        l1.setDailyCalories(dailyCalorieList);
        CalorieLog expectedCalorieLog = new CalorieLog();
        expectedCalorieLog.add(d1);
        assertEquals(expectedCalorieLog, l1);
    }

    @Test
    public void setDailyCalories_listWithDuplicateDailyCalories_throwsDuplicateDailyCalorieException() {
        List<DailyCalorie> listWithDuplicateDailyCalories = Arrays.asList(d1, d1);
        assertThrows(DuplicateDailyCalorieException.class, () -> l1.setDailyCalories(listWithDuplicateDailyCalories));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> l1
                .asUnmodifiableObservableList().remove(0));
    }

}
