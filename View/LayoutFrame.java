package View;

import Model.Canvas;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Observer;

/**
 * Created by SPARK on 2016-02-28.
 */

public class LayoutFrame extends JFrame implements Observer {


    private static int xPos = 10;
    private static int yPos = 10;
    private static final int OFFSET = 50;

    public LayoutFrame(String title, JPanel contents) {
        super(title);
        this.setContentPane(contents);
        this.setSize(Canvas.screenSize.width, Canvas.screenSize.height);
        this.setLocation(xPos, yPos);
        xPos += OFFSET;
        yPos += OFFSET;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        setResizable(true);
        this.getSize();
        System.out.println("this.getSize().width = " + this.getSize().width);
        System.out.println("this.getSize().heigh = t" + this.getSize().height);
        //setResizable(false);


    }

    public static BufferedImage resizeImg(BufferedImage img, int newW, int newH) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
        Graphics2D g = dimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return dimg;
    }


    @Override
    public void update(java.util.Observable obs, Object x) {
        repaint();


        BufferedImage image = new BufferedImage(Canvas.getWidth(), Canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = image.createGraphics();
        g2.drawImage(Canvas.image, 0, 0, null);
        g2.dispose();


        BufferedImage temp = resizeImg(image, Canvas.drawArea.getWidth(), Canvas.drawArea.getHeight());


        System.out.println("update(" + obs + "," + x + ");");
    }

}