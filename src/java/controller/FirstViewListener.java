package controller;

import model.*;
import view.*;
import view.Menu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

public class FirstViewListener implements ActionListener {
    private FirstView login;
    private CheckLogin cl;
    private Menu myMenu;
    private FirstView signup;

    private Registration rg;

    private MenuListener myMenuList;

    public FirstViewListener(FirstView f, CheckLogin c) throws IOException, FontFormatException {
        this.login = f;
        this.cl = c;
        this.rg = new Registration();
        login.getGp().getFieldLogin().getPlsLogin().addActionListener(this);
        login.getGp().getFieldLogin().getPlsSignUp().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login.getGp().getFieldLogin().getPlsLogin()) {
            boolean isLogin = false;
            Constants c=new Constants();
            c.setUsername(login.getGp().getFieldLogin().getTxtUS().getText());
            String password = new String(login.getGp().getFieldLogin().getTxtPW().getPassword());
            try {
                isLogin = cl.check(password);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            if (!isLogin)
                JOptionPane.showMessageDialog(FrameManager.getInstance(), "Username o password non corretti", "Avviso", JOptionPane.WARNING_MESSAGE);
            else {
                FrameManager.getInstance().setVisible(false);
                try {
                    myMenu=new Menu();
                    FrameManager.getInstance().ContentPane(myMenu);
                    FrameManager.getInstance().setVisible(true);
                    myMenuList = new MenuListener(myMenu, login);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (FontFormatException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        if (e.getSource() == login.getGp().getFieldLogin().getPlsSignUp()) {
            try {
                FrameManager.getInstance().setVisible(false);
                signup = new FirstView(false);
                FrameManager.getInstance().setContentPane(signup);
                FrameManager.getInstance().setVisible(true);
                signup.getGp().getSignUpPLS().addActionListener(this);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (FontFormatException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (signup != null) {
            if (e.getSource() == signup.getGp().getSignUpPLS()) {
                Constants myC=new Constants();
                String nome = signup.getGp().getSignUpView().getTxtNome().getText();
                String cognome = signup.getGp().getSignUpView().getTxtCognome().getText();
                Date data = signup.getGp().getSignUpView().getTxtData().getDate();
                java.sql.Date sqlData = new java.sql.Date(data.getTime());
                String email = signup.getGp().getSignUpView().getTxtEmail().getText();
                String username=signup.getGp().getSignUpView().getTxtUS().getText();
                myC.setUsername(username);
                String password = new String(signup.getGp().getSignUpView().getTxtPW().getPassword());
                try {
                    ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("resources/skin.png"));
                    Image img = icon.getImage().getScaledInstance(50, 70, Image.SCALE_SMOOTH);
                    icon.setImage(img);
                    int RisAdd = rg.add(nome, cognome, sqlData, email,username, password);
                    switch (RisAdd) {
                        case 1: {
                            //andrebbe verificato se l'utente è connesso a internet, ho deciso di non farlo perchè, nell'eventualità che non fosse connesso il programma continua comunque a funzionare
                            //offrendo tutte le funzionalità; l'unica cosa che cambia è che non viene mandata l'email di benvenuto, ma avendo fatto questa operazione in un thread
                            //l'applicazione continua comunque a funzionare...
                            EmailSender emailSender = new EmailSender(email);
                            Thread thread = new Thread(emailSender);
                            thread.start();

                            JOptionPane.showMessageDialog(null, "<html>Registration successful!<br>Check your email and start playing :)<br>Proceed with LOGIN.</html>", "SUCCESS", JOptionPane.INFORMATION_MESSAGE, icon);
                            FrameManager.getInstance().setContentPane(login);
                        }
                        break;
                        case 0:
                            JOptionPane.showMessageDialog(null, "ERROR", "ERROR", JOptionPane.WARNING_MESSAGE);
                            break;
                        case 2:
                            JOptionPane.showMessageDialog(null, "The password must be at least 8 characters long;\nit must contain at least 1 uppercase letter and 1 symbol ().!_-%$£&?^*@#", "ERROR", JOptionPane.WARNING_MESSAGE);
                            break;
                        case 3:
                            JOptionPane.showMessageDialog(null, "Username already used", "ERROR", JOptionPane.WARNING_MESSAGE);
                            break;
                        case 4:
                            JOptionPane.showMessageDialog(null, "Invalid email\nIncorrect syntax or email already associated with an account.\n", "ERROR", JOptionPane.WARNING_MESSAGE);
                            break;
                        default:
                            break;
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}