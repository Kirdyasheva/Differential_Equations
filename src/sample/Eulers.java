package sample;

import javafx.scene.chart.XYChart;

public class Eulers {
    public static XYChart.Series Eulers(double x0, double y0, double x, double N) {
        double currentX = x0;
        double currentY = y0;
        XYChart.Series series = new XYChart.Series();
        double n = (x - x0)/N;

        //ObservableList<XYChart.Data> data = FXCollections.observableArrayList();

        while (currentX <= x + n) { //the euler method and the chart plotting
            //data.add(new XYChart.Data(currentX, currentY)); //adding new points for the plot

            series.getData().add(new XYChart.Data(currentX, currentY));

            currentY += n * Function.func(currentX, currentY); //euler method calculation
            currentX += n; //euler method calculation
        }
        return series;
    }
}
