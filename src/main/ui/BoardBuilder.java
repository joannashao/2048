package ui;

import model.GameBoard;

// Builds a new game board and updates it
public class BoardBuilder {
    private GameBoard gameBoard;

    // EFFECTS: initiates a new game board with two random values
    public void initiateBoard() {
        gameBoard = new GameBoard(0);
        gameBoard.generateBlockValue();
        gameBoard.generateBlockValue();
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

    // EFFECTS: moves the blocks on the board and return if the block has been moved
    public boolean moveBlock(String nextDir) {
        switch (nextDir) {
            case "w":
                return gameBoard.moveUp();
            case "s":
                return gameBoard.moveDown();
            case "a":
                return gameBoard.moveLeft();
            case "d":
                return gameBoard.moveRight();
            default:
                return false;
        }
    }

    // EFFECTS: check if the blocks can merge or move to empty slot
    public boolean checkCanMove() {
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

    // EFFECTS: update the score and generates a new random block
    public void boardUpdate() {
        gameBoard.scoreUpdate();
        gameBoard.generateBlockValue();
    }

    // EFFECTS: returns the current score of this game
    public int currentScore() {
        return gameBoard.getScore();
    }

}
