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
    public static int ternarySearch(int[] search, int key) {
        /**
         * Your Code Here!
         */
        return -1;
    }

    /**
     * This method converts a string representing an array of integers, delimited by
     * the {@code ,} character, into an array of integers
     * 
     * @param str the delimited string of integers
     * @return the integer array
     */
    public static int[] arrayString(String str) {
        String[] arr = str.split(",");
        int[] nums = new int[arr.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(arr[i]);
        }
        return nums;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String voice = "";
        while (!voice.equals("exit")) {
            voice = scan.nextLine();
            int[] searchMe = arrayString(voice);
            voice = scan.nextLine();
            int key = Integer.parseInt(voice);
            System.out.println(ternarySearch(searchMe, key));
            voice = scan.nextLine();
        }
        scan.close();
    }
}