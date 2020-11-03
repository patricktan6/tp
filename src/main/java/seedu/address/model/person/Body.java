package seedu.address.model.person;

import java.util.Objects;

public class Body{

    private double height;
    private double weight;

    public Body() {
        this.height = 160;
        this.weight = 45;
    }

    public void setHeight(double h) {
        assert(h > 0);
        this.height = h;
    }

    public void setWeight(double w) {
        assert(w > 0);
        this.weight = w;
    }

    public double getHeight() {
        return this.height;
    }

    public double getWeight() {
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
        return otherBody.getHeight() == getHeight() && otherBody.getWeight() == getWeight();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(this.height, this.weight);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Body measurements: ");
        builder.append("\nHeight: " + this.height + "m\n");
        builder.append("Weight: " + this.weight + "kg\n");
        return builder.toString();
    }

    public double getBmi() {
        return this.weight / Math.pow((this.height / 100.0), 2);
    }
}
