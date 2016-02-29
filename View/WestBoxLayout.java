package View;


import Controller.GuiArea;
import Model.Canvas;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by SPARK on 2016-02-27.
 */
public class WestBoxLayout extends JPanel {


    public static GuiArea guiArea;//

    // Using struts and glue
    public WestBoxLayout() {
        super();
        this.setBackground(Color.gray);
        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        guiArea = new GuiArea(); // GuiArea
        this.add(guiArea, BorderLayout.CENTER);

        //guiArea.setMinimumSize(new Dimension(126,126));
        //guiArea.setMaximumSize(new Dimension(126,126));

        //guiArea.setResizable(false);
        //this.setResizable(false);


        //this.add(guiArea); //, BoxLayout.X_AXIS);
        //this.add(Box.createRigidArea(new Dimension(66,66)));


        JButton setClear = new JButton("Clear");
        setClear.setBackground(Color.white);
        setClear.setForeground(Color.black);
        setClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Canvas.g2.setPaint(Color.white);
                System.out.println("Clear Button Pressed");
                // draw white on entire draw area to clear
/*
                Canvas.g2.fillRect(0, 0, EastBoxLayout.drawArea.getSize().width,
                        EastBoxLayout.drawArea.getSize().height);
                //DrawArea.g2.setPaint(Color.black);
                Canvas.g2.setPaint(Canvas.lineColor);
                EastBoxLayout.drawArea.repaint();
*/
                Canvas.g2.fillRect(0, 0, Canvas.theWidth, Canvas.theHeight);
                //DrawArea.g2.setPaint(Color.black);
                Canvas.g2.setPaint(Canvas.lineColor);

                Canvas.drawArea.repaint();
                //DrawArea.repaint();

            }
        });
        this.add(setClear);

/*
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawOval(0,0,50,50);
        }
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(100,100);
        }
*/





        JButton colorPalette = new JButton("Color Palette");
        colorPalette.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Color Palette Pressed");

                Color lineColor = JColorChooser.showDialog(null, "Choose a Color", Color.black);

                repaint();
                guiArea.backColor = Color.white;
                guiArea.g2.setColor(guiArea.backColor);
                guiArea.g2.fillRect(0,0,126,126);
                Canvas.lineColor = lineColor;
                guiArea.g2.setPaint(Canvas.lineColor);
                guiArea.g2.fillOval(63-(Canvas.strokeThickness)/2,63-(Canvas.strokeThickness)/2,
                        Canvas.strokeThickness,Canvas.strokeThickness);





                Canvas.g2.setPaint(lineColor);
                if (lineColor != null) lineColor = Color.black;
            }
        });
        this.add(colorPalette);






        JSlider theStroke = new JSlider(0, 50, Canvas.strokeThickness);
        theStroke.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Object source = e.getSource();
                if (source instanceof BoundedRangeModel) {
                    BoundedRangeModel aModel = (BoundedRangeModel) source;
                    if (!aModel.getValueIsAdjusting()) {
                        System.out.println("Changed: " + aModel.getValue());
                    }
                } else if (source instanceof JSlider) {
                    JSlider theJSlider = (JSlider) source;
                    if (!theJSlider.getValueIsAdjusting()) {
                        System.out.println("Slider changed: " + theJSlider.getValue());
                        Canvas.strokeThickness = theJSlider.getValue();
                    }
                } else {
                    System.out.println("Something changed: " + source);
                }
            }
        });

        this.add(new JLabel("Thickness"));
        this.add(theStroke);



        //this.add(Box.createVerticalGlue());
        //this.add(Box.createHorizontalGlue());
        //this.add(Box.createRigidArea());

        //this.add(Box.createVerticalStrut(63));
        //this.add(Box.createHorizontalStrut(63));

        JButton setEraser = new JButton("Eraser");
        setEraser.setBackground(Color.black);
        setEraser.setForeground(Color.black);
        setEraser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Eraser Pressed");

                Canvas.lineColor = Color.white;
                guiArea.backColor = Color.black;
                /*
                GuiArea.g2.setPaint(Color.black);
                GuiArea.g2.fillRect(0, 0, 126, 126);
                 */

                //GuiArea.eraser();
                guiArea.repaint();
                guiArea.eraser();


                Canvas.g2.setPaint(Canvas.lineColor);
                //DrawArea.g2.setPaint(Color.white);
                // Problem: This causes Thickness of Stroke to be strictly changed to 20.
                // DrawArea.strokeThickness = 20;
            }
        });
        this.add(setEraser);


        JButton setRed = new JButton("Red");
        setRed.setBackground(Color.red);
        setRed.setForeground(Color.red);
        setRed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Red Pressed");
                /*
                GuiArea.lineColor = Color.RED;
                 */

                guiArea.repaint();
                guiArea.red();

                Canvas.g2.setPaint(Color.red);
            }
        });
        this.add(setRed);

        JButton setBlue = new JButton("Blue");
        setBlue.setBackground(Color.blue);
        setBlue.setForeground(Color.blue);
        setBlue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Blue Pressed");
                /*
                GuiArea.lineColor = Color.blue;
                 */


                guiArea.repaint();
                guiArea.blue();

                Canvas.g2.setPaint(Color.blue);
            }
        });
        this.add(setBlue);

        JButton setGreen = new JButton("Green");
        setGreen.setBackground(Color.green);
        setGreen.setForeground(Color.green);
        setGreen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Green Pressed");
                /*
                GuiArea.lineColor = Color.green;
                 */

                guiArea.repaint();
                guiArea.green();

                Canvas.g2.setPaint(Color.green);
            }
        });
        this.add(setGreen);

        JButton setYellow = new JButton("Yellow");
        setYellow.setBackground(Color.yellow);
        setYellow.setForeground(Color.yellow);
        setYellow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Yellow Pressed");
                /*
                GuiArea.lineColor = Color.yellow;
                 */

                guiArea.repaint();
                guiArea.yellow();

                Canvas.g2.setPaint(Color.yellow);

                /*
                guiArea.g2.setPaint(guiArea.lineColor);

                guiArea.g2.fillOval(63-(DrawArea.strokeThickness)/2,63-(DrawArea.strokeThickness)/2,
                        DrawArea.strokeThickness,DrawArea.strokeThickness);
                 */
            }
        });
        //setYellow.setSize(5,5);
        this.add(setYellow);
    }
}