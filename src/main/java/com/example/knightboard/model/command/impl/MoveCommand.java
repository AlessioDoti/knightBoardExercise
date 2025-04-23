package com.example.knightboard.model.command.impl;

import com.example.knightboard.model.command.Command;
import com.example.knightboard.service.KnightService;

public class MoveCommand extends Command {

    private final int step;

    public MoveCommand(KnightService knightService, int step) {
        super(knightService);
        this.step = step;
    }

    @Override
    public void doExecute() {
        knightService.move(step);
    }
}
