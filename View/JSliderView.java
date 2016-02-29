package View;

import Model.Canvas;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by SPARK on 2016-02-29.
 */
public class JSliderView implements Observer {

    public void update(Observable o, Object arg) {
        System.out.println("SouthBoxLayout: updated");
    }


    public static JSlider JSliderView() {
        JSlider stateSlider = new JSlider(0,100, Canvas.CURRENT_STATE/(Canvas.MAX_STATE+1)); //(JSlider.HORIZONTAL, 0, 100, 0);
        return stateSlider;
    }
}
