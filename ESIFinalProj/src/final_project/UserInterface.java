package final_project;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class UserInterface {
    private static OrderManager manager = new OrderManager();

    public static void main(String[] args) {
        String voice = "";
        try (Scanner scan = new Scanner(System.in)) {
            while (!voice.equals("exit")) {
                voice = scan.nextLine().toLowerCase();
                switch (voice) {
                    case "new client":
                        System.out.println("New client made!");
                        break;
                    case "new order":
                        System.out.println("New order made!");
                        break;
                    default:
                        break;
                }
            }
            System.out.println("Goodbye");
        } catch (NoSuchElementException e) {
            System.out.println(e);
        }
    }
}