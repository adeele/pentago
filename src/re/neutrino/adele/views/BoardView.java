package re.neutrino.adele.views;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * Class BoardView
 * creates panel on witch draws the board
 */
class BoardView extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
    }

    private void drawSquare(Graphics2D g2, int x, int y, int size) {
        Rectangle2D.Double square = new Rectangle2D.Double(x, y, size, size);
        g2.setPaint(Constant.BOREDER_COLOR);
        g2.draw(square);
        g2.setPaint(Constant.BG_DARK_COLOR);
        square.setFrame(x + 3, y + 3, size - 5, size - 5);
        g2.fill(square);
    }

    private void drawCircle(Graphics2D g2, int x, int y, int size, boolean filled) {
        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, size, size);
        g2.setPaint(Constant.BOREDER_COLOR);
        g2.draw(circle);
        if(filled) {
            g2.setPaint(Color.black);
            g2.fill(circle);
        }
    }

    private void drawBoard(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));

        drawSquare(g2, 150, 200, 250);
        drawSquare(g2, 400, 200, 250);
        drawSquare(g2, 150, 450, 250);
        drawSquare(g2, 400, 450, 250);

        drawPlaces(g2);
    }

    private void drawPlaces(Graphics2D g2) {
        int x = 0, y = 0;
        for(int i = 0; i < 6; i++) {
            if(i >= 3)
                x = 30;
            for(int j = 0; j < 6; j++) {
                if(j >= 3)
                    y = 30;
                drawCircle(g2, 180 + x + 75 * i, 230 + y + 75 * j, 40, false);
            }
            y = 0;
        }
    }
}