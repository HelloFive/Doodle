import View.EastBoxLayout;
import View.NorthBoxLayout;
import View.SouthBoxLayout;
import View.WestBoxLayout;
import View.*;
//import View.LayoutFrame;



import javax.swing.*;
import java.awt.*;

/**
 * Created by SPARK on 2016-02-27.
 */
public class Doodle extends JPanel {
    public static void main(String[] args) {
        JFrame Doodle = new LayoutFrame("Doodle", new NestedLayout());
        Doodle.setLocationRelativeTo(null);
        Doodle.setMinimumSize(new Dimension(720, 480));
        Doodle.setMaximumSize(new Dimension(1080, 720));
    }
}