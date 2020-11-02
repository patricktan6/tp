package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLessons.GES1028;
import static seedu.address.testutil.TypicalLessons.MA1101R;
import static seedu.address.testutil.TypicalLessons.MA1521;
import static seedu.address.testutil.TypicalLessons.getTypicalFitNus;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyFitNus;

public class JsonAddressBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAddressBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readFitNus_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readFitNus(null));
    }

    private java.util.Optional<ReadOnlyFitNus> readFitNus(String filePath) throws Exception {
        return new JsonAddressBookStorage(Paths.get(filePath)).readFitNus(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readFitNus("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readFitNus("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readFitNus_invalidLessonFitNus_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readFitNus("invalidPersonAddressBook.json"));
    }

    @Test
    public void readFitNus_invalidAndValidLessonFitNus_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readFitNus("invalidAndValidPersonAddressBook.json"));
    }

    @Test
    public void readAndSaveFitNus_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempfitNUS.json");
        AddressBook original = getTypicalFitNus();
        JsonAddressBookStorage jsonAddressBookStorage = new JsonAddressBookStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveFitNus(original, filePath);
        ReadOnlyFitNus readBack = jsonAddressBookStorage.readFitNus(filePath).get();
        assertEquals(original, new AddressBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addLesson(MA1101R);
        original.removeLesson(GES1028);
        jsonAddressBookStorage.saveFitNus(original, filePath);
        readBack = jsonAddressBookStorage.readFitNus(filePath).get();
        assertEquals(original, new AddressBook(readBack));

        // Save and read without specifying file path
        original.addLesson(MA1521);
        jsonAddressBookStorage.saveFitNus(original); // file path not specified
        readBack = jsonAddressBookStorage.readFitNus().get(); // file path not specified
        assertEquals(original, new AddressBook(readBack));

    }

    @Test
    public void saveFitNus_nullFitNus_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFitNus(null, "SomeFile.json"));
    }

    /**
     * Saves {@code fitNUS} at the specified {@code filePath}.
     */
    private void saveFitNus(ReadOnlyFitNus fitNus, String filePath) {
        try {
            new JsonAddressBookStorage(Paths.get(filePath))
                    .saveFitNus(fitNus, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveFitNus_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFitNus(new AddressBook(), null));
    }
}
