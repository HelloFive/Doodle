package View;

import Model.*;
import Model.Canvas;

import javax.swing.*;
import java.awt.*;
import java.util.Observer;

/**
 * Created by SPARK on 2016-02-28.
 */
public class NestedLayout extends JPanel  implements Observer {


    @Override
    public void update(java.util.Observable obs, Object x) {
        repaint();
        System.out.println("update(" + obs + "," + x + ");");
    }

    //private Canvas model;

    public NestedLayout() {
        super();
        this.setBackground(Color.gray);
        JPanel north = new NorthBoxLayout();
        JPanel east = new EastBoxLayout();
        JPanel west = new WestBoxLayout();
        JPanel south = new SouthBoxLayout();

        north.setBorder(BorderFactory.createTitledBorder(""));
        //east.setBorder(BorderFactory.createTitledBorder(""));
        west.setBorder(BorderFactory.createTitledBorder(""));
        south.setBorder(BorderFactory.createTitledBorder(""));

        this.setLayout(new BorderLayout());
        this.add(north, BorderLayout.NORTH);
        this.add(east, BorderLayout.CENTER);
        this.add(west, BorderLayout.WEST);
        this.add(south, BorderLayout.SOUTH);




    }
}