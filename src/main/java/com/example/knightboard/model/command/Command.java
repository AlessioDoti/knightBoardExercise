package com.example.knightboard.model.command;

import com.example.knightboard.service.KnightService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class Command {

    protected final KnightService knightService;

    public abstract void doExecute();
}
