package view;

import model.FrameManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class StatsView extends JPanel {
    private ImagePanel sfondo;

    private FieldStats stats;

    public StatsView() throws IOException, FontFormatException {
        sfondo = new ImagePanel();
        sfondo.setBackground(new Color(0f,0f,0f,0f));
        sfondo.changeBackground(ImageIO.read(ClassLoader.getSystemResource("resources/Stats.png")));
        sfondo.setLayout(null);
        stats=new FieldStats();
        stats.setBounds(53, 140, 300, 300);
        sfondo.add(stats);
        FrameManager.getInstance().setSize(400,600);
        FrameManager.getInstance().centerWindow();
        FrameManager.getInstance().setContentPane(sfondo);
        FrameManager.getInstance().setVisible(true);
    }

    public FieldStats getFieldStats() {
        return stats;
    }
}
