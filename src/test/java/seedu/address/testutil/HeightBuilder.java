package seedu.address.testutil;

import seedu.address.model.person.Height;

public class HeightBuilder {
    
    public static final double DEFAULT_HEIGHT = 170.0;
    
    private double height;

    /**
     * Creates a {@code HeightBuilder} with the default details.
     */
    public HeightBuilder() {
        height = DEFAULT_HEIGHT;
    }

    /**
     * Initializes the HeightBuilder with {@code heightToCopy}.
     */
    public HeightBuilder(double heightToCopy) {
        height = heightToCopy;
    }

    /**
     * Sets the height of the {@code Height} that we are building.
     */
    public HeightBuilder withHeight(double height) {
        this.height = height;
        return this;
    }
    
    public Height build() {
        return new Height(height);
    }
}
