package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Chart");

        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();

        LineChart<Number, Number> chart = new LineChart<Number, Number>(xAxis, yAxis);
        chart.setTitle("Euler method of approximation");
        XYChart.Series mainChart = new XYChart.Series();
        mainChart.setName("My chart");

        double x0 = 0;
        double y0 = 3;
        double x = 5.5;
        double n = 0.1; //the size of the step

        double currentX = x0;
        double currentY = y0;

        ObservableList<XYChart.Data> data = FXCollections.observableArrayList();

        while (currentX != x + n) { //the euler method and the chart plotting
            data.add(new XYChart.Data(currentX, currentY)); //adding new points for the plot

            currentY += n * func(currentX, currentY); //euler method calculation
            currentX += n; //euler method calculation
        }

        mainChart.setData(data);

        Scene scene = new Scene(chart, 600, 600);
        chart.getData().add(mainChart);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public double func(double x, double y) {
        return (x * y * y) + (3 * x * y);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
