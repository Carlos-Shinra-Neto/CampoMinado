package br.com.sirP.mineField.models;

import java.util.ArrayList;
import java.util.List;

public class Field {

    private final int line;
    private final int column;

    //Campo esta aberto
    private boolean open;
    //Campo esta com bandeira
    private boolean flagged;
    //Campo esta com bomba
    private boolean mine;

    private List<Field> neighbors = new ArrayList<>();

    public Field(int line, int column){
        this.line = line;
        this.column = column;
    }

    public boolean addNeighbor(Field neighbor){
        boolean differentLine = line != neighbor.line;
        boolean differentColumn = column != neighbor.column;
        boolean diagonal = differentLine && differentColumn;

        int deltaLine = Math.abs(line - neighbor.line);
        int deltaColumn = Math.abs(column - neighbor.column);

        int totalDelta = deltaColumn + deltaLine;

        if(totalDelta == 1 && !diagonal){
            neighbors.add(neighbor);
            return true;
        } else if (totalDelta == 2 && diagonal) {
            neighbors.add(neighbor);
            return true;
        } else {
            return false;
        }

    }
}
