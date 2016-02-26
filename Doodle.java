import javax.swing.*;
import java.awt.*;

/**
 * Created by SPARK on 2016-02-02.
 */
public class Doodle {
    public static void main(String[] args) {
        // These are stacked bottom to top, so put the first one
        // to be discussed last.
        JFrame Doodle = new LayoutFrame("NestedLayout", new NestedLayout());
        //new LayoutFrame("BorderLayout", new DemoBorderLayout());
        Doodle.setLocationRelativeTo(null);
        Doodle.setMinimumSize(new Dimension(540, 360));

    }
}
