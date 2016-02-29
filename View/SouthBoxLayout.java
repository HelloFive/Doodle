package View;

import Model.Canvas;
import Model.iView;
import javafx.beans.Observable;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;
import java.util.TimerTask;

/**
 * Created by SPARK on 2016-02-27.
 */

public class SouthBoxLayout extends JPanel implements Observer {

    private Canvas model;


    @Override
    public void update(java.util.Observable arg0, Object arg1) {
        repaint();
    }

    public static JSlider stateSlider;
/*

    public class event implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            int value = stateSlider.getValue();
            Canvas.AIM_STATE = value;
            System.out.println("JSlider value = " + stateSlider.getValue());

            Canvas.CURRENT_STATE = 100*Canvas.AIM_STATE/(Canvas.MAX_STATE+1);
            System.out.println("Canvas.AIM_STATE = " + Canvas.AIM_STATE);

            System.out.println("Canvas.MAX_STATE = " + Canvas.MAX_STATE);
            System.out.println("Canvas.CURRENT_STATE = " + Canvas.CURRENT_STATE);

            //System.out.println("stateSlider.value() = " + stateSlider.getValue());
        }
    }

*/

    public SouthBoxLayout(Canvas model_) {

        super();
        this.setBackground(Color.GRAY);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(new JButton("Play"));
        //this.add(Box.createVerticalGlue());


        // set the model
        model = model_;


        stateSlider = new JSlider(0,100, Canvas.CURRENT_STATE/(Canvas.MAX_STATE+1)); //(JSlider.HORIZONTAL, 0, 100, 0);
//        stateSlider = new JSliderView();
        stateSlider.setMajorTickSpacing(20);
        stateSlider.setPaintTicks(true);
        //add(stateSlider);

/*
        event e = new event();
        stateSlider.addChangeListener(e);
*/

/*

        stateSlider.addChangeListener(new ChangeListener() {
            //@Override
            public void stateChanged(ChangeEvent e) {
//                Canvas.AIM_STATE = stateSlider.getValue();
                int value = stateSlider.getValue();
                Canvas.AIM_STATE = value;
                System.out.println("JSlider value = " + stateSlider.getValue());

                Canvas.CURRENT_STATE = 100*Canvas.AIM_STATE/(Canvas.MAX_STATE+1);
                System.out.println("Canvas.AIM_STATE = " + Canvas.AIM_STATE);

                System.out.println("Canvas.MAX_STATE = " + Canvas.MAX_STATE);
                System.out.println("Canvas.CURRENT_STATE = " + Canvas.CURRENT_STATE);

            }
        });
*/




        stateSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Object source = e.getSource();
                if (source instanceof BoundedRangeModel) {
                    BoundedRangeModel aModel = (BoundedRangeModel) source;
                    if (!aModel.getValueIsAdjusting()) {
                        System.out.println("Changed: " + aModel.getValue());
                    }
                } else if (source instanceof JSlider) {
                    JSlider stateSlider = (JSlider) source;
                    if (!stateSlider.getValueIsAdjusting()) {
                        Canvas.AIM_STATE = stateSlider.getValue();
                        System.out.println("AIM_STATE changed to " + stateSlider.getValue());
                        System.out.println("Canvas.AIM_STATE:  " + Canvas.AIM_STATE);
                        Canvas.CURRENT_STATE = (Canvas.MAX_STATE+1)*Canvas.AIM_STATE/100;
                        if(Canvas.CURRENT_STATE > Canvas.MAX_STATE) Canvas.CURRENT_STATE = Canvas.MAX_STATE;
                        System.out.println("Canvas.MAX_STATE = " + Canvas.MAX_STATE);
                        System.out.println("Canvas.CURRENT_STATE = " + Canvas.CURRENT_STATE);

                        Canvas.drawArea.clear();
                        for (int j=0; j+1<Canvas.CURRENT_STATE; j++) {
                            System.out.println("stateSlider.j=" + j);
                            Canvas.g2.setStroke(new BasicStroke(Canvas.Strokes.get(j), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                            Canvas.g2.setColor(Canvas.Colors.get(j));
                            Canvas.g2.drawLine(Canvas.Points.get(j).x, Canvas.Points.get(j).y,
                                    Canvas.Points.get(j + 1).x, Canvas.Points.get(j + 1).y);

                            //Canvas.g2.setStroke(new BasicStroke(Canvas.strokeThickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                            //Canvas.g2.drawLine(Canvas.From[j].x, Canvas.From[j].y, Canvas.To[j].x, Canvas.To[j].y);

                            repaint();
                        }

                    }
                } else {
                    System.out.println("Something changed: " + source);
                }
            }
        });


        //stateSlider.add



/*
        stateSlider = new JSlider(0, (int) Canvas.MAX_STATE);
        stateSlider.setPaintTicks(true);
        stateSlider.setPaintLabels(true);
        stateSlider.setMajorTickSpacing(20);
        stateSlider.setMinorTickSpacing(5);
        //addSlider(slider, "Labels");
*/

/*
        JPanel panel = new JPanel();
        panel.add(new JLabel("Labels"));
        panel.add(stateSlider);

        panel.setBackground(Color.lightGray);
        this.add(panel);
*/

        this.add(stateSlider);

        //this.add(slider, "Video");

        //this.add(new JButton("Start"));
        JButton startButton = new JButton("Start");

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                System.out.println("Start Button: Pressed");


                if (Canvas.CURRENT_STATE != 0) {
                    stateSlider.setValue(0);
                    Canvas.CURRENT_STATE = 0;
                }

                java.util.Timer timer = new java.util.Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        if (stateSlider.getValue() < 100) {
                            stateSlider.setValue(stateSlider.getValue() + 1);
                            Canvas.CURRENT_STATE += 1;
                        } else {
                            Canvas.CURRENT_STATE -= 1;
                            timer.cancel();
                            timer.purge();
                        }
                    }
                }, 1000, 1000);
            }
        });

/*                Canvas.drawArea.clear();
                System.out.println("drawArea cleared");
                Canvas.drawArea.setDoubleBuffered(false);
                //drawArea.clear();
                repaint();


                Timer delayTimer = new Timer(0, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        repaint();
                    }
                });

                int delayed = 0;
                for (int j=0; j+1<Canvas.MAX_STATE; j++) {

                    System.out.println("    StarButton j=" + j);


                    //int delayed = (int) (Canvas.Times.get(j+1)-Canvas.Times.get(j))/100000;
                    delayed += (int) (Canvas.Times.get(j+1)-Canvas.Times.get(j))/10000;
                    System.out.println("Time will delay for " + delayed);

                    int stroke = Canvas.Strokes.get(j);
                    Color theColor = Canvas.Colors.get(j);
                    Point From = Canvas.Points.get(j);
                    Point To = Canvas.Points.get(j+1);

                    Canvas.g2.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                    Canvas.g2.setColor(theColor);
                    Canvas.g2.drawLine(From.x, From.y, To.x, To.y);
                    //repaint();

                    delayTimer.setDelay(delayed);
*/


/*
                    delayTimer = new Timer (delayed, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            //System.out.println("Timer entered");
                            if(j)
                            repaint();
                            //System.out.println("Timer exited");
                       }
                    });
                    delayTimer.start();
                    System.out.println("delayTimer.start();");
*/


/*
                    new Timer().schedule(
                            new TimerTask() {
                                @Override
                                public void run() {
                                    Canvas.g2.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                                    Canvas.g2.setColor(theColor);
                                    Canvas.g2.drawLine(From.x, From.y, To.x, To.y);
                                    repaint();
                                }
                            },
                            delayed
                    );
*/

/*
                    if(!Canvas.isPaused) {
                        Canvas.g2.setStroke(new BasicStroke(Canvas.Strokes.get(j), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                        Canvas.g2.setColor(Canvas.Colors.get(j));
                        Canvas.g2.drawLine(Canvas.Points.get(j).x, Canvas.Points.get(j).y,
                                Canvas.Points.get(j + 1).x, Canvas.Points.get(j + 1).y);
                        repaint();


                        try {
                            System.out.println("Thread will sleep for " + (Canvas.Times.get(j + 1) - Canvas.Times.get(j))/1000000);
                            //Thread.sleep((long) (Canvas.Times.get(j + 1) - Canvas.Times.get(j))/1000000);
                        } catch (Exception exception) {
                            System.out.println("FPS ERROR!???!??!??!?!?!?");
                        }
                    }
*/

/*                }
            }
        });*/
        this.add(startButton);



        //this.add(Box.createVerticalStrut(10));
        this.add(new JButton("End"));

        this.add(new JButton("Reverse"));



    }
}