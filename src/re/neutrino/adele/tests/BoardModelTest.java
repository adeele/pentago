package re.neutrino.adele.tests;

import org.junit.Test;
import re.neutrino.adele.Ball;
import re.neutrino.adele.models.BoardModel;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Checking win test
 */
public class BoardModelTest {
    @Test
    public void checkWinOblique() throws Exception {
        BoardModel boardModel = new BoardModel();

        boardModel.placeBall(0, 0);
        boardModel.rotate(new Point(1, 4), 1);
        boardModel.placeBall(0, 5);
        boardModel.rotate(new Point(1, 4), 1);
        boardModel.placeBall(1, 1);
        boardModel.rotate(new Point(1, 4), 1);
        boardModel.placeBall(0, 1);
        boardModel.rotate(new Point(1, 4), 1);
        boardModel.placeBall(2, 2);
        boardModel.rotate(new Point(1, 4), 1);
        boardModel.placeBall(0, 2);
        boardModel.rotate(new Point(1, 4), 1);
        boardModel.placeBall(3, 3);
        boardModel.rotate(new Point(1, 4), 1);
        boardModel.placeBall(0, 3);
        boardModel.rotate(new Point(1, 4), 1);
        boardModel.placeBall(4, 4);

        boolean result = boardModel.checkWin(4,4, Ball.WHITE);

        assertEquals(true, result);
    }

    @Test
    public void checkWinOnPermanentPlace() throws Exception {
        BoardModel boardModel = new BoardModel();

        boardModel.placeBall(3, 0);
        boardModel.rotate(new Point(1, 1), 1);
        boardModel.placeBall(0, 0);
        boardModel.rotate(new Point(1, 1), 1);
        boardModel.placeBall(3, 1);
        boardModel.rotate(new Point(1, 1), 1);
        boardModel.placeBall(0, 1);
        boardModel.rotate(new Point(1, 1), 1);
        boardModel.placeBall(3, 2);
        boardModel.rotate(new Point(1, 1), 1);
        boardModel.placeBall(0, 2);
        boardModel.rotate(new Point(1, 1), 1);
        boardModel.placeBall(3, 4);
        boardModel.rotate(new Point(1, 1), 1);
        boardModel.placeBall(0, 5);
        boardModel.rotate(new Point(1, 1), 1);
        boardModel.placeBall(3, 5);
        boardModel.rotate(new Point(1, 1), 1);

        boolean result = boardModel.checkBoardWin();

        assertEquals(false, result);
    }

    @Test
    public void checkWinAfterRotation() throws Exception {
        BoardModel boardModel = new BoardModel();
        boardModel.placeBall(0, 0);
        boardModel.rotate(new Point(1, 4), 1);
        boardModel.placeBall(0, 5);
        boardModel.rotate(new Point(1, 4), 1);
        boardModel.placeBall(1, 1);
        boardModel.rotate(new Point(1, 4), 1);
        boardModel.placeBall(0, 1);
        boardModel.rotate(new Point(1, 4), 1);
        boardModel.placeBall(2, 2);
        boardModel.rotate(new Point(1, 4), 1);
        boardModel.placeBall(0, 2);
        boardModel.rotate(new Point(1, 4), 1);
        boardModel.placeBall(3, 5);
        boardModel.rotate(new Point(1, 4), 1);
        boardModel.placeBall(0, 3);
        boardModel.rotate(new Point(1, 4), 1);
        boardModel.placeBall(4, 4);
        boardModel.rotate(new Point(4, 4), -1);

        boolean result = boardModel.checkBoardWin();

        assertEquals(true, result);
    }
}