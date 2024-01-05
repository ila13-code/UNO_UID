package view;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;

public class MenuButton extends JPanel
{
    private MyFont font;
    private JButton plsVScpu;
    private JButton plsVSplr;
    private JButton plsStat;
    private JButton plsSettings;
    private JButton plsExit;
    private JButton plsRules;

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

    public MenuButton() {
        font=new MyFont(16f);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setOpaque(false);


        plsVScpu=new JButton("PLAYvsCPU");
        FormattaPLS(plsVScpu);

        plsVSplr=new JButton("PLAYvsFRIEND");
        FormattaPLS(plsVSplr);

        plsStat=new JButton("STATS");
        FormattaPLS(plsStat);

        plsSettings=new JButton("SETTINGS");
        FormattaPLS(plsSettings);

        plsRules=new JButton("RULES");
        FormattaPLS(plsRules);

        plsExit=new JButton("LEAVE");
        FormattaPLS(plsExit);

        this.setPreferredSize(new Dimension(300,300));
        this.add(plsVScpu);
        this.add(new JLabel(" "));
        this.add(plsVSplr);
        this.add(new JLabel(" "));
        this.add(plsStat);
        this.add(new JLabel(" "));
        this.add(plsSettings);
        this.add(new JLabel(" "));
        this.add(plsRules);
        this.add(new JLabel(" "));
        this.add(plsExit);
    }

    public JButton getPlsVScpu() {
        return plsVScpu;
    }

    public JButton getPlsVSplr() {
        return plsVSplr;
    }

    public JButton getPlsStat() {
        return plsStat;
    }

    public JButton getPlsSettings() {
        return plsSettings;
    }

    public JButton getPlsExit() {
        return plsExit;
    }

    public JButton getPlsRules() {return plsRules;}

}
