package view;

import model.FrameManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Menu extends ImagePanel
{
    private MenuButton menuPls;


    public Menu() throws IOException, FontFormatException {
        this.changeBackground(ImageIO.read(ClassLoader.getSystemResource("resources/SchermataAvvio.png")));
        this.setLayout(null);
        menuPls = new MenuButton();
        menuPls.setBounds(127, 220, 240, 300);
        this.add(menuPls);
        FrameManager.getInstance().setSize(980,650);
        FrameManager.getInstance().centerWindow();
    }

    public MenuButton getMenuPls() {
        return menuPls;
    }

}
