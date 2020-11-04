package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

public class Body {

    private Height height;
    private Weight weight;

    /**
     * Creates a Body object.
     */
    public Body() {
        this.height = new Height(160);
        this.weight = new Weight(45);
    }

    public void setHeight(Height h) {
        requireNonNull(h);
        this.height = h;
    }

    public void setWeight(Weight w) {
        requireNonNull(w);
        this.weight = w;
    }

    public Height getHeight() {
        return this.height;
    }

    public Weight getWeight() {
        return this.weight;
    }

    /**
     * Returns true if both Body have the same data fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Body)) {
            return false;
        }

        Body otherBody = (Body) other;
        return height.equals(otherBody.getHeight()) && weight.equals(otherBody.getWeight());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(this.height, this.weight);
    }

    @Override
    public String toString() {
        return "Body measurements: \n" +
                "Height: " + height + "\n" +
                "Weight: " + weight + "\n";
    }

    public double getBmi() {
        return weight.getWeight() / Math.pow((height.getHeight() / 100.0), 2);
    }
}
