package app;

public class Recursion {
    public static String call(String a) {
        if (a.length() == 1)
            return a;
        return a.substring(0, 2) + call(a.substring(0, a.length() - 1));
    }

    public static void main(String[] args) {
        System.out.println(call("yes"));
    }
}