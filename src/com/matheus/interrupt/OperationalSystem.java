package com.matheus.interrupt;

import java.util.ArrayList;
import java.util.List;

import com.matheus.interrupt.exceptions.ProcessorInterruptedException;
import com.matheus.interrupt.utils.Status;

public class OperationalSystem implements Runnable {
    
    private static final int DEFAULT_PROCESSES = 8;
    private List<Process> processes = new ArrayList<>();
    private Processor processor;

    public OperationalSystem(Processor processor) {
        this.processor  = processor;
        for (int i = 0; i < DEFAULT_PROCESSES; i++) {
            processes.add(new Process(i));
        }
    }

    public void initRotine(ProcessorInterruptedException interruption) {
        Process process = new Process(interruption.getProcess().getProcessId(), 30, false);
        runProcess(process);
        Process pro = interruption.getProcess();
        pro.setStatus(Status.READY);
    }

    private void runProcess(Process process) {
        boolean repeat = false;
        do {
            try {
                processor.runProcess(process);
                if (!process.hasFinished() && process.getCurrentInstruction().isIoInstruction()) {
                    process.setStatus(Status.BLOCKED);
                }
                repeat = false;
            } catch (ProcessorInterruptedException e) {
                //e.printStackTrace();
                repeat = true;
                process.setStatus(Status.INTERRUPTED);
                initRotine(e);
            }
        } while (repeat);
    }

    public void startRunningProcesses() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        runProcesses();
    }

    private void runProcesses() {
        while (processes.stream().filter(pro -> pro.getStatus() != Status.FINISHED).count() != 0) {
            for (var pro : processes) {
                if (pro.getStatus() == Status.READY) {
                    runProcess(pro);
                    break;
                }
            }
        }
    }
}