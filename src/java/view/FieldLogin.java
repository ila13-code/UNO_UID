package view;

/*
Classe che estende un JPanel e contiene tutti i campi della prima schermata (quella di login).
Sono inclusi i motodi getter poichè servono a recuperare i dati inseriti dall'utente e gli eventi scatenati dallo stesso all'interno del controller
(in FirstViewListener)
 */

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class FieldLogin extends JPanel
{
    private JLabel lblUS;
    private JTextField txtUS;
    private JLabel lblPW;
    private JPasswordField txtPW;
    private JButton plsLogin;
    private JButton plsSignUp;

    private JLabel lblOR;

    private MyFont font;

    private void FormattaLBL(JLabel lbl)
    {
        lbl.setForeground(Color.BLACK);
        lbl.setFont(font.getFont());
        lbl.setHorizontalAlignment(JLabel.CENTER);
    }

    private void FormattaPLS(JButton pls)
    {
        pls.setBackground(new Color(0xFFF1C900, true));
        pls.setFont(font.getFont());
        pls.setForeground(Color.BLACK);
        pls.setPreferredSize(new Dimension(100,100));
        Border roundedBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
        roundedBorder = BorderFactory.createCompoundBorder(roundedBorder, BorderFactory.createEmptyBorder(3, 10, 3, 9));
        roundedBorder = BorderFactory.createCompoundBorder(BorderFactory.createRaisedSoftBevelBorder(), roundedBorder);
        pls.setBorder(roundedBorder);
    }

    public FieldLogin() throws IOException, FontFormatException {
        font=new MyFont(16f);
        this.setLayout(new GridBagLayout());

        GridBagConstraints constraints=new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 2;

        //prima riga
        constraints.gridx = 0;
        constraints.gridy = 0;
        lblUS=new JLabel("USERNAME");
        FormattaLBL(lblUS);
        this.add(lblUS, constraints);

        //seconda riga
        constraints.gridx = 0;
        constraints.gridy = 1;
        txtUS=new JTextField();
        txtUS.setFont(new Font(null, Font.BOLD, 16));
        this.add(txtUS, constraints);

        //terza riga
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(new JLabel(" "),constraints);

        //quarta riga
        constraints.gridx = 0;
        constraints.gridy = 3;
        lblPW=new JLabel("PASSWORD");
        FormattaLBL(lblPW);
        this.add(lblPW, constraints);

        //quinta riga
        constraints.gridx = 0;
        constraints.gridy = 4;
        txtPW=new JPasswordField();
        txtPW.setFont(new Font(null, Font.BOLD, 16));
        this.add(txtPW,constraints);

        //sesta riga
        constraints.gridx = 0;
        constraints.gridy = 5;
        this.add(new JLabel(" "),constraints);

        //settima riga
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.ipady=7;
        constraints.ipadx=60;
        plsLogin=new JButton("LOGIN");
        FormattaPLS(plsLogin);
        this.add(plsLogin,constraints);

        //ottava riga
        constraints.gridx = 0;
        constraints.gridy = 7;
        lblOR=new JLabel("OR");
        FormattaLBL(lblOR);
        this.add(lblOR,constraints);

        //nona riga
        constraints.gridx = 0;
        constraints.gridy = 8;
        constraints.ipady=7;
        constraints.ipadx=60;
        plsSignUp=new JButton("SIGNUP");
        FormattaPLS(plsSignUp);
        this.add(plsSignUp,constraints);

        this.setOpaque(false);

    }

    public JTextField getTxtUS()
    {
        return txtUS;
    }

    public JPasswordField getTxtPW()
    {
        return txtPW;
    }

    public JButton getPlsLogin() {
        return plsLogin;
    }

    public JButton getPlsSignUp() {
        return plsSignUp;
    }
}
