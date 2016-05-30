package re.neutrino.adele.views;

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
 * Class BoardView
 * creates panel on witch draws the board
 */
public class BoardView implements FieldChangedEventListener {
    private final BoardPanel panel = new BoardPanel();
    private final JLabel labelTurn = new JLabel("TURN: WHITE");
    private final BoardCtrl boardCtrl;
    private final Circle[] circles = new Circle[36];
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
    private final Square[] squares = new Square[] {
            new Square(150, 200, 250),
            new Square(400, 200, 250),
            new Square(150, 450, 250),
            new Square(400, 450, 250)
    };
    private BufferedImage arrow1, arrow2, arrow3, arrow4, arrow5, arrow6, arrow7, arrow8;


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
            System.out.println("EXCEPTION");
        }

        this.boardCtrl = boardCtrl;
        initPanel();
        initLabel();
        panel.add(labelTurn);
        initPlaces();
    }

    private void initLabel() {
        labelTurn.setBackground(GameConstant.BG_DARK_COLOR);
        labelTurn.setOpaque(true);
        labelTurn.setForeground(GameConstant.FG_COLOR);
        labelTurn.setFont(GameConstant.DEFAULT_FONT_BIG);
        labelTurn.setBorder(BorderFactory.createEmptyBorder(40, 228, 40, 228));
        labelTurn.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void initPanel() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.addMouseListener(new MouseListener() {


            @Override
            public void mouseClicked(MouseEvent e) {
                boardCtrl.handleBoardClick(circles, e.getPoint());
                boardCtrl.handleArrowClick(arrows, e.getPoint());
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

    private void repaint() {
        panel.repaint();
    }

    public JPanel getPanel() {
        return panel;
    }

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

    private int getIndex(int x, int y) {
        return x + y * 6;
    }

    private class BoardPanel extends JPanel{
        @Override
        public void paintComponent(Graphics g) {
            drawBoard(g);
            drawArrows(g);
        }
    }

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