package controller;

import model.FrameManager;
import view.LostView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LostController implements ActionListener {
    private LostView l;
    public LostController() throws IOException {
        FrameManager.getInstance().setVisible(false);
        l=new LostView();
        l.getPls().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==l.getPls())
        {
            MenuListener.back();
        }
    }
}
