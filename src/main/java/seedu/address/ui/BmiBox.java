package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Body;

public class BmiBox extends UiPart<Region> {

    private static final String FXML = "BmiBox.fxml";

    private final Logger logger = LogsCenter.getLogger(CalorieGraph.class);

    private ObservableList<Body> body;

    // Independent Ui parts residing in this Ui container

    @FXML
    private Label bmi2;


    /** Constructs BmiBox
     *
     * @param body
     */
    public BmiBox(ObservableList<Body> body) {
        super(FXML);
        this.body = body;
        Body observedBody = body.get(0);
        String text = "BMI Metrics: \n\n"
                + "Height: " + observedBody.getHeight().toString() + "\n\n"
                + "Weight: " + observedBody.getWeight().toString() + "\n\n"
                + "BMI: " + String.format("%.2f", observedBody.getBmi());
        bmi2.setText(text);

        body.addListener((ListChangeListener<Body>) change -> {
            update();
        });
    }

    private void update() {
        Body observedBody = body.get(0);
        String text = "BMI Metrics: \n\n"
                + "Height: " + observedBody.getHeight().toString() + "\n\n"
                + "Weight: " + observedBody.getWeight().toString() + "\n\n"
                + "BMI: " + String.format("%.2f", observedBody.getBmi());
        bmi2.setText(text);
    }
}
