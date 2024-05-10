package org.example;

public class Regexps {
    public static final String boldComplete = "(?<=[ ,.:;\\n\\t]|^)\\*\\*(?=\\S)(.+?)(?<=\\S)\\*\\*(?=[ ,.:;\\n\\t]|$)";
    public static final String italicComplete = "(?<=[ ,.:;\\n\\t]|^)_(?=\\S)(.+?)(?<=\\S)_(?=[ ,.:;\\n\\t]|$)";
    public static final String monospacedComplete = "(?<=[ ,.:;\\n\\t]|^)`(?=\\S)(.+?)(?=\\S)`(?=[ ,.:;\\n\\t]|$)";
    public static final String boldLeft = "(?<=[ ,.:;\\n\\t]|^)\\*\\*(?=\\S)";
    public static final String boldRight = "(?<=\\S)\\*\\*(?=[ ,.:;\\n\\t]|$)";
    public static final String italicLeft = "(?<=[ ,.:;\\n\\t]|^)_(?=\\S)";
    public static final String italicRight = "(?<=\\S)_(?=[ ,.:;\\n\\t]|$)";
    public static final String monospacedLeft = "(?<=[ ,.:;\\n\\t]|^)`(?=\\S)";
    public static final String monospacedRight = "(?=\\S)`(?=[ ,.:;\\n\\t]|$)";
}
