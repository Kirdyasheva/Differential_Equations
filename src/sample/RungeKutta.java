package sample;

import javafx.scene.chart.XYChart;

public class RungeKutta {
    public static XYChart.Series rungeKutta(double x0, double y0, double x, double n) {
        double currentX = x0;
        double currentY = y0;
        double k1;
        double k2;
        double k3;
        double k4;
        XYChart.Series series = new XYChart.Series();

        while (currentX <= x + n) {
            series.getData().add(new XYChart.Data(currentX, currentY));
            k1 = Function.func(currentX, currentY);
            k2 = Function.func(currentX + n * 0.5, currentY + n * k1 * 0.5);
            k3 = Function.func(currentX + n * 0.5, currentY + n * k2 * 0.5);
            k4 = Function.func(currentX + n, currentY + n * k3);

            currentY += n / 6 * (k1 + 2 * k2 + 2 * k3 + k4);
            currentX += n;
        }

        return series;
    }
}
