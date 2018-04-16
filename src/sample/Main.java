package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Chart");

        NumberAxis xAxis = new NumberAxis(0, 5, 1);
        NumberAxis yAxis = new NumberAxis(-10, 1000, 1);

        LineChart<Number, Number> chart = new LineChart<Number, Number>(xAxis, yAxis);
        chart.setTitle("xy^2 + 3xy");
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();

        series1.setName("Euler method");
        series2.setName("Improved Euler method");

        double x0 = 0.0;
        double y0 = 3.0;
        double x = 5.5;
        double n = 0.0; //the size of the step

        double currentX = x0;
        double currentY = y0;

        //ObservableList<XYChart.Data> data = FXCollections.observableArrayList();

        while (currentX <= x + n) { //the euler method and the chart plotting
            //data.add(new XYChart.Data(currentX, currentY)); //adding new points for the plot

            series1.getData().add(new XYChart.Data(currentX, currentY));

            currentY += n * func(currentX, currentY); //euler method calculation
            currentX += n; //euler method calculation
        }

        //series1.setData(data);

        currentX = x0;
        currentY = y0;

        while (currentX <= x + n) {
            series2.getData().add(new XYChart.Data(currentX, currentY)); //adding data to the chart

            currentY += 0.5 * n * (func(currentX, currentY) + func(currentX + n, currentY + n * func(currentX, currentY)));
            currentX += n; //improved Euler method calculation
        }

        Scene scene = new Scene(chart, 600, 600);
        chart.getData().add(series1);
        chart.getData().add(series2);

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
