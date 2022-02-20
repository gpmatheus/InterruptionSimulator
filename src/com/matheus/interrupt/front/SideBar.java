package com.matheus.interrupt.front;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import com.matheus.interrupt.front.progressBars.Progress;

public abstract class SideBar extends JPanel {

    protected Color color;
    protected List<Progress> progressBars = new ArrayList<>();
    
    public SideBar(int cells, Color color) {
        this.color = color;
        setBackground(color);
        setPreferredSize(new Dimension(300, 20));
    }
}
