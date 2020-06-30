package simple_grader;

import java.util.ArrayList;

public final class Paper {

    ArrayList<String> studentAnswers = new ArrayList<String>();
    private boolean exitStatus = false;
    private double correct = 0;
    private double length = 0;
    private boolean ADMIN = false;

    public Paper(boolean ADMIN) {
        this.ADMIN = ADMIN;
    }

    public void answer(String answer) {
        if (answer == null) {
            return;
        }
        if (ADMIN)
            System.out.println(answer.toLowerCase());
        studentAnswers.add(answer.toLowerCase());
    }

    public void setExitStatus(int exitValue) {
        this.exitStatus = exitValue == 0;
    }

    public boolean getExitStatus() {
        return exitStatus;
    }

    public double getScore() {
        return (double) ((int) (10000.0 * correct / length)) / 100.0;
    }

    public void grade(Test test) {

        length = test.getLength();
        if (studentAnswers.size() < length) {
            System.out.println("Warning: You did not answer every question\n");
        } else if (studentAnswers.size() > length) {
            System.out.println("Warning: You gave too many answers\n");
        }

        Object[] Questions = test.getQuestions();
        Object[] Answers = test.getAnswers();
        correct = 0;
        for (int i = 0; i < Questions.length; i++) {

            try {
                Object question = Questions[i];
                Object answer = Answers[i];

                if (ADMIN) {
                    System.out.println("Question : " + question.toString());
                    System.out.println("Answer : " + studentAnswers.get(i).toString());
                    System.out.println("Expected Answer : " + Answers[i].toString());
                    if (answer.toString().toLowerCase().equals(studentAnswers.get(i)))
                        correct++;
                } else {
                    System.out.print("Question " + i + " : " + studentAnswers.get(i).toString());
                    if (answer.toString().toLowerCase().equals(studentAnswers.get(i))) {
                        System.out.println(" | Correct √");
                        correct++;
                    } else {
                        System.out.println(" | Incorrect X");
                    }
                }

                System.out.println();

            } catch (IndexOutOfBoundsException e) {
                if (ADMIN)
                    System.out.println("Missing answer for question " + i + "\n");
            }
        }
    }
}