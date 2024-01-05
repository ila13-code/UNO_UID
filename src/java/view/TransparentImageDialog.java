package view;

import javax.swing.*;
import java.awt.*;

public class TransparentImageDialog {
    public TransparentImageDialog(String url){
            JDialog dialog = new JDialog();
            dialog.setUndecorated(true);
            dialog.setBackground(new Color(0, 0, 0, 0));
            dialog.getContentPane().setLayout(new BorderLayout());
            ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource(url));
            Image scaledImage=imageIcon.getImage().getScaledInstance(500,200, Image.SCALE_SMOOTH);
            ImageIcon i=new ImageIcon(scaledImage);
            JLabel imageLabel = new JLabel(i);

            dialog.getContentPane().add(imageLabel, BorderLayout.CENTER);

            Timer timer = new Timer(2000, e -> dialog.dispose());
            timer.setRepeats(false);
            timer.start();

            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
    }
}

