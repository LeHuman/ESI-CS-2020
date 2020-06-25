package simple_grader;

import java.util.ArrayList;

public class Tests { // TODO: move tests to external encrypted file or online somewhere
    public static ArrayList<Object[]> RECURSION = new ArrayList<Object[]>() {
        private static final long serialVersionUID = 1L;
        {
            add(new String[] { "", "JA", "CAJ", "JACJAC", "JJACJACJ", "JACJACJCJACJACJACJ", "JJAAJJAAJCACAJJAC" });
            add(new Integer[] { 0, 0, 0, 2, 2, 5, 1 });
        }
    };
    public static ArrayList<Object[]> EXCEPTION = new ArrayList<Object[]>() {
        private static final long serialVersionUID = 1L;
        {
            add(new String[] { "oh hi hec-", "And then I said, HeCk!", "What the, frick do you do?", "hecking",
                    "you a DUmBo Bro", "thats pretty cool", "GwOAEs you little, you can't say heck!", "wow", "heck",
                    "24" });
            add(new String[] { "none", "heck", "frick", "heck", "dumbo", "cool", "heck", "none", "none", "none" });
        }
    };
}