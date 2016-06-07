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
    /**
     * Tests if reports win on obliques
     * @throws Exception
     */
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

        assertEquals(true, boardModel.isFinished());
    }

    /**
     * Tests if not report the win after checking place with no ball
     * @throws Exception
     */
    @Test
    public void checkNotWinOnPermanentPlace() throws Exception {
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

        assertEquals(false, boardModel.isFinished());
    }

    /**
     * Tests if reports win after rotation
     * @throws Exception
     */
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

        assertEquals(true, boardModel.isFinished());
    }
}