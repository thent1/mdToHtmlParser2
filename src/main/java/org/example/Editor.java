package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Editor {

    public static String mdToHtmlString(String inputString) throws Exception {
        String[] stringArr = inputString.split("\n");
        stringArr = Checker.removeElementsByIndexes(stringArr, Checker.getExtraEmptyIndexes(stringArr));
        Checker.isValid(stringArr);
        stringArr = Editor.replaceAllInArray(stringArr);
        List<Integer> preformattedLines = Checker.getPreformattedLines(stringArr);
        stringArr = Editor.setPrefTags(stringArr);
        stringArr = Editor.setParagraphs(stringArr, preformattedLines);
        String result = String.join("\n", stringArr);
        return result;
    }

    public static String[] mdToHtmlStringArr(String inputString) throws Exception {
        String[] stringArr = inputString.split("\n");
        stringArr = Checker.removeElementsByIndexes(stringArr, Checker.getExtraEmptyIndexes(stringArr));
        Checker.isValid(stringArr);
        stringArr = Editor.replaceAllInArray(stringArr);
        List<Integer> preformattedLines = Checker.getPreformattedLines(stringArr);
        stringArr = Editor.setPrefTags(stringArr);
        stringArr = Editor.setParagraphs(stringArr, preformattedLines);
        return stringArr;
    }

    public static String[] setParagraphs(String[] inputArray, List<Integer> preformattedLinesIndexes) throws Exception {
        if (inputArray.length == 0 || (inputArray[0].equals("") && inputArray.length == 1)) {
            return inputArray;
        }

        String[] result = inputArray;
        result[0] = "<p>" + result[0];
        result[result.length-1] = result[result.length-1] + "</p>";

        List<Integer> emptyLinesList = Checker.getEmptyLinesIndexes(result);
        List<Integer> linesToRemove = new ArrayList<>();

        for (int i = 0; i < emptyLinesList.size(); i++) {
            if (!preformattedLinesIndexes.contains(emptyLinesList.get(i))) {
                result[emptyLinesList.get(i)-1] = result[emptyLinesList.get(i)-1] + "</p>";
                result[emptyLinesList.get(i)+1] = "<p>" + result[emptyLinesList.get(i)+1];
                linesToRemove.add(emptyLinesList.get(i));
            }
        }

        result = Checker.removeElementsByIndexes(result, linesToRemove);

        return result;
    }

    public static String[] setPrefTags(String[] inputArray) {
        List<Integer> indexesOfPrefs = Checker.getPrefMarkIndexes(inputArray);
        String[] result = inputArray;
        boolean isOpened = false;

        for (int i = 0; i < indexesOfPrefs.size(); i++) {
            if (!isOpened) {
                result[indexesOfPrefs.get(i)] = "<pre>";
                isOpened = true;
            } else {
                result[indexesOfPrefs.get(i)] = "</pre>";
                isOpened = false;
            }
        }

        return result;
    }

    public static String[] replaceAllInArray(String[] inputString) throws Exception {
        List<Integer> preformattedLines = Checker.getPreformattedLines(inputString);
        String[] resultArray = inputString;

        for (int i = 0; i < resultArray.length; i++) {
            if (preformattedLines.contains(i)) {
                continue;
            }

            resultArray[i] = replaceBold(resultArray[i]);
            resultArray[i] = replaceItalic(resultArray[i]);
            resultArray[i] = replaceMono(resultArray[i]);
        }

        return resultArray;
    }
    public static String replaceBold(String inputString) {
        Pattern boldPattern = Pattern.compile(Regexps.boldComplete);
        Matcher boldMatcher = boldPattern.matcher(inputString);

        return boldMatcher.replaceAll("<b>$1</b>");
    }

    public static String replaceItalic(String inputString) {
        Pattern italicPattern = Pattern.compile(Regexps.italicComplete);
        Matcher italicMatcher = italicPattern.matcher(inputString);

        return italicMatcher.replaceAll("<i>$1</i>");
    }

    public static String replaceMono(String inputString) {
        Pattern monoPattern = Pattern.compile(Regexps.monospacedComplete);
        Matcher monoMatcher = monoPattern.matcher(inputString);

        return monoMatcher.replaceAll("<tt>$1</tt>");
    }
}
