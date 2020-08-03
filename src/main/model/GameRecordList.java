package model;

import persistence.Reader;
import persistence.Saveable;

import java.io.PrintWriter;
import java.util.ArrayList;

// Represents a list of game records
public class GameRecordList implements Saveable {
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
        if (list.size() >= MAX_SIZE) {
            list.remove(0);
            list.add(g);
        } else {
            list.add(g);
        }
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
    public ArrayList<String> getList() {
        ArrayList<String> stringList = new ArrayList<>();
        for (GameRecord gameRecord : list) {
            stringList.add(gameRecord.getScore() + " " + gameRecord.getMonth() + " " + gameRecord.getDay());
        }
        return stringList;
    }

    @Override
    public void save(PrintWriter printWriter) {
        for (GameRecord gameRecord : list) {
            printWriter.print(gameRecord.getScore());
            printWriter.print(Reader.DELIMITER);
            printWriter.print(gameRecord.getMonth());
            printWriter.print(Reader.DELIMITER);
            printWriter.println(gameRecord.getDay());
        }


    }
}
