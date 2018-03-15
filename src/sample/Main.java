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
        double n = (x-x0)/0.01; //number of iterations

        primaryStage.setScene(new Scene(root, 1200, 600));
        primaryStage.show();
    }

    public double func(double x, double y) {
        return x * y * y + 3 * x * y;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
