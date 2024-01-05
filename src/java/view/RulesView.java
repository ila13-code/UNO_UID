package view;

import model.FrameManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;

public class RulesView extends ImagePanel
{
    private JButton plsBack;
    private MyFont font;

    private void FormattaPLS(JButton pls) {
        font=new MyFont(16f);
        pls.setBackground(new Color(0xFFF1C900, true));
        pls.setFont(font.getFont());
        pls.setForeground(Color.BLACK);
        Border roundedBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
        roundedBorder = BorderFactory.createCompoundBorder(roundedBorder, BorderFactory.createEmptyBorder(3, 10, 3, 9));
        roundedBorder = BorderFactory.createCompoundBorder(BorderFactory.createRaisedSoftBevelBorder(), roundedBorder);
        pls.setBorder(roundedBorder);
    }
    public RulesView() throws IOException, FontFormatException {
        this.changeBackground(ImageIO.read(ClassLoader.getSystemResource("resources/rules.png")));
        this.setLayout(null);

        plsBack=new JButton("BACK");
        FormattaPLS(plsBack);
        plsBack.setBounds(250,650,100,30);
        this.add(plsBack);

        FrameManager.getInstance().setSize(600,700);
        FrameManager.getInstance().centerWindow();
        FrameManager.getInstance().ContentPane(this);
        FrameManager.getInstance().setVisible(true);
    }

    public JButton getPlsBack() {return plsBack;}
}
