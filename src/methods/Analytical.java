package methods;

import javafx.scene.chart.XYChart;

public class Analytical {
    public static double function(double x) {
        double y;
        y = 3 / (2 * Math.pow(Math.E, -3 / 2 * x * x) - 1);
        return y;
    }

    public static XYChart.Series analytical(double x0, double y0, double x, double N) {
        double currentX = x0;
        double currentY = y0;
        XYChart.Series series = new XYChart.Series();
        double n = Math.abs(x - x0) / N;

        while (currentX <= x) { //the analytical calculation and the chart plotting
            series.getData().add(new XYChart.Data(currentX, currentY));

            currentY = function(currentX); // analytical calculation
            currentX += n; // analytical calculation
        }
        return series;
    }
}
