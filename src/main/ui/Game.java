package ui;

import model.Cell;
import model.GameBoard;
import model.GameRecord;
import model.GameRecordList;
import persistence.Reader;
import persistence.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Represents the main frame in witch the game board will appear
public class Game {
    private static final String GAME_RECORD_FILE = "./data/gameRecords.txt";
    private GameRecordList list;
    private Scanner scanner;
    GameRecord record = new GameRecord(0, " ", " ");
    private boolean stillPlaying = true;
    private GameBoard gameBoard;


    // EFFECTS: construct the game
    public Game() {
        runGame();
    }

    // EFFECTS: allow user to either start the game, enter a new record, view record list or restart the game
    private void runGame() {
        scanner = new Scanner(System.in);
        String userResponse;
        list = new GameRecordList();

        loadList();

        while (stillPlaying) {
            displayMenu();
            userResponse = scanner.next();

            if (userResponse.equals("3")) {
                stillPlaying = false;
            } else {
                processUserResponse(userResponse);
            }
        }
        endGame();
    }

    // MODIFIES: this
    // EFFECTS: loads game record list from GAME_RECORD_FILE, if that file exists; otherwise initialize empty list
    private void loadList() {
        try {
            List<GameRecord> fileList = Reader.readGameRecords(new File(GAME_RECORD_FILE));
            for (GameRecord gameRecord : fileList) {
                list.addNewRecord(gameRecord);
            }
        } catch (IOException e) {
            List<GameRecord> recordList = new ArrayList<>();
        }
    }


    // EFFECTS: displays menu of option to user
    private void displayMenu() {
        System.out.println("Welcome to the game!");
        System.out.println("1: Start a new game   2: View record list   3: Quit\n");
        System.out.println("HOW TO PLAY: Move the blocks by pressing [a], [s], [d],[w]! ");
        System.out.println("             [a]: left   [s]: down   [d]: right   [w]: up");
        System.out.println("             When two blocks with the same number touch, they merge into one!");
        System.out.println("             During the game, enter R to restart the game.\n");
        System.out.println("Try to get to 2048!");
    }

    private void processUserResponse(String userResponse) {
        if (userResponse.equals("1")) {
            startGame();
        } else if (userResponse.equals("2")) {
            printList();
        } else if (userResponse.equals("3")) {
            endGame();
        } else {
            System.out.println("Option not valid! Please choose again");
        }
    }

    // EFFECTS: prints record list to screen
    private void printList() {
        System.out.println(list.getList());
    }

    // EFFECTS: return the score entered by user
    private int scoreUpdate() {
        return gameBoard.getScore();
    }

    // EFFECTS: return the month entered by user
    private String monthUpdate() {
        System.out.println("Please enter the month");
        return scanner.next();
    }

    // EFFECTS: return the day entered by user
    private String dayUpdate() {
        System.out.println("Please enter the day");
        return scanner.next();
    }

    // EFFECTS: initiate the game
    private void startGame() {
        gameBoard = new GameBoard(0);
        gameBoard.generateNum();
        gameBoard.generateNum();

        while (stillPlaying) {
            gameBoard.printBoard();
            moveOrRestart();
            gameBoard.updateScore();
            stillPlaying = checkGameOver();
            if (stillPlaying) {
                gameBoard.generateNum();
            }
        }
        gameBoard.printBoard();
    }

    public void moveOrRestart() {
        boolean moved = false;
        Scanner scanner = new Scanner(System.in);

        while (!moved) {
            String userResponse = scanner.next();

            switch (userResponse) {
                case ("w") :
                    moved = gameBoard.moveUp();
                    break;
                case ("s") :
                    moved = gameBoard.moveDown();
                    break;
                case ("a") :
                    moved = gameBoard.moveLeft();
                    break;
                case ("d") :
                    moved = gameBoard.moveRight();
                    break;
                case ("R") : restartGame();

                default : System.out.println("Please enter valid input!");
            } gameBoard.updateScore();
        }
    }

    private void restartGame() {
        record = new GameRecord(0, " ", " ");
        record.update(scoreUpdate(), monthUpdate(), dayUpdate());
        list.addNewRecord(record);
    }

    private boolean checkGameOver() {
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                Cell cell = new Cell(r,c);
                if (gameBoard.isEmpty(cell)) {
                    return true;
                } else if (gameBoard.checkCellCanMerge(cell)) {
                    return true;
                }
            }
        }
        return false;
    }


    // EFFECTS: ends the game
    private void endGame() {
        try {
            Writer writer = new Writer(new File(GAME_RECORD_FILE));
            writer.write(list);
            writer.close();
            System.out.println("Game over!");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save records to " + GAME_RECORD_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // due to programming error
        }
    }
}
