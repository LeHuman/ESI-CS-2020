package practiceAnswers;

import java.util.Scanner;

public class Search {
    /**
     * This method searches for the index of the given value {@code key} in the
     * array {@code search} by subsequently splitting the array into thirds until it
     * finds the third that contains the key value
     * 
     * This version of ternary search does not have to be recursive
     * 
     * @param search array of integers to be sorted
     * @param key    the value that must be found
     * @return the index of the key, -1 if not found
     */
    public static int ternarySearch(int[] A, int x) {
        int left = 0;
        int right = A.length - 1;

        while (left <= right) {
            int leftMid = left + (right - left) / 3;
            int rightMid = right - (right - left) / 3;

            if (A[leftMid] == x) {
                return leftMid;

            } else if (A[rightMid] == x) {
                return rightMid;
            } else if (A[leftMid] > x) {
                right = leftMid - 1;
            } else if (A[rightMid] < x) {
                left = rightMid + 1;
            } else {
                left = leftMid + 1;
                right = rightMid - 1;
            }
        }
        return -1;
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
     * Second line is the integer key
     * <p>
     * Enter exit at anytime to exit the program
     * 
     * @param args String array passed when calling main
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String voice = "";
        boolean first = true;
        int[] arr = new int[] { 0 };
        int key = 0;
        while (!(voice = scan.nextLine()).equals("exit")) {
            if (first) {
                arr = arrayString(voice);
            } else {
                key = Integer.parseInt(voice);
                System.out.println(ternarySearch(arr, key));
            }

            first = !first;
        }
        scan.close();
    }
}