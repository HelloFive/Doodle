import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * Created by SPARK on 2016-02-23.
 */
public class GuiArea extends JComponent {

    // Image in which we're going to draw
    private Image image;

    // Graphics2d object --> used to draw on
    public static Graphics2D g2;

    public static int strokeThickness = 0;


    public static Color backColor;
    public static Color lineColor;

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
                    lineColor = JColorChooser.showDialog(null, "Choose a Color", Color.black);
                    DrawArea.g2.setPaint(lineColor);

                    repaint();
                    backColor = Color.white;
                    g2.setColor(backColor);
                    g2.fillRect(0,0,126,126);
                    g2.setPaint(lineColor);
                    g2.fillOval(63-(DrawArea.strokeThickness)/2,63-(DrawArea.strokeThickness)/2,
                            DrawArea.strokeThickness,DrawArea.strokeThickness);

                }
            }
        });

/*
        addMouseListener(new MouseAdapter() {

            public void mouseReleased(MouseEvent e) {
                // save coord x,y when mouse is pressed
                if ((currentX==firstX)&&(currentY==firstY)) {
                    currentX = e.getX();
                    currentY = e.getY();

                    System.out.println("currentX = " + currentX + ", currentY = " + currentY);

                    lineColor = JColorChooser.showDialog(null, "Choose a Color", Color.black);
                    DrawArea.g2.setPaint(lineColor);


                    clear();
                    repaint();
                }
            }
        });
*/

        addMouseMotionListener(new MouseMotionAdapter() {

            public void mouseDragged(MouseEvent e) {
                currentX = e.getX();
                currentY = e.getY();

                System.out.println("currentX = " + currentX + ", currentY = " + currentY);


                if (g2 != null) {
                    if ((DrawArea.strokeThickness <= 100) && (DrawArea.strokeThickness >= 0)) {
                        if ( (currentX < 126) && (currentY < 126)) {
                            if (currentY-oldY < 0) {
                                //System.out.println("DrawArea.strokeThickness -= " + Math.sqrt((currentX - baseX) ^ 2 - (currentY - baseY) ^ 2));
                                //DrawArea.strokeThickness -= Math.sqrt((currentX - baseX) ^ 2 - (currentY - baseY) ^ 2);
                                System.out.println("DrawArea.strokeThickness += " + (currentY - oldY));
                                DrawArea.strokeThickness += (currentY - oldY);

                                if (DrawArea.strokeThickness < 0) {
                                    System.out.println("   DrawArea.strokeThickness is less than 0");
                                    DrawArea.strokeThickness = 0;
                                }
                                System.out.println("DrawArea.strokeThickness = " + DrawArea.strokeThickness);


                            } else {
                                //System.out.println("DrawArea.strokeThickness += " + Math.sqrt((currentX - baseX) ^ 2 - (currentY - baseY) ^ 2));
                                //DrawArea.strokeThickness += Math.sqrt((currentX - baseX) ^ 2 - (currentY - baseY) ^ 2);
                                System.out.println("DrawArea.strokeThickness += " + (currentY - oldY));
                                DrawArea.strokeThickness += (currentY - oldY);

                                if (DrawArea.strokeThickness > 100) {
                                    System.out.println("   DrawArea.strokeThickness is greater than 100");
                                    DrawArea.strokeThickness = 100;
                                }
                                System.out.println("DrawArea.strokeThickness = " + DrawArea.strokeThickness);
                            }
                        }
                    }

                    //g2.fillOval(getSize().width/2,getSize().height/2,DrawArea.strokeThickness+100,DrawArea.strokeThickness+100);

                    // store current corrds x,y as olds x,y
                    oldX = currentX;
                    oldY = currentY;

                    //clear();

                    // refresh gui area to repaint
                    repaint();

                    System.out.println("   current lineColor = " + lineColor);

                    g2.setPaint(backColor);
                    g2.fillRect(0,0,126,126);
                    g2.setColor(lineColor);
                    g2.fillOval(63-(DrawArea.strokeThickness)/2,63-(DrawArea.strokeThickness)/2,
                            DrawArea.strokeThickness,DrawArea.strokeThickness);

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

            //g2.fillOval(63-(DrawArea.strokeThickness+50)/2,63-(DrawArea.strokeThickness+50)/2,
            //        DrawArea.strokeThickness,DrawArea.strokeThickness);

            //g2.setPaint(lineColor);

            // clear draw area
            //clear();
            backColor = Color.white;
            g2.setPaint(backColor);
            g2.fillRect(0, 0, 126, 126); //g2.fillRect(0, 0, getSize().width, getSize().height);
            lineColor = Color.black;
            g2.setPaint(lineColor);
            repaint();

            //g2.fillOval(63-(DrawArea.strokeThickness+50)/2,63-(DrawArea.strokeThickness+50)/2,
            //        DrawArea.strokeThickness,DrawArea.strokeThickness);
        }

        g.drawImage(image, 0, 0, null);

        g2.fillOval(63-(DrawArea.strokeThickness)/2,63-(DrawArea.strokeThickness)/2,
                DrawArea.strokeThickness,DrawArea.strokeThickness);



        //g2.fillOval(getSize().width/2,getSize().height/2,DrawArea.strokeThickness,DrawArea.strokeThickness);

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
        lineColor = Color.white;
        g2.setPaint(lineColor);
        g2.fillOval(63-(DrawArea.strokeThickness)/2,63-(DrawArea.strokeThickness)/2,
                DrawArea.strokeThickness,DrawArea.strokeThickness);
    }

    public void red() {
        backColor = Color.white;
        g2.setPaint(backColor);
        g2.fillRect(0, 0, 126, 126);
        lineColor = Color.red;
        g2.setPaint(lineColor);
        g2.fillOval(63-(DrawArea.strokeThickness)/2,63-(DrawArea.strokeThickness)/2,
                DrawArea.strokeThickness,DrawArea.strokeThickness);
    }
    public void blue() {
        backColor = Color.white;
        g2.setPaint(backColor);
        g2.fillRect(0, 0, 126, 126);
        lineColor = Color.blue;
        g2.setPaint(lineColor);
        g2.fillOval(63-(DrawArea.strokeThickness)/2,63-(DrawArea.strokeThickness)/2,
                DrawArea.strokeThickness,DrawArea.strokeThickness);
    }
    public void green() {
        backColor = Color.white;
        g2.setPaint(backColor);
        g2.fillRect(0, 0, 126, 126);
        lineColor = Color.green;
        g2.setPaint(lineColor);
        g2.fillOval(63-(DrawArea.strokeThickness)/2,63-(DrawArea.strokeThickness)/2,
                DrawArea.strokeThickness,DrawArea.strokeThickness);
    }
    public void yellow() {
        backColor = Color.white;
        g2.setPaint(backColor);
        g2.fillRect(0, 0, 126, 126);
        lineColor = Color.yellow;
        g2.setPaint(lineColor);
        g2.fillOval(63-(DrawArea.strokeThickness)/2,63-(DrawArea.strokeThickness)/2,
                DrawArea.strokeThickness,DrawArea.strokeThickness);
    }

}
