package com.matheus.interrupt.front.progressBars;

import java.awt.Color;

import com.matheus.interrupt.utils.Status;

public class ProcessProgress extends Progress {

    private Status status;

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public void updateString(String str) {
        if (str != null) {
            setString(str);
            return;
        }
        setString("process " + processId + ": " + status.name());        
    }
    
    public void setBarProperties(int processId, int percentage, boolean visible, Status status) {
        this.status = status;
        super.setBarProperties(processId, percentage, visible);
        if (status == null)
            return;
        switch (status) {
            case READY :
                setBackground(Color.GREEN);
                break;
            case RUNNING :
                setBackground(Color.BLUE);
                break;
            case BLOCKED :
                setBackground(Color.RED);
                break;
            case INTERRUPTED :
                setBackground(Color.ORANGE);
                break;
            case FINISHED :
                setBackground(Color.WHITE);
        }
    }
}
