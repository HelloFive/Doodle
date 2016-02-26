import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by SPARK on 2016-02-20.
 */

public class SouthBoxLayout extends DemoPanel {
    // Using struts and glue
    public SouthBoxLayout() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(new JButton("Play"));
        //this.add(Box.createVerticalGlue());
        JSlider slider = new JSlider(0, 100, 50);

        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        //addSlider(slider, "Labels");


        //slider.addChangeListener(listener);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Labels"));
        panel.add(slider);
        panel.setBackground(Color.lightGray);
        this.add(panel);

        //this.add(slider, "Video");

        //this.add(new JButton("Start"));
        JButton startButton = new JButton("Start");

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Start Button: Pressed");
                EastBoxLayout.drawArea.clear();
                for (int j=0; j<DrawArea.i; j++) {
                    DrawArea.g2.setStroke(new BasicStroke(DrawArea.strokeThickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                    DrawArea.g2.drawLine(DrawArea.From[j].x, DrawArea.From[j].y, DrawArea.To[j].x, DrawArea.To[j].y);
                    repaint();
                }
            }
        });
        this.add(startButton);



        //this.add(Box.createVerticalStrut(10));
        this.add(new JButton("End"));

        this.add(new JButton("Play"));
        this.add(new JButton("Pause"));
        this.add(new JButton("Reverse"));

    }
}
