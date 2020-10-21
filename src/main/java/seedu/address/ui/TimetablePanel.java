package seedu.address.ui;

import java.util.logging.Logger;

import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;

public class TimetablePanel extends UiPart<Region> {
    private static final String FXML = "TimetablePanel.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Logic logic;

    /** Constructs TimetablePanel
     *
     * @param logic
     */
    public TimetablePanel(Logic logic) {
        super(FXML);

        this.logic = logic;
    }
}
