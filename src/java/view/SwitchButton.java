package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwitchButton extends Component{
    private Timer tm;
    private float location;
    private boolean selected;
    private boolean mouseOver;
    private float speed=1f;

    private int width;
    private int height;

    public  SwitchButton() {
        setBackground(new Color(0,174,255));
        width=80;
        height=40;
        location=4;
        setForeground(new Color(0xFFF1C900, true));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        tm=new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selected)
                {
                    int endLocation=width-height-2;
                    if(location<endLocation)
                    {
                        location+=speed;
                        repaint();
                    }
                    else
                    {
                        tm.stop();
                        location=endLocation;
                        repaint();
                    }
                }
                else
                {
                    int endLocation=4;
                    if(location>endLocation)
                    {
                        location-=speed;
                        repaint();
                    }
                    else
                    {
                        tm.stop();
                        location=endLocation;
                        repaint();
                    }
                }
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e))
                {
                    if(mouseOver)
                    {
                        selected=!selected;
                        tm.start();
                    }
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                mouseOver=true;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mouseOver=false;
            }
        });
    }

    public void paint(Graphics g)
    {
        Graphics2D g2d=(Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        float alpha=getAlpha();
        if(alpha<1)
        {
            g2d.setColor(Color.GRAY);
            g2d.fillRoundRect(5,5, width, height-4, 25,25);
        }
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
        g2d.setColor(getBackground());
        g2d.fillRoundRect(5,5, width, height-4, 25,25);
        g2d.setColor(getForeground());
        g2d.setComposite(AlphaComposite.SrcOver);
        g2d.fillOval((int)location+5,5,height-4, height-4);
        super.paint(g);
    }

    private float getAlpha()
    {
        float w=width-height;
        return (int)(location-4)/w;
    }

    public boolean getSelected() {return selected;}

    public void setSelected(boolean b) {
        if (b) {
            selected=b;
            location = width - height - 2;
            repaint();
        }
    }
}
