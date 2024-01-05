package controller;

import model.*;
import view.*;
import view.Menu;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;

public class MenuListener implements ActionListener
{
    private static Menu myMenu;

    private StatsView myS;

    private SettingsView mySV;
    private RulesView myRules;

    private FirstView lg;

    private ChangeCoverView c;

    private GameListener gL;

    private SaveInDB s;

    private Constants constants;
    public static void back() //metodo privato (accessibile solo all'interno di questa classe) che ricostruisce la schermata di menù; viene richiamato quando viene scaturito un evento che richiede questa funzione
    {
        FrameManager.getInstance().setVisible(false);
        FrameManager.getInstance().ContentPane(myMenu);
        FrameManager.getInstance().setSize(980,650);
        FrameManager.getInstance().centerWindow();
        FrameManager.getInstance().setVisible(true);
    }

    public MenuListener(Menu myMenu, FirstView login) throws IOException, FontFormatException {
        this.myMenu=myMenu;
        this.lg=login;
        s=new SaveInDB();
        constants=new Constants();
        Music.getInstance().playMusic();
        this.myMenu.getMenuPls().getPlsVScpu().addActionListener(this);
        this.myMenu.getMenuPls().getPlsVSplr().addActionListener(this);
        this.myMenu.getMenuPls().getPlsStat().addActionListener(this);
        this.myMenu.getMenuPls().getPlsSettings().addActionListener(this);
        this.myMenu.getMenuPls().getPlsExit().addActionListener(this);
        this.myMenu.getMenuPls().getPlsRules().addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==myMenu.getMenuPls().getPlsVScpu()) {
            try {
                gL = new GameListener();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            FrameManager.getInstance().setVisible(false);
            FrameManager.getInstance().setContentPane(gL.getP());
            FrameManager.getInstance().setSize(Toolkit.getDefaultToolkit().getScreenSize());
            FrameManager.getInstance().centerWindow();
            FrameManager.getInstance().setVisible(true);
        }
        if(e.getSource()==myMenu.getMenuPls().getPlsVSplr())
        {
            TransparentImageDialog t=new TransparentImageDialog("resources/contactUs.png");
        }
        if(e.getSource()==myMenu.getMenuPls().getPlsSettings())
        {
            FrameManager.getInstance().setVisible(false);
            try {
                mySV=new SettingsView();
                mySV.getSf().getPlsBack().addActionListener(this);
                mySV.getSf().getPlsChangeAccount().addActionListener(this);
                mySV.getSf().getPlsChangeCover().addActionListener(this);
                mySV.getSf().getPlsMusic().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(mySV.getSf().getPlsMusic().getSelected())
                            Music.getInstance().playMusic();
                        else
                            Music.getInstance().stopMusic();
                    }
                });
                mySV.getSf().getPlsDaltonici().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(mySV.getSf().getPlsDaltonici().getSelected())
                        {
                            try {
                                s.setIsDaltonic(true);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            constants.setIsDaltonic(true);
                        }
                        else
                        {
                            try {
                                s.setIsDaltonic(false);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            constants.setIsDaltonic(false);
                        }
                    }
                });
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (FontFormatException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource()==myMenu.getMenuPls().getPlsStat())
        {
            FrameManager.getInstance().setVisible(false);
            try {
                myS=new StatsView();
                StatsReturn sr=new StatsReturn();
                myS.getFieldStats().getLblwm().setText(""+sr.GamesWon());
                myS.getFieldStats().getLbllm().setText(""+sr.GamesLost());
                myS.getFieldStats().getLblcp().setText(""+sr.MostColorUsed());
                myS.getFieldStats().getLblmt().setText(""+sr.TotalTimeGame());
                myS.getFieldStats().getPlsBack().addActionListener(this);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (FontFormatException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(myS!=null)
            if(e.getSource()==myS.getFieldStats().getPlsBack())
                back();
        if(mySV!=null) {
            if (e.getSource() == mySV.getSf().getPlsBack())
                back();
            if(e.getSource()==mySV.getSf().getPlsChangeAccount())
            {
                FrameManager.getInstance().setVisible(false);
                FrameManager.getInstance().setContentPane(lg);
                FrameManager.getInstance().setSize(800,600);
                FrameManager.getInstance().centerWindow();
                FrameManager.getInstance().setVisible(true);
            }
            if(e.getSource()==mySV.getSf().getPlsChangeCover())
            {
                FrameManager.getInstance().setVisible(false);
                try {
                    this.c=new ChangeCoverView();
                    c.getPlsBack().addActionListener(this);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if(c!=null) {
                if (e.getSource() == c.getPlsBack())
                { //prima di tornare alla schermata principale salvo la skin selezionata nel db..
                    try {
                        String url=c.getSelectedPath();
                        s.changeCover(url);
                        constants.setSkin(url);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    back();
                }
            }
        }
        if(e.getSource()==myMenu.getMenuPls().getPlsRules())
        {
            FrameManager.getInstance().setVisible(false);
            try {
                myRules=new RulesView();
                myRules.getPlsBack().addActionListener(this);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (FontFormatException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(myRules!=null)
        {
            if(e.getSource()==myRules.getPlsBack())
                back();
        }
        if(e.getSource()==myMenu.getMenuPls().getPlsExit())
        {
            System.exit(0); //arresta il programma
        }
    }
}
