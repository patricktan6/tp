package seedu.address.testutil;

import seedu.address.model.FitNus;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code FitNus ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private FitNus fitNus;

    public AddressBookBuilder() {
        fitNus = new FitNus();
    }

    public AddressBookBuilder(FitNus fitNus) {
        this.fitNus = fitNus;
    }

    /**
     * Adds a new {@code Person} to the {@code FitNus} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        fitNus.addPerson(person);
        return this;
    }

    public FitNus build() {
        return fitNus;
    }
}
