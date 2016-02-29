package Model;

import Controller.DrawArea;
import View.SouthBoxLayout;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by SPARK on 2016-02-28.
 */
public class Canvas extends Observable {


    public static DrawArea drawArea = new DrawArea();

    public static Color lineColor;
    public static int strokeThickness = 30;

    public static Image image;

    public static int theWidth;
    public static int theHeight;


    public static ArrayList<Point> Points = new ArrayList<Point>();
    public static ArrayList<Integer> Strokes = new ArrayList<Integer>();
    public static ArrayList<Color> Colors = new ArrayList<Color>();
    public static ArrayList<Long> Times = new ArrayList<Long>();
    public static int MAX_STATE=0;
    public static int CURRENT_STATE=0;
    public static int AIM_STATE = 0;


    // Graphical object to draw on
    public static Graphics2D g2;


    public static boolean StartClicked = false;
    public static boolean StopClicked = false;
    public static boolean PlayClicked = false;
    public static boolean PauseClicked = false;
    public static boolean ReverseClicked = false;


    public static boolean isRunning = false;
    public static boolean isPaused = false;
    public static long lastUpdate;
    public static int FPS = 35;



    public static void SaveLines(Point point) {
        Points.add(MAX_STATE, point);
        Strokes.add(MAX_STATE, strokeThickness);
        Colors.add(MAX_STATE, lineColor);
        Times.add(MAX_STATE, System.nanoTime());
        //if (MAX_STATE == 1) Times.set(0,Times.get(1));
        MAX_STATE++;
        SouthBoxLayout.stateSlider.setValue(Canvas.CURRENT_STATE/(Canvas.MAX_STATE+1));
    }


    public void update() {
        setChanged();
        notifyObservers();
    }


    public DrawArea getDrawArea() {
        return this.drawArea;
    }

    public Color getColor() {
        return this.lineColor;
    }

    public int getStroke() {
        return this.strokeThickness;
    }

    public Image getImage() {
        return this.image;
    }

    public int getWidth() {
        return this.theWidth;
    }
    public int getHeight() {
        return this.theHeight;
    }

/*
    public static ArrayList<Point> Points = new ArrayList<Point>();
    public static ArrayList<Integer> Strokes = new ArrayList<Integer>();
    public static ArrayList<Color> Colors = new ArrayList<Color>();
    public static ArrayList<Long> Times = new ArrayList<Long>();
*/


    public int getMaxState() {
        return this.MAX_STATE;
    }

    public int getCurrentState() {
        return this.CURRENT_STATE;
    }
    public int getAimState() {
        return this.AIM_STATE;
    }




}
