package View;

import Model.Canvas;
import Model.iView;
//import javafx.beans.Observable;

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

    private JSlider stateSlider = new JSlider(0,100, Canvas.CURRENT_STATE/(Canvas.MAX_STATE+1));


    private class CurrentStateController implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            int state = stateSlider.getValue();
            Canvas.setCurrentState(state);
        }
    }

    public SouthBoxLayout() {

        super();
        this.setBackground(Color.GRAY);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));



        JButton playButton = new JButton("Play");
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                System.out.println("Start Button: Pressed");

                Canvas.isRunning = true;


                if (Canvas.CURRENT_STATE != 0) {
                    stateSlider.setValue(0);
                    Canvas.CURRENT_STATE = 0;
                }

                java.util.Timer timer = new java.util.Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        if(Canvas.isRunning) {
                            if (stateSlider.getValue() < 100) {
                                stateSlider.setValue(stateSlider.getValue() + 1);
                                Canvas.CURRENT_STATE += 1;
                            } else {
                                Canvas.CURRENT_STATE -= 1;
                                timer.cancel();
                                timer.purge();
                            }
                        }
                    }
                }, 50, 50);

            }
        });
        this.add(playButton);







        this.stateSlider.addChangeListener(new CurrentStateController());

        stateSlider.setMajorTickSpacing(20);
        stateSlider.setPaintTicks(true);

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
                        //System.out.println("AIM_STATE changed to " + stateSlider.getValue());
                        //System.out.println("Canvas.AIM_STATE:  " + Canvas.AIM_STATE);
                        Canvas.CURRENT_STATE = (Canvas.MAX_STATE+1)*Canvas.AIM_STATE/100;
                        if(Canvas.CURRENT_STATE > Canvas.MAX_STATE) Canvas.CURRENT_STATE = Canvas.MAX_STATE;
                        //System.out.println("Canvas.MAX_STATE = " + Canvas.MAX_STATE);
                        //System.out.println("Canvas.CURRENT_STATE = " + Canvas.CURRENT_STATE);

                        Canvas.drawArea.clear();
                        for (int j=0; j+1<Canvas.CURRENT_STATE; j++) {
                            //System.out.println("stateSlider.j=" + j);
                            Canvas.g2.setStroke(new BasicStroke(Canvas.Strokes.get(j), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                            Canvas.g2.setColor(Canvas.Colors.get(j));
                            Canvas.g2.drawLine(Canvas.Points.get(j).x, Canvas.Points.get(j).y,
                                    Canvas.Points.get(j + 1).x, Canvas.Points.get(j + 1).y);

                            //Canvas.g2.setStroke(new BasicStroke(Canvas.strokeThickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                            //Canvas.g2.drawLine(Canvas.From[j].x, Canvas.From[j].y, Canvas.To[j].x, Canvas.To[j].y);

                            //repaint();
                        }

                    }
                } else {
                    System.out.println("Something changed: " + source);
                }
            }
        });


        this.add(stateSlider);


        JButton pauseButton = new JButton("Pause");
        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pause Button: Pressed");
                if (Canvas.isPaused%2 == 0) {
                    Canvas.isRunning = false;
                } else {
                    Canvas.isRunning = true;
                }
                Canvas.isPaused++;
            }
        });
        this.add(pauseButton);

        JButton endButton = new JButton("End");
        endButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                System.out.println("End Button: Pressed");

                Canvas.CURRENT_STATE = Canvas.MAX_STATE;
                stateSlider.setValue(100);


            }
        });
        this.add(endButton);

        JButton undoButton = new JButton("Undo");
        undoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                System.out.println("Undo Button: Pressed");

                if (Canvas.MAX_STATE > 0) {
                    System.out.println("Canvas.MAX_STATE = " + Canvas.MAX_STATE);

                    Canvas.MAX_STATE = Canvas.MAX_STATE * 9 / 10;
                    System.out.println("Canvas.MAX_STATE = " + Canvas.MAX_STATE);

                    Canvas.CURRENT_STATE = Canvas.MAX_STATE;
                    if (Canvas.CURRENT_STATE / Canvas.MAX_STATE == 1) {
                        stateSlider.setValue(90);
                        Canvas.CURRENT_STATE = Canvas.CURRENT_STATE * 9 / 10;
                    } else {
                        stateSlider.setValue(100 * Canvas.CURRENT_STATE / Canvas.MAX_STATE);
                    }

                    System.out.println("stateSlider.getValue() = " + stateSlider.getValue());
                    System.out.println("Canvas.CURRENT_STATE = " + Canvas.CURRENT_STATE);
                    System.out.println("Canvas.MAX_STATE = " + Canvas.MAX_STATE);


                    stateSlider.setValue(100);
                } else {
                    System.out.println("No More States to be Undo");
                }
            }
        });
        this.add(undoButton);

/*
        JButton reverseButton = new JButton("Reverse");
        reverseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                System.out.println("Reverse Button: Pressed");

                Canvas.isReversed = true;


                if (Canvas.CURRENT_STATE != 0) {
                    stateSlider.setValue(0);
                    Canvas.CURRENT_STATE = 0;
                } else {
                    Canvas.CURRENT_STATE = Canvas.MAX_STATE;
                    stateSlider.setValue(100);
                }

                java.util.Timer timer = new java.util.Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        if(Canvas.isReversed) {
                            if (stateSlider.getValue() < 100) {
                                stateSlider.setValue(stateSlider.getValue() - 1);
                                Canvas.CURRENT_STATE -= 1;
                                //Canvas.CURRENT_STATE = stateSlider.getValue()/100*Canvas.MAX_STATE;
                            } else {
                                Canvas.CURRENT_STATE += 1;
                                timer.cancel();
                                timer.purge();
                            }
                        }
                    }
                }, 100, 100);

            }
        });
        this.add(reverseButton);
*/





    }

    @Override
    public void update(java.util.Observable obs, Object x) {
        repaint();
        System.out.println("update(" + obs + "," + x + ");");
    }
}