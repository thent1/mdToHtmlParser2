package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        String mdFilePath;
        String outputFilePath;
        String outputFormat = "html";

        if (args.length == 0) {
            System.out.println("Enter arguments for program");
        } else {

            mdFilePath = args[0];

            if (Arrays.asList(args).contains("--format")) {
                outputFormat = args[Arrays.asList(args).indexOf("--format") + 1];
            }

            if (Arrays.asList(args).contains("--out")) {

                outputFilePath = args[Arrays.asList(args).indexOf("--out") + 1];
                saveAsFile(mdFilePath, outputFilePath, outputFormat);
            } else {
                stdOut(mdFilePath, outputFormat);
            }
        }
    }

    public static void stdOut(String filePath, String format) throws Exception {
        String fileText = FileScanner.toString(filePath);
        if (format.equals("html")) {
            String[] output = Editor.mdToHtmlStringArr(fileText);
            outputStrings(output);
        } else if (format.equals("ansi")) {
            String[] output = Editor.mdToAnsiStringArr(fileText);
            outputStrings(output);
        } else {
            throw new IllegalArgumentException("Wrong format");
        }
    }

    public static void saveAsFile(String filePath, String outputPath, String format) throws Exception {
        String fileText = FileScanner.toString(filePath);
        if (format.equals("html")) {
            String output = Editor.mdToHtmlString(fileText);
            FileSaver.stringToFile(output, outputPath);
        } else if (format.equals("ansi")) {
            String output = Editor.mdToAnsiString(fileText);
            FileSaver.stringToFile(output, outputPath);
        } else {
            throw new IllegalArgumentException("Wrong format");
        }

    }

    public static void outputStrings(String[] array) {
        for (String str : array) {
            System.out.println(str);
        }
    }
}