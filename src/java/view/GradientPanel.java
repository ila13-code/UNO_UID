package view;

/*
La classe GradientPanel segue la logica di quella FirstView; infatti viene usata per costruire la schermata di login o quella di signup
(se al costruttore viene passato come parametro true costruisce quella di login; se gli viene passato false quella di signup.
Il GradientPanel in sè è un JPanel su cui viene disegnato un rettangolo dai bordi arrotondati sfumato; al di sopra di esso vengono inseriti
i campi delle rispettive schermate.
Anche qui sono presenti i getter che ci permettono di risalire ai dati inseriti dall'utente all'interno del controller (FirstViewListener).
 */

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;

public class GradientPanel extends JPanel
{
    private FieldLogin fieldTXT;
    private SignUpView signUp;

    private JButton SignUpPLS;

    private MyFont font;

    private void FormattaPLS(JButton pls) throws IOException, FontFormatException {
        font=new MyFont(16f);
        pls.setBackground(new Color(0xFFF1C900, true));
        pls.setFont(font.getFont());
        pls.setForeground(Color.BLACK);
        Border roundedBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
        roundedBorder = BorderFactory.createCompoundBorder(roundedBorder, BorderFactory.createEmptyBorder(3, 10, 3, 9));
        roundedBorder = BorderFactory.createCompoundBorder(BorderFactory.createRaisedSoftBevelBorder(), roundedBorder);
        pls.setBorder(roundedBorder);
        pls.setPreferredSize(new Dimension(200,35));
    }

    public GradientPanel(boolean ret) throws IOException, FontFormatException {

        if(ret) {
            fieldTXT=new FieldLogin();
            this.setLayout(null);
            JPanel myP=new JPanel(new BorderLayout());
            myP.add(fieldTXT, BorderLayout.CENTER);
            myP.setOpaque(false);
            myP.setBounds(0,0,300,300);
            this.add(myP);
            this.setOpaque(false);
            this.setPreferredSize(new Dimension(300,300));
        }
        else
        {
            SignUpPLS=new JButton("SIGNUP");
            FormattaPLS(SignUpPLS);
            signUp=new SignUpView();
            this.setLayout(null);
            JPanel myP=new JPanel();
            myP.add(signUp);
            myP.add(SignUpPLS);
            myP.setOpaque(false);
            myP.setBounds(80,30,250,400);
            this.add(myP);
            this.setOpaque(false);
            this.setPreferredSize(new Dimension(400,450));
        }

    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int width = this.getWidth();
        int height = this.getHeight();
        Color backgroundColor=new Color(0xFFEB1616, true);
        Color gradientColor=new Color(0xD78A8A8A, true);
        GradientPaint gp = new GradientPaint(0, 0, backgroundColor, width+100, height+100, gradientColor);
        g2d.setPaint(gp);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillRoundRect(0, 0, width, height,50,50);
    }

    public FieldLogin getFieldLogin()
    {
        return fieldTXT;
    }

    public JButton getSignUpPLS()
    {
        return SignUpPLS;
    }

    public SignUpView getSignUpView()
    {
        return signUp;
    }

}
