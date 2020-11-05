package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Body;
import seedu.address.model.person.Height;
import seedu.address.model.person.Weight;

/**
 * Jackson-friendly version of {@link Body}.
 */
class JsonAdaptedBody {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Body's %s field is missing!";

    private final double height;
    private final double weight;

    /**
     * Constructs a {@code JsonAdaptedBody} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedBody(@JsonProperty("height") double height,
                               @JsonProperty("weight") double weight) {
        this.height = height;
        this.weight = weight;
    }

    /**
     * Converts a given {@code Body} into this class for Jackson use.
     */
    public JsonAdaptedBody(Body source) {
        this.height = source.getHeight().getHeight();
        this.weight = source.getWeight().getWeight();
    }

    /**
     * Converts this Jackson-friendly adapted Body object into the model's {@code Body} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Body.
     */
    public Body toModelType() throws IllegalValueException {
        if (height <= 0) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Integer.class.getSimpleName()));
        } else if (weight <= 0) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Integer.class.getSimpleName()));
        }
        Body newBody = new Body();
        newBody.setHeight(new Height(height));
        newBody.setWeight(new Weight(weight));
        return newBody;
    }

}
