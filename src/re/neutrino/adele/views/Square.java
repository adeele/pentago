package re.neutrino.adele.views;

import re.neutrino.adele.GameConstant;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Square representation for drawing
 */
class Square {
    private final Rectangle2D.Double square;

    Square(int x, int y, int size) {
        square = new Rectangle2D.Double(x, y, size, size);
    }
    void draw(Graphics2D g2) {
        g2.setPaint(GameConstant.BG_DARK_COLOR);
        g2.fill(square);
        g2.setPaint(GameConstant.BOREDER_COLOR);
        g2.draw(square);
    }
}
