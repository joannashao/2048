package model;

import java.util.ArrayList;

// Represents a list of game records
public class GameRecordList {
    public static final int MAX_SIZE = 10;
    public ArrayList<GameRecord> list;

    //EFFECTS: constructs a new empty game record list
    public GameRecordList() {
        list = new ArrayList<GameRecord>();
    }

    //MODIFIES: this
    //EFFECTS: add a new game record to the record list if list is not full yet, otherwise remove the first record and
    //         then add the new record
    public void addNewRecord(GameRecord g) {
        if (list.size() == MAX_SIZE) {
            list.remove(0);
            list.add(g);
        }
        list.add(g);
    }

    //EFFECTS: return the number of game records in the list
    public int getLength() {
        return list.size();
    }

    //EFFECTS: return the first record in the list (the one entered earliest)
    public GameRecord getFirstRecord() {
        return list.get(0);
    }

    //EFFECTS: return the whole list as strings
    ArrayList<String> stringList = new ArrayList<>();

    public ArrayList<String> getList() {
        for (GameRecord gameRecord : list) {
            stringList.add(gameRecord.getScore() + " " + gameRecord.getDate());
        }
        return stringList;
    }
}
