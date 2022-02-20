package com.matheus.interrupt.front;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class ProgressBarHolder extends JPanel {
    
    public ProgressBarHolder(Color color) {
        setBackground(color);
        setPreferredSize(new Dimension(290, 65));
    }
}
