package persistence;

import model.GameRecord;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reader {
    public static final String DELIMITER = ",";

    // EFFECTS: returns a list of game records parsed from file; throws IOException if an exception is raised when
    // opening / reading from file
    public static List<GameRecord> readGameRecords(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string containing the content of one row of the
    // file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of game records parsed from list of strings where each string contains data for one
    // game record
    private static List<GameRecord> parseContent(List<String> fileContent) {
        List<GameRecord> gameRecords = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            gameRecords.add(parseRecords(lineComponents));
        }

        return gameRecords;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components has size 2 where element 0 represents the score, element 1 represents the month, and
    // elements 2 represents the day
    // EFFECTS: returns an account constructed from components
    private static GameRecord parseRecords(ArrayList<String> components) {
        int score = Integer.parseInt(components.get(0));
        String month = components.get(1);
        String day = components.get(2);
        return new GameRecord(score, month, day);
    }

}
