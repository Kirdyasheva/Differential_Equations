import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("Form.fxml"));
        //primaryStage.setTitle("Chart");

        /*NumberAxis xAxis = new NumberAxis(0, 5, 1);
        NumberAxis yAxis = new NumberAxis(-10, 1000, 1);

        TextField method = new TextField();

        LineChart<Number, Number> chart = new LineChart<Number, Number>(xAxis, yAxis);
        chart.setTitle("xy^2 + 3xy");
        XYChart.Series euler = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        XYChart.Series series3 = new XYChart.Series();
        XYChart.Series series4 = new XYChart.Series();

        euler.setName("Euler method");
        series2.setName("Improved Euler method");
        series3.setName("Analytical Solution");
        series4.setName("Runge-Kutta method");

        double x0 = 0.0;
        double y0 = 3.0;
        double x = 5.5;
        double n = 0.001; //the size of the step

        double currentX = x0;
        double currentY = y0;

        //ObservableList<XYChart.Data> data = FXCollections.observableArrayList();

        while (currentX <= x + n) { //the euler method and the chart plotting
            //data.add(new XYChart.Data(currentX, currentY)); //adding new points for the plot

            euler.getData().add(new XYChart.Data(currentX, currentY));

            currentY += n * func(currentX, currentY); //euler method calculation
            currentX += n; //euler method calculation
        }

        //euler.setData(data);

        currentX = x0;
        currentY = y0;

        while (currentX <= x + n) {
            series2.getData().add(new XYChart.Data(currentX, currentY)); //adding data to the chart

            //currentY += 0.5 * n * (func(currentX, currentY) + func(currentX + n, currentY + n * func(currentX, currentY)));
            currentY += n * func(currentX + n * 0.5, currentY + n * 0.5 * func(currentX, currentY));
            currentX += n; //improved Euler method calculation
        }

        currentX = x0;
        currentY = y0;

        while (currentX <= x + n) {
            series3.getData().add(new XYChart.Data(currentX, currentY)); //adding data to the chart

            currentY = analytic(currentX);
            currentX += n; //analytical solution plotting
        }

        currentX = x0;
        currentY = y0;
        double k1;
        double k2;
        double k3;
        double k4;
        //ObservableList<XYChart.Data> data = FXCollections.observableArrayList();

        while (currentX <= x + n) {
            //data.add(new XYChart.Data(currentX, currentY)); //adding new points for the plot

            series4.getData().add(new XYChart.Data(currentX, currentY));
            k1 = func(currentX, currentY);
            k2 = func(currentX + n * 0.5, currentY + n * k1 * 0.5);
            k3 = func(currentX + n * 0.5, currentY + n * k2 * 0.5);
            k4 = func(currentX + n, currentY + n * k3);

            currentY += n / 6 * (k1 + 2 * k2 + 2 * k3 + k4);
            currentX += n;
        }

        Scene scene = new Scene(chart, 600, 600);
        chart.getData().add(euler);
        chart.getData().add(series2);
        chart.getData().add(series3);
        chart.getData().add(series4);*/

        /*NumberAxis xAxis = new NumberAxis(0, 5, 1);
        NumberAxis yAxis = new NumberAxis(-10, 1000, 1);
        LineChart<Number, Number> chart = new LineChart<Number, Number>(xAxis, yAxis);
        Scene scene = new Scene(chart, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();*/

        Parent root = FXMLLoader.load(getClass().getResource("Form.fxml"));
        primaryStage.setTitle("Differential Equations - Numerical Methods");
        primaryStage.setScene(new Scene(root, 1300, 600));
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public double func(double x, double y) {
        return (x * y * y) + (3 * x * y);
    }

    public double analytic(double x) {
        double y;
        y = 3 / (2 * Math.pow(Math.E, -3 / 2 * x * x) - 1);
        return y;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
