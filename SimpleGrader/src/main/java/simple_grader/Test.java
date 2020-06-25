package simple_grader;

import java.util.ArrayList;

public class Test extends ArrayList<Object[]> {

    private static final long serialVersionUID = 2741324118260330237L;
    private static final int QUESTION = 0;
    private static final int ANSWER = 1;
    private int length;

    Test() {
        this.length = 0;
    }

    Test(Object[] questions, Object[] answers) throws InconsistentArrayLengthException {
        this.length = questions.length;
        if (answers.length != length)
            throw new InconsistentArrayLengthException();
        super.add(questions);
        super.add(answers);
    }

    protected Object[] getQuestions() {
        return super.get(QUESTION);
    }

    protected Object[] getAnswers() {
        return super.get(ANSWER);
    }

    protected int getLength() {
        return length;
    }

}