package com.matheus.interrupt;

public class Instruction {
    private int id;
    private boolean isIoInstruction;

    public Instruction(int id, boolean isIoInstruction) {
        this.id = id;
        this.isIoInstruction = isIoInstruction;
    }

    public int getId() {
        return id;
    }

    public boolean isIoInstruction() {
        return isIoInstruction;
    }
}
