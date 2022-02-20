package com.matheus.interrupt;

import com.matheus.interrupt.exceptions.ProcessorInterruptedException;
import com.matheus.interrupt.utils.Status;

public class Processor implements Runnable {

    private Thread thread;
    private Process currentProcess;
    private Computer computer;
    private OperationalSystem sysop = new OperationalSystem(this);
    private ProcessorInterruptedException interruption;

    public Processor(Computer computer) {
        this.computer = computer;
    }

    public OperationalSystem getSysop() {
        return sysop;
    }

    public void startProcessing() {
        sysop.startRunningProcesses();
    }

    public void runProcess(Process process) throws ProcessorInterruptedException {
        if (thread != null && thread.isAlive()) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        currentProcess = process;
        thread = new Thread(this);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (interruption != null) {
            var toThrow = interruption;
            interruption = null;
            throw toThrow;
        }
    }

    //método que seria chamado pelo sistema operacional
    public void stopProcessingProcess() {
    }

    public synchronized void interrupt(ProcessorInterruptedException interruption) {
        if (currentProcess.isRotine() && thread.isAlive()) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } 
        if (!thread.isAlive()) {
            sysop.initRotine(interruption);
        }
        this.interruption = interruption;
        computer.processorInterrupted(interruption);
    }

    @Override
    public void run() {
        process();
    }

    private void process() {
        currentProcess.setStatus(Status.RUNNING);
        while (interruption == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Instruction instruction = currentProcess.getNextInstruction();
            if (instruction == null)
                return;
            if (instruction.isIoInstruction()) {
                computer.startIo(currentProcess);
                return;
            }
        }
    }
    
}
