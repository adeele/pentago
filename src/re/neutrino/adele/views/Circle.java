package re.neutrino.adele.views;

import re.neutrino.adele.GameConstant;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Circle representation for drawing
 */
public class Circle
{

    private final Ellipse2D.Double circle;
    private Color color = null;

    /**
     * Creates circle of the specific parameters
     *
     * @param x    x-coordinate
     * @param y    y-coordinate
     * @param size diameter length
     */
    Circle(int x, int y, int size)
    {
        circle = new Ellipse2D.Double(x, y, size, size);
    }

    /**
     * Draws circle bounds
     *
     * @param g2 graphics
     */
    void draw(Graphics2D g2)
    {
        if (color != null)
            fill(g2, color);
        g2.setPaint(GameConstant.BOREDER_COLOR);
        g2.draw(circle);
    }

    /**
     * Fills the circle
     *
     * @param g2    graphics
     * @param color to fill
     */
    private void fill(Graphics2D g2, Color color)
    {
        g2.setPaint(color);
        g2.fill(circle);
    }

    /**
     * Check if point is within the circle
     *
     * @param p point
     * @return if circle contains point
     */
    public boolean contains(Point p)
    {
        return circle.contains(p.x, p.y);
    }

    /**
     * Sets black ball of the fill
     */
    void setBlack()
    {
        color = Color.BLACK;
    }

    /**
     * Sets white ball of the fill
     */
    void setWhite()
    {
        color = Color.WHITE;
    }

    /**
     * Set transparent ball of the fill
     */
    void setTransparent()
    {
        color = GameConstant.BG_DARK_COLOR;
    }
}