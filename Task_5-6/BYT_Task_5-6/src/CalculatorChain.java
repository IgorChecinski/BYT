import java.util.Scanner;

public class CalculatorChain {

    public static void main(String[] args) {
        Handler addHandler = new AddHandler();
        Handler subtractHandler = new SubtractHandler();
        Handler multiplyHandler = new MultiplyHandler();
        Handler divideHandler = new DivideHandler();

        // Setting the chain of responsibility
        addHandler.setNextHandler(subtractHandler);
        subtractHandler.setNextHandler(multiplyHandler);
        multiplyHandler.setNextHandler(divideHandler);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter first number:");
        double num1 = scanner.nextDouble();

        System.out.println("Enter second number:");
        double num2 = scanner.nextDouble();

        System.out.println("Enter operation (add, subtract, multiply, divide):");
        String operation = scanner.next();

        Operation op = new Operation(num1, num2, operation);
        addHandler.process(op);

        scanner.close();
    }
}
