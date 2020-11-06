package seedu.address.testutil;

import seedu.address.model.person.Weight;

public class WeightBuilder {
    public static final double DEFAULT_WEIGHT = 65.0;

    private double weight;

    /**
     * Creates a {@code WeightBuilder} with the default details.
     */
    public WeightBuilder() {
        weight = DEFAULT_WEIGHT;
    }

    /**
     * Initializes the WeightBuilder with {@code weightToCopy}.
     */
    public WeightBuilder(double weightToCopy) {
        weight = weightToCopy;
    }

    /**
     * Sets the weight of the {@code Weight} that we are building.
     */
    public WeightBuilder withHeight(double height) {
        this.weight = height;
        return this;
    }

    public Weight build() {
        return new Weight(weight);
    }
}
