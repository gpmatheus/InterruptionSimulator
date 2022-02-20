package com.matheus.interrupt.front;

import java.awt.Color;
import java.util.Optional;

import com.matheus.interrupt.front.progressBars.IoProgress;
import com.matheus.interrupt.front.progressBars.Progress;

public class RightSideBar extends SideBar {

    public RightSideBar(int cells, Color color) {
        super(cells, color);
        for (int i = 0; i < cells; i++) {
            IoProgress ioProg = new IoProgress();
            progressBars.add(ioProg);
            ioProg.setVisible(false);
            ProgressBarHolder panel = new ProgressBarHolder(color);
            panel.add(ioProg);
            add(panel);
        }
    }

    public void setBarProperties(int processId, int percentage, boolean visible) {
        Optional<Progress> progressBar = progressBars.stream().filter(bar -> {
            return bar.getProcessId() == processId;
        }).findFirst();
        if (progressBar.isEmpty())
            progressBar = progressBars.stream().filter(bar -> {
                return !bar.isVisible();
            }).findFirst();
        if (progressBar.isEmpty() || progressBar.get() instanceof IoProgress == false)
            return;
        var ioProgressBar = (IoProgress) progressBar.get();
        ioProgressBar.setBarProperties(processId, percentage, visible);
    }

    public void ioFinished(int processId) {
        Progress progressBar = progressBars.stream().filter(bar -> {
            return bar.getProcessId() == processId;
        }).findFirst().get();
        if (progressBar instanceof IoProgress == false)
            return;
        var ioProgressBar = (IoProgress) progressBar;
        ioProgressBar.setVisible(false);
    }

    public void pileProgressBars() {
        Progress currentProgress;
        Progress nextProgress;
        for (int i = 0; i < progressBars.size() - 1; i++) {
            currentProgress = progressBars.get(i);
            nextProgress = progressBars.get(i + 1);
            if (currentProgress instanceof IoProgress) {
                var curPro = (IoProgress) currentProgress;
                if (i == 0) {
                    if (nextProgress.isVisible()) {
                        curPro.setBarProperties(nextProgress.getProcessId(), nextProgress.getPercentage(), nextProgress.isVisible());
                    }
                } else {
                    curPro.setBarProperties(nextProgress.getProcessId(), nextProgress.getPercentage(), nextProgress.isVisible());
                }
            }
        }
    }
}
