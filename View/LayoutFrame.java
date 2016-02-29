package View;

import javax.swing.*;

/**
 * Created by SPARK on 2016-02-28.
 */

public class LayoutFrame extends JFrame {
    private static int xPos = 10;
    private static int yPos = 10;
    private static final int OFFSET = 50;

    public LayoutFrame(String title, JPanel contents) {
        super(title);
        this.setContentPane(contents);
        this.setSize(1080, 720);
        this.setLocation(xPos, yPos);
        xPos += OFFSET;
        yPos += OFFSET;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        setResizable(true);
        //setResizable(false);


    }

}