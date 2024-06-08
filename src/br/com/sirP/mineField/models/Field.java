package br.com.sirP.mineField.models;

import br.com.sirP.mineField.exceptions.ExplosionException;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private final int line;
    private final int column;
    private boolean open;
    private boolean flagged;
    private boolean mine;

    private List<Field> neighbors = new ArrayList<>();

    public Field(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public boolean addNeighbor(Field neighbor) {
        boolean differentLine = line != neighbor.line;
        boolean differentColumn = column != neighbor.column;
        boolean diagonal = differentLine && differentColumn;

        int deltaLine = Math.abs(line - neighbor.line);
        int deltaColumn = Math.abs(column - neighbor.column);

        int totalDelta = deltaColumn + deltaLine;

        if (totalDelta == 1 && !diagonal) {
            neighbors.add(neighbor);
            return true;
        } else if (totalDelta == 2 && diagonal) {
            neighbors.add(neighbor);
            return true;
        } else {
            return false;
        }

    }

    public boolean open() {
        if (!open && !flagged) {
            open = true;
            if (mine) {
                throw new ExplosionException();
            }
            if (isNeighborSafe()) {
                neighbors.forEach(v -> {
                    v.open();
                });
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean mine() {
        return mine = true;
    }

    boolean isNeighborSafe() {
        return neighbors.stream().noneMatch(v -> {
            return v.mine;
        });
    }

    public void changeFlag() {
        if (!open) {
            flagged = !flagged;
        }
    }

    public boolean isFlagged() {
        return flagged;
    }

    public boolean isOpen(){
        return open;
    }
}
