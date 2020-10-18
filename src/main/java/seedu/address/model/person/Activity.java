package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

public abstract class Activity {

    protected final Name name;

    /**
     * Constructs an Activity object.
     * @param name The name of the activity.
     */
    public Activity(Name name) {
        requireNonNull(name);
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    /**
     * Returns true if both activities of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two activities.
     */
    public abstract boolean isSameActivity(Activity otherActivity);
}
