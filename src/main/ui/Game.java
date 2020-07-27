package ui;


import model.GameRecord;
import model.GameRecordList;

import java.util.Scanner;

// Represents a game
public class Game {
    private GameRecordList recordList;
    private Scanner scanner;

    //EFFECTS: run the game
    public Game() {
        runGame();
    }

    private void  runGame() {
        boolean stillPlaying = true;
        recordList = new GameRecordList();
        scanner = new Scanner(System.in);
        GameRecord record = new GameRecord(0, " ", " ");

        while (stillPlaying) {
            System.out.println("Welcome! Enter A to start the game or enter L to view record list!");
            System.out.println("During the game, press R to restart the game.");
            if (scanner.nextLine().equals("A")) {
                startGame();
            } else if (scanner.nextLine().equals("L")) {
                viewList();
            }

            if (scanner.nextLine().equals("R")) {
                record.update(scoreUpdate(), monthUpdate(), dayUpdate());
                recordList.addNewRecord(record);
            }
        }
        System.out.println("Game over!");
    }

    private int scoreUpdate() {
        System.out.println("Please enter your score");
        return scanner.nextInt();
    }

    private String monthUpdate() {
        System.out.println("Please enter the month");
        return scanner.next();
    }

    private String dayUpdate() {
        System.out.println("Please enter the day");
        return scanner.next();
    }

    private void startGame() {
        System.out.println("Playing game...");
    }

    private void viewList() {
        System.out.println("Record list: " + recordList.getList());
    }

}
