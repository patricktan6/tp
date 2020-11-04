package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Lesson;

/**
 * A utility class to help with building fitNUS objects.
 * Example usage: <br>
 *     {@code FitNus fn = new FitNusBuilder().withLesson("MA1521", "MA1101R").build();}
 */
public class FitNusBuilder {

    private AddressBook addressBook;

    public FitNusBuilder() {
        addressBook = new AddressBook();
    }

    public FitNusBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Lesson} to the {@code fitNUS} that we are building.
     */
    public FitNusBuilder withLesson(Lesson lesson) {
        addressBook.addLesson(lesson);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
