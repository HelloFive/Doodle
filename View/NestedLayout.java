package View;

import Model.*;
import Model.Canvas;

import javax.swing.*;
import java.awt.*;

/**
 * Created by SPARK on 2016-02-28.
 */
public class NestedLayout extends JPanel {

    private Canvas model;

    public NestedLayout() {
        super();
        this.setBackground(Color.gray);
        JPanel north = new NorthBoxLayout();
        JPanel east = new EastBoxLayout();
        JPanel west = new WestBoxLayout();
        JPanel south = new SouthBoxLayout(model);

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