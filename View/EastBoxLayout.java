package View;

import Model.Canvas;


import javax.swing.*;
import java.awt.*;



/**
 * Created by SPARK on 2016-02-27.
 */
public class EastBoxLayout extends JPanel{
    // Swing Paint

/*
    JComponent DrawingBoard;
    JComponent DrawArea;
*/

//    public static DrawArea drawArea;

    public EastBoxLayout() {
        super();
        this.setBackground(Color.GRAY);
//        drawArea = new DrawArea();
        //final DrawingBoard drawArea = new DrawingBoard;
        //final DrawArea drawArea = new DrawArea();


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Canvas.drawArea, BorderLayout.CENTER);
        this.setBackground(Color.WHITE);
    }
}