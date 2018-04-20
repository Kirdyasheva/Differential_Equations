package sample;

import javafx.scene.chart.XYChart;

public class ImprovedEuler {
    public static XYChart.Series Eulers(double x0, double y0, double x, double n) {
        double currentX = x0;
        double currentY = y0;
        XYChart.Series series = new XYChart.Series();

        while (currentX <= x + n) { //the euler method and the chart plotting

            series.getData().add(new XYChart.Data(currentX, currentY));

            currentY += n * Function.func(currentX + n * 0.5, currentY + n * 0.5 * Function.func(currentX, currentY));
            currentX += n; //euler method calculation
        }
        return series;
    }
}
