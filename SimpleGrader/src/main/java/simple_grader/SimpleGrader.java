package simple_grader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import simple_grader.resource_converter.TestCases;

public class SimpleGrader {
    private static final long WAITTIME = 6;
    private static final String SECRETKEY = "Jumex";
    private static final String VERSION = "V1.0";
    private static boolean ADMIN = false;
    private static HashMap<String, Test> tests;

    private static Process runFile(String fileName) throws IOException, SecurityException {
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("java", fileName);
        Process pro = builder.start();
        return pro;
    }

    private static Paper askQuestions(Process pro, Test test) throws InterruptedException { // TODO: check for timeout
        OutputStream stdin = pro.getOutputStream();
        InputStream stdout = pro.getInputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));

        Object[] questions = test.getQuestions();
        Paper paper = new Paper(ADMIN);
        boolean premature = false;

        for (int i = 0; i < questions.length; i++) {
            Object question = questions[i];
            boolean success = false;
            try {
                writer.write(question.toString());
                writer.write("\n");
                writer.flush();
                success = true;
            } catch (IOException e) {
                System.out.println("Warning: Issue sending question, going to next question");
            }
            if (success) {
                try {
                    String answer = reader.readLine();
                    paper.answer(answer);
                } catch (IOException e) {
                    System.out.println("Warning: Issue receiving answer, going to next question");
                    paper.answer("nil");
                }
            }
            if (!pro.isAlive()) {
                premature = true;
                break;
            }
        }

        if (!premature) {
            try {
                writer.write("exit");
                writer.flush();
                writer.close();
            } catch (IOException e) {
                System.out.println("Warning: Issue sending exit command");
            }

            System.out.println("\n-----< Waiting to exit >-----");
            pro.waitFor(WAITTIME, TimeUnit.SECONDS);
            if (pro.isAlive()) {
                pro.destroyForcibly();
                System.out.println("File took too long to exit");
            }
            if (!pro.isAlive())
                paper.setExitStatus(pro.exitValue());
        }
        return paper;
    }

    private static Paper test(Process runner, Test test) throws IOException, InterruptedException {
        Paper studentPaper = askQuestions(runner, test);

        if (studentPaper.getExitStatus())
            System.out.println("\nSuccesfully ran file!");
        else
            System.out.println("\nFile closed abnormally");

        return studentPaper;
    }

    private static Process getFileProcess(String fileName)
            throws IOException, InterruptedException, FailedToCompileException {

        Process runner = runFile(fileName + ".java");
        return runner;
    }

    private static void cleanUp() {

    }

    private static void vocalizeStart(String test, String file) {
        System.out.println("\nSimpleGrader " + VERSION + " | " + test + " | " + file + ".java");
    }

    private static void runTest(String testName, String fileName)
            throws TestNotFoundException, IOException, InterruptedException, FailedToCompileException {
        if (!tests.containsKey(testName))
            throw new TestNotFoundException();

        Test test = tests.get(testName);
        Process studentProgram = getFileProcess(fileName);

        vocalizeStart(testName, fileName);

        Paper studentPaper = test(studentProgram, test);

        System.out.println("\n---------[ Grading ]---------\n");
        studentPaper.grade(test);
        System.out.println("-----------------------------\n");
        System.out.println("Grade: " + studentPaper.getScore() + "%");
        System.out.println("\n-----------------------------\n");
    }

    private static void help() {
        String str = "\n-----[ Avaliable Tests ]-----\n\n";
        for (HashMap.Entry<String, Test> test : tests.entrySet()) {
            str += test.getKey().toString() + "\n";
        }
        str += "\n-----------[ Help ]----------\n";
        str += "\nWhen making a homework file make sure to\nuse the scanner class to take in input.";
        str += "\nAny new line that is output by your file\nwill be considered an answer.";
        str += "\nAnswers are case insensitive.\n";
        str += "\nIn anycase, a template for any specific\nhomework should have been given to you.\n";
        str += "\n----------[ Usage ]----------\n";
        str += "\nMake sure to name your file the appropriate\nname and use this jar to test it\n";
        str += "\nIf testing freezes pressing Ctrl + C when\nfocused on the terminal will close the process\n";
        str += "\nDo not include '.java' when typing filename\n";
        str += "\nEg. usage:\n";
        str += "\n\tjava -jar SimpleGrader.jar Recursion";
        System.out.println(str);
    }

    // TODO: check that JDK is installed
    public static void main(String[] args) throws IOException, InconsistentArrayLengthException, InterruptedException {
        HashMap<String, Test> testMap = TestCases.getMap(SECRETKEY);
        tests = testMap;

        String testName;
        String fileName;

        try {
            testName = args[0];
            fileName = args[1];
        } catch (IndexOutOfBoundsException e) {
            help();
            return;
        }

        if (args.length == 2 && args[1] != null && args[1].equals(SECRETKEY)) {
            ADMIN = true;
            System.out.println("\n----------< ADMIN >----------");
        }

        try {
            try {
                runTest(testName.toUpperCase(), fileName);
            } catch (Exception e) {
                System.out.println("\n------[ ERROR MESSAGE ]------");
                if (ADMIN)
                    System.out.println("\n" + e);
                throw e;
            }
        } catch (FailedToCompileException e) {
            System.out.println("\n" + e.getMessage());
        } catch (TestNotFoundException e) {
            System.out.println("\nTest not found!");
            help();
        } finally {
            cleanUp();
        }
    }
}