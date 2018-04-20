package sample;

public class Function {

    private static double C = 0; //constant for initial value problem

    public static double func(double x, double y) {
        return (x * y * y) + (3 * x * y);
    }

    public static void recalculateConstant(double x, double y){
        
    }
}
