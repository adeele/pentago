package re.neutrino.adele.tests;

import org.junit.Test;
import re.neutrino.adele.Ball;
import re.neutrino.adele.models.BoardModel;

import static org.junit.Assert.*;

/**
 * Checking win test
 */
public class BoardModelTest {
    @Test
    public void checkWinOblique() throws Exception {
        BoardModel boardModel = new BoardModel();

        boardModel.placeBall(0, 0);
        boardModel.placeBall(0, 5);
        boardModel.placeBall(1, 1);
        boardModel.placeBall(0, 1);
        boardModel.placeBall(2, 2);
        boardModel.placeBall(0, 2);
        boardModel.placeBall(3, 3);
        boardModel.placeBall(0, 3);
        boardModel.placeBall(4, 4);

        boolean result = boardModel.checkWin(4,4, Ball.WHITE);

        assertEquals(true, result);
    }

}