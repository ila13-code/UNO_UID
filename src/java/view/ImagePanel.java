package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class ImagePanel extends JPanel
{
    private BufferedImage img = null;

    public ImagePanel() {}

    public void changeBackground(BufferedImage i) {
        img = i;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(img != null)
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
    }
}
