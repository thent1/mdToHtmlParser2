package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checker {


    static String[] stringArray;

    public static List<Integer> getEmptyLinesIndexes(String[] inputArray) {
        List<Integer> resultArray = new ArrayList<>();

        for (int i = 0; i < inputArray.length; i++) {
            if (inputArray[i] == "") {
                resultArray.add(i);
            }
        }

        return resultArray;
    }

    public static boolean isValid(String[] inputArray) throws Exception {
        List<Integer> preformattedLines = Checker.getPreformattedLines(inputArray);

        for (int i = 0; i < inputArray.length; i++) {
            if (preformattedLines.contains(i)){
                continue;
            }

            if (isNested(inputArray[i])) {
                throw new IllegalArgumentException("Text shouldn't contain nested markers");
            }

            if (!isClosed(inputArray[i])) {
                throw new IllegalArgumentException("Markers unclosed");
            }
        }

        return true;
    }

    public static List<Integer> getPrefMarkIndexes(String[] stringArray) {
        List<Integer> arrayOfIndexes = new ArrayList<>();
        for (int i = 0; i < stringArray.length; i++) {
            if (stringArray[i].equals("```")) {
                if (stringArray[i].length() > 3) {
                    throw new IllegalArgumentException("In line with preformatting marker shouldn't be other symbols");
                }
                arrayOfIndexes.add(i);
            }

        }
        return arrayOfIndexes;
    }

    public static boolean isNested(String inputString) {

        List<String> trimmedList = new ArrayList<>();

            Pattern boldPattern = Pattern.compile(Regexps.boldComplete);
            Matcher boldMatcher = boldPattern.matcher(inputString);

            while (boldMatcher.find()) {
                trimmedList.add(boldMatcher.group().substring(2, boldMatcher.group().length() - 2));
            }

            Pattern italicPattern = Pattern.compile(Regexps.italicComplete);
            Matcher italicMatcher = italicPattern.matcher(inputString);

            while (italicMatcher.find()) {
                trimmedList.add(italicMatcher.group().substring(1, italicMatcher.group().length() - 1));
            }

            Pattern monoPattern = Pattern.compile(Regexps.monospacedComplete);
            Matcher monoMatcher = monoPattern.matcher(inputString);

            while (monoMatcher.find()) {
               trimmedList.add(monoMatcher.group().substring(1, monoMatcher.group().length() - 1));
            }

            for (int i = 0; i < trimmedList.size(); i++) {

                boldMatcher = boldPattern.matcher(trimmedList.get(i));
                italicMatcher = italicPattern.matcher(trimmedList.get(i));
                monoMatcher = monoPattern.matcher(trimmedList.get(i));

                boolean falseExpression = boldMatcher.find() || italicMatcher.find() || monoMatcher.find();

                if (falseExpression) {
                    return true;
                }
            }


        return false;
    }

    public static boolean isClosed(String inputString) {

        final String[] completeRegexs = {Regexps.boldComplete, Regexps.italicComplete, Regexps.monospacedComplete};
        final String[] partialRegexs = {
                Regexps.boldLeft, Regexps.boldRight,
                Regexps.italicLeft, Regexps.italicRight,
                Regexps.monospacedLeft, Regexps.monospacedRight
        };

        int completeCounter = 0;
        int partialCounter = 0;
        Pattern pattern;
        Matcher matcher;

        for (int i = 0; i < completeRegexs.length; i++) {
            pattern = Pattern.compile(completeRegexs[i]);
            matcher = pattern.matcher(inputString);

            while (matcher.find()) {
                completeCounter++;
            }
        }

        for (int i = 0; i < partialRegexs.length; i++) {
            pattern = Pattern.compile(partialRegexs[i]);
            matcher = pattern.matcher(inputString);

            while (matcher.find()) {
                partialCounter++;
            }
        }

        return completeCounter * 2 == partialCounter;

    }

    public static String[] removeElementsByIndexes(String[] inputArray, List<Integer> indexesToRemove) {
        String[] resultArr = inputArray;

        for (int i = 0; i < resultArr.length; i++) {
            if (indexesToRemove.contains(i)) {
                resultArr[i] = null;
            }
        }

        resultArr = Arrays.stream(resultArr)
                .filter(s -> s != null)
                .toArray(String[]::new);

        return resultArr;
    }

    public static List<Integer> getExtraEmptyIndexes(String[] inputArray) throws Exception {

        int emptyCounter = 0;
        ArrayList<Integer> extraLinesIndexes = new ArrayList<>();
        List<Integer> preformattedLines = Checker.getPreformattedLines(inputArray);

        for (int i = 0; i < inputArray.length; i++) {
            if (inputArray[i].equals("") && !preformattedLines.contains(i)) {
                emptyCounter = emptyCounter + 1;
                if (emptyCounter > 1) {
                    extraLinesIndexes.add(i);
                }
            } else {
                emptyCounter = 0;
            }
        }
        
        return extraLinesIndexes;
    }

    public static List<Integer> getPreformattedLines(String[] inputString) throws Exception {
        ArrayList<Integer> arrayOfIndexes = new ArrayList<>();
        boolean prefIsOpen = false;
        for (int i = 0; i < inputString.length; i++) {
            if (inputString[i].equals("```")) {
                if (inputString[i].length() > 3) {
                    throw new IllegalArgumentException("In line with preformatting marker shouldn't be other symbols");
                }
                prefIsOpen = !prefIsOpen;
                arrayOfIndexes.add(i);
                continue;
            }

            if (prefIsOpen) {
                arrayOfIndexes.add(i);
            }
        }

        if (prefIsOpen) {
            throw new IllegalArgumentException("Preformatted marker should be closed");
        }

        return arrayOfIndexes;
    }

}
