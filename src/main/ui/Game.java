package ui;


import model.GameRecord;
import model.GameRecordList;

import java.util.Scanner;

// Represents a game
public class Game {
    private GameRecordList list;
    private Scanner scanner;
    GameRecord record = new GameRecord(0, " ", " ");
    GameRecordList recordList = new GameRecordList();

    //EFFECTS: construct the game
    public Game() {
        runGame();
    }

    //EFFECTS: allow user to either start the game, enter a new record, view record list or restart the game
    private void  runGame() {
        boolean stillPlaying = true;
        scanner = new Scanner(System.in);


        while (stillPlaying) {
            System.out.println("Welcome! Enter 1 to start the game or enter 2 to view record list!");
            System.out.println("During the game, enter R to restart the game.");

            String userResponse = scanner.next();

            if (userResponse.equals("1")) {
                startGame();
            } else if (userResponse.equals("2")) {
                System.out.println(recordList.getList());
            }
        }
        System.out.println("Game over!");
    }

    //EFFECTS: return the score entered by user
    private int scoreUpdate() {
        System.out.println("Please enter your score");
        return scanner.nextInt();
    }

    //EFFECTS: return the month entered by user
    private String monthUpdate() {
        System.out.println("Please enter the month");
        return scanner.next();
    }

    //EFFECTS: return the day entered by user
    private String dayUpdate() {
        System.out.println("Please enter the day");
        return scanner.next();
    }

    //EFFECTS: initiate the game
    private void startGame() {
        System.out.println("Playing game...");
        String userResponse = scanner.next();
        if (userResponse.equals("R")) {
            record = new GameRecord(0, " ", " ");
            record.update(scoreUpdate(), monthUpdate(), dayUpdate());
            recordList.addNewRecord(record);
        }
    }


}
