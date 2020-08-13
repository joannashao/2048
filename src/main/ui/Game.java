package ui;


import exception.ImpossibleScoreException;
import model.GameBoard;
import model.GameRecord;
import model.GameRecordList;
import persistence.Reader;
import persistence.Writer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Represents a game
public class Game extends JFrame implements ActionListener {
    private static final String GAME_RECORD_FILE = "./data/gameRecords.txt";

    private JTextPane menu;
    private final JButton listBtn;
    private JButton enter;
    private JTextField monthField;
    private JTextField dayField;
    private JLabel monthLabel;
    private JLabel dayLabel;
    private final ImageIcon gameOver;
    private final JLabel gameOverLabel;

    private GameRecordList list;
    private Scanner scanner;
//    private GameRecord recordScore = new GameRecord(0, " ", " ");
    private GameRecord recordScore;
    boolean stillPlaying = true;
    private boolean inGame = true;
    private GameBoard gameBoard;
    private int currentScore;


    // EFFECTS: construct the game
    public Game() {
        super("2048");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 300));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        gameOver = new ImageIcon("./data/GameOver.png");
        gameOverLabel = new JLabel(gameOver);
        listBtn = new JButton("View Record List");
        listBtn.setActionCommand("list");
        listBtn.addActionListener(this);
        displayMenu();
        add(listBtn);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        runGame();

    }

    // MODIFIES: this
    // EFFECTS: catches when an action event has occurred and perform the tasks (display the record list or show the
    // options for entering records and save the record to file
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("list")) {
            getList();

        } else if (e.getActionCommand().equals("enter")) {
            try {
                recordScore.update(currentScore, monthField.getText(), dayField.getText());
            } catch (ImpossibleScoreException exception) {
                exception.printStackTrace();
            }
            list.addNewRecord(recordScore);
            stillPlaying = false;
            try {
                Writer writer = new Writer(new File(GAME_RECORD_FILE));
                writer.write(list);
                writer.close();
            } catch (FileNotFoundException exc) {
                System.out.println("Unable to save records to " + GAME_RECORD_FILE);
            } catch (UnsupportedEncodingException exc) {
                exc.printStackTrace();
            }
            printGameOverScreen();
        }
    }

    private void printGameOverScreen() {
        getContentPane().removeAll();
        add(gameOverLabel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: displays menu of option to user
    private void displayMenu() {
        menu = new JTextPane();
        menu.setPreferredSize(new Dimension(550, 160));
        Font f = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
        menu.setFont(f);
        menu.setText("Welcome to the game!\n"
                + "Enter 1 to start the game!\n"
                + "HOW TO PLAY: enter [a], [w],[d] or [s] to move the blocks!\n"
                + "                             [a]: left   [w]: up   [d]: right   [s]: down\n"
                + "                             During the game, enter r to restart the game.\n"
                + "When blocks with same value touches, they merge into one!\n"
                + "Try to get to 2048:)");
        menu.setEditable(false);
        add(menu);
    }

    // EFFECTS: allow user to either start the game, enter a new record, view record list or restart the game
    private void runGame() {

        while (stillPlaying) {
            processUserResponse();
        }
        endGame();
    }

    // EFFECTS: starts a new game if user enters 1
    private void processUserResponse()  {
        scanner = new Scanner(System.in);
        String userResponse = scanner.next();

        if (userResponse.equals("1")) {
            startGame();
        } else {
            System.out.println("Option not valid! Please choose again");
        }
    }

    // EFFECTS: load the record list from file and print it
    private void getList() {
        loadList();
        printList();
    }

    // MODIFIES: this
    // EFFECTS: loads game record list from GAME_RECORD_FILE, if that file exists; otherwise initialize empty list
    private void loadList() {
        list = new GameRecordList();
        try {
            List<GameRecord> fileList = Reader.readGameRecords(new File(GAME_RECORD_FILE));
            for (GameRecord gameRecord : fileList) {
                list.addNewRecord(gameRecord);
            }
        } catch (IOException e) {
            List<GameRecord> recordList = new ArrayList<>();
        }
    }

    // EFFECTS: prints record list to screen
    private void printList() {
        String recordList = list.getList().toString();
        menu.setText(recordList);
        menu.setEditable(false);
    }

    // EFFECTS: initiate the game
    private void startGame() {
        loadList();
        gameBoard = new GameBoard(0);
        gameBoard.generateBlockValue();
        gameBoard.generateBlockValue();

        while (inGame) {
            printBoard();
            boolean moved = move();
            getContentPane().removeAll();
            displayMenu();
            add(listBtn);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
            setResizable(false);
            inGame = checkGameNotOver();
            if (inGame && moved) {
                gameBoard.scoreUpdate();
                gameBoard.generateBlockValue();
            } else {
                stillPlaying = false;
            }
        }
    }

    // EFFECTS: print out the game board with randomized number
    public void printBoard() {
        System.out.println("|-----|-----|-----|-----|");
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                if (gameBoard.getBlock(r, c) != null) {
                    System.out.printf("|%5d", gameBoard.getBlock(r, c).getValue());
                } else {
                    System.out.print("|     ");
                }
            }
            System.out.println("|");
            System.out.println("|-----|-----|-----|-----|");
        }
    }

    // EFFECTS: takes in a user input for either moving the blocks(numbers) or restart the game
    private boolean move() {
        boolean moved = false;
        scanner = new Scanner(System.in);

        String userResponse = scanner.next();
        switch (userResponse) {
            case ("w"): moved = gameBoard.moveUp();
                break;

            case ("s"): moved = gameBoard.moveDown();
                break;

            case ("a"): moved = gameBoard.moveLeft();
                break;

            case ("d"): moved = gameBoard.moveRight();
                break;

            case ("r"): moved = false;
                endGame();
                startGame();
                break;

            default:
                System.out.println("Please enter valid input!");

        }
        return moved;
    }

    // EFFECTS: check if the game is not over. return true if game board is have empty slot or if two blocks can merge
    // return false otherwise
    private boolean checkGameNotOver() {
        if (!this.inGame) {
            return false;
        } else {
            for (int r = 0; r < 4; r++) {
                for (int c = 0; c < 4; c++) {
                    if (gameBoard.getBlock(r, c) == null) {
                        return true;
                    } else if (gameBoard.checkBlockCanMerge(r, c)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    // EFFECTS: ends the game
    private void endGame() {
        System.out.println("Game over!");
        currentScore = gameBoard.getScore();
        recordScore = new GameRecord(0, " ", " ");
        repaint();
        remove(listBtn);
        remove(menu);
        monthLabel = new JLabel("Month: ");
        monthField = new JTextField(5);
        dayLabel = new JLabel("Day: ");
        dayField = new JTextField(5);

        enter = new JButton("OK");
        enter.setActionCommand("enter");
        enter.addActionListener(this);

        add(monthLabel);
        add(monthField);
        add(dayLabel);
        add(dayField);
        add(enter);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        stillPlaying = true;

    }


}
