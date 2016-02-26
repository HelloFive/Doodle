import javax.swing.*;
import java.awt.*;

/**
 * Created by SPARK on 2016-02-20.
 */
public class EastBoxLayout extends DemoPanel {
    // Swing Paint

    public static DrawArea drawArea;
    public EastBoxLayout() {
        super();
        drawArea = new DrawArea();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(drawArea, BorderLayout.CENTER);
        this.setBackground(Color.WHITE);
    }
}