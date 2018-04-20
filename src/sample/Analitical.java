package sample;

import javafx.scene.chart.XYChart;

public class Analitical {
    public static double function(double x) {
        double y;
        y = 3 / (2 * Math.pow(Math.E, -3 / 2 * x * x) - 1);
        return y;
    }

    public static XYChart.Series analitical(double x0, double y0, double x, double N) {
        double currentX = x0;
        double currentY = y0;
        XYChart.Series series = new XYChart.Series();

        double n = (Math.abs(x - x0)) / N;


        while (currentX <= x + n) { //the euler method and the chart plotting

            series.getData().add(new XYChart.Data(currentX, currentY));

            currentY += function(currentX); //euler method calculation
            currentX += n; //euler method calculation
        }
        return series;
    }
}
