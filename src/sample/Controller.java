package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class Controller {
    public TextField X;
    public TextField x0;
    public TextField N;
    public TextField y0;
    public TextField Nmax;
    public TextField Nmin;

    public CheckBox euler;
    public CheckBox improvedEuler;
    public CheckBox rungeKutta;
    public CheckBox original;


    @FXML
    private LineChart<Number, Number> functions;
    @FXML
    private LineChart<Number, Number> errors;
    @FXML
    private LineChart<Number, Number> maxErrors;

    @FXML
    private Label warning;


    public XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
    public XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
    public XYChart.Series<Number, Number> series3 = new XYChart.Series<>();
    public XYChart.Series<Number, Number> series4 = new XYChart.Series<>();

    private XYChart.Series<Number, Number> eulersErrorSeries = new XYChart.Series<>();
    private XYChart.Series<Number, Number> improvedEulersErrorSeries = new XYChart.Series<>();
    private XYChart.Series<Number, Number> rungeKuttaErrorSeries = new XYChart.Series<>();

    private XYChart.Series<Number, Number> eulersMaxErrorSeries = new XYChart.Series<>();
    private XYChart.Series<Number, Number> improvedEulersMaxErrorSeries = new XYChart.Series<>();
    private XYChart.Series<Number, Number> rungeKuttaMaxErrorSeries = new XYChart.Series<>();

    public void setNamed() {
        series1.setName("Euler method");
        series2.setName("Improved Euler method");
        series3.setName("Analitical Solution");
        series4.setName("Runge-Kutta method");
        functions.setTitle("xy^2 + 3xy");
    }


    private void addListenersForText() {
        X.textProperty().addListener((observable, oldValue, newValue) -> {
            //checkNumbers(X, newValue, oldValue);
            //recalculate();
            X.setText(newValue);
        });
        x0.textProperty().addListener((observable, oldValue, newValue) -> {
            //checkNumbers(x0, newValue, oldValue);
            //recalculate();
            x0.setText(newValue);
        });
        y0.textProperty().addListener((observable, oldValue, newValue) -> {
            //checkNumbers(y0, newValue, oldValue);
            //recalculate();
            y0.setText(newValue);
        });
        N.textProperty().addListener((observable, oldValue, newValue) -> {
            //checkNumbers(N, newValue, oldValue);
            //recalculate();
            N.setText(newValue);
        });
    }

    private void addListenerForBox() {
        euler.selectedProperty().addListener((observable, oldValue, newValue) -> {
            drawGraph(newValue, series1, functions);
            calculate();
        });
        improvedEuler.selectedProperty().addListener((observable, oldValue, newValue) -> {
            drawGraph(newValue, series2, functions);
        });
        rungeKutta.selectedProperty().addListener((observable, oldValue, newValue) -> {
            drawGraph(newValue, series3, functions);
        });

    }

    private void drawGraph(Boolean checkBoxValue,
                           XYChart.Series<Number, Number> series,
                           LineChart<Number, Number> chart) {
        if (checkBoxValue) {
            chart.getData().add(series);
        } else {
            chart.getData().remove(series);
        }
    }

    private void calculate() {
        double x0 = Double.parseDouble(this.x0.getText());
        double X = Double.parseDouble(this.X.getText());
        double y0 = Double.parseDouble(this.y0.getText());
        int N = Integer.parseInt(this.N.getText());
        series1 = (Eulers.Eulers(x0, y0, X, N));

    }

    public Controller() {
    }

    @FXML
    public void initialize() {
        addListenersForText();
        addListenerForBox();
        setNamed();

        maxErrors.getData().add(eulersMaxErrorSeries);
        maxErrors.getData().add(improvedEulersMaxErrorSeries);
        maxErrors.getData().add(rungeKuttaMaxErrorSeries);

        calculate();
        calculateErrors();
    }

    public void calculateErrors(XYChart.Series<Number, Number> generatedSeries,
                                XYChart.Series<Number, Number> errorSeries) {
        errorSeries.getData().clear();

    }

}
