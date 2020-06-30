package practiceAnswers;

import java.util.Scanner;

public class Exception {

    /**
     * This method decides how to manipulate it's parameters based off their values
     * <p>
     * If {@code b < 5} then the result should be {@code a[1]/b}
     * <p>
     * If {@code a[5] == b} then the result should be {@code a[a[5]/b]}
     * <p>
     * If {@code a.length < b} then the result should be {@code b/a.length}
     * <p>
     * In any other case return {@code b/a[1]}
     * <p>
     * This method also catches any errors that may occur and returns -1 if so
     * 
     * @param a integer array to be searched
     * @param b second integer argument
     * @return the calculated integer, -1 if unable to calculate
     */
    public static int compute(int[] a, int b) {

        try {
            if (b < 5) {
                return a[1] / b;
            } else if (a[5] == b) {
                return a[a[5] / b];
            } else if (a.length < b) {
                return b / a.length;
            } else {
                return b / a[1];
            }
        } catch (ArithmeticException | ArrayIndexOutOfBoundsException e) {
            return -1;
        }
    }

    /**
     * This helper method converts a string representing an array of integers,
     * delimited by the {@code ,} character, into an array of integers
     * 
     * @param str the delimited string of integers
     * @return the integer array
     */
    public static int[] arrayString(String str) {
        if (str == null || str.length() == 0)
            return new int[] {};
        String[] arr = str.split(",");
        for (String i : arr) {
            if (i.length() == 0)
                throw new NumberFormatException("Malformed string array");
        }
        int[] nums = new int[arr.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(arr[i]);
        }
        return nums;
    }

    /**
     * This main method is already setup to use a scanner to read user input
     * <p>
     * Only edit where your code should go
     * <p>
     * User Input:
     * <p>
     * First line is an integer array delimited by a ,
     * <p>
     * Second line is an integer
     * <p>
     * Enter exit at anytime to exit the program
     * 
     * @param args String array passed when calling main
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String voice = "";
        boolean first = true;
        int[] a = new int[] { 0 };
        int b = 0;
        while (!(voice = scan.nextLine()).equals("exit")) {
            if (first) {
                a = arrayString(voice);
            } else {
                b = Integer.parseInt(voice);
                System.out.println(compute(a, b));
            }

            first = !first;
        }
        scan.close();
    }
}