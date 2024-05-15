package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileScanner {

    public static String toString(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        StringBuilder stringBuilder = new StringBuilder();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
            if (scanner.hasNextLine()) {
                stringBuilder.append("\n");
            }
        }
        scanner.close();
        return stringBuilder.toString();
    }

}
