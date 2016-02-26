/**
 * Created by SPARK on 2016-02-19.
 */
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JComponent;
import javax.swing.text.Position;



public class DrawArea extends JComponent {

    // Image in which we're going to draw
    private Image image;

    public static Image[] Images;

    public static Point[] From, To;
    public static Color[] Colors;
    public static int i = 0;

    // Graphics@d object --> used to draw on
    public static Graphics2D g2;

    public static int strokeThickness = 30;



    // Mouse coordinates
    private int currentX, currentY, oldX, oldY;

    public DrawArea() {
        setDoubleBuffered(false);
        addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                g2.fillOval(e.getX()-strokeThickness/2, e.getY()-strokeThickness/2, strokeThickness, strokeThickness);

                // save coord x,y when mouse is pressed
                oldX = e.getX();
                oldY = e.getY();

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


                if (g2 != null) {
                    // draw line if g2 context not null

                    //g2.setStroke(new BasicStroke(strokeThickness));
                    g2.setStroke(new BasicStroke(strokeThickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                    g2.drawLine(oldX, oldY, currentX, currentY);


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
        if (image == null) {
            // image to draw null ==> we create
            image = createImage(getSize().width, getSize().height);
            g2 = (Graphics2D) image.getGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // clear draw area
            clear();
        }

        g.drawImage(image, 0, 0, null);
    }

    // now we create exposed methods

    public void clear() {
        g2.setPaint(Color.white);
        // draw white on entire draw area to clear
        g2.fillRect(0, 0, getSize().width, getSize().height);
        g2.setPaint(Color.black);
        repaint();
    }


    public void red() {
        // apply red color on g2 context
        g2.setPaint(Color.red);
    }
    public void black() {
        // apply black color on g2 context
        g2.setPaint(Color.black);
    }
    public void magenta() {
        // apply magenta color on g2 context
        g2.setPaint(Color.magenta);
    }
    public void green() {
        // apply green color on g2 context
        g2.setPaint(Color.green);
    }
    public void blue() {
        // apply blue color on g2 context
        g2.setPaint(Color.blue);
    }

    // Now, we're going to create main part of the program to use the draw area





}
