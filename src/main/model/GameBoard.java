package model;

import java.util.Scanner;

// Represents a game board
public class GameBoard {
    private final int[][] board = new int[4][4];
    private int score;

    public GameBoard(int score) {
        this.score = score;
    }

    public int getCell(Cell cell) {
        return board[cell.getColumn()][cell.getRow()];
    }

    public boolean isEmpty(Cell cell) {
        return getCell(cell) == 0;
    }

    public boolean checkCellCanMerge(Cell cell) {
        int r = cell.getRow();
        int c = cell.getColumn();
        if (checkCellAbove(r,c)) {
            return true;
        } else if (checkCellBelow(r,c)) {
            return true;
        } else if (checkLeftCell(r,c)) {
            return true;
        } else if (checkRightCell(r,c)) {
            return true;
        }
        return false;
    }

    public boolean checkCellAbove(int r, int c) {
        return board[r][c] == board[r - 1][c];
    }

    public boolean checkCellBelow(int r, int c) {
        return board[r][c] == board[r + 1][c];
    }

    public boolean checkLeftCell(int r, int c) {
        return board[r][c] == board[r][c - 1];
    }

    public boolean checkRightCell(int r, int c) {
        return board[r][c] == board[r][c + 1];
    }

    public int getScore() {
        return this.score;
    }

    public int updateScore() {
        return this.score += 1;
    }

    public void generateNum() {
        boolean placed = false;
        while (!placed) {
            int c = (int)(Math.random() * 4);
            int r = (int)(Math.random() * 4);
            if (board[c][r] == 0) {
                board[c][r] = ((int)((Math.random() * 2) + 1) * 2);
                placed = true;
            }
        }
    }

    public void printBoard() {
        System.out.println("|-----|-----|-----|-----|");
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 4; r++) {
                if (board[c][r] != 0) {
                    System.out.print(String.format("|%5d", board[c][r]));
                } else {
                    System.out.print("|     ");
                }
            }
            System.out.println("|");
            if (c != 3) {
                System.out.println("|-----|-----|-----|-----|");
            } else {
                System.out.println("|-----|-----|-----|-----|");
            }
        }
    }

    public boolean moveUp() {
        boolean moved = false;

        for (int c = 0; c < 4; c++) {
            for (int r = 1; r < 4; r++) {
                if ((board[r][c] != 0) && (board[r - 1][c] == 0)) {
                    board[r - 1][c] = board[r][c];
                    board[r][c] = 0;
                    moved = true;
                } else if (checkCellAbove(r,c)) {
                    board[r - 1][c] += board[r][c];
                    board[r][c] = 0;
                    moved = true;
                }
            }
        }
        return moved;
    }

    public boolean moveDown() {
        boolean moved = false;

        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 3; r++) {
                if ((board[r][c] != 0) && (board[r + 1][c] == 0)) {
                    board[r + 1][c] = board[r][c];
                    board[r][c] = 0;
                    moved = true;
                } else if (checkCellBelow(r,c)) {
                    board[r + 1][c] += board[r][c];
                    board[r][c] = 0;
                    moved = true;
                }
            }
        }
        return moved;
    }

    public boolean moveLeft() {
        boolean moved = false;

        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 3; c++) {
                if ((board[r][c] != 0) && (board[r][c - 1] == 0)) {
                    board[r][c - 1] = board[r][c];
                    board[r][c] = 0;
                    moved = true;
                } else if (checkLeftCell(r,c)) {
                    board[r][c - 1] += board[r][c];
                    board[r][c] = 0;
                    moved = true;
                }
            }
        }
        return moved;
    }

    public boolean moveRight() {
        boolean moved = false;

        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 3; c++) {
                if ((board[r][c] != 0) && (board[r][c + 1] == 0)) {
                    board[r][c + 1] = board[r][c];
                    board[r][c] = 0;
                    moved = true;
                } else if (checkRightCell(r,c)) {
                    board[r][c + 1] += board[r][c];
                    board[r][c] = 0;
                    moved = true;
                }
            }
        }
        return moved;
    }


}
