package app;

import java.util.Scanner;

public class Test implements ImplementMe{

    // public void overrideMe(String str, int a);

    @Override
    public void overrideMe(String str, int a) {
        for (int i = 0; i < a; i++) {
            System.out.println(str);
        }
    }

    // public int fibonacci(int n) {
    // if (n == 0)
    // return 0;
    // else if (n == 1)
    // return 1;
    // else
    // return fibonacci(n - 1) + fibonacci(n - 2);
    // }

    // public int fibonacci(int n) {
    // int num1 = 0, num2 = 1;
    // for (int i = 1; i <= n; ++i) {
    // System.out.print(num1 + " ");
    // int sum = num1 + num2;
    // num1 = num2;
    // num2 = sum;
    // }
    // return num1;
    // }

    public static void validate(String input) throws NoSwearingException {
        if (input.equals("heck")) {
            throw new NoSwearingException("No Swearing!");
        }
    }

    public static void main(String[] args) {

        try (Scanner scan = new Scanner(System.in)) {
            String input = scan.nextLine();
            while (!input.equals("exit")) {
                validate(input);
                System.out.println(input);
            }
        } catch (NoSwearingException e) {
            System.out.println("You can't be swearing!");
        }
    }
}