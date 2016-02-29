package View;


import Controller.GuiArea;
import Model.Canvas;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;

/**
 * Created by SPARK on 2016-02-27.
 */
public class WestBoxLayout extends JPanel implements Observer {


    public static GuiArea guiArea;//

    private JSlider theStroke = new JSlider(JSlider.HORIZONTAL, 0, 50, Canvas.getStroke());


    private class theStrokeController implements ChangeListener {
        public void stateChanged (ChangeEvent e) {
            int stroke = theStroke.getValue();
            Canvas.setStrokeThickness(stroke);
        }
    }


    // Using struts and glue
    public WestBoxLayout() {
        super();
        this.setBackground(Color.gray);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        guiArea = new GuiArea(); // GuiArea
        this.add(guiArea, BorderLayout.CENTER);



        JButton setClear = new JButton("Clear");
        setClear.setBackground(Color.white);
        setClear.setForeground(Color.black);
        setClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Canvas.g2.setPaint(Color.white);
                System.out.println("Clear Button Pressed");

                Canvas.g2.fillRect(0, 0, Canvas.getWidth(), Canvas.getHeight());
                Canvas.g2.setPaint(Canvas.getColor());

                Canvas.drawArea.repaint();

            }
        });
        this.add(setClear);


        JButton setEraser = new JButton("Eraser");
        setEraser.setBackground(Color.black);
        //setEraser.setForeground(Color.black);
        setEraser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Eraser Pressed");

                Canvas.setColor(Color.white);//); = Color.white;
                guiArea.backColor = Color.black;

                guiArea.repaint();
                guiArea.eraser();


                Canvas.g2.setPaint(Canvas.getColor());
            }
        });
        this.add(setEraser);


        JButton colorPalette = new JButton("Color Palette");
        colorPalette.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Color Palette Pressed");

                Color lineColor = JColorChooser.showDialog(null, "Choose a Color", Color.black);

                repaint();
                guiArea.backColor = Color.white;
                guiArea.g2.setColor(guiArea.backColor);
                guiArea.g2.fillRect(0,0,126,126);
                Canvas.setColor(lineColor);// = lineColor;
                guiArea.g2.setPaint(Canvas.getColor());
                guiArea.g2.fillOval(63-(Canvas.getStroke())/2,63-(Canvas.getStroke())/2,
                        Canvas.getStroke(),Canvas.getStroke());





                Canvas.g2.setPaint(lineColor);
                if (lineColor != null) lineColor = Color.black;
            }
        });
        this.add(colorPalette);


        JButton springButton = new JButton("Spring");
        springButton.setBackground(Color.black);
        //springButton.setForeground(Color.black);
        springButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Yellow Pressed");
                if (Canvas.countSpring%2 == 0) Canvas.isSpring = true;
                else Canvas.isSpring = false;

                Canvas.countSpring++;
            }
        });
        this.add(springButton);


        //this.add(Box.createVerticalGlue());
        //this.add(Box.createHorizontalGlue());
        //this.add(Box.createRigidArea());

        //this.add(Box.createVerticalStrut(63));
        //this.add(Box.createHorizontalStrut(63));


        JButton setRed = new JButton("Red");
        setRed.setBackground(Color.red);
        setRed.setForeground(Color.red);
        setRed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Red Pressed");

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

                guiArea.repaint();
                guiArea.yellow();

                Canvas.g2.setPaint(Color.yellow);
            }
        });
        //setYellow.setSize(5,5);
        this.add(setYellow);




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
                        Canvas.setStrokeThickness(theJSlider.getValue());// = theJSlider.getValue();
                    }
                } else {
                    System.out.println("Something changed: " + source);
                }
            }
        });
        theStroke.setPreferredSize(new Dimension(100, 20));


        this.theStroke.addChangeListener(new theStrokeController());

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
                    JSlider theStroke = (JSlider) source;
                    if (!theStroke.getValueIsAdjusting()) {
                        Canvas.setStrokeThickness(theStroke.getValue());// = theStroke.getValue();
                        //System.out.println("AIM_STATE changed to " + stateSlider.getValue());
                        //System.out.println("Canvas.AIM_STATE:  " + Canvas.AIM_STATE);

                        if(Canvas.getStroke() > 50) Canvas.setStrokeThickness(50);// = 50;
                        //System.out.println("Canvas.MAX_STATE = " + Canvas.MAX_STATE);
                        //System.out.println("Canvas.CURRENT_STATE = " + Canvas.CURRENT_STATE);

                    }
                } else {
                    System.out.println("Something changed: " + source);
                }
            }
        });
        this.add(new JLabel("Thickness"));
        this.add(theStroke);


    }

    @Override
    public void update(java.util.Observable obs, Object x) {
        repaint();
        System.out.println("update(" + obs + "," + x + ");");
    }

}