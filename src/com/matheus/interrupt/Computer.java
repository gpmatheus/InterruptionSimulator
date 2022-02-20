package com.matheus.interrupt;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.matheus.interrupt.exceptions.ProcessorInterruptedException;
import com.matheus.interrupt.front.MainScreenController;

public class Computer {

    private Processor processor = new Processor(this);
    private List<IoBoardController> controllers = new ArrayList<>();
    private MainScreenController mainController = MainScreenController.getMainScreenController();

    public void begin() {
        mainController.setVisible();
        processor.startProcessing();
    }

    public void startIo(Process process) {
        IoBoardController ioController = new IoBoardController(processor, process);
        controllers.add(ioController);
        ioController.executeIo();
    }

    public void processorInterrupted(ProcessorInterruptedException interruption) {
        controllers = controllers.stream().filter(con -> {
            return con.getProcess().getProcessId() != interruption.getProcess().getProcessId();
        }).collect(Collectors.toList());
    }
}
