package com.matheus.interrupt;

import java.util.Random;

import com.matheus.interrupt.exceptions.ProcessorInterruptedException;
import com.matheus.interrupt.front.MainScreenController;

public class IoBoardController implements Runnable {

    private Random random = new Random();
    private Processor processor;
    private Process process;
    private int currentStep = 0;
    private int stepsNumber = random.nextInt(100) + 90;
    private MainScreenController mainController = MainScreenController.getMainScreenController();

    public IoBoardController(Processor processor, Process process) {
        this.processor = processor;
        this.process = process;
    }

    public Process getProcess() {
        return process;
    }

    public void executeIo() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        execute();
    }

    private void execute() {
        double percentage;
        while (currentStep < stepsNumber) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            percentage = (double) currentStep / (double) stepsNumber * 100;
            mainController.updateRightSide(process.getProcessId(), (int) percentage, true);
            currentStep++;
        }
        mainController.ioFinished(process.getProcessId());
        processor.interrupt(new ProcessorInterruptedException(process, "Processador interrompido"));
    }
}
