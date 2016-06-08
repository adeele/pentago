package re.neutrino.adele.views;

import re.neutrino.adele.models.Ball;
import re.neutrino.adele.FieldChangedEvent;
import re.neutrino.adele.FieldChangedEventListener;
import re.neutrino.adele.GameConstant;
import re.neutrino.adele.controllers.BoardCtrl;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Game main panel with the board to play
 */
public class BoardView implements FieldChangedEventListener {
    private final BoardPanel panel = new BoardPanel();
    private JLabel header = new JLabel(GameConstant.TURN_WHITE);
    private final BoardCtrl boardCtrl;
    private final Circle[] circles = new Circle[36];
    private boolean arrowsVisibility = false;
    private boolean endOfGame = false;
    private BufferedImage arrow1, arrow2, arrow3, arrow4, arrow5, arrow6, arrow7, arrow8;
    private final Square[] squares = new Square[] {
            new Square(150, 200, 250),
            new Square(400, 200, 250),
            new Square(150, 450, 250),
            new Square(400, 450, 250)
    };
    private final Rectangle[] arrows = new Rectangle[] {
            new Rectangle(110, 200, 40, 100),
            new Rectangle(150, 160, 100, 40),
            new Rectangle(550, 160, 100, 40),
            new Rectangle(650, 200, 40, 100),
            new Rectangle(650, 600, 40, 100),
            new Rectangle(550, 700, 100, 40),
            new Rectangle(150, 700, 100, 40),
            new Rectangle(110, 600, 40, 100),
    };

    /**
     * Creates whole view of the board and surroundings
     * @param boardCtrl reference to the controller
     */
    public BoardView(BoardCtrl boardCtrl) {
        try {
            arrow1 = ImageIO.read(new File("/home/adele/studies/pentago/src/re/neutrino/adele/images/arrow1.png"));
            arrow2 = ImageIO.read(new File("/home/adele/studies/pentago/src/re/neutrino/adele/images/arrow2.png"));
            arrow3 = ImageIO.read(new File("/home/adele/studies/pentago/src/re/neutrino/adele/images/arrow3.png"));
            arrow4 = ImageIO.read(new File("/home/adele/studies/pentago/src/re/neutrino/adele/images/arrow4.png"));
            arrow5 = ImageIO.read(new File("/home/adele/studies/pentago/src/re/neutrino/adele/images/arrow5.png"));
            arrow6 = ImageIO.read(new File("/home/adele/studies/pentago/src/re/neutrino/adele/images/arrow6.png"));
            arrow7 = ImageIO.read(new File("/home/adele/studies/pentago/src/re/neutrino/adele/images/arrow7.png"));
            arrow8 = ImageIO.read(new File("/home/adele/studies/pentago/src/re/neutrino/adele/images/arrow8.png"));
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        this.boardCtrl = boardCtrl;
        initPanel();
        initHeader();
        panel.add(header);
        initPlaces();
    }

    /**
     * Initializes label header
     */
    private void initHeader() {
        header.setBackground(GameConstant.BG_DARK_COLOR);
        header.setOpaque(true);
        header.setForeground(GameConstant.FG_COLOR);
        header.setFont(GameConstant.DEFAULT_FONT_BIG);
        header.setBorder(BorderFactory.createEmptyBorder(40, 228, 40, 228));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    /**
     * Initializes panel and adds mouse listener waiting for the move
     */
    private void initPanel() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                boardCtrl.handleClick(circles, arrows, e.getPoint());
                if((header.getText().equals(GameConstant.WINNER_WHITE) || header.getText().equals(GameConstant.WINNER_BLACK)) && e.getClickCount() == 2 ) {
                    drawEndOfGamePanel();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    /**
     * Draws board and ball places
     * @param g graphics
     */
    private void drawBoard(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));

        for(Square s : squares) {
            s.draw(g2);
        }

        for(Circle c : circles) {
            c.draw(g2);
        }
    }

    /**
     * Initializes ball places
     */
    private void initPlaces() {
        int x = 0, y = 0;
        for(int i = 0; i < 6; i++) {
            if(i >= 3)
                x = 30;
            for(int j = 0; j < 6; j++) {
                if(j >= 3)
                    y = 30;
                circles[i * 6 + j] = new Circle(180 + x + 75 * i, 230 + y + 75 * j, 40);
            }
            y = 0;
        }
    }

    /**
     * Repaints the panel
     */
    private void repaint() {
        panel.repaint();
    }

    /**
     * Provides access to the panel
     * @return panel
     */
    public JPanel getPanel() {
        return panel;
    }

    /**
     * Colors places if it has changed
     * @param e event
     */
    @Override
    public void onFieldChanged(FieldChangedEvent e) {
        Circle circle = circles[getIndex(e.getX(), e.getY())];
        switch (e.getBall()) {
            case BLACK:
                circle.setBlack();
                break;
            case WHITE:
                circle.setWhite();
                break;
            case NONE:
                circle.setTransparent();
        }
        repaint();
    }

    /**
     * Translates 2D array to 1D array
     * @param x x-coordinate
     * @param y y-coordinate
     * @return index in 1D array
     */
    private int getIndex(int x, int y) {
        return x + y * 6;
    }

    /**
     * Informs about the winner
     * @param ball winner ball
     */
    private void setLabelWin(Ball ball) {
        if(ball == Ball.BLACK)
            header.setText(GameConstant.WINNER_BLACK);
        else
            header.setText(GameConstant.WINNER_WHITE);
        repaint();
    }

    /**
     * Changes label text after switch turn
     * @param label with changed text
     */
    public void setLabel(String label) {
        header.setText(label);
    }

    /**
     * Decides if display arrows
     * @param visibility if display or not
     */
    public void setArrowsVisible(boolean visibility) {
        arrowsVisibility = visibility;
    }

    /**
     * Displays button on the end of the game
     */
    private JButton displayButton(String label, int offset, int x, int y) {
        JButton button = new JButton(label);
        panel.add(Box.createRigidArea(new Dimension(offset, offset)));
        panel.add(button);

        button.setBackground(GameConstant.BG_LIGHT_COLOR);
        button.setBorder(BorderFactory.createEmptyBorder(x, y, x, y));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setForeground(GameConstant.FG_COLOR);
        button.setFont(GameConstant.DEFAULT_FONT_SMALL);
        repaint();
        return button;
    }

    /**
     * Displays end-of-game buttons
     */
    private void drawEndOfGamePanel() {
        endOfGame = true;
        JButton buttonMenu = displayButton("Menu", 160, 40, 120);
        buttonMenu.addActionListener(e -> boardCtrl.openMenu());
        JButton buttonQuit = displayButton("Quit", 50, 40, 130);
        buttonQuit.addActionListener(e -> boardCtrl.quit());
    }

    /**
     * End-of-game action
     * @param winner who wins
     */
    public void endOfGame(Ball winner) {
        setLabelWin(winner);
    }

    /**
     * Panel inherits JPanel drawing ability
     */
    private class BoardPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            if(arrowsVisibility) {
                drawBoard(g);
                drawArrows(g);
            }
            else {
                for (Rectangle rect: arrows) {
                    g2.setPaint(GameConstant.BG_COLOR);
                    g2.fill(rect);
                    drawBoard(g);
                }
            }
            if(endOfGame) {
                Rectangle frame = new Rectangle(200, 250, 400, 400);
                g2.setPaint(GameConstant.BOREDER_COLOR);
                g2.fill(frame);
                g2.setPaint(GameConstant.FG_COLOR);
                g2.draw(frame);
            }
        }
    }

    /**
     * Draw arrows on appropriate places
     * @param g graphics
     */
    private void drawArrows(Graphics g) {
        g.drawImage(arrow1, 150, 160, 100, 40, null);
        g.drawImage(arrow2, 550, 160, 100, 40, null);
        g.drawImage(arrow3, 650, 200, 40, 100, null);
        g.drawImage(arrow4, 650, 600, 40, 100, null);
        g.drawImage(arrow5, 550, 700, 100, 40, null);
        g.drawImage(arrow6, 150, 700, 100, 40, null);
        g.drawImage(arrow7, 110, 600, 40, 100, null);
        g.drawImage(arrow8, 110, 200, 40, 100, null);
    }
}