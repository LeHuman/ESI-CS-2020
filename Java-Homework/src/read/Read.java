package read;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Read {
    public static void main(String[] args) {
File doc = new File("readfile");
try (Scanner read = new Scanner(doc)) {
    while (read.hasNextLine()) {
        System.out.println(read.nextLine());
    }
} catch (FileNotFoundException e) {
    System.out.println("File Not Found!");
} catch (NoSuchElementException e) {
    System.out.println("File is Blank!");
}
    }
}