package re.neutrino.adele.views;

import re.neutrino.adele.FieldChangedEvent;
import re.neutrino.adele.FieldChangedEventListener;
import re.neutrino.adele.GameConstant;
import re.neutrino.adele.controllers.BoardCtrl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Class BoardView
 * creates panel on witch draws the board
 */
public class BoardView implements FieldChangedEventListener {
    private final BoardPanel panel = new BoardPanel();
    private final JLabel labelTurn = new JLabel("TURN: WHITE");
    private final BoardCtrl boardCtrl;
    private final Square[] squares = new Square[] {
            new Square(150, 200, 250),
            new Square(400, 200, 250),
            new Square(150, 450, 250),
            new Square(400, 450, 250)
    };

    private final Circle[] circles = new Circle[36];

    public BoardView(BoardCtrl boardCtrl) {
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
                boardCtrl.handleClick(circles, e.getPoint());
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

    public void repaint() {
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
        panel.repaint();
    }

    private int getIndex(int x, int y) {
        return x + y * 6;
    }

    private class BoardPanel extends JPanel{
        @Override
        public void paintComponent(Graphics g) {
            drawBoard(g);
        }
    }
}