package methods;

public class Function {

    private static double C = 0; //constant for initial value problem

    public static double func(double x, double y) {
        return (x * y * y) + (3 * x * y);
    }

    public static void recalculateConstant(double x, double y){
        C = - (1.0 / 3) * Math.log(Math.abs((y + 3) / y)) - 1.0 / 2 * x * x;
    }
}
