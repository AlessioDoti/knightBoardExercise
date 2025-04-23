package com.example.knightboard.model.command.impl;

import com.example.knightboard.model.command.Command;
import com.example.knightboard.model.enumeration.Direction;
import com.example.knightboard.service.KnightService;

public class RotateCommand extends Command {

    private final Direction direction;

    public RotateCommand(KnightService knightService, Direction direction) {
        super(knightService);
        this.direction = direction;
    }

    @Override
    public void doExecute() {
        knightService.rotate(direction);
    }
}
