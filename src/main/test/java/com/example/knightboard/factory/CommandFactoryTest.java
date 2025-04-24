package com.example.knightboard.factory;

import com.example.knightboard.model.command.impl.MoveCommand;
import com.example.knightboard.model.command.impl.RotateCommand;
import com.example.knightboard.model.command.impl.StartCommand;
import com.example.knightboard.service.KnightService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CommandFactoryTest {

    @InjectMocks
    private CommandFactory commandFactory;

    @Mock
    private KnightService knightService;

    private static final String VALID_START_COMMAND = "START 1,2,NORTH";
    private static final String NO_COORD_START_COMMAND = "START NORTH";
    private static final String NO_X_COORD_START_COMMAND = "START 1,NORTH";
    private static final String NO_DIRECTION_START_COMMAND = "START 1,2";
    private static final String INVALID_DIRECTION_START_COMMAND = "START 1,2,LEFT";
    private static final String VALID_ROTATE_COMMAND = "ROTATE SOUTH";
    private static final String INVALID_DIRECTION_ROTATE_COMMAND = "ROTATE LEFT";
    private static final String VALID_MOVE_COMMAND = "MOVE 5";
    private static final String INVALID_MOVE_COMMAND = "MOVE A";

    @Test
    public void whenInvokeGetCommandWithAValidStartCommand_ShouldReturnAStartCommand(){
        var ret = commandFactory.getCommand(VALID_START_COMMAND);
        assertInstanceOf(StartCommand.class, ret);
    }

    @Test
    public void whenInvokeGetCommandWithAStartCommandWhithoutXandY_ShouldThrowANumberFormatException(){
        assertThrows(NumberFormatException.class, () -> commandFactory.getCommand(NO_COORD_START_COMMAND));
    }

    @Test
    public void whenInvokeGetCommandWithAStartCommandWithoutXorY_ShouldThrowANumberFormatException(){
        assertThrows(NumberFormatException.class, () -> commandFactory.getCommand(NO_X_COORD_START_COMMAND));
    }

    @Test
    public void whenInvokeGetCommandWithAStartCommandWithoutDirection_ShouldThrowAnArrayIndexOutOfBoundsException(){
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> commandFactory.getCommand(NO_DIRECTION_START_COMMAND));
    }

    @Test
    public void whenInvokeGetCommandWithAStartCommandWithAnInvalidDirectionDirection_ShouldThrowAnIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> commandFactory.getCommand(INVALID_DIRECTION_START_COMMAND));
    }

    @Test
    public void whenInvokeGetCommandWithAValidRotateCommand_ShouldReturnAValidRotateCommand(){
        var ret = commandFactory.getCommand(VALID_ROTATE_COMMAND);
        assertInstanceOf(RotateCommand.class, ret);
    }

    @Test
    public void whenInvokeGetCommandWithAnInvalidDirectionRotateCommand_ShouldThrowAnIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> commandFactory.getCommand(INVALID_DIRECTION_ROTATE_COMMAND));
    }

    @Test
    public void whenInvokeGetCommandWithAValidMoveCommand_ShouldReturnAValidMoveCommand(){
        var ret = commandFactory.getCommand(VALID_MOVE_COMMAND);
        assertInstanceOf(MoveCommand.class, ret);
    }

    @Test
    public void whenInvokeGetCommandWithAnInvalidMoveCommand_ShouldThrowANumberFormatException(){
        assertThrows(NumberFormatException.class, () -> commandFactory.getCommand(INVALID_MOVE_COMMAND));
    }
}
