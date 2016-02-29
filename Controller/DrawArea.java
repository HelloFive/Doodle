package Controller;

import Model.Canvas;
import View.SouthBoxLayout;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
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
        addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {

                // save coord x,y when mouse is pressed
                oldX = e.getX();
                oldY = e.getY();

                Canvas.g2.fillOval(e.getX()-Canvas.strokeThickness/2, e.getY()-Canvas.strokeThickness/2,
                        Canvas.strokeThickness, Canvas.strokeThickness);

/*
                Canvas.Points.add(Canvas.MAX_STATE, new Point(e.getX(), e.getY()));
                Canvas.Strokes.add(Canvas.MAX_STATE, Canvas.strokeThickness);
                Canvas.Colors.add(Canvas.MAX_STATE, Canvas.lineColor);
                Canvas.Times.add(Canvas.MAX_STATE, System.nanoTime());
                Canvas.MAX_STATE++;
                SouthBoxLayout.stateSlider.setValue(Canvas.CURRENT_STATE/(Canvas.MAX_STATE+1));
*/

                Canvas.SaveLines(new Point(e.getX(), e.getY()));

//                SouthBoxLayout.stateSlider = new JSlider(0, (int) Canvas.MAX_STATE, Canvas.CURRENT_STATE/(Canvas.MAX_STATE+1));

                /*
                From[i].x = oldX;
                From[i].y = oldY;
                */


            }

        });

        addMouseMotionListener(new MouseMotionAdapter() {



            public void mouseMoved (MouseEvent e) {
                //player.position.x = e.getX() - getWidth()/2;
                //g2.drawOval(e.getX(), e.getY(), DrawArea.strokeThickness,DrawArea.strokeThickness);


                repaint();
            }

            public void mouseDragged(MouseEvent e) {
                currentX = e.getX();
                currentY = e.getY();

                System.out.println("DrawArea: currentX = " + currentX + ", currentY = " + currentY);

                /*
                if ((currentX != oldX)&&(currentY != oldY)) {
                    To[i].x = currentX;
                    To[i].y = currentY;
                    Colors[i] = g2.getColor();
                    System.out.println("i increased.");
                    i++;
                }
                */


                if (Canvas.g2 != null) {
                    // draw line if g2 context not null

                    //g2.setStroke(new BasicStroke(strokeThickness));
                    Canvas.g2.setStroke(new BasicStroke(Canvas.strokeThickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                    Canvas.g2.drawLine(oldX, oldY, currentX, currentY);


/*
                    Canvas.Points.add(Canvas.MAX_STATE, new Point(e.getX(), e.getY()));
                    Canvas.Strokes.add(Canvas.MAX_STATE, Canvas.strokeThickness);
                    Canvas.Colors.add(Canvas.MAX_STATE, Canvas.lineColor);
                    Canvas.Times.add(Canvas.MAX_STATE, System.nanoTime());
                    Canvas.MAX_STATE++;
*/


                    Canvas.SaveLines(new Point(e.getX(), e.getY()));

//                    SouthBoxLayout.stateSlider = new JSlider(0, (int) Canvas.MAX_STATE, Canvas.CURRENT_STATE/(Canvas.MAX_STATE+1));


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


/*            System.out.println("getWidth() = " + getWidth());
            System.out.println("getHeight() = " + getHeight());
            System.out.println("getSize().width = " + getSize().width);
            System.out.println("getSize().height = " + getSize().height);

            System.out.println("Canvas.theWidth = " + Canvas.theWidth);
            System.out.println("Canvas.theHeight = " + Canvas.theHeight);
*/


            Canvas.theWidth = getSize().width;
            Canvas.theHeight = getSize().height;

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

/*
    public void tick(Graphics g) {
        long deltatime = (System.nanoTime()-Canvas.lastUpdate)/1000000.0; // in milliseconds
        ball.tick(deltatime);
        if(bricks.isEmpty() && BrickLines == 10) OnGameOver(true);
        else if(bricks.isEmpty()) {
            BrickLines++;
            next();
        }
        repaint();
    }*/


}
