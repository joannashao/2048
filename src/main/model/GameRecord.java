package model;

import exception.ImpossibleScoreException;

// Represents a game record
public class GameRecord {
    private int score;
    private String month;
    private String day;


    //REQUIRES: gameRecord >= 0
    //EFFECTS: constructs a new game record with a score and the date
    public GameRecord(int score, String month, String day) {
        this.score = score;
        this.month = month;
        this.day = day;
    }

    //EFFECTS: return the value of score
    public int getScore() {
        return score;
    }

    //EFFECTS: return the month
    public String getMonth() {
        return month;
    }

    //EFFECTS: return the day
    public String getDay() {
        return day;
    }

    //MODIFIES: this
    //EFFECTS: update the record
    public void update(int score, String month, String day) throws ImpossibleScoreException {
        if (score < 0) {
            throw new ImpossibleScoreException();
        }
        this.score = score;
        this.month = month;
        this.day = day;
    }

}
