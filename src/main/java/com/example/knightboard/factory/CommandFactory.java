package com.example.knightboard.factory;

import com.example.knightboard.model.command.impl.MoveCommand;
import com.example.knightboard.model.command.impl.RotateCommand;
import com.example.knightboard.model.command.impl.StartCommand;
import com.example.knightboard.model.enumeration.Direction;
import com.example.knightboard.service.KnightService;
import lombok.RequiredArgsConstructor;
import com.example.knightboard.model.command.Command;

@RequiredArgsConstructor
public class CommandFactory {

    private final KnightService knightService;

    private static final String START = "START";
    private static final String ROTATE = "ROTATE";
    private static final String MOVE = "MOVE";

    public Command getCommand(String stringCommand){

        var splittedCommand = stringCommand.split(" ");

        return switch (splittedCommand[0]) {
            case START -> getStartCommand(splittedCommand[1]);
            case ROTATE -> getRotateCommand(splittedCommand[1]);
            case MOVE -> getMoveCommand(splittedCommand[1]);
            default -> throw new RuntimeException("Invalid command provided");
        };

    }

    private StartCommand getStartCommand(String params){
        var paramsList = params.split(",");

        return new StartCommand(knightService, Integer.parseInt(paramsList[0]),
                Integer.parseInt(paramsList[1]),
                Direction.valueOf(paramsList[2]));
    }

    private RotateCommand getRotateCommand(String direction){
        return new RotateCommand(knightService, Direction.valueOf(direction));
    }

    private MoveCommand getMoveCommand(String step){
        return new MoveCommand(knightService, Integer.parseInt(step));
    }
}
