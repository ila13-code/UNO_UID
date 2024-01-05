package controller;

import model.FrameManager;
import view.WinView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class WinController implements ActionListener {
    private WinView w;
    public WinController() throws IOException {
        FrameManager.getInstance().setVisible(false);
        w=new WinView();
        w.getPls().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==w.getPls())
        {
            MenuListener.back();
        }
    }
}
