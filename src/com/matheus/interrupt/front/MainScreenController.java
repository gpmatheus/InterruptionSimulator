package com.matheus.interrupt.front;

import com.matheus.interrupt.front.progressBars.ProcessProgress;
import com.matheus.interrupt.utils.Status;

public class MainScreenController {
    
    private static MainScreenController controller;
    private MainScreen mainScreen = new MainScreen();
    private LeftSideBar leftSideBar;
    private RightSideBar rightSideBar;
    private ProcessProgress currentProcessing;

    public static MainScreenController getMainScreenController() {
        if (controller != null)
            return controller;
        controller = new MainScreenController();
        return controller;
    }

    private MainScreenController() {
        leftSideBar = mainScreen.getLeftSideBar();
        rightSideBar = mainScreen.getRightSideBar();
        currentProcessing = mainScreen.getCurrentProcessProgress();
    }

    public void setVisible() {
        mainScreen.setVisible(true);
    }

    public void updateLeftSide(int processId, String str, int percentage, boolean visible, Status status) {
        if (status == Status.RUNNING) {
            currentProcessing.setBarProperties(processId, percentage, visible, status);
            if (str != null)
                currentProcessing.setString(str);
        }
        if (str == null)
            leftSideBar.setBarProperties(processId, percentage, visible, status);
    }

    public void updateRightSide(int processId, int percentage, boolean visible) {
        rightSideBar.setBarProperties(processId, percentage, visible);
    }

    public void ioFinished(int processId) {
        rightSideBar.ioFinished(processId);
        rightSideBar.pileProgressBars();
    }
}
