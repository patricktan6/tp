package seedu.address.model.routine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRoutines.LEG_DAY;
import static seedu.address.testutil.TypicalRoutines.UPPER_BODY;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Name;
import seedu.address.model.person.Routine;
import seedu.address.model.person.UniqueRoutineList;
import seedu.address.model.person.exceptions.DuplicateRoutineException;
import seedu.address.model.person.exceptions.RoutineNotFoundException;

public class UniqueRoutineListTest {

    private final UniqueRoutineList uniqueRoutineList = new UniqueRoutineList();

    @Test
    public void contains_nullRoutine_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoutineList.contains(null));
    }

    @Test
    public void contains_routineNotInList_returnsFalse() {
        assertFalse(uniqueRoutineList.contains(LEG_DAY));
    }

    @Test
    public void contains_routineInList_returnsTrue() {
        uniqueRoutineList.add(LEG_DAY);
        assertTrue(uniqueRoutineList.contains(LEG_DAY));
    }

    @Test
    public void contains_routineWithSameIdentityFieldsInList_returnsTrue() {
        uniqueRoutineList.add(LEG_DAY);
        Name copyName = new Name("Leg Day");
        Routine copyRoutine = new Routine(copyName);
        assertTrue(uniqueRoutineList.contains(copyRoutine));
    }

    @Test
    public void add_nullRoutine_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoutineList.add(null));
    }

    @Test
    public void add_duplicateRoutine_throwsDuplicateRoutineException() {
        uniqueRoutineList.add(LEG_DAY);
        assertThrows(DuplicateRoutineException.class, () -> uniqueRoutineList.add(LEG_DAY));
    }

    @Test
    public void setRoutine_nullTargetRoutine_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoutineList.setRoutine(null, LEG_DAY));
    }

    @Test
    public void setRoutine_nullEditedRoutine_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoutineList.setRoutine(LEG_DAY, null));
    }

    @Test
    public void setRoutine_targetRoutineNotInList_throwsRoutineNotFoundException() {
        assertThrows(RoutineNotFoundException.class, () -> uniqueRoutineList.setRoutine(LEG_DAY, LEG_DAY));
    }

    @Test
    public void setRoutine_editedRoutineIsSameRoutine_success() {
        uniqueRoutineList.add(LEG_DAY);
        uniqueRoutineList.setRoutine(LEG_DAY, LEG_DAY);
        UniqueRoutineList expectedUniqueRoutineList = new UniqueRoutineList();
        expectedUniqueRoutineList.add(LEG_DAY);
        assertEquals(expectedUniqueRoutineList, uniqueRoutineList);
    }

    @Test
    public void setRoutine_editedRoutineHasSameIdentity_success() {
        uniqueRoutineList.add(LEG_DAY);
        Name copyName = new Name("Leg Day");
        Routine copyRoutine = new Routine(copyName);
        uniqueRoutineList.setRoutine(LEG_DAY, copyRoutine);
        UniqueRoutineList expectedUniqueRoutineList = new UniqueRoutineList();
        expectedUniqueRoutineList.add(copyRoutine);
        assertEquals(expectedUniqueRoutineList, uniqueRoutineList);
    }

    @Test
    public void setRoutine_editedRoutineHasDifferentIdentity_success() {
        uniqueRoutineList.add(LEG_DAY);
        uniqueRoutineList.setRoutine(LEG_DAY, UPPER_BODY);
        UniqueRoutineList expectedUniqueRoutineList = new UniqueRoutineList();
        expectedUniqueRoutineList.add(UPPER_BODY);
        assertEquals(expectedUniqueRoutineList, uniqueRoutineList);
    }

    @Test
    public void setRoutine_editedRoutineHasNonUniqueIdentity_throwsDuplicateRoutineException() {
        uniqueRoutineList.add(LEG_DAY);
        uniqueRoutineList.add(UPPER_BODY);
        assertThrows(DuplicateRoutineException.class, () -> uniqueRoutineList.setRoutine(LEG_DAY, UPPER_BODY));
    }

    @Test
    public void remove_nullRoutine_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoutineList.remove(null));
    }

    @Test
    public void remove_routineDoesNotExist_throwsRoutineNotFoundException() {
        assertThrows(RoutineNotFoundException.class, () -> uniqueRoutineList.remove(LEG_DAY));
    }

    @Test
    public void remove_existingRoutine_removesRoutine() {
        uniqueRoutineList.add(LEG_DAY);
        uniqueRoutineList.remove(LEG_DAY);
        UniqueRoutineList expectedUniqueRoutineList = new UniqueRoutineList();
        assertEquals(expectedUniqueRoutineList, uniqueRoutineList);
    }

    @Test
    public void setRoutines_nullUniqueRoutineList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoutineList.setRoutines((UniqueRoutineList) null));
    }

    @Test
    public void setRoutines_uniqueRoutineList_replacesOwnListWithProvideduniqueRoutineList() {
        uniqueRoutineList.add(LEG_DAY);
        UniqueRoutineList expectedUniqueRoutineList = new UniqueRoutineList();
        expectedUniqueRoutineList.add(UPPER_BODY);
        uniqueRoutineList.setRoutines(expectedUniqueRoutineList);
        assertEquals(expectedUniqueRoutineList, uniqueRoutineList);
    }

    @Test
    public void setRoutines_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoutineList.setRoutines((List<Routine>) null));
    }

    @Test
    public void setRoutines_list_replacesOwnListWithProvidedList() {
        uniqueRoutineList.add(LEG_DAY);
        List<Routine> routineList = Collections.singletonList(UPPER_BODY);
        uniqueRoutineList.setRoutines(routineList);
        UniqueRoutineList expectedUniqueRoutineList = new UniqueRoutineList();
        expectedUniqueRoutineList.add(UPPER_BODY);
        assertEquals(expectedUniqueRoutineList, uniqueRoutineList);
    }

    @Test
    public void setRoutines_listWithDuplicateRoutines_throwsDuplicateRoutineException() {
        List<Routine> listWithDuplicateRoutines = Arrays.asList(LEG_DAY, LEG_DAY);
        assertThrows(DuplicateRoutineException.class, () -> uniqueRoutineList.setRoutines(listWithDuplicateRoutines));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> uniqueRoutineList
                .asUnmodifiableObservableList().remove(0));
    }
}
