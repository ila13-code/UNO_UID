package view;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

import org.jdesktop.swingx.JXDatePicker;

public class SignUpView extends JPanel
{
    private JLabel lblUS;
    private JTextField txtUS;
    private JLabel lblPW;
    private JPasswordField txtPW;
    private JLabel lblNome;
    private JTextField txtNome;
    private JLabel lblCognome;
    private JTextField txtCognome;
    private JLabel lblEmail;
    private JTextField txtEmail;
    private JLabel lblData;

    public JTextField getTxtUS() {
        return txtUS;
    }

    public JPasswordField getTxtPW() {
        return txtPW;
    }

    public JTextField getTxtNome() {
        return txtNome;
    }

    public JTextField getTxtCognome() {
        return txtCognome;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public JXDatePicker getTxtData() {
        return txtData;
    }

    private JXDatePicker txtData;

    private MyFont font;

    private void FormattaLBL(JLabel lbl)
    {
        lbl.setForeground(Color.BLACK);
        lbl.setFont(font.getFont());
        lbl.setHorizontalAlignment(JLabel.CENTER);
    }

    public SignUpView() {
        font = new MyFont(16f);
        this.setLayout(new GridLayout(13,1));

        lblNome=new JLabel("First Name");
        FormattaLBL(lblNome);
        this.add(lblNome);

        txtNome=new JTextField();
        txtNome.setFont(new Font(null, Font.BOLD, 14));
        this.add(txtNome);

        lblCognome=new JLabel("Surname");
        FormattaLBL(lblCognome);
        this.add(lblCognome);

        txtCognome=new JTextField();
        txtCognome.setFont(new Font(null, Font.BOLD, 14));
        this.add(txtCognome);

        lblData=new JLabel("Date of Birth");
        FormattaLBL(lblData);
        this.add(lblData);

        txtData=new JXDatePicker();
        txtData.setFont(new Font(null, Font.BOLD, 14));
        txtData.getMonthView().setMonthStringBackground(new Color(0xFFF1C900, true));
        txtData.getMonthView().setSelectionBackground(new Color(0xFFF1C900, true));
        txtData.getMonthView().setTodayBackground(new Color(0xFFF1C900, true));
        txtData.setFormats(new SimpleDateFormat("yyyy-MM-dd"));
        this.add(txtData);

        lblEmail=new JLabel("Email");
        FormattaLBL(lblEmail);
        this.add(lblEmail);

        txtEmail=new JTextField();
        txtEmail.setFont(new Font(null, Font.BOLD, 14));
        this.add(txtEmail);

        lblUS=new JLabel("Username");
        FormattaLBL(lblUS);
        this.add(lblUS);

        txtUS=new JTextField();
        txtUS.setFont(new Font(null, Font.BOLD, 14));
        this.add(txtUS);

        lblPW=new JLabel("Password");
        FormattaLBL(lblPW);
        this.add(lblPW);

        txtPW=new JPasswordField();
        txtPW.setFont(new Font(null, Font.BOLD, 14));
        this.add(txtPW);

        JSeparator mySeparator=new JSeparator(JSeparator.HORIZONTAL);
        mySeparator.setVisible(false);
        this.add(mySeparator);

        this.setOpaque(false);
    }
}
