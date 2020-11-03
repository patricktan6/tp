package seedu.address.ui;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Body;
import seedu.address.model.person.DailyCalorie;

import java.util.logging.Logger;

public class BmiBox extends UiPart<Region>{

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
    public  BmiBox(ObservableList<Body> body) {
        super(FXML);
        this.body = body;
        Body observedBody = body.get(0);
        bmi2.setText("BMI Metrics: \n\nHeight: "
                + String.format("%.2f",observedBody.getHeight())
                + "m\n\nWeight: "
                + String.format("%.2f",observedBody.getWeight())
                + "kg\n\nBMI: "
                + String.format("%.2f",observedBody.getBmi()));

        body.addListener((ListChangeListener<Body>) change -> {
            update();
        });
    }

    private void update() {
        Body observedBody = body.get(0);
        bmi2.setText("BMI Metrics: \n\nHeight: "
                + String.format("%.2f",observedBody.getHeight())
                + "m\n\nWeight: "
                + String.format("%.2f",observedBody.getWeight())
                + "kg\n\nBMI: "
                + String.format("%.2f",observedBody.getBmi()));
    }
}
