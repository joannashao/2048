package model;

// Represents a 4x4 game board with a score
public class GameBoard {
    public static final int ROW = 4;
    public static final int COL = 4;

    protected Block[][] board;
    private int score;

    // REQUIRES: score >= 0
    // EFFECTS: constructs a new game board
    public GameBoard(int score) {
        this.score = score;
        board = new Block[ROW][COL];
    }

    public Block getBlock(int r, int c) {
        return board[r][c];
    }

    public int getScore() {
        return this.score;
    }

    // EFFECTS: updates the score by one
    public void scoreUpdate() {
        this.score += 1;
    }

    //EFFECTS: checks if a block with the given coordinate can merge with blocks above, below, or on its left and right
    //         return true if it can; false otherwise
    public boolean checkBlockCanMerge(int r, int c) {
        if (checkBlockAbove(r,c)) {
            return true;
        } else if (checkBlockBelow(r,c)) {
            return true;
        } else if (checkLeftBlock(r,c)) {
            return true;
        } else {
            return checkRightBlock(r, c);
        }
    }

    // EFFECTS: check if block with the given coordinate has the same value as the block above it
    public boolean checkBlockAbove(int r, int c) {
        if (r >= 1) {
            if (board[r][c] != null) {
                return board[r][c].equals(board[r - 1][c]);
            }
        }
        return false;
    }

    // EFFECTS: check if block with the given coordinate has the same value as the block below it
    public boolean checkBlockBelow(int r, int c) {
        if (r <= 2) {
            if (board[r][c] != null) {
                return board[r][c].equals(board[r + 1][c]);
            }
        }
        return false;
    }

    // EFFECTS: check if block with the given coordinate has the same value as the block on its left
    public boolean checkLeftBlock(int r, int c) {
        if (c >= 1) {
            if (board[r][c] != null) {
                return board[r][c].equals(board[r][c - 1]);
            }
        }
        return false;
    }

    // EFFECTS: check if block with the given coordinate has the same value as the block on its right
    public boolean checkRightBlock(int r, int c) {
        if (c <= 2) {
            if (board[r][c] != null) {
                return board[r][c].equals(board[r][c + 1]);
            }
        }
        return false;
    }

    // EFFECTS:
    public boolean moveUp() {
        boolean moved = false;
        boolean columnAllDone;

        for (int c = 0; c < 4; c++) {
            columnAllDone = false;
            while (!columnAllDone) {
                columnAllDone = true;
                for (int r = 1; r < 4; r++) {
                    if ((board[r][c] != null) && (board[r - 1][c] == null)) {
                        board[r - 1][c] = board[r][c];
                        board[r][c] = null;
                        moved = true;
                        columnAllDone = false;
                    } else if (checkBlockAbove(r,c)) {
                        int newValue = board[r][c].getValue() * 2;
                        board[r - 1][c].setValue(newValue);
                        board[r][c] = null;
                        moved = true;
                        columnAllDone = false;
                    }
                }
            }

        }
        return moved;
    }

    public boolean moveDown() {
        boolean moved = false;
        boolean columnAllDone;

        for (int c = 0; c < 4; c++) {
            columnAllDone = false;
            while (!columnAllDone) {
                columnAllDone = true;
                for (int r = 2; r >= 0; r--) {
                    if ((board[r][c] != null) && (board[r + 1][c] == null)) {
                        board[r + 1][c] = board[r][c];
                        board[r][c] = null;
                        moved = true;
                        columnAllDone = false;
                    } else if (checkBlockBelow(r,c)) {
                        int newValue = board[r][c].getValue() * 2;
                        board[r + 1][c].setValue(newValue);
                        board[r][c] = null;
                        moved = true;
                        columnAllDone = false;
                    }
                }
            }

        }
        return moved;
    }

    public boolean moveLeft() {
        boolean moved = false;
        boolean columnAllDone;

        for (int r = 0; r < 4; r++) {
            columnAllDone = false;
            while (!columnAllDone) {
                columnAllDone = true;
                for (int c = 1; c < 4; c++) {
                    if ((board[r][c] != null) && (board[r][c - 1] == null)) {
                        board[r][c - 1] = board[r][c];
                        board[r][c] = null;
                        moved = true;
                        columnAllDone = false;
                    } else if (checkLeftBlock(r,c)) {
                        int newValue = board[r][c].getValue() * 2;
                        board[r][c - 1].setValue(newValue);
                        board[r][c] = null;
                        moved = true;
                        columnAllDone = false;
                    }
                }
            }

        }
        return moved;
    }

    public boolean moveRight() {
        boolean moved = false;
        boolean columnAllDone;

        for (int r = 0; r < 4; r++) {
            columnAllDone = false;
            while (!columnAllDone) {
                columnAllDone = true;
                for (int c = 0; c < 3; c++) {
                    if ((board[r][c] != null) && (board[r][c + 1] == null)) {
                        board[r][c + 1] = board[r][c];
                        board[r][c] = null;
                        moved = true;
                        columnAllDone = false;
                    } else if (checkRightBlock(r,c)) {
                        int newValue = board[r][c].getValue() * 2;
                        board[r][c + 1].setValue(newValue);
                        board[r][c] = null;
                        moved = true;
                        columnAllDone = false;
                    }
                }
            }

        }
        return moved;
    }

    public void generateBlockValue() {
        boolean done = false;
        while (!done) {
            int r = (int)(Math.random() * 4);
            int c = (int)(Math.random() * 4);
            if (board[r][c] == null)  {
                board[r][c] = new Block(((int)((Math.random() * 2) + 1) * 2), r, c);
                done = true;
            }
        }
    }


}
