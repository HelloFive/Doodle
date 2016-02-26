import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

/**
 * Created by SPARK on 2016-01-31.
 */
public class Layout {


}

class LayoutFrame extends JFrame {
    private static int xPos = 10;
    private static int yPos = 10;
    private static final int OFFSET = 50;

    public LayoutFrame(String title, DemoPanel contents) {
        super(title);
        this.setContentPane(contents);
        this.setSize(1080, 720);
        this.setLocation(xPos, yPos);
        xPos += OFFSET;
        yPos += OFFSET;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        setResizable(true);
        //setResizable(false);







    }

}

/**
 * Set attributes common to all panels.
 */
class DemoPanel extends JPanel {
    public DemoPanel() {
        super();
        this.setBackground(Color.GRAY);
    }
}


/**
 * Demo a nested layout -- a container widget that holds other widgets, including
 * other containers that themselves hold widgets and/or other containers, etc.
 *
 * @author bwbecker
 *
 */
class NestedLayout extends DemoPanel {
    public NestedLayout() {
        super();
        DemoPanel north = new NorthBoxLayout();
        DemoPanel east = new EastBoxLayout();
        DemoPanel west = new WestBoxLayout();
        DemoPanel south = new SouthBoxLayout();

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




/*
 // BackUp codes

 */

/*

class RandomLayout implements LayoutManager {
    public void addLayoutComponent(String name, Component comp) {
        ; // no-op
    }
    public void layoutContainer(Container parent) {
        Component[] components = parent.getComponents();
        Dimension parentSize = parent.getSize();

        for (int i = 0; i < components.length; i++) {
            Component c = components[i];
            c.setSize(c.getPreferredSize());
            int x = (int)(Math.random() * (parentSize.width - c.getWidth()));
            int y = (int)(Math.random() * (parentSize.height - c.getHeight()));
            c.setLocation(x, y);
        }
    }
    public Dimension minimumLayoutSize(Container parent) {
        return new Dimension(400, 300);
    }
    public Dimension preferredLayoutSize(Container parent) {
        return this.minimumLayoutSize(parent);
    }
    public void removeLayoutComponent(Component comp) {
        ; // no-op
    }
}
*/
