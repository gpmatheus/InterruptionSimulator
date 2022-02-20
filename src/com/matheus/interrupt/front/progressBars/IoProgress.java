package com.matheus.interrupt.front.progressBars;

public class IoProgress extends Progress {

    public void setBarProperties(int processId, int percentage, boolean visible) {
        super.setBarProperties(processId, percentage, visible);
    }
    
    @Override
    public void updateString(String str) {
        if (str != null) {
            setString(str);
            return;
        }
        setString("IO do processo " + processId);
    }
}
