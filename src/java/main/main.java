package main;

import controller.FirstViewListener;
import model.CheckLogin;
import view.FirstView;
import model.FrameManager;

import java.awt.*;
import java.io.IOException;

public class main
{
    public static void main(String[] args) throws IOException, FontFormatException {
        FrameManager.getInstance().setSize(800,600);
        FrameManager.getInstance().centerWindow();
        FirstView f=new FirstView(true);
        FrameManager.getInstance().ContentPane(f);
        FrameManager.getInstance().setVisible(true);
        CheckLogin lg=new CheckLogin();
        FirstViewListener fListener=new FirstViewListener(f, lg);
        FrameManager.getInstance().requestFocus();
    }
}
