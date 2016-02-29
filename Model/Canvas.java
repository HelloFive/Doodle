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


    public void changeSomething() {
        setChanged();
        notifyObservers();
        System.out.println("Canvas Model Updated.");
    }


    public Canvas() {

    }


    public static Dimension screenSize = new Dimension(1080, 720);


    public static DrawArea drawArea = new DrawArea();

    private static Color lineColor;
    private static int strokeThickness = 30;

    public static Image image;

    private static int theWidth;
    private static int theHeight;


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
    public static int isPaused = 0;
    public static boolean isReversed = false;
    public static long lastUpdate;
    public static int FPS = 35;
    public static boolean isSpring = false;
    public static int countSpring = 0;



    public static void SaveLines(Point point) {
        Points.add(MAX_STATE, point);
        Strokes.add(MAX_STATE, strokeThickness);
        Colors.add(MAX_STATE, lineColor);
        Times.add(MAX_STATE, System.nanoTime());
        //if (MAX_STATE == 1) Times.set(0,Times.get(1));
        MAX_STATE++;
        //SouthBoxLayout.stateSlider.setValue(Canvas.CURRENT_STATE/(Canvas.MAX_STATE+1));

    }


    public  DrawArea getDrawArea() {
        return this.drawArea;
    }

    public static Color getColor() {
        return lineColor;
    }

    public static int getStroke() {
        return strokeThickness;
    }

    public  Image getImage() {
        return this.image;
    }

    public static int getWidth() {
        return theWidth;
    }
    public static int getHeight() {
        return theHeight;
    }

    public static void setWidth(int width) {
        theWidth = width;
    }
    public static void setHeight(int height) {
        theHeight = height;
    }


    public static void setColor(Color theColor) {
        lineColor = theColor;
    }

    public static void setStrokeThickness (int stroke) {
        strokeThickness = stroke;
    }


/*
    public static ArrayList<Point> Points = new ArrayList<Point>();
    public static ArrayList<Integer> Strokes = new ArrayList<Integer>();
    public static ArrayList<Color> Colors = new ArrayList<Color>();
    public static ArrayList<Long> Times = new ArrayList<Long>();
*/

    public static void setCurrentState(int state) {
        CURRENT_STATE = state;
    }

    public int getMaxState() {
        return this.MAX_STATE;
    }

    public int getCurrentState() {
        return this.CURRENT_STATE;
    }
    public int getAimState() {
        return this.AIM_STATE;
    }


    public static void increaseMaxState(){
        MAX_STATE++;
    }




}
