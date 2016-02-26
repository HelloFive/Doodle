import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by SPARK on 2016-02-20.
 */
public class NorthBoxLayout extends DemoPanel {
    // Using struts and glue
    public NorthBoxLayout() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JMenuBar menuBar = new JMenuBar();

        JMenu theFile = new JMenu("File");
        menuBar.add(theFile);

        JMenuItem theFile2 = new JMenuItem("Save Current Doodle");
        theFile2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Save Current Doodle: Pressed");
            }
        });
        theFile.add(theFile2);

        JMenuItem theFile3 = new JMenuItem("Load Current Doodle");
        theFile3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Load Current Doodle: Pressed");
            }
        });
        theFile.add(theFile3);

        JMenuItem theFile4 = new JMenuItem("Create New Doodle");
        theFile4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LayoutFrame("NestedLayout", new NestedLayout());
                System.out.println("Create New Doodle: Pressed");
            }
        });
        theFile.add(theFile4);

        JMenuItem theFile5 = new JMenuItem("Close the Doodle");
        theFile5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Doodle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                System.exit(0);
                System.out.println("Create New Doodle: Pressed");
            }
        });
        theFile.add(theFile5);


        JMenu theView = new JMenu("View");
        menuBar.add(theView);

        JMenuItem theView2 = new JMenuItem("Full Size");
        theView2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Full size: Pressed");
            }
        });
        theView.add(theView2);

        JMenuItem theView3 = new JMenuItem("Fit Size");
        theView3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Fit Size: Pressed");
            }
        });
        theView.add(theView3);


        this.add(menuBar);
    }
}