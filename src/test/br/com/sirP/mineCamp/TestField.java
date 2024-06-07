package test.br.com.sirP.mineCamp;

import br.com.sirP.mineField.models.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TestField {

    private Field field;

    @BeforeEach
    void startField(){
       field = new Field(3,3);
    }

    @Test
    void testNeighborIsReal(){
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
                resultLeftUpDiagonal &&
                resultRightUpDiagonal &&
                resultLeftDownDiagonal &&
                resultRightDownDiagonal;

        assertTrue(allResults);
    }

    @Test
    void testNeighborIsFalse(){
        Field falseNeighbor = new Field(1, 1);
        boolean resultFalseNeighbor = field.addNeighbor(falseNeighbor);

        assertFalse(resultFalseNeighbor);
    }

}
