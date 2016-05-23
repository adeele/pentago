package re.neutrino.adele.views;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Circle representation for drawing
 */
public class Circle {

    private final Ellipse2D.Double circle;
    private Color color = null;

    Circle(int x, int y, int size) {
        circle = new Ellipse2D.Double(x, y, size, size);
    }
    void draw(Graphics2D g2) {
        if(color != null)
            fill(g2, color);
        g2.setPaint(Constant.BOREDER_COLOR);
        g2.draw(circle);
    }

    private void fill(Graphics2D g2, Color color) {
        g2.setPaint(color);
        g2.fill(circle);
    }

    public boolean contains(Point p) {
       return circle.contains(p.x, p.y);
    }

    public void setBlack() {
        color = Color.BLACK;
    }

    public void setWhite() {
        color = Color.WHITE;
    }
}