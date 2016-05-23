package re.neutrino.adele.views;

import re.neutrino.adele.controllers.BoardCtrl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Class BoardView
 * creates panel on witch draws the board
 */
public class BoardView {
    private final JFrame frame = new JFrame(Constant.PENTAGO);
    private final BoardPanel root = new BoardPanel();
    private final JLabel labelTurn = new JLabel("TURN: WHITE");
    private final BoardCtrl boardCtrl = new BoardCtrl();
    private final Square[] squares = new Square[] {
            new Square(150, 200, 250),
            new Square(400, 200, 250),
            new Square(150, 450, 250),
            new Square(400, 450, 250)
    };

    private final Circle[] circles = new Circle[36];

    public BoardView() {
        initFrame();
        initRoot();
        frame.add(root);
        initLabel();
        root.add(labelTurn);
        initPlaces();
    }

    private void initLabel() {
        labelTurn.setBackground(Constant.BG_DARK_COLOR);
        labelTurn.setOpaque(true);
        labelTurn.setForeground(Constant.FG_COLOR);
        labelTurn.setFont(Constant.DEFAULT_FONT_BIG);
        labelTurn.setBorder(BorderFactory.createEmptyBorder(40, 228, 40, 228));
        labelTurn.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void initRoot() {
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.addMouseListener(new MouseListener() {


            @Override
            public void mouseClicked(MouseEvent e) {
                boardCtrl.handleClick(BoardView.this, circles, e.getPoint());
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

    private void initFrame() {
        frame.setBounds(500, 150, 800, 800);
        frame.setVisible(true);
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
        root.repaint();
    }

    private class BoardPanel extends JPanel{
        @Override
        public void paintComponent(Graphics g) {
            drawBoard(g);
        }
    }
}