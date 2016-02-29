package View;

import Controller.*;
import Model.Canvas;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observer;

/**
 * Created by SPARK on 2016-02-27.
 */
public class NorthBoxLayout extends JPanel implements Observer {


    @Override
    public void update(java.util.Observable obs, Object x) {
        repaint();
        System.out.println("update(" + obs + "," + x + ");");
    }

    public NorthBoxLayout() {
        super();
        this.setBackground(Color.GRAY);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JMenuBar menuBar = new JMenuBar();

        DrawArea drawArea = new DrawArea();

        JMenu theFile = new JMenu("File");
        menuBar.add(theFile);

        JMenuItem theFile2 = new JMenuItem("Save Current Doodle");
        theFile2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                BufferedImage image = new BufferedImage(Canvas.theWidth,
                        Canvas.theHeight, BufferedImage.TYPE_INT_ARGB);

                Graphics2D g2 = image.createGraphics();
                g2.drawImage(Canvas.image, 0, 0, null);
                g2.dispose();

                JFileChooser fileChooser = new JFileChooser();
                File theDirectory = new File("/Users/SPARK/Desktop");
                fileChooser.setCurrentDirectory(theDirectory);
                FileNameExtensionFilter pngFilter = new FileNameExtensionFilter("PNG file (*.png)", "png");
                //FileNameExtensionFilter pngFilter = new FileNameExtensionFilter("JPG file (*.jpg)", "jpg");
                //FileNameExtensionFilter pngFilter = new FileNameExtensionFilter("GIF file (*.gif)", "gif");
                fileChooser.addChoosableFileFilter(pngFilter);
                fileChooser.setFileFilter(pngFilter);

                int status = fileChooser.showSaveDialog(theFile2);

                if (status == JFileChooser.APPROVE_OPTION) {
                    try {
                        ImageIO.write(image, "png", fileChooser.getSelectedFile());
                        JOptionPane.showMessageDialog(null, "Image saved to " + fileChooser.getSelectedFile().getName());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });
        theFile.add(theFile2);



        JMenuItem theFile3 = new JMenuItem("Load Current Doodle");
        theFile3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                FileFilter filter =  new FileNameExtensionFilter("PNG file (*.png)", "png");
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.addChoosableFileFilter(filter);
                fileChooser.setCurrentDirectory(new java.io.File("/Users/SPARK/Desktop"));
                fileChooser.setDialogTitle("Load a File");
                if(fileChooser.showOpenDialog(theFile2) == JFileChooser.APPROVE_OPTION) {


                    File f = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    BufferedImage image = new BufferedImage(Canvas.theWidth, Canvas.theHeight, BufferedImage.TYPE_INT_ARGB);
                    try {
                        image = ImageIO.read(f);
                    } catch (IOException exception) {

                    }
                    Canvas.drawArea.clear();
                    Canvas.g2.drawImage(image,0,0,null);

                    JOptionPane.showMessageDialog(null, "Image saved to " + fileChooser.getSelectedFile().getName());
                    System.out.println("Reading Complete.");



                };
                System.out.println("You chose: " + fileChooser.getSelectedFile().getAbsolutePath());

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