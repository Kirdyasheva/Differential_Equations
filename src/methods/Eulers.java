package methods;

import javafx.scene.chart.XYChart;

public class Eulers {
    public static XYChart.Series Eulers(double x0, double y0, double x, double N) {
        Double currentX = x0;
        Double currentY = y0;
        XYChart.Series series = new XYChart.Series();
        Double n = Math.abs(x - x0) / N;

        while (currentX <= x + n) { //the euler method and the chart plotting
            series.getData().add(new XYChart.Data(currentX, currentY));

            currentY += n * Function.func(currentX, currentY); //euler method calculation
            currentX += n; //euler method calculation
        }
        return series;
    }
}
