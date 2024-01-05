package view;

import model.FrameManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class SettingsView extends ImagePanel
{
    private SettingsFields sf;

    public SettingsView() throws IOException, FontFormatException {
        this.changeBackground(ImageIO.read(ClassLoader.getSystemResource("resources/Settings.png")));
        this.setLayout(null);
        sf = new SettingsFields();
        sf.setBounds(40, 120, 340, 400);
        this.add(sf);
        FrameManager.getInstance().setSize(800,500);
        FrameManager.getInstance().centerWindow();
        FrameManager.getInstance().ContentPane(this);
        FrameManager.getInstance().setVisible(true);
    }

    public SettingsFields getSf() {
        return sf;
    }
}
