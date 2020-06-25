package simple_grader;

import java.util.ArrayList;

public enum TestEnum {
    RECURSION(Tests.RECURSION), EXCEPTION(Tests.EXCEPTION);

    protected Test test;

    TestEnum(ArrayList<Object[]> testQAs) {
        try {
            test = new Test(testQAs.get(0), testQAs.get(1));
        } catch (InconsistentArrayLengthException e) {
            System.out.println("\n|ERROR creating a test!|\n|      Tell a TA!      |");
        }
    }

    public Test getTest() {
        return test;
    }
}