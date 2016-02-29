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
        // These are stacked bottom to top, so put the first one
        // to be discussed last.
        JFrame Doodle = new LayoutFrame("NestedLayout", new NestedLayout());
        //new LayoutFrame("BorderLayout", new DemoBorderLayout());
        Doodle.setLocationRelativeTo(null);
        Doodle.setMinimumSize(new Dimension(540, 360));
    }
}