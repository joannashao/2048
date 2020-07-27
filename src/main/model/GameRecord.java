package model;

// Represents a game record
public class GameRecord {
    private int score;
    private String month;
    private String day;


    //REQUIRES: gameRecord >= 0
    //EFFECTS: constructs a new game record with a score and the date
    public GameRecord(int gameRecord, String month, String day) {
        score = gameRecord;
        this.month = month;
        this.day = day;
    }

    //EFFECTS: return the value of score
    public int getScore() {
        return score;
    }

    //EFFECTS: return the date
    public String getDate() {
        return month + " " + day;
    }

}
