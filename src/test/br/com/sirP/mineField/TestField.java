package br.com.sirP.mineField.models;

import br.com.sirP.mineField.exceptions.ExplosionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestField {
    private Field field;

    @BeforeEach
    void startField() {
        field = new Field(3, 3);
    }

    @Test
    void testNeighborIsReal() {
        Field leftNeighbor = new Field(3, 2);
        boolean resultLeft = field.addNeighbor(leftNeighbor);
        Field rightNeighbor = new Field(3, 4);
        boolean resultRight = field.addNeighbor(rightNeighbor);
        Field upNeighbor = new Field(2, 3);
        boolean resultUp = field.addNeighbor(upNeighbor);
        Field downNeighbor = new Field(4, 3);
        boolean resultDown = field.addNeighbor(downNeighbor);
        Field leftUpNeighbor = new Field(2, 2);
        boolean resultLeftUpDiagonal = field.addNeighbor(leftUpNeighbor);
        Field rightUpNeighbor = new Field(2, 4);
        boolean resultRightUpDiagonal = field.addNeighbor(rightUpNeighbor);
        Field leftDownNeighbor = new Field(4, 2);
        boolean resultLeftDownDiagonal = field.addNeighbor(leftDownNeighbor);
        Field rightDownNeighbor = new Field(4, 4);
        boolean resultRightDownDiagonal = field.addNeighbor(rightDownNeighbor);
        boolean allResults =
                resultDown &&
                        resultLeft &&
                        resultUp &&
                        resultRight &&
                        resultRightUpDiagonal &&
                        resultLeftDownDiagonal &&
                        resultRightDownDiagonal;
        assertTrue(allResults);
    }

    @Test
    void testNeighborIsFalse() {
        Field falseNeighbor = new Field(1, 1);
        boolean resultFalseNeighbor = field.addNeighbor(falseNeighbor);
        assertFalse(resultFalseNeighbor);
    }

    @Test
    void testFlag() {
        assertFalse(field.isFlagged());
    }

    @Test
    void testChangeFlag() {
        field.changeFlag();
        boolean firstChange = field.isFlagged();
        field.changeFlag();
        boolean secondChange = field.isFlagged();
        assertTrue(firstChange);
        assertFalse(secondChange);
    }

    @Test
    void testOpen() {
        assertTrue(field.open());
        field.changeFlag();
        assertFalse(field.open());
        field.changeFlag();
        field.mine();
        field.open();
        assertFalse(field.open());
    }

    @Test
    void testExplosion() {
        field.mine();
        assertThrows(ExplosionException.class, () -> {
            field.open();
        });
    }

    @Test
    void testOpenNeighbor(){
        Field firstNeighbor = new Field(2,2);
        Field secondNeighbor = new Field(1, 1);

        field.addNeighbor(firstNeighbor);
        firstNeighbor.addNeighbor(secondNeighbor);

        field.open();

        assertTrue(firstNeighbor.isOpen() && secondNeighbor.isOpen());
    }

    @Test
    void testOpenNeighborMined(){

    }
}
