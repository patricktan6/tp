package seedu.address.model.person;

import java.util.HashMap;
import java.util.Map;

public enum Day {
    MONDAY("monday"),
    TUESDAY("tuesday"),
    WEDNESDAY("wednesday"),
    THURSDAY("thursday"),
    FRIDAY("friday"),
    SATURDAY("saturday"),
    SUNDAY("sunday"),
    UNKNOWN;

    public static final String MESSAGE_CONSTRAINTS =
            "Day should only be one of the following: Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday";

    private static final Map<String, Day> MAPPING = new HashMap<>();

    private final String day;

    Day() {
        day = null;
    }

    Day(String day) {
        this.day = day;
    }

    static {
        for (Day day : Day.values()) {
            MAPPING.put(day.day, day);
        }
    }

    public String getDay() {
        return day;
    }

    public static Day getDayEnum(String day) {
        Day dayEnum = MAPPING.get(day);
        if (dayEnum == null) {
            return UNKNOWN;
        }
        return dayEnum;
    }

    public boolean isSameDay(Day otherDay) {
        if (otherDay == this) {
            return true;
        }

        return otherDay != null
                && otherDay.getDay().equals(day);
    }

    public static boolean isUnknownDay(Day day) {
        return day.equals(UNKNOWN);
    }
}
