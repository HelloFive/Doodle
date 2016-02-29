package Model;

import Controller.DrawArea;
import View.SouthBoxLayout;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by SPARK on 2016-02-29.
 */
/*

public class CanvasModel extends Observable {


    private DrawArea drawArea = new DrawArea();

    private Color lineColor;
    private int strokeThickness = 30;

    private Image image;

    private int theWidth;
    private int theHeight;


    public static ArrayList<Point> Points = new ArrayList<Point>();
    public static ArrayList<Integer> Strokes = new ArrayList<Integer>();
    public static ArrayList<Color> Colors = new ArrayList<Color>();
    public static ArrayList<Long> Times = new ArrayList<Long>();
    private int MAX_STATE=0;
    private int CURRENT_STATE=0;
    private int AIM_STATE = 0;


    // Graphical object to draw on
    public static Graphics2D g2;


    private boolean StartClicked = false;
    private boolean StopClicked = false;
    private boolean PlayClicked = false;
    private boolean PauseClicked = false;
    private boolean ReverseClicked = false;


    private boolean isRunning = false;
    private boolean isPaused = false;
    private long lastUpdate;
    private int FPS = 35;

    public CanvasModel() {

    }



    public static void SaveLines(Point point) {
        Points.add(this.getMaxState(), point);
        Strokes.add(this.getMaxState(), this.getStroke());
        Colors.add(this.getMaxState(), this.getColor());
        Times.add(this.getMaxState(), System.nanoTime());
        //if (MAX_STATE == 1) Times.set(0,Times.get(1));
        this.increaseMaxState();
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

*/
/*
    public static ArrayList<Point> Points = new ArrayList<Point>();
    public static ArrayList<Integer> Strokes = new ArrayList<Integer>();
    public static ArrayList<Color> Colors = new ArrayList<Color>();
    public static ArrayList<Long> Times = new ArrayList<Long>();
*//*



    public int getMaxState() {
        return this.MAX_STATE;
    }

    public void increaseMaxState(){
        MAX_STATE++;
    }


    public int getCurrentState() {
        return this.CURRENT_STATE;
    }
    public int getAimState() {
        return this.AIM_STATE;
    }




}
*/
