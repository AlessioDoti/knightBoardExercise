package com.example.knightboard.model.command.impl;

import com.example.knightboard.model.command.Command;
import com.example.knightboard.model.enumeration.Direction;
import com.example.knightboard.service.KnightService;

public class StartCommand extends Command {

    private final int x;
    private final int y;
    private final Direction direction;


    public StartCommand(KnightService knightService, int x, int y, Direction direction) {
        super(knightService);
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    @Override
    public void doExecute() {
        knightService.start(x, y, direction);
    }
}
