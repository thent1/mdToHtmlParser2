package org.example;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class FileSaver {

    public static void stringToFile(String inputString, String filepath) throws IOException {
        File file = new File(filepath);
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.println(inputString);
        System.out.println("File successfully saved");
        printWriter.close();
    }

    public static void arrayToFile(String[] inputArray, String filepath) throws IOException {
        String inputString = String.join("\n", inputArray);
        File file = new File(filepath);
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.println(inputString);
        System.out.println("File successfully saved");
        printWriter.close();
    }

}
