package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
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

    public Controller() {
    }

    @FXML
    public void initialize() {
        addListenerForBox();
        addListenersForText();
        setNamed();

        maxErrors.getData().add(eulersMaxErrorSeries);
        maxErrors.getData().add(improvedEulersMaxErrorSeries);
        maxErrors.getData().add(rungeKuttaMaxErrorSeries);

        calculate();
        calculateMaxErrors();
    }

    public void setNamed() {
        series1.setName("Euler method");
        series2.setName("Improved Euler method");
        series3.setName("Analitical Solution");
        series4.setName("Runge-Kutta method");
        functions.setTitle("xy^2 + 3xy");
        eulersErrorSeries.setName("Euler's Method");
        eulersMaxErrorSeries.setName("Euler's Method");
        improvedEulersErrorSeries.setName("Improved Euler's Method");
        improvedEulersMaxErrorSeries.setName("Improved Euler's Method");
        rungeKuttaErrorSeries.setName("Runge-Kutta Method");
        rungeKuttaMaxErrorSeries.setName("Runge-Kutta Method");
    }


    private void addListenersForText() {
        X.textProperty().addListener((observable, oldValue, newValue) -> {
            //checkNumbers(X, newValue, oldValue);
            //recalculate();
            X.setText(newValue);
            calculate();
        });
        x0.textProperty().addListener((observable, oldValue, newValue) -> {
            //checkNumbers(x0, newValue, oldValue);
            //recalculate();
            x0.setText(newValue);
            calculate();
        });
        y0.textProperty().addListener((observable, oldValue, newValue) -> {
            //checkNumbers(y0, newValue, oldValue);
            //recalculate();
            y0.setText(newValue);
            calculate();
        });
        N.textProperty().addListener((observable, oldValue, newValue) -> {
            //checkNumbers(N, newValue, oldValue);
            //recalculate();
            N.setText(newValue);
            calculate();
        });
    }

    private void addListenerForBox() {
        euler.selectedProperty().addListener((observable, oldValue, newValue) -> {
            drawGraph(newValue, series1, functions);
            drawGraph(newValue, eulersErrorSeries, errors);
        });
        improvedEuler.selectedProperty().addListener((observable, oldValue, newValue) -> {
            drawGraph(newValue, series2, functions);
            drawGraph(newValue, improvedEulersErrorSeries, errors);
        });
        rungeKutta.selectedProperty().addListener((observable, oldValue, newValue) -> {
            drawGraph(newValue, series3, functions);
            drawGraph(newValue, rungeKuttaErrorSeries, errors);
        });
        original.selectedProperty().addListener((observable, oldValue, newValue) -> {
            drawGraph(newValue, series4, functions);
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

        series1.getData().clear();
        series2.getData().clear();
        series3.getData().clear();
        series4.getData().clear();

        series1 = (Eulers.Eulers(x0, y0, X, N));
        series2 = ImprovedEuler.Eulers(x0, y0, X, N);
        series3 = RungeKutta.rungeKutta(x0, y0, X, N);
        series4 = Analitical.analitical(x0, y0, X, N);

        calculateErrors(series1, eulersErrorSeries);
        calculateErrors(series2, improvedEulersErrorSeries);
        calculateErrors(series3, rungeKuttaErrorSeries);

    }


    public void calculateErrors(XYChart.Series<Number, Number> generatedSeries,
                                XYChart.Series<Number, Number> errorSeries) {
        errorSeries.getData().clear();
        int numberOfEements = series4.getData().size() > generatedSeries.getData().size() ?
                generatedSeries.getData().size() : series4.getData().size();

        for (int i = 0; i < numberOfEements; i++) {
            // Calculate error of generated series
            double temp = Math.abs((double) series4.getData().get(i).getYValue()
                    - (double) generatedSeries.getData().get(i).getYValue());

            // Add error to error series
            errorSeries.getData().add(new XYChart.Data<>(series4.getData().get(i).getXValue(), temp));
        }

    }

    public void calculateMaxErrors() {
        double temp;
        double x0 = 0;
        double X = 5;
        double y0 = 0;
        int Nmin = Integer.parseInt(this.Nmin.getText());
        int Nmax = Integer.parseInt(this.Nmax.getText());

        eulersMaxErrorSeries.getData().clear();
        improvedEulersMaxErrorSeries.getData().clear();
        rungeKuttaMaxErrorSeries.getData().clear();

        Function.recalculateConstant(x0, y0);
        double originalValue;

        for (int N = Nmin; N <= Nmax; N++) {
            originalValue = Analitical.function(X);
            temp = Math.abs(originalValue - (double) Eulers.Eulers(x0, X, y0, N).getData().get(N));
            eulersMaxErrorSeries.getData().add(new XYChart.Data<>(N, temp));
            temp = Math.abs(originalValue - (double) ImprovedEuler.Eulers(x0, X, y0, N).getData().get(N));
            improvedEulersMaxErrorSeries.getData().add(new XYChart.Data<>(N, temp));
            temp = Math.abs(originalValue - (double) RungeKutta.rungeKutta(x0, X, y0, N).getData().get(N));
            rungeKuttaMaxErrorSeries.getData().add(new XYChart.Data<>(N, temp));
        }
    }


}
