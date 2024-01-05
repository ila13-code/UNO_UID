package view;

import model.Card;
import model.Constants;
import model.GamePvsCPU;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class PlayerFieldView extends ImagePanel //view del gioco 1vs1 (usata indistintamente se 1vsCPU o 1vs1)
{
    private int nOpponentCards; //numero di carte in mano all'avversario
    private GamePvsCPU game;
    private ArrayList<JLabel> OpponentCards;
    private ArrayList<JLabel> PlayerCards;

    private JLabel CardInGame;
    private int YP;
    private JPanel p1;
    private JPanel p2;
    private JPanel p3;
    private JButton One;
    private MyFont f;
    private JLabel lblColor;
    private JLabel CPUturn;
    private  JLabel Pturn;

    private JLabel deckPng;

    private JLabel circleUID;

    private void FormattaLBL(JLabel lbl)
    {
        lbl.setForeground(Color.BLACK);
        lbl.setFont(f.getFont().deriveFont(40f));
        lbl.setHorizontalAlignment(JLabel.CENTER);
    }
    public PlayerFieldView(GamePvsCPU g) throws IOException {
        PlayerCards=new ArrayList<>();
        f=new MyFont(15f);
        One=new JButton();
        lblColor=new JLabel();
        lblColor.setBorder(new LineBorder(Color.BLACK, 2));
        FormattaLBL(lblColor);
        CPUturn=new JLabel();
        Pturn=new JLabel();
        circleUID=new JLabel();
        p1=new JPanel();
        p2=new JPanel();
        p3=new JPanel();
        p3.setLayout(null);
        p1.setLayout(null);
        p2.setLayout(null);
        YP=640;
        this.changeBackground(ImageIO.read(ClassLoader.getSystemResource("resources/CampoGioco2.png")));
        this.setLayout(null);
        OpponentCards=new ArrayList<>();
        game=g;

        JLabel lblCPU=new JLabel("CPU");
        FormattaLBL(lblCPU);
        lblCPU.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width - 450, 0, 300, 150);
        this.add(lblCPU);

        JLabel lblPlayer=new JLabel(Constants.USER.toUpperCase());
        FormattaLBL(lblPlayer);
        lblPlayer.setBounds(100, Toolkit.getDefaultToolkit().getScreenSize().height-150, 300, 150);
        this.add(lblPlayer);

        lblColor.setBounds((Toolkit.getDefaultToolkit().getScreenSize().width/2)-200, (Toolkit.getDefaultToolkit().getScreenSize().height/2)-25, 50,50);
        lblColor.setVisible(false);
        this.add(lblColor);

        ImageIcon iCPU=new ImageIcon(ClassLoader.getSystemResource("resources/CPUturn.png"));
        Image imgCPU=iCPU.getImage().getScaledInstance(150,150, Image.SCALE_SMOOTH);
        ImageIcon iC=new ImageIcon(imgCPU);
        CPUturn.setIcon(iC);
        CPUturn.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width - 300, 100, 150, 150);
        CPUturn.setVisible(false);
        this.add(CPUturn);

        ImageIcon iPL=new ImageIcon(ClassLoader.getSystemResource("resources/Pturn.png"));
        Image imgP=iPL.getImage().getScaledInstance(150,150, Image.SCALE_SMOOTH);
        ImageIcon iP=new ImageIcon(imgP);
        Pturn.setIcon(iP);
        Pturn.setBounds(120, Toolkit.getDefaultToolkit().getScreenSize().height-260, 150, 150);
        this.add(Pturn);

        deckPng=new JLabel();
        ImageIcon d=new ImageIcon(ClassLoader.getSystemResource("resources/"+(Constants.skin).substring(0, (Constants.skin).indexOf('.'))+"Mazzo.png"));
        Image imgD=d.getImage().getScaledInstance(300,300, Image.SCALE_SMOOTH);
        ImageIcon i=new ImageIcon(imgD);
        deckPng.setIcon(i);
        deckPng.setBounds((Toolkit.getDefaultToolkit().getScreenSize().width/2)+320, (Toolkit.getDefaultToolkit().getScreenSize().height/2)-180, 300,300);
        this.add(deckPng);

        ImageIcon c=new ImageIcon(ClassLoader.getSystemResource("resources/circleUID.png"));
        Image iCc=c.getImage().getScaledInstance(200,185, Image.SCALE_SMOOTH);
        ImageIcon cI=new ImageIcon(iCc);
        circleUID.setIcon(cI);
        circleUID.setBounds((Toolkit.getDefaultToolkit().getScreenSize().width/2)+280, (Toolkit.getDefaultToolkit().getScreenSize().height/2)+120, 200,185);
        circleUID.setVisible(false);
        this.add(circleUID);

        p1.setOpaque(false);
        p2.setOpaque(false);
        p3.setOpaque(false);
        One.setOpaque(false);
        One.setContentAreaFilled(false);
        One.setBorderPainted(false);
        One.setFocusPainted(false);

        this.add(p1);
        p1.setBounds(0,0, Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
        this.add(p2);
        p2.setBounds(0,0,Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
        setN(7);
        p3.setBounds(0,0, Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
        this.add(p3);
        One.setBounds(1100,575,100,130);
        this.add(One);
        UpdatePlayerCard();
        this.revalidate();
    }


    private void overlapCards(int x, int y, int overlapPerc, int space, int centerCard, ArrayList<JLabel> c, JPanel p) //overlapPerc è la percentuale di sovrapposizione; space è lo spazio tra le carte (lo sfrutto nei casi 2-3-4)
    {//centerCard mi serve per reimpostare la x (lo uso per centrare le carte sul "tavolo" se l'utente ne ha in mano 2, 3 o 4)...
        int cardWidth = 140;
        for (int k = 0; k <= c.size()-1; k++) {
            int cardX = x + k * (cardWidth-cardWidth* overlapPerc / 100) + space;
            c.get(k).setBounds(cardX+centerCard, y, cardWidth, 220);
            p.add(c.get(k));
        }
    }

    private void UpdateLabelCardInGame()
    {
        String nameCard=game.getCurrentCard().getName(); //recupero il nome dell'ultima carta in gioco
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("resources/carte/" +nameCard + ".png"));
        Image scaledImage = img.getImage().getScaledInstance(140, 220, Image.SCALE_SMOOTH);
        ImageIcon i=new ImageIcon(scaledImage);
        JLabel lbl = new JLabel(i);
        lbl.setName(nameCard);
        if(Constants.isDaltonic) {
            if (!(nameCard.equals("+4") || nameCard.equals("cambiaColore"))) {
                JPanel overlayPanel = new JPanel();
                overlayPanel.setLayout(new OverlayLayout(overlayPanel));
                Card c = new Card(nameCard);
                String text = "";
                switch (c.getColor().charAt(0)) {
                    case 'v':
                        text = "G";
                        break;
                    case 'g':
                        text = "Y";
                        break;
                    default:
                        text = String.valueOf(c.getColor().charAt(0)).toUpperCase();
                        break;
                }

                JLabel textLabel = new JLabel(text, JLabel.CENTER);
                textLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                textLabel.setAlignmentY(JLabel.CENTER_ALIGNMENT);
                textLabel.setFont(f.getFont());

                overlayPanel.add(lbl);
                overlayPanel.setOpaque(false);
                overlayPanel.setLayout(null);
                textLabel.setBounds(0, 0, 220, 100);
                lbl.setLayout(null);
                lbl.add(textLabel);
            }
        }
        CardInGame=lbl; //imposto la carta visibile sul "piatto" all'ultima giocata
    }

    public void drawCardInTable() //disegno la carta in gioco sul piatto
    {
        UpdateLabelCardInGame();
        CardInGame.setBounds(700,320, 140, 220);
        p3.add(CardInGame);
        p3.setComponentZOrder(CardInGame,0);
        repaint();
        revalidate();
    }

    public void UpdatePlayerCard()
    {
        PlayerCards.clear();
        String nameCard;
        for(int k=0; k<=game.getCards().size()-1; k++)
        {
            nameCard=game.getCards().get(k).getName();
            ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("resources/carte/" +nameCard + ".png"));
            Image scaledImage = img.getImage().getScaledInstance(140, 220, Image.SCALE_SMOOTH);
            ImageIcon i=new ImageIcon(scaledImage);
            JLabel lbl = new JLabel(i);
            lbl.setName(nameCard);
            lbl.setEnabled(true);

            if(Constants.isDaltonic) {
                if (!(nameCard.equals("+4") || nameCard.equals("cambiaColore"))) {
                    JPanel overlayPanel = new JPanel();
                    overlayPanel.setLayout(new OverlayLayout(overlayPanel));
                    Card c = new Card(nameCard);
                    String text = "";
                    switch (c.getColor().charAt(0)) {
                        case 'v':
                            text = "G";
                            break;
                        case 'g':
                            text = "Y";
                            break;
                        default:
                            text = String.valueOf(c.getColor().charAt(0)).toUpperCase();
                            break;
                    }

                    JLabel textLabel = new JLabel(text, JLabel.CENTER);
                    textLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                    textLabel.setAlignmentY(JLabel.CENTER_ALIGNMENT);
                    textLabel.setFont(f.getFont());

                    overlayPanel.add(lbl);
                    overlayPanel.setOpaque(false);
                    overlayPanel.setLayout(null);
                    textLabel.setBounds(0, 0, 220, 100);
                    lbl.setLayout(null);
                    lbl.add(textLabel);
                }
            }
            PlayerCards.add(lbl);
        }
    }

    public void drawCards(ArrayList<JLabel> cardsDraw, int x, int y, JPanel p) //metodo usato sia per disegnare le carte del giocatore che quelle della PC
    {
        p.removeAll();

        switch (cardsDraw.size())
        {
            case 1:
            {
                cardsDraw.get(0).setBounds(x+230, y, 140, 220);
                p.add(cardsDraw.get(0));
            }break;
            case 2:
            {
                overlapCards(x, y, 0,10, 150,cardsDraw, p);
            }break;
            case 3:
            {
                overlapCards(x, y,0,10, 75,cardsDraw, p);
            }break;
            case 4:
            {
                overlapCards(x, y,0,10, 0,cardsDraw, p);
            }break;
            case 5:
            {
                overlapCards(x, y,22, 0,0, cardsDraw,p);
            }break;
            case 6:
            {
                overlapCards(x, y, 39,0,0,cardsDraw,p);
            }break;
            case 7:
            {
                overlapCards(x, y, 49,0,0,cardsDraw,p);
            }break;
            case 8:
            {
                overlapCards(x, y, 57,0,0,cardsDraw,p);
            }break;
            case 9:
            {
                overlapCards(x, y, 62,0,0,cardsDraw,p);
            }break;
            case 10:
            {
                overlapCards(x, y, 67,0,0,cardsDraw,p);
            }break;
            case 11:
            {
                overlapCards(x, y, 68,0,0,cardsDraw,p);
            }break;
            case 12:
            {
                overlapCards(x, y, 71,0,0,cardsDraw,p);
            }break;
            case 13:
            {
                overlapCards(x, y, 74,0,0,cardsDraw,p);
            }break;
            case 14:
            {
                overlapCards(x, y, 75,0,0,cardsDraw,p);
            }break;
            case 15:
            {
                overlapCards(x, y, 76,0,0,cardsDraw,p);
            }break;
            case 16:
            {
                overlapCards(x, y, 77,0,0,cardsDraw,p);
            }break;
            case 17:
            {
                overlapCards(x, y, 79,0,0,cardsDraw,p);
            }break;
            case 18:
            {
                overlapCards(x, y, 81,0,0,cardsDraw,p);
            }break;
            case 19:
            {
                overlapCards(x, y, 82,0,0,cardsDraw,p);
            }break;
            case 20:
            {
                overlapCards(x, y, 83,0,0,cardsDraw,p);
            }break;
            default: break;
        }
        repaint();
        revalidate();
    }

    public void animateCardUp(JLabel label) {
        YP = label.getY();
        int targetY = 490; // Metà dello schermo in altezza

        Timer timer = new Timer(1, null);
        timer.addActionListener(new ActionListener() {
            private int deltaY = 5;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (label.getY() > targetY) {
                    label.setLocation(label.getX(), label.getY() - deltaY);
                } else {
                    timer.stop();
                }
            }
        });
        timer.start();
    }


    public void animateCardDown(JLabel label) {
        int targetY = YP;

        Timer timer = new Timer(1, null);
        timer.addActionListener(new ActionListener() {
            private int deltaY = 5;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (label.getY() < targetY) {
                    label.setLocation(label.getX(), label.getY() + deltaY);
                } else {
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    public void addOpponentCard()
    {
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("resources/"+ Constants.skin));
        Image scaledImage = img.getImage().getScaledInstance(140, 220, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel lbl = new JLabel(scaledIcon);
        OpponentCards.add(lbl);
        nOpponentCards++;
    }

    public void removeOpponentCard()
    {
        OpponentCards.remove(OpponentCards.size()-1);
        nOpponentCards--;
    }

    public void setN(int n)
    {
        while(nOpponentCards<n) {
            addOpponentCard();
        }
        while(nOpponentCards>n)
        {
            removeOpponentCard();
        }
    }

    public ArrayList<JLabel> getPlayerCards() {
        return PlayerCards;
    }

    public JPanel getP1() {
        return p1;
    }

    public JPanel getP2() {
        return p2;
    }

    public ArrayList<JLabel> getOpponentCards() {
        return OpponentCards;
    }

    public JButton getOne() {
        return One;
    }

    public void setLblColor(Color c) {
        if(Constants.isDaltonic) {
            String txt = "";
            if (c.equals(Color.red)) {
                txt = "R";
            } else if (c.equals(Color.green)) {
                txt = "G";
            } else if (c.equals(Color.blue)) {
                txt = "B";
            } else if (c.equals(Color.yellow)) {
                txt = "Y";
            }
            lblColor.setText(txt);
        }
        lblColor.setBackground(c);
        lblColor.setOpaque(true);
        lblColor.setVisible(true);
        revalidate();
    }

    public void removeLblColor()
    {
        lblColor.setVisible(false);
    }

    public void setVisibleCPUturn(boolean b) {
        CPUturn.setVisible(b);
    }

    public void setVisiblePturn(boolean b) {
        Pturn.setVisible(b);
    }

    public void setVisibleCircleUID(boolean b) {circleUID.setVisible(b);}

}
