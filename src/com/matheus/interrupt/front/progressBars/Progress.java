package com.matheus.interrupt.front.progressBars;

import java.awt.Dimension;

import javax.swing.JProgressBar;

public abstract class Progress extends JProgressBar {
    
    private final int INIT_VALUE = 0;
    private final int MAXIMUM_VALUE = 100;
    protected int processId;
    protected int percentage;

    public Progress() {
        setPreferredSize(new Dimension(280, 30));
        setStringPainted(true);
        setMinimum(INIT_VALUE);
        setMaximum(MAXIMUM_VALUE);
        setValue(INIT_VALUE);
    }

    public int getProcessId() {
        return processId;
    }

    public abstract void updateString(String str);

    public void updateBar(int value) {
        if (value < 0 || value > 100)
            return;
        setValue(value);
    }

    public int getPercentage() {
        return percentage;
    }

    protected void setBarProperties(int processId, int percentage, boolean visible) {
        this.processId = processId;
        this.percentage = percentage;
        updateBar(percentage);
        updateString(null);
        setVisible(visible);
    }
}
