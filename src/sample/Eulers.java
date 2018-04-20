package sample;

import javafx.scene.chart.XYChart;

public class Eulers {
    public void Eulers(double x0, double y0, double x, double n) {
        double currentX = x0;
        double currentY = y0;

        //ObservableList<XYChart.Data> data = FXCollections.observableArrayList();

        while (currentX <= x + n) { //the euler method and the chart plotting
            //data.add(new XYChart.Data(currentX, currentY)); //adding new points for the plot

            series1.getData().add(new XYChart.Data(currentX, currentY));

            currentY += n * Function.func(currentX, currentY); //euler method calculation
            currentX += n; //euler method calculation
        }
    }
}
