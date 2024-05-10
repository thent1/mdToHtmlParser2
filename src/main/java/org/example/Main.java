package org.example;

import org.example.Editor;
import org.example.FileSaver;
import org.example.FileScanner;

public class Main {
    public static void main(String[] args) throws Exception {
        String mdFilePath;
        String outputFilePath;
        boolean outArgSuccesCondition = (args.length == 3 && args[1].equals("--out"));

        if (args.length == 0) {
            System.out.println("Enter arguments for program");
        } else if (args.length == 1) {
            mdFilePath = args[0];
            stdOut(mdFilePath);
        } else if (outArgSuccesCondition) {
            mdFilePath = args[0];
            outputFilePath = args[2];
            saveAsFile(mdFilePath, outputFilePath);
        } else {
            System.out.println("Wrong arguments");
        }

    }

    public static void stdOut(String filePath) throws Exception {
        String fileText = FileScanner.toString(filePath);
        String[] output = Editor.mdToHtmlStringArr(fileText);
        outputStrings(output);
    }

    public static void saveAsFile(String filePath, String outputPath) throws Exception {
        String fileText = FileScanner.toString(filePath);
        String output = Editor.mdToHtmlString(fileText);
        FileSaver.stringToFile(output, outputPath);
    }
    public static void outputStrings(String[] array) {
        for (String str : array) {
            System.out.println(str);
        }
    }
}