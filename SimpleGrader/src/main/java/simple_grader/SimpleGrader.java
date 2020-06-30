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

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import simple_grader.resource_converter.TestCases;

import java.lang.InterruptedException;

public class SimpleGrader {
    private static final long WAITTIME = 6;
    private static final String SECRETKEY = "Jumex";
    private static final String VERSION = "V1.0";
    private static boolean ADMIN = false;
    private static HashMap<String, Test> tests;

    private static String getLines(InputStream ins) throws IOException {
        String line = null;
        String str = "\n";
        BufferedReader in = new BufferedReader(new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            str += line;
        }
        str += "\n";
        return str;
    }

    private static Process runFile(String fileName) throws IOException, SecurityException { // TODO: check for timeout
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("java", fileName);
        Process pro = builder.start();
        return pro;
    }

    private static Paper askQuestions(Process pro, Test test) throws InterruptedException {
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

    private static Paper test(String testName, Test test)
            throws IOException, SecurityException, InterruptedException, ClassNotFoundException {
        Process compile = Runtime.getRuntime().exec("javac " + testName + ".java");
        compile.waitFor();
        if (compile.exitValue() != 0) {
            String error = getLines(compile.getErrorStream());
            if (error.contains("error: file not found:"))
                error = "\nFile was not found!\n";
            System.out.println(error);
            throw new ClassNotFoundException("Falied to compile file");
        }

        Process runner = runFile(testName + ".java");
        Paper studentPaper = askQuestions(runner, test);

        if (studentPaper.getExitStatus())
            System.out.println("\nSuccesfully ran file!");
        else
            System.out.println("\nFile closed abnormally");

        return studentPaper;
    }

    private static void runTest(String testName) throws IllegalArgumentException, IOException, InterruptedException,
            SecurityException, ClassNotFoundException {
        Test test = tests.get(testName.toUpperCase());
        Paper studentPaper = test(testName, test);
        System.out.println("\n---------[ Grading ]---------\n");
        studentPaper.grade(test);
        System.out.println("-----------------------------\n");
        System.out.println("Grade: " + studentPaper.getScore() + "%");
        System.out.println("\n-----------------------------\n");
    }

    private static void vocalizeStart(String file) {
        System.out.println("\nSimpleGrader " + VERSION + " | Testing | " + file + ".java");
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
    public static void main(String[] args)
            throws InvalidFormatException, IOException, InconsistentArrayLengthException {
        HashMap<String, Test> testMap = TestCases.getMap(SECRETKEY);
        tests = testMap;

        String str;

        if (args.length == 0) {
            help();
            return;
        } else {
            str = args[0];
        }

        if (args.length == 2 && args[1] != null && args[1].equals(SECRETKEY)) {
            ADMIN = true;
            System.out.println("\n----------< ADMIN >----------");
        }

        try {
            vocalizeStart(str);
            runTest(str);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            if (ADMIN)
                System.out.println(e);
            System.out.println("\nTest not found!");
            help();
        } catch (IOException e) {
            System.out.println(e);
        } catch (SecurityException e) {
            System.out.println(e);
        } catch (InterruptedException e) {
            if (ADMIN)
                System.out.println(e);
        }

    }
}