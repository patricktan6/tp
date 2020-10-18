package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

public class Slot {

    private final Routine routine;

    private final Day day;

    private final Duration duration;

    /**
     * Constructs a new Slot object.
     * @param routine The routine to be added to timetable.
     * @param day The day to add to.
     * @param duration The duration to input in the timetable.
     */
    public Slot(Routine routine, Day day, Duration duration) {
        requireAllNonNull(routine, day, duration);
        this.routine = routine;
        this.day = day;
        this.duration = duration;
    }

    public Routine getRoutine() {
        return routine;
    }

    public Day getDay() {
        return day;
    }

    public Duration getDuration() {
        return duration;
    }

    /**
     * Returns true if this slot has overlapping duration with the other slot.
     * @param otherSlot The other slot to be checked.
     * @return true if this slot has overlapping duration with the other slot.
     */
    public boolean hasOverlapDuration(Slot otherSlot) {
        return day.isSameDay(otherSlot.getDay())
                && duration.hasOverlapDuration(otherSlot.getDuration());
    }

    /**
     * Returns true if both slots have the same day and duration.
     * This defines a weaker notion of equality between two slots.
     */
    public boolean isSameSlot(Slot otherSlot) {
        if (otherSlot == this) {
            return true;
        }

        return otherSlot != null
                && otherSlot.getDay().isSameDay(day)
                && otherSlot.getDuration().isSameDuration(duration);
    }

    /**
     * Returns true if both slots have the same activity, day and duration.
     * This defines a stronger notion of equality between two slots.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Slot)) {
            return false;
        }

        Slot otherSlot = (Slot) other;
        return otherSlot.getRoutine().equals(routine)
                && otherSlot.getDay().equals(day)
                && otherSlot.getDuration().isSameDuration(duration);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(routine, day, duration);
    }

    @Override
    public String toString() {
        return routine.getName() + " on " + day.toString() + " from " + duration.toString();
    }
}
