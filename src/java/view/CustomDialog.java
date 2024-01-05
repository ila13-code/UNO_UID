package view;


import javax.swing.*;
import java.awt.*;


public class CustomDialog extends JDialog {

    private String selectedColor;

    public CustomDialog() {

        setSize(300, 200);

        JButton redButton = createButton("rosso", Color.RED);
        JButton greenButton = createButton("verde", Color.GREEN);
        JButton blueButton = createButton("blu", Color.BLUE);
        JButton yellowButton = createButton("giallo", Color.YELLOW);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
        buttonPanel.add(redButton);
        buttonPanel.add(greenButton);
        buttonPanel.add(blueButton);
        buttonPanel.add(yellowButton);

        JLabel titleLabel= new JLabel();
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("resources/chooser.png"));
        titleLabel.setIcon(imageIcon);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.setBackground(new Color(241, 201, 0, 194));

        getContentPane().add(mainPanel);
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
    }

    private JButton createButton(String colorName, Color color) {
        String text="";
        switch (colorName.charAt(0))
        {
            case 'v': text="G";break;
            case 'g': text="Y"; break;
            default: text=String.valueOf(colorName.charAt(0)).toUpperCase(); break;
        }
        JButton button = new JButton(text);
        MyFont f=new MyFont(button.getFont().getSize() + 4f);
        button.setFont(f.getFont());
        button.setName(colorName);
        button.setBackground(color);
        button.addActionListener(e -> {
            JButton clickedButton = (JButton) e.getSource();
            selectedColor = clickedButton.getName();
            dispose(); //chiudi il dialogo dopo la selezione
        });
        return button;
    }

    public String getSelectedColor() {
        return selectedColor;
    }
}