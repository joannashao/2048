package model;

import java.util.Objects;

// Represents a block on the game board with a value and a coordinate on the board
public class Block {
    private int value;
    private int row;
    private int column;

    public Block(int value, int r, int c) {
        this.value = value;
        this.row = r;
        this.column = c;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Block block = (Block) o;
        return value == block.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
