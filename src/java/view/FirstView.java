package view;

/*
Classe che costruisce le schermate di avvio (se al costruttore
viene passato come parametro true costruisce la schermata di login; se false quella di signup).
All'interno di entrambe le schermate si trova un oggetto di tipo GradientPanel.
Questa classe estende ImagePanel poichè ha un'immmagine di sfondo (che viene disegnata).
 */

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class FirstView extends ImagePanel
{
    private GradientPanel gp;
    public FirstView(boolean ret) throws IOException, FontFormatException {

        this.changeBackground(ImageIO.read(ClassLoader.getSystemResource("resources/sfondoAvvio.png")));
        this.setLayout(null);
        if(ret) {
            gp = new GradientPanel(true);
            gp.setBounds(250, 130, 300, 300);
            this.add(gp);
        }
        if(!ret)
        {
            gp=new GradientPanel(false);
            gp.setBounds(200,60,400,450);
            this.add(gp);
        }

    }

    public GradientPanel getGp()
    {
        return gp;
    }

}
