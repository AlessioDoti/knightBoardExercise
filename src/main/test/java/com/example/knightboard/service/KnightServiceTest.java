package com.example.knightboard.service;

import com.example.knightboard.exception.DomainException;
import com.example.knightboard.model.KnightPosition;
import com.example.knightboard.model.enumeration.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class KnightServiceTest {

    @InjectMocks
    private KnightService knightService;

    private final int[][] board = {{0,0,1}, {0,0,1}, {0,0,1}};
    private KnightPosition knightPosition;

    @BeforeEach
    public void setUp(){
        knightPosition = new KnightPosition();
        setField(knightService, "knightPosition", knightPosition);
        setField(knightService, "board", board);
    }

    @Test
    public void whenInvokeStartWithAValidStartPosition_ShouldSetTheStartPositionOnTheKnight(){
        knightService.start(0,1, Direction.NORTH);
        assertEquals(0, knightPosition.getX());
        assertEquals(1, knightPosition.getY());
        assertEquals(Direction.NORTH, knightPosition.getDirection());
    }

    @Test
    public void whenInvokeStartWithAValidStartPositionTwice_ShouldReSetTheStartPositionOnTheKnight(){
        knightService.start(0,1, Direction.NORTH);

        assertEquals(0, knightPosition.getX());
        assertEquals(1, knightPosition.getY());
        assertEquals(Direction.NORTH, knightPosition.getDirection());

        knightService.start(1,2, Direction.SOUTH);

        assertEquals(1, knightPosition.getX());
        assertEquals(2, knightPosition.getY());
        assertEquals(Direction.SOUTH, knightPosition.getDirection());
    }

    @Test
    public void whenInvokeStartWithAnOutOfBoardStartPosition_ShouldThrowADomainException(){
        assertThrows(DomainException.class, () -> knightService.start(4,5, Direction.SOUTH));
    }

    @Test
    public void whenInvokeStartWithAnObstacleStartPosition_ShouldThrowADomainException(){
        assertThrows(DomainException.class, () -> knightService.start(2,1, Direction.SOUTH));
    }

    @Test
    public void whenInvokeMoveWithValidStartPositionAndStep_shouldMoveTheKnight(){
        knightService.start(0,0, Direction.NORTH);

        knightService.move(1);
        assertEquals(1, knightPosition.getY());
    }

    @Test
    public void whenInvokeMoveWithValidStartPositionAndObstacleStep_shouldMoveTheKnightUntilTheObstacle(){
        knightService.start(0,0, Direction.EAST);

        knightService.move(5);
        assertEquals(1, knightPosition.getX());
    }

    @Test
    public void whenInvokeMoveWithValidStartPositionAndBoarderStep_shouldMoveTheKnightAtTheBorder(){
        knightService.start(0,0, Direction.NORTH);

        knightService.move(2);
        assertEquals(2, knightPosition.getY());
    }

    @Test
    public void whenInvokeMoveWithValidStartPositionAndZeroBoarder_shouldNotMoveTheKnight(){
        knightService.start(0,0, Direction.NORTH);

        knightService.move(0);
        assertEquals(0, knightPosition.getY());
    }

    @Test
    public void whenInvokeMoveWithValidStartPositionAndANegativeOutOfTheBorderStep_shouldThrowADomainException(){
        knightService.start(0,0, Direction.SOUTH);

        assertThrows(DomainException.class, () -> knightService.move(2));
    }

    @Test
    public void whenInvokeMoveWithValidStartPositionAndAPositiveOutOfTheBorderStep_shouldThrowADomainException(){
        knightService.start(0,0, Direction.NORTH);

        assertThrows(DomainException.class, () -> knightService.move(4));
    }

    @Test
    public void whenInvokeMoveWithNoStartPosition_shouldThrowARuntimeException(){
        assertThrows(RuntimeException.class, () -> knightService.move(4));
    }

    @Test
    public void whenInvokeMoveWithAnEmptyBoard_ShouldThrowDomainException(){
        knightService.start(0,0, Direction.NORTH);
        setField(knightService, "board", new int[0][0]);
        assertThrows(DomainException.class, () -> knightService.move(1));
    }

    private void setField(Object target, String fieldName, Object value) {
        try {
            var field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (Exception ignored) {
        }
    }
}
