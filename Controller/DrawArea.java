package Controller;

import Model.Canvas;
import View.SouthBoxLayout;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Observer;

/**
 * Created by SPARK on 2016-02-28.
 */

public class DrawArea extends JComponent  implements Observer {



/*
    private class MaxStateController implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            Canvas.increaseMaxState();
        }
    }
*/



    @Override
    public void update(java.util.Observable obs, Object x) {
        repaint();
        System.out.println("update(" + obs + "," + x + ");");
    }

    // Mouse coordinates
    private int currentX, currentY, oldX, oldY;

    public DrawArea() {

        setDoubleBuffered(false);

        addKeyListener (new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    if (Canvas.countSpring % 2 == 0) {
                        System.out.println("Pressed S: Activated Spring Mode");
                        Canvas.isSpring = true;
                    } else {
                        System.out.println("Pressed S: Deactivated Spring Mode");
                        Canvas.isSpring = false;
                    }
                    Canvas.countSpring++;
                }
            }
        });

        addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {

                // save coord x,y when mouse is pressed
                oldX = e.getX();
                oldY = e.getY();

                Canvas.g2.fillOval(e.getX()-Canvas.getStroke()/2, e.getY()-Canvas.getStroke()/2,
                        Canvas.getStroke(), Canvas.getStroke());

                Canvas.SaveLines(new Point(e.getX(), e.getY()));

            }

        });

        addMouseMotionListener(new MouseMotionAdapter() {



            public void mouseMoved (MouseEvent e) {
                if (Canvas.isSpring) {
                    Canvas.g2.drawOval(e.getX(), e.getY(), Canvas.getStroke(), Canvas.getStroke());
                }

                repaint();
            }

            public void mouseDragged(MouseEvent e) {
                currentX = e.getX();
                currentY = e.getY();

                //System.out.println("DrawArea: currentX = " + currentX + ", currentY = " + currentY);


                if (Canvas.g2 != null) {
                    // draw line if g2 context not null

                    //g2.setStroke(new BasicStroke(strokeThickness));
                    Canvas.g2.setStroke(new BasicStroke(Canvas.getStroke(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                    Canvas.g2.drawLine(oldX, oldY, currentX, currentY);

                    Canvas.SaveLines(new Point(e.getX(), e.getY()));

                    // store current corrds x,y as olds x,y
                    oldX = currentX;
                    oldY = currentY;


                    // refresh draw area to repaint
                    repaint();
                }
            }
        });


    }

    protected void paintComponent (Graphics g) {
        if (Canvas.image == null) {
            // image to draw null ==> we create
            Canvas.image = createImage(getSize().width, getSize().height);

            Canvas.setWidth(getSize().width);
            Canvas.setHeight(getSize().height);

            Canvas.g2 = (Graphics2D) Canvas.image.getGraphics();
            Canvas.g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            clear();
        }

        g.drawImage(Canvas.image, 0, 0, null);
    }

    public void clear() {
        Canvas.g2.setPaint(Color.white);
        // draw white on entire draw area to clear
        Canvas.g2.fillRect(0, 0, getSize().width, getSize().height);
        Canvas.g2.setPaint(Color.black);
        repaint();
    }
}
