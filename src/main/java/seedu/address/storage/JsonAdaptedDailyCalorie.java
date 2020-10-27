package seedu.address.storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.DailyCalorie;
import seedu.address.model.person.Person;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedDailyCalorie {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "DailyCalorie's %s field is missing!";

    private final String date;
    private final String calories;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedDailyCalorie(@JsonProperty("date") String date,
                              @JsonProperty("calories") String calories) {
        this.date = date;
        this.calories = calories;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedDailyCalorie(DailyCalorie source) {
        date = source.getDate().toString();
        calories = Integer.toString(source.getCalories());
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public DailyCalorie toModelType() throws IllegalValueException {

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LocalDate.class.getSimpleName()));
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate modelLocalDate = LocalDate.parse(date, formatter);
        DailyCalorie modelDailyCalorie = new DailyCalorie(modelLocalDate);
        int modelCalories = Integer.parseInt(calories);
        modelDailyCalorie.addCalories(modelCalories);
        return modelDailyCalorie;
    }

}
