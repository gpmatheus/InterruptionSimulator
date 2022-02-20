package com.matheus.interrupt.front;

import java.awt.Color;
import java.util.Optional;

import com.matheus.interrupt.front.progressBars.ProcessProgress;
import com.matheus.interrupt.front.progressBars.Progress;
import com.matheus.interrupt.utils.Status;

public class LeftSideBar extends SideBar {

    public LeftSideBar(int cells, Color color) {
        super(cells, color);
        for (int i = 0; i < cells; i++) {
            ProcessProgress processProg = new ProcessProgress();
            progressBars.add(processProg);
            ProgressBarHolder panel = new ProgressBarHolder(color);
            panel.add(processProg);
            add(panel);
        }
    }
    
    public void setBarProperties(int processId, int percentage, boolean visible, Status status) {
        Optional<Progress> progressBar = progressBars.stream().filter(bar -> {
            return bar.getProcessId() == processId;
        }).findFirst();
        if (progressBar.isEmpty())
            progressBar = progressBars.stream().filter(bar -> {
                if (bar instanceof ProcessProgress == false)
                    return false;
                var proBar = (ProcessProgress) bar;
                return proBar.getStatus() == null;
            }).findFirst();
        if (progressBar.isEmpty() || progressBar.get() instanceof ProcessProgress == false)
            return;
        var processProgressBar = (ProcessProgress) progressBar.get();
        processProgressBar.setBarProperties(processId, percentage, visible, status);
    }

    public ProcessProgress getProcessProgress(int processId) {
        Optional<Progress> progressBar = progressBars.stream().filter(bar -> {
            return bar.getProcessId() == processId;
        }).findFirst();
        if (progressBar.isEmpty() || progressBar.get() instanceof ProcessProgress == false)
            return null;
        return (ProcessProgress) progressBar.get();
    }
}
