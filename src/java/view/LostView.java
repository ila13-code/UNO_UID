package view;

import model.FrameManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

public class LostView extends ImagePanel
{
    private JButton pls;

    public LostView() throws IOException {
        this.changeBackground(ImageIO.read(ClassLoader.getSystemResource("resources/lost.png")));
        this.setLayout(null);
        pls=new JButton();
        pls.setOpaque(false);
        pls.setContentAreaFilled(false);
        pls.setBorderPainted(false);
        pls.setBounds(330,280, 140,240);
        this.add(pls);
        FrameManager.getInstance().setSize(800,600);
        FrameManager.getInstance().centerWindow();
        FrameManager.getInstance().setContentPane(this);
        FrameManager.getInstance().setVisible(true);
    }

    public JButton getPls() {return pls;}

}
