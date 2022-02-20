package com.matheus.interrupt;

import java.util.Random;

import com.matheus.interrupt.front.MainScreenController;
import com.matheus.interrupt.utils.Status;

public class Process {
    
    private int processId;
    private Random random = new Random();
    private Instruction[] instructions;
    private int currentInstruction = 0;
    private int numberOfInstructions = random.nextInt(70) + 80;
    private Status status;
    private boolean notRotine;
    private String rotineString;
    private MainScreenController mainController = MainScreenController.getMainScreenController();

    public Process(int processId) {
        this(processId, 0, true);
    }

    public Process(int processId, Integer numberOfInstructions, boolean notRotine) {
        this.processId = processId;
        this.notRotine = notRotine;
        if (numberOfInstructions > 0)
            this.numberOfInstructions = numberOfInstructions;
        int ioInstruction = random.nextInt((int) (this.numberOfInstructions * 1.5f));
        instructions = new Instruction[this.numberOfInstructions];
        for (int i = 0; i < instructions.length; i++) {
            boolean isIo = false;
            if (notRotine)
                isIo = ioInstruction == i;
            instructions[i] = new Instruction(i, isIo);
        }
        if (isRotine())
            rotineString = "rotina do IO " + processId;
        setStatus(Status.READY);
    }

    public synchronized Instruction getNextInstruction() {
        if (currentInstruction < numberOfInstructions)
            currentInstruction++;
        Instruction instruction = null;
        if (currentInstruction >= numberOfInstructions) {
            setStatus(Status.FINISHED);
        } else {
            instruction = instructions[currentInstruction];
        }
        mainController.updateLeftSide(processId, rotineString, (int) getPercentage(), true, status);
        return instruction;
    }

    public Instruction getCurrentInstruction() {
        if (currentInstruction == numberOfInstructions)
            return null;
        return instructions[currentInstruction];
    }

    public boolean isRotine() {
        return !notRotine;
    }

    public double getPercentage() {
        return (double) currentInstruction / (double) numberOfInstructions * 100;
    }

    public boolean hasFinished() {
        return this.status == Status.FINISHED;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {

        this.status = status;
        mainController.updateLeftSide(processId, rotineString, (int) getPercentage(), true, status);
    }

    public int getProcessId() {
        return processId;
    }
}
