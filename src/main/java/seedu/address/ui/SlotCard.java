package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.slot.Slot;

/**
 * An UI component that displays information of a {@code Slot}.
 */
public class SlotCard extends UiPart<Region> {

    private static final String FXML = "SlotListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on FitNus level 4</a>
     */

    public final Slot slot;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label time;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code SlotCode} with the given {@code Slot} and index to display.
     */
    public SlotCard(Slot slot, int displayedIndex) {
        super(FXML);
        this.slot = slot;
        id.setText(displayedIndex + ". ");
        name.setText(slot.getActivity().getName().toString());
        time.setText(slot.getDuration().getStartTime().toString() + " - "
                + slot.getDuration().getEndTime().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SlotCard)) {
            return false;
        }

        // state check
        SlotCard card = (SlotCard) other;
        return id.getText().equals(card.id.getText())
                && slot.equals(card.slot);
    }
}
