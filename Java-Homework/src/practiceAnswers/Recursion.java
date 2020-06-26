package practiceAnswers;

import java.util.Scanner;

public class Recursion {

    /**
     * This method recursivly counts the number of times the pattern {@code "JAC"}
     * appears in a given string
     * <p>
     * Allowed String methods are: subString length equals
     * 
     * @param str the string to check for a pattern
     * @return the number of times the pattern was found
     */
    public static int findJAC(String str) {
        if (str.length() <= 2)
            return 0;
        if (str.substring(0, 3).equals("JAC"))
            return 1 + findJAC(str.substring(1));
        return findJAC(str.substring(1));
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String voice = "";
        while (!voice.equals("exit")) {
            if (scan.hasNextLine()) {
                voice = scan.nextLine();
                System.out.println(findJAC(voice));
            }
        }
        scan.close();
    }
}