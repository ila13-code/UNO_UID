package view;

import model.Constants;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;

public class SettingsFields extends JPanel
{
    private SwitchButton plsMusic;
    private SwitchButton plsDaltonici;

    private JButton plsChangeCover;

    private JButton plsChangeAccount;
    private JButton plsBack;

    private MyFont font;

    private void FormattaLBL(JLabel lbl, Color c)
    {
        lbl.setForeground(c);
        lbl.setFont(font.getFont());
        lbl.setHorizontalAlignment(JLabel.CENTER);
    }

    private void FormattaPLS(JButton pls)
    {
        pls.setBackground(new Color(0xFFF1C900, true));
        pls.setFont(font.getFont());
        pls.setForeground(Color.BLACK);
        Border roundedBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
        roundedBorder = BorderFactory.createCompoundBorder(roundedBorder, BorderFactory.createEmptyBorder(3, 10, 3, 9));
        roundedBorder = BorderFactory.createCompoundBorder(BorderFactory.createRaisedSoftBevelBorder(), roundedBorder);
        pls.setBorder(roundedBorder);
    }


    public SettingsFields() throws IOException, FontFormatException {
        font=new MyFont(20f);
        this.setLayout(null);
        this.setOpaque(false);

        JLabel lblM=new JLabel("MUSIC");
        FormattaLBL(lblM, Color.BLACK);
        lblM.setBounds(0,0, 150,100);
        this.add(lblM);

        plsMusic=new SwitchButton();
        plsMusic.setBounds(240,25, 150,100);
        plsMusic.setSelected(true); //al primo avvio IN OGNI CASO la musica viene avviata...
        this.add(plsMusic);

        JLabel lblD=new JLabel("<html>COLORBLIND<br>MODE</html>");
        FormattaLBL(lblD, Color.BLACK);
        lblD.setBounds(20,80, 200,100);
        this.add(lblD);


        plsDaltonici=new SwitchButton();
        plsDaltonici.setBounds(237,105, 150,100);
        plsDaltonici.setSelected(Constants.isDaltonic); //imposto già dalla costruzione della view il pulsante in base al valore salvato nel profilo dell'utente..
        this.add(plsDaltonici);

        plsChangeCover=new JButton("CHANGE COVER");
        FormattaPLS(plsChangeCover);
        plsChangeCover.setBounds(18, 200, 300,40);
        this.add(plsChangeCover);

        plsChangeAccount=new JButton("LOG OUT");
        FormattaPLS(plsChangeAccount);
        plsChangeAccount.setBounds(18, 250, 300,40);
        this.add(plsChangeAccount);

        plsBack=new JButton("BACK");
        FormattaPLS(plsBack);
        plsBack.setBounds(18, 300, 300,40);
        this.add(plsBack);
    }

    public SwitchButton getPlsMusic() {
        return plsMusic;
    }

    public SwitchButton getPlsDaltonici() {
        return plsDaltonici;
    }

    public JButton getPlsChangeCover() {
        return plsChangeCover;
    }

    public JButton getPlsChangeAccount() {
        return plsChangeAccount;
    }

    public JButton getPlsBack() {
        return plsBack;
    }
}
