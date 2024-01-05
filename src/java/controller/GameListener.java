package controller;

import model.Card;
import model.FrameManager;
import model.GamePvsCPU;
import model.SaveInDB;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class GameListener implements ActionListener
{
    private GamePvsCPU g;
    private PlayerFieldView p;
    private TransparentImageDialog tId;

    private long startTime;
    private long endTime;
    private long isUID;
    private boolean pOne;
    private boolean isUIDpressed;
    private void updateView()
    {
        p.drawCardInTable();
        p.drawCards(p.getPlayerCards(), 475, 640, p.getP1());
        p.drawCards(p.getOpponentCards(), 475, 0, p.getP2());
    }

    public GameListener() throws IOException, InterruptedException, SQLException {
        pOne=false;
        g=new GamePvsCPU();
        p=new PlayerFieldView(g);
        g.setIsTurnOf(true); //il giocatore inizia per primo
        startTime=System.currentTimeMillis();
        updateView();
        playPlayer();
        p.getOne().addActionListener(this);
    }

    public void addEventListener()
    {
        ArrayList<Boolean> up = new ArrayList<Boolean>();
        for (int k = 0; k <= p.getPlayerCards().size() - 1; k++) {
            up.add(false);
            p.getPlayerCards().get(k).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JLabel lbl = (JLabel) e.getSource();
                    if (up.get(p.getPlayerCards().indexOf(lbl))) {
                        p.animateCardDown(lbl);
                        up.set(p.getPlayerCards().indexOf(lbl), false);
                    } else {
                        p.animateCardUp(lbl);
                        up.set(p.getPlayerCards().indexOf(lbl), true);
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    JLabel lbl = (JLabel) e.getSource();
                    int selection = g.PlayerDropCard(new Card(lbl.getName()));
                    if (lbl.getX() <= 900 && lbl.getX() >= 500 && lbl.getY() <= 420 && lbl.getY() >= 180 && selection != 0) {
                        if (selection == 2) {
                            CustomDialog dialog = new CustomDialog();
                            dialog.addWindowListener(new WindowAdapter() {
                                @Override
                                public void windowClosed(WindowEvent e) {
                                    g.setCurrentCard(new Card(lbl.getName()));
                                    g.getCards().removeCard(new Card(lbl.getName()));
                                    p.UpdatePlayerCard();
                                    p.setN(g.getSizeOpponentCards());
                                    updateView();
                                    FrameManager.getInstance().setOpacity(1.0f);
                                    String color = dialog.getSelectedColor();
                                    g.getCurrentCard().setColorBonus(color);
                                    Color c = Color.black;
                                    switch (g.getCurrentCard().getColor()) {
                                        case "rosso":
                                            c = Color.red;
                                            break;
                                        case "verde":
                                            c = Color.green;
                                            break;
                                        case "giallo":
                                            c = Color.yellow;
                                            break;
                                        case "blu":
                                            c = Color.blue;
                                            break;
                                        default:
                                            break;
                                    }
                                    p.setLblColor(c);
                                    dialog.dispose();
                                    g.setIsTurnOf(false);
                                    p.setVisibleCPUturn(true);
                                    p.setVisiblePturn(false);
                                    playCPU();
                                }

                            });
                            dialog.setVisible(true);
                            FrameManager.getInstance().setOpacity(0.9f);
                        } else if (selection==1){
                            p.removeLblColor();
                            g.setCurrentCard(new Card(lbl.getName()));
                            g.getCards().removeCard(new Card(lbl.getName()));
                            p.UpdatePlayerCard();
                            p.setN(g.getSizeOpponentCards());
                            g.setIsTurnOf(false);
                            updateView();
                            p.setVisibleCPUturn(true);
                            p.setVisiblePturn(false);
                            playCPU();
                        } if(selection==3) //il giocatore ha lanciato uno stop o un cambia giro...
                        {
                            p.removeLblColor();
                            g.setCurrentCard(new Card(lbl.getName()));
                            g.getCards().removeCard(new Card(lbl.getName()));
                            p.UpdatePlayerCard();
                            p.setN(g.getSizeOpponentCards());
                            updateView();
                            try {
                                playPlayer();
                            } catch (InterruptedException ex) {
                                throw new RuntimeException(ex);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                    updateView();
                }
            });
            int X = 0, Y = 0;

            p.getPlayerCards().get(k).addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    if (g.getIsTurnOf()) {
                        JLabel lbl = (JLabel) e.getSource();
                        int x = e.getXOnScreen() - X;
                        int y = e.getYOnScreen() - Y;
                        lbl.setLocation(x, y);
                    }
                }
            });

            p.getPlayerCards().get(k).setLocation(X, Y);
        }
        updateView();
    }

    public void playPlayer() throws InterruptedException, IOException, SQLException {
        if(pOne)
        {
            if((System.currentTimeMillis()-isUID)/1000>=5)
            {
                p.setVisibleCircleUID(false);
                tId=new TransparentImageDialog("resources/penalty.png");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                g.drawPCard();
                g.drawPCard();
                p.UpdatePlayerCard();
                updateView();
                addEventListener();
                pOne=false;
            }
        }
        if(g.GetEndGame()==1)
        {
            endTime=System.currentTimeMillis();
            Thread.sleep(3000);
            ArrayList<Integer> r=g.getColorPlayed();
            SaveInDB s=new SaveInDB();
            s.registerGame(g.GetEndGame(), (int)((endTime-startTime)/60000), r.get(0), r.get(1), r.get(2), r.get(3));
            LostController lc=new LostController();
        }
        else {
            if (g.GetEndGame() == 0) {
                p.UpdatePlayerCard();
                p.setN(g.getSizeOpponentCards());
                updateView();
                addEventListener();
                if (!(g.PlayerCanPlay())) {
                    g.drawPCard();
                    p.UpdatePlayerCard();
                    p.setN(g.getSizeOpponentCards());
                    updateView();
                    addEventListener();
                }
                if (!(g.PlayerCanPlay())) //passa il turno in automatico se non ha carte da lanciare
                {
                    p.UpdatePlayerCard();
                    p.setN(g.getSizeOpponentCards());
                    updateView();
                    tId = new TransparentImageDialog("resources/Ppass.png");
                    Thread.sleep(1000);
                    g.setIsTurnOf(false);
                    p.setVisibleCPUturn(true);
                    p.setVisiblePturn(false);
                    playCPU();
                }
            }
        }
    }


    private void dropCPU()
    {
        boolean b=g.CPUdropCard();
        if(b) {
            p.UpdatePlayerCard();
            p.setN(g.getSizeOpponentCards());
            updateView();
            if(g.getCurrentCard().getBonus())
            {
                //mostra il colore selezionato dalla CPU se la carta è un BONUS
                Color c=Color.black;
                switch (g.getCurrentCard().getColor())
                {
                    case "rosso": c=Color.red; break;
                    case "verde": c=Color.green; break;
                    case "giallo": c=Color.yellow; break;
                    case "blu": c=Color.blue; break;
                    default: break;
                }
                p.setLblColor(c);
            }
            else
                p.removeLblColor();
        }
        else {
            if(g.GetEndGame()==0) {
                try {
                    p.UpdatePlayerCard();
                    p.setN(g.getSizeOpponentCards());
                    updateView();
                    Thread.sleep(2000);
                    tId = new TransparentImageDialog("resources/CPUpass.png");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void playCPU() {
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                if(g.isPlayerOne() && pOne==false) //se il giocatore ha fatto UID...
                {
                    p.setVisibleCircleUID(true);
                    isUID = System.currentTimeMillis(); //rilevo il momento in cui aveva 1 carta in mano...
                    pOne=true;
                }
                try {
                    Thread.sleep(5000); // Ritardo di 5 secondi (5000 millisecondi)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (g.GetEndGame() == 2) {
                    endTime=System.currentTimeMillis();
                    ArrayList<Integer> r=g.getColorPlayed();
                    SaveInDB s=new SaveInDB();
                    try {
                        s.registerGame(g.GetEndGame(), (int)((endTime-startTime)/60000), r.get(0), r.get(1), r.get(2), r.get(3));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        WinController wc=new WinController();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    dropCPU();
                    if (g.isCPUone()) {
                        try {
                            Thread.sleep(2000);
                            tId = new TransparentImageDialog("resources/UIDcpu.png");
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if (g.getIsTurnOf()) {
                        p.setVisiblePturn(true);
                        p.setVisibleCPUturn(false);
                        try {
                            playPlayer();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        updateView();
                        playCPU();
                    }
                }
            }
        });
        t.start();
    }

    public PlayerFieldView getP() {
        return p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==p.getOne())
        {
            if(g.setPlayerOne()) {
                try {
                    if((System.currentTimeMillis()-isUID)/1000>=5)
                    {
                        p.setVisibleCircleUID(false);
                        tId=new TransparentImageDialog("resources/penalty.png");
                        Thread.sleep(2000);
                        g.drawPCard();
                        g.drawPCard();
                        p.UpdatePlayerCard();
                        updateView();
                        addEventListener();
                        pOne=false;
                    }
                    else
                    {
                        p.setVisibleCircleUID(false);
                        pOne=false;
                        tId=new TransparentImageDialog("resources/UIDp.png");
                        Thread.sleep(2000);
                    }
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
