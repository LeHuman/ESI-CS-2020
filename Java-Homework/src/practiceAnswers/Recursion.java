package practiceAnswers;

public class Recursion {

    /**
     * Recursivly counts the number of times the pattern 'JAC' appears in a given
     * string
     * 
     * The only allowed string methods are: subString length equals
     * 
     * 
     * @param str The string to check
     * @return int The returned number of times the pattern was found
     */
    public static int findJAC(String str) {
        if (str.length() <= 2)
            return 0;
        if (str.substring(0, 3).equals("JAC"))
            return 1 + findJAC(str.substring(1));
        return findJAC(str.substring(1));
    }

    public static void main(String[] args) {
        System.out.println("okiedokie");
    }
}