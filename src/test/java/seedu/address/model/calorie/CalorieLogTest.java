package seedu.address.model.calorie;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.DailyCalorie;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CalorieLogTest {

    LocalDate testDate1 = LocalDate.of(2020, 10, 28);
    DailyCalorie d1 = new DailyCalorie(testDate1);
    DailyCalorie d2 = new DailyCalorie(testDate1);


    @Test
    public void isSameDailyCalorie() {
        // same object -> returns true
        assertTrue(d1.isSameDailyCalorie(d1));
        assertTrue(d2.isSameDailyCalorie(d2));

        // null -> returns false
        assertFalse(d1.isSameDailyCalorie(null));
        assertFalse(d2.isSameDailyCalorie(null));

        // same LocalDate -> returns true
        assertTrue(d1.isSameDailyCalorie(d2));

        // same LocalDate, different calories -> returns true
        d1.addCalories(10);
        assertTrue(d1.isSameDailyCalorie(d2));

        // same LocalDate, same calories -> returns true
        d2.addCalories(10);
        assertTrue(d1.isSameDailyCalorie(d2));

    }

    @Test
    public void equals() {
        LocalDate testDate2 = LocalDate.of(2020, 10, 26);
        DailyCalorie d3 = new DailyCalorie(testDate2);

        // same values with no calories -> returns true
        assertTrue(d1.equals(d2));

        // same object -> returns true
        assertTrue(d1.equals(d1));
        assertTrue(d2.equals(d2));

        // null -> returns false
        assertFalse(d1.equals(null));
        assertFalse(d2.equals(null));

        // different type -> returns false
        assertFalse(d1.equals(5));

        // same LocalDate, different DailyCalorie -> returns false
        d2.addCalories(1500);
        assertFalse(d1.equals(d2));

        // different LocalDate, same DailyCalorie -> returns false
        assertFalse(d1.equals(d3));

        // different LocalDate, different DailyCalorie -> returns false
        assertFalse(d2.equals(d3));
    }

}
