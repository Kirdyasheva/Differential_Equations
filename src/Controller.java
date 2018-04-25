import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import methods.*;


public class Controller {
    public TextField X;
    public TextField x0;
    public TextField N;
    public TextField y0;
    public TextField Nmax;
    public TextField Nmin;

    public CheckBox euler;
    public CheckBox improvedEuler;
    public CheckBox rungeKutta;
    public CheckBox analytical;


    @FXML
    private LineChart<Number, Number> functions;
    @FXML
    private LineChart<Number, Number> errors;
    @FXML
    private LineChart<Number, Number> maxErrors;

    @FXML
    private NumberAxis functionsXAxis;
    @FXML
    private NumberAxis functionsYAxis;

    @FXML
    private NumberAxis errorsXAxis;
    @FXML
    private NumberAxis errorsYAxis;

    @FXML
    private NumberAxis maxErrorsXAxis;
    @FXML
    private NumberAxis maxErrorsYAxis;

    public XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
    public XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
    public XYChart.Series<Number, Number> series3 = new XYChart.Series<>();
    public XYChart.Series<Number, Number> series4 = new XYChart.Series<>();

    private XYChart.Series<Number, Number> eulersErrorSeries = new XYChart.Series<>();
    private XYChart.Series<Number, Number> improvedEulersErrorSeries = new XYChart.Series<>();
    private XYChart.Series<Number, Number> rungeKuttaErrorSeries = new XYChart.Series<>();

    private XYChart.Series<Number, Number> eulersMaxErrorSeries = new XYChart.Series<>();
    private XYChart.Series<Number, Number> improvedEulersMaxErrorSeries = new XYChart.Series<>();
    private XYChart.Series<Number, Number> rungeKuttaMaxErrorSeries = new XYChart.Series<>();

    public Controller() {
    }

    @FXML
    public void initialize() {
        setNames();

        // functions chart
        functions.setAnimated(false);

        functionsXAxis.setAutoRanging(false);
        functionsXAxis.setLowerBound(0);
        functionsXAxis.setUpperBound(5);
        functionsXAxis.setTickUnit(0.5);

        functionsYAxis.setAutoRanging(false);
        functionsYAxis.setLowerBound(-1000);
        functionsYAxis.setUpperBound(1000);
        functionsYAxis.setTickUnit(100.0);

        // errors chart
        errors.setAnimated(false);

        errorsXAxis.setAutoRanging(false);
        errorsXAxis.setLowerBound(0);
        errorsXAxis.setUpperBound(2.5);
        errorsXAxis.setTickUnit(0.25);

        errorsYAxis.setAutoRanging(false);
        errorsYAxis.setLowerBound(0);
        errorsYAxis.setUpperBound(100);
        errorsYAxis.setTickUnit(10.0);

        // max errors chart
        maxErrors.setAnimated(true);

        maxErrorsXAxis.setAutoRanging(true);
        maxErrorsXAxis.setLowerBound(15);
        maxErrorsXAxis.setUpperBound(100);
        maxErrorsXAxis.setTickUnit(10);

        maxErrorsYAxis.setAutoRanging(true);
        maxErrorsYAxis.setLowerBound(0);
        maxErrorsYAxis.setUpperBound(0.00000001);
        maxErrorsYAxis.setTickUnit(0.000000001);


        maxErrors.getData().add(eulersMaxErrorSeries);
        maxErrors.getData().add(improvedEulersMaxErrorSeries);
        maxErrors.getData().add(rungeKuttaMaxErrorSeries);

        calculate();
        calculateMaxErrors();
    }

    public void setNames() {
        series1.setName("Euler method");
        series2.setName("Improved Euler method");
        series3.setName("Runge-Kutta method");
        series4.setName("Analytical Solution");
        functions.setTitle("xy^2 + 3xy");
        eulersErrorSeries.setName("Euler's Method");
        eulersMaxErrorSeries.setName("Euler's Method");
        improvedEulersErrorSeries.setName("Improved Euler's Method");
        improvedEulersMaxErrorSeries.setName("Improved Euler's Method");
        rungeKuttaErrorSeries.setName("Runge-Kutta Method");
        rungeKuttaMaxErrorSeries.setName("Runge-Kutta Method");
    }

    @FXML
    public void drawEuler() {
        drawGraph(euler.isSelected(), series1, functions);
        drawGraph(euler.isSelected(), eulersErrorSeries, errors);
    }

    @FXML
    public void drawImprovedEuler() {
        drawGraph(improvedEuler.isSelected(), series2, functions);
        drawGraph(improvedEuler.isSelected(), improvedEulersErrorSeries, errors);
    }

    @FXML
    public void drawRungeKutta() {
        drawGraph(rungeKutta.isSelected(), series3, functions);
        drawGraph(rungeKutta.isSelected(), rungeKuttaErrorSeries, errors);
    }

    @FXML
    public void drawAnalytical() {
        drawGraph(analytical.isSelected(), series4, functions);
    }

    @FXML
    public void recalculate() {
        calculate();
        calculateMaxErrors();
    }

    @FXML
    public void recalculateMaxErrors() {
        calculateMaxErrors();
    }

    private void drawGraph(Boolean checkBoxValue,
                           XYChart.Series<Number, Number> series,
                           LineChart<Number, Number> chart) {
        if (checkBoxValue) {
            chart.getData().add(series);
        } else {
            chart.getData().remove(series);
        }
    }

    private void calculate() {
        double x0 = Double.parseDouble(this.x0.getText());
        double X = Double.parseDouble(this.X.getText());
        double y0 = Double.parseDouble(this.y0.getText());
        int N = Integer.parseInt(this.N.getText());

        series1.getData().clear();
        series2.getData().clear();
        series3.getData().clear();
        series4.getData().clear();

        series1.getData().addAll(Eulers.Eulers(x0, y0, X, N).getData());
        series2.getData().addAll(ImprovedEuler.Eulers(x0, y0, X, N).getData());
        series3.getData().addAll(RungeKutta.rungeKutta(x0, y0, X, N).getData());
        series4.getData().addAll(Analytical.analytical(x0, y0, X, N).getData());

        calculateErrors(series1, eulersErrorSeries);
        calculateErrors(series2, improvedEulersErrorSeries);
        calculateErrors(series3, rungeKuttaErrorSeries);
    }

    public void calculateErrors(XYChart.Series<Number, Number> generatedSeries,
                                XYChart.Series<Number, Number> errorSeries) {
        errorSeries.getData().clear();
        int numberOfElements = series4.getData().size() > generatedSeries.getData().size() ?
                generatedSeries.getData().size() : series4.getData().size();

        for (int i = 0; i < numberOfElements; i++) {
            // Calculate error of generated series
            double temp = Math.abs((double) series4.getData().get(i).getYValue()
                    - (double) generatedSeries.getData().get(i).getYValue());

            // Add error to error series
            errorSeries.getData().add(new XYChart.Data<>(series4.getData().get(i).getXValue(), temp));
        }

    }

    public void calculateMaxErrors() {
        double temp;
        double x0 = Double.parseDouble(this.x0.getText());
        double X = Double.parseDouble(this.X.getText());
        double y0 = Double.parseDouble(this.y0.getText());
        int Nmin = Integer.parseInt(this.Nmin.getText());
        int Nmax = Integer.parseInt(this.Nmax.getText());

        eulersMaxErrorSeries.getData().clear();
        improvedEulersMaxErrorSeries.getData().clear();
        rungeKuttaMaxErrorSeries.getData().clear();

        double originalValue;

        for (int N = Nmin; N <= Nmax; N++) {
            originalValue = Analytical.function(X);
            temp = Math.abs(originalValue - ((XYChart.Series<Number, Number>) Eulers.Eulers(x0, y0, X, N)).getData().get(N).getYValue().doubleValue());
            eulersMaxErrorSeries.getData().add(new XYChart.Data<>(N, temp));
            temp = Math.abs(originalValue - ((XYChart.Series<Number, Number>) ImprovedEuler.Eulers(x0, y0, X, N)).getData().get(N).getYValue().doubleValue());
            improvedEulersMaxErrorSeries.getData().add(new XYChart.Data<>(N, temp));
            temp = Math.abs(originalValue - ((XYChart.Series<Number, Number>) RungeKutta.rungeKutta(x0, y0, X, N)).getData().get(N).getYValue().doubleValue());
            rungeKuttaMaxErrorSeries.getData().add(new XYChart.Data<>(N, temp));
        }
    }
}
