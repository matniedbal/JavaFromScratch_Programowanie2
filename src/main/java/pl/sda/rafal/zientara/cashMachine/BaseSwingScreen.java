package pl.sda.rafal.zientara.cashMachine;

import javax.swing.*;

public class BaseSwingScreen {
    protected JFrame frame;

    public void show() {
        frame.setVisible(true);
    }

    public void hide() {
        frame.setVisible(false);
    }
}
