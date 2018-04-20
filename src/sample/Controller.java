package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class Controller {
    TextField X;
    TextField x0;
    TextField N;
    TextField y0;
    TextField Nmax;
    TextField Nmin;

    CheckBox euler;
    CheckBox improvedEuler;
    CheckBox rungeKutta;
    CheckBox original;

    NumberAxis xAxis = new NumberAxis(0, 5, 1);
    NumberAxis yAxis = new NumberAxis(-10, 1000, 1);

    LineChart<Number, Number> chart = new LineChart<Number, Number>(xAxis, yAxis);
    XYChart.Series series1 = new XYChart.Series();
    XYChart.Series series2 = new XYChart.Series();
    XYChart.Series series3 = new XYChart.Series();
    XYChart.Series series4 = new XYChart.Series();

    public void setNamed() {
        series1.setName("Euler method");
        series2.setName("Improved Euler method");
        series3.setName("Analitical Solution");
        series4.setName("Runge-Kutta method");
        chart.setTitle("xy^2 + 3xy");
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

    private void addListenerForBoxe() {
        euler.selectedProperty().addListener((observable, oldValue, newValue) -> {
            drawGraph(newValue, eulersSeries, functions);
            drawGraph(newValue, eulersErrorSeries, errors);
        });
        improvedEuler.selectedProperty().addListener((observable, oldValue, newValue) -> {
            drawGraph(newValue, improvedEulersSeries, functions);
            drawGraph(newValue, improvedEulersErrorSeries, errors);
        });
        rungeKutta.selectedProperty().addListener((observable, oldValue, newValue) -> {
            drawGraph(newValue, rungeKuttaSeries, functions);
            drawGraph(newValue, rungeKuttaErrorSeries, errors);
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

    private void calculate(){
        double x0 = Double.parseDouble(this.x0.getText());
        double X = Double.parseDouble(this.X.getText());
        double y0 = Double.parseDouble(this.y0.getText());
        int N = Integer.parseInt(this.N.getText());


    }

    public Controller(){
        addListenersForText();
        addListenerForBoxe();
        setNamed();
    }

}
