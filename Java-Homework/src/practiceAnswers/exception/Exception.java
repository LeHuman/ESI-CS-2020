package practiceAnswers.exception;

import java.util.Scanner;

public class Exception {

    // take in numbers and do stuff with it

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String voice = "";
        while (!voice.equals("exit")) {
            voice = scan.nextLine();
        }
        scan.close();
    }
}