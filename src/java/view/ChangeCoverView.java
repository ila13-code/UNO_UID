package view;

import model.Constants;
import model.FrameManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class ChangeCoverView extends ImagePanel
{
    private JButton plsBack;
    private MyFont font;

    private String selectedPath;

    private ArrayList<JLabel> lstLbl;
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

    private void addLabelSkin()
    {
        JLabel l1=new JLabel();
        ImageIcon img=new ImageIcon(ClassLoader.getSystemResource("resources/skin.png"));
        Image i=img.getImage().getScaledInstance(140,220, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon=new ImageIcon(i);
        l1.setIcon(scaledIcon);
        l1.setName("skin.png");
        lstLbl.add(l1);
        for(int k=2; k<=4; k++)
        {
            JLabel l=new JLabel();
            ImageIcon img1=new ImageIcon(ClassLoader.getSystemResource("resources/skin"+k+".png"));
            Image i1=img1.getImage().getScaledInstance(140,220, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon1=new ImageIcon(i1);
            l.setIcon(scaledIcon1);
            l.setName("skin"+k+".png");
            lstLbl.add(l);
        }
    }

    private void drawSkin()
    {
        lstLbl.get(0).setBounds(70,120,150,230);
        lstLbl.get(1).setBounds(250,120,150,230);
        lstLbl.get(2).setBounds(430,120,150,230);
        lstLbl.get(3).setBounds(610,120,150,230);
        for(int k=0; k<=lstLbl.size()-1; k++)
            this.add(lstLbl.get(k));
    }

    private void addEvent()
    {
        for(int k=0; k<=lstLbl.size()-1; k++)
        {
            lstLbl.get(k).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JLabel l=(JLabel) e.getSource();
                    for (JLabel label : lstLbl) {
                        label.setBorder(null);
                    }
                    l.setBorder(BorderFactory.createLineBorder(new Color(0xFFF1C900, true), 5));
                    selectedPath = l.getName();
                }
            });
        }
    }


    public ChangeCoverView() throws IOException {
        lstLbl=new ArrayList<>();
        font=new MyFont(18f);
        this.changeBackground(ImageIO.read(ClassLoader.getSystemResource("resources/chooseAcover.png")));
        this.setLayout(null);
        plsBack=new JButton("BACK");
        FormattaPLS(plsBack);
        plsBack.setBounds(300,400, 200,50);
        this.add(plsBack);

        addLabelSkin();
        drawSkin();
        for(int k=0; k<=lstLbl.size()-1; k++)
        {
            if(lstLbl.get(k).getName().equals(Constants.skin)) {
                lstLbl.get(k).setBorder(BorderFactory.createLineBorder(new Color(0xFFF1C900, true), 5));
                selectedPath=Constants.skin;
                break;
            }
        }
        addEvent();

        FrameManager.getInstance().setSize(800,500);
        FrameManager.getInstance().centerWindow();
        FrameManager.getInstance().ContentPane(this);
        FrameManager.getInstance().setVisible(true);
    }

    public JButton getPlsBack()
    {
        return plsBack;
    }

    public String getSelectedPath()
    {
        return selectedPath;
    }
}
