package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Set;

public class Timetable {

    private final UniqueSlotList slots;

    {
        slots = new UniqueSlotList();
    }

    public boolean hasSlot(Slot slot) {
        requireNonNull(slot);
        return slots.contains(slot);
    }

    public boolean hasOverlapDuration(Slot slot) {
        requireNonNull(slot);
        return slots.hasOverlapDuration(slot);
    }

    public void addSlot(Slot slot) {
        slots.add(slot);
    }

    public void deleteSlot(Slot slot) {
        slots.remove(slot);
    }
}
