package methods;

import javafx.scene.chart.XYChart;

public class ImprovedEuler {
    public static XYChart.Series Eulers(double x0, double y0, double x, double N) {
        double currentX = x0;
        double currentY = y0;
        XYChart.Series series = new XYChart.Series();
        double n = Math.abs(x - x0) / N;
        series.getData().add(new XYChart.Data<>(currentX, currentY));

        double value;
        double temp;

        while (currentX <= x) { //the improved euler method and the chart plotting
            temp = Function.func(currentX,currentY);
            value = n*Function.func(currentX+n/2, currentY+ n*temp/2);
            currentX += n;
            currentY += value;

            //currentY += n * Function.func(currentX + n * 0.5, currentY + n * 0.5 * Function.func(currentX, currentY));
            //currentX += n;
            series.getData().add(new XYChart.Data<>(currentX, currentY));
        }

        return series;
    }
}
