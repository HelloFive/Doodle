package Controller;

import Model.Canvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Observer;

/**
 * Created by SPARK on 2016-02-28.
 */

public class GuiArea extends JComponent implements Observer {




    // Image in which we're going to draw
    private Image image;

    // Graphics2d object --> used to draw on
    public static Graphics2D g2;

    //public static int strokeThickness = 0;


    public static Color backColor;
    //public static Color lineColor;

    // Mouse coordinates
    private int currentX, currentY, oldX, oldY, firstX, firstY, baseX, baseY;

    public GuiArea() {

        setDoubleBuffered(false);
        setMinimumSize(new Dimension(126,126));
        setMaximumSize(new Dimension(126,126));



        baseX = 63;
        baseY = 63;


        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                // save coord x,y when mouse is pressed
                firstX = e.getX();
                firstY = e.getY();
                oldX = e.getX();
                oldY = e.getY();

                System.out.println("oldX = " + oldX + ", oldY = " + oldY);

                if (e.getClickCount() == 2) {
                    Canvas.setColor(JColorChooser.showDialog(null, "Choose a Color", Color.black));
                    Canvas.g2.setPaint(Canvas.getColor());

                    repaint();
                    backColor = Color.white;
                    g2.setColor(backColor);
                    g2.fillRect(0,0,126,126);
                    g2.setPaint(Canvas.getColor());
                    g2.fillOval(63-(Canvas.getStroke())/2,63-(Canvas.getStroke())/2,
                            Canvas.getStroke(), Canvas.getStroke());

                }
            }
        });


        addMouseMotionListener(new MouseMotionAdapter() {

            public void mouseDragged(MouseEvent e) {
                currentX = e.getX();
                currentY = e.getY();

                System.out.println("currentX = " + currentX + ", currentY = " + currentY);


                if (Canvas.g2 != null) {
                    if ((Canvas.getStroke() <= 100) && (Canvas.getStroke() >= 0)) {
                        if ( (currentX < 126) && (currentY < 126)) {
                            if (currentY-oldY < 0) {
                                System.out.println("Canvas.strokeThickness += " + (currentY - oldY));
                                //Canvas.strokeThickness += (currentY - oldY);
                                Canvas.setStrokeThickness(Canvas.getStroke()+currentY-oldY);

                                if (Canvas.getStroke() < 0) {
                                    System.out.println("   Canvas.strokeThickness is less than 0");
                                    Canvas.setStrokeThickness(0);
                                }
                                System.out.println("Canvas.strokeThickness = " + Canvas.getStroke());


                            } else {
                                System.out.println("Canvas.strokeThickness += " + (currentY - oldY));
                                //Canvas.strokeThickness += (currentY - oldY);
                                Canvas.setStrokeThickness(Canvas.getStroke()+currentY-oldY);

                                if (Canvas.getStroke() > 100) {
                                    System.out.println("   Canvas.strokeThickness is greater than 100");
                                    Canvas.setStrokeThickness(100);// = 100;
                                }
                                System.out.println("Canvas.strokeThickness = " + Canvas.getStroke());
                            }
                        }
                    }

                    // store current corrds x,y as olds x,y
                    oldX = currentX;
                    oldY = currentY;

                    // refresh gui area to repaint
                    repaint();

                    System.out.println("   current Canvas.lineColor = " + Canvas.getColor());

                    g2.setPaint(backColor);
                    g2.fillRect(0,0,126,126);
                    g2.setColor(Canvas.getColor());
                    g2.fillOval(63-(Canvas.getStroke())/2,63-(Canvas.getStroke())/2,
                            Canvas.getStroke(),Canvas.getStroke());

                }
            }
        });
    }

    protected void paintComponent (Graphics g) {
        if (image == null) {
            // image to draw null ==> we create
            //image = createImage(getSize().width, getSize().height);
            image = createImage(126,126);
            g2 = (Graphics2D) image.getGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


            backColor = Color.white;
            g2.setPaint(backColor);
            g2.fillRect(0, 0, 126, 126); //g2.fillRect(0, 0, getSize().width, getSize().height);
            Canvas.setColor(Color.black);
            g2.setPaint(Canvas.getColor());
            repaint();

        }

        g.drawImage(image, 0, 0, null);

        g2.fillOval(63-(Canvas.getStroke())/2,63-(Canvas.getStroke())/2,
                Canvas.getStroke(),Canvas.getStroke());


    }
/*
    public void clear() {
        backColor = Color.white;
        g2.setPaint(backColor);
        // draw white on entire draw area to clear
        //g2.fillRect(0, 0, getSize().width, getSize().height);
        g2.fillRect(0, 0, 126, 126);
        lineColor = Color.black;
        g2.setPaint(Color.black);
        repaint();
    }
*/


    public void eraser() {
        backColor = Color.black;
        g2.setPaint(backColor);
        g2.fillRect(0, 0, 126, 126);
        Canvas.setColor(Color.white);// = Color.white;
        g2.setPaint(Canvas.getColor());
        g2.fillOval(63-(Canvas.getStroke())/2,63-(Canvas.getStroke())/2,
                Canvas.getStroke(),Canvas.getStroke());
    }

    public void red() {
        backColor = Color.white;
        g2.setPaint(backColor);
        g2.fillRect(0, 0, 126, 126);
        Canvas.setColor(Color.red);// = Color.red;
        g2.setPaint(Canvas.getColor());
        g2.fillOval(63-(Canvas.getStroke())/2,63-(Canvas.getStroke())/2,
                Canvas.getStroke(),Canvas.getStroke());
    }
    public void blue() {
        backColor = Color.white;
        g2.setPaint(backColor);
        g2.fillRect(0, 0, 126, 126);
        Canvas.setColor(Color.blue);// = Color.blue;
        g2.setPaint(Canvas.getColor());
        g2.fillOval(63-(Canvas.getStroke())/2,63-(Canvas.getStroke())/2,
                Canvas.getStroke(),Canvas.getStroke());
    }
    public void green() {
        backColor = Color.white;
        g2.setPaint(backColor);
        g2.fillRect(0, 0, 126, 126);
        Canvas.setColor(Color.green);// = Color.green;
        g2.setPaint(Canvas.getColor());
        g2.fillOval(63-(Canvas.getStroke())/2,63-(Canvas.getStroke())/2,
                Canvas.getStroke(),Canvas.getStroke());
    }
    public void yellow() {
        backColor = Color.white;
        g2.setPaint(backColor);
        g2.fillRect(0, 0, 126, 126);
        Canvas.setColor(Color.yellow);// = Color.yellow;
        g2.setPaint(Canvas.getColor());
        g2.fillOval(63-(Canvas.getStroke())/2,63-(Canvas.getStroke())/2,
                Canvas.getStroke(),Canvas.getStroke());
    }


    @Override
    public void update(java.util.Observable obs, Object x) {
        repaint();
        System.out.println("update(" + obs + "," + x + ");");
    }
}
