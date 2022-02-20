package com.matheus.interrupt.exceptions;

import com.matheus.interrupt.Process;

public class ProcessorInterruptedException extends Exception {
    
    private Process process;

    public ProcessorInterruptedException(Process process, String problem) {
        super(problem);
        this.process = process;
    }

    public Process getProcess() {
        return process;
    }
}
