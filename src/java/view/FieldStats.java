package view;


import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;

public class FieldStats extends JPanel
{
    private JButton plsBack;
    private MyFont font;
    private JLabel lblwm;

    private JLabel lbllm;

    private JLabel lblmt;

    private JLabel lblcp;

    private void FormattaPLS(JButton pls) throws IOException, FontFormatException {
        pls.setBackground(new Color(0xFFF1C900, true));
        pls.setFont(font.getFont());
        pls.setForeground(Color.BLACK);
        Border roundedBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
        roundedBorder = BorderFactory.createCompoundBorder(roundedBorder, BorderFactory.createEmptyBorder(3, 10, 3, 9));
        roundedBorder = BorderFactory.createCompoundBorder(BorderFactory.createRaisedSoftBevelBorder(), roundedBorder);
        pls.setBorder(roundedBorder);
    }

    private void FormattaLBL(JLabel lbl, Color c)
    {
        lbl.setForeground(c);
        lbl.setFont(font.getFont());
        lbl.setHorizontalAlignment(JLabel.CENTER);
    }

    public FieldStats() throws IOException, FontFormatException {
        font=new MyFont(18f);
        this.setLayout(new GridLayout(10,1));
        this.setOpaque(false);

        JLabel lblPV=new JLabel("MATCHES WON");
        FormattaLBL(lblPV, Color.BLACK);
        this.add(lblPV);

        lblwm=new JLabel("");
        FormattaLBL(lblwm, Color.BLACK);
        this.add(lblwm);

        JLabel lblPP=new JLabel("MATCHES LOST");
        FormattaLBL(lblPP, Color.BLACK);
        this.add(lblPP);

        lbllm=new JLabel("");
        FormattaLBL(lbllm, Color.BLACK);
        this.add(lbllm);

        JLabel lblMT=new JLabel("MINUTES OF PLAY");
        FormattaLBL(lblMT, Color.BLACK);
        this.add(lblMT);

        lblmt=new JLabel("");
        FormattaLBL(lblmt, Color.BLACK);
        this.add(lblmt);

        JLabel lblCP=new JLabel("FAVOURITE COLOR");
        FormattaLBL(lblCP, Color.BLACK);
        this.add(lblCP);

        lblcp=new JLabel("");
        FormattaLBL(lblcp, Color.BLACK);
        this.add(lblcp);

        /*
        DA RIVEDERE PER USARE UN FLOWCHART
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("WON", 40);
        dataset.setValue("LOST", 30);
        JFreeChart chart = ChartFactory.createPieChart(null, dataset, true, true, false);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        ChartPanel chartPanel = new ChartPanel(chart);
        this.add(chartPanel);*/

        this.add(new JLabel(" "));
        plsBack=new JButton("BACK");
        FormattaPLS(plsBack);
        this.add(plsBack);
    }

    public JLabel getLblwm() {
        return lblwm;
    }

    public JLabel getLbllm()
    {
        return lbllm;
    }

    public JLabel getLblmt()
    {
        return lblmt;
    }

    public JLabel getLblcp()
    {
        return lblcp;
    }

    public JButton getPlsBack() {return plsBack;}
}
