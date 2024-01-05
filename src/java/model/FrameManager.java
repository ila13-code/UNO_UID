package model;

/*
Classe singleton (è possibile creare solo un'istanza di questa classe all'interno dell'intero progetto, e questa sarà accessibile da ogni punto dello stesso
(con FrameManager.getInstance()).
Ho reso questa classe singleton per fare in modo che sia possibile usare un solo JFrame e usare il metodo .setContentPane(<component>) per
modificarne il contenuto. In questo modo ogni schermata verrà ridisegnata sempre sulla stessa finestra.
 */

import view.ImagePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrameManager extends JFrame
{
    public ImagePanel w;
    private static FrameManager instance;

    public static FrameManager getInstance() {
        if (instance == null) {
            instance = new FrameManager();
        }
        return instance;
    }

    public FrameManager() {
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setBackground(new Color(0f,0f,0f,0f));
        //ALLA PRESSIONE DI ALT+Q, qualsiasi sia la schermata in cui ci si trova, l'applicazione viene arrestata...
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_PRESSED) {
                    if (e.getKeyCode() == KeyEvent.VK_Q && e.isAltDown()) {
                        System.exit(0);
                    }
                }
                return false;
            }
        });
    }

    public void centerWindow()
    {
        Toolkit toolkit=Toolkit.getDefaultToolkit();
        Dimension screenDimension=toolkit.getScreenSize();
        int x=(screenDimension.width-this.getWidth())/2;
        int y=(screenDimension.height-this.getHeight())/2;
        this.setLocation(x, y);
    }


    public void ContentPane(ImagePanel w)
    {
        this.w=w;
        this.setContentPane(this.w);
        repaint();
        revalidate();
    }
}


