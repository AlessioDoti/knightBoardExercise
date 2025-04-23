package com.example.knightboard.service;

import com.example.knightboard.exception.DomainException;
import com.example.knightboard.model.KnightPosition;
import com.example.knightboard.model.enumeration.Direction;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class KnightService {

    private final KnightPosition knightPosition;
    private final int[][] board;

    private static final String X_AXIS = "X";
    private static final String Y_AXIS = "Y";

    public void start(int x, int y, Direction direction){
        // assuming that knight start position can be re-set
        if(y > board.length || x > board[0].length || Objects.equals(1, board[x][y])) { throw new DomainException("INVALID_START_POSITION"); }

        knightPosition.setX(x);
        knightPosition.setY(y);
        knightPosition.setDirection(direction);
    }

    public void rotate(Direction direction){
        knightPosition.setDirection(direction);
    }

    public void move(int step){
        // no start position
        if(Objects.isNull(knightPosition.getDirection())) { throw new RuntimeException(); }

        var newX = knightPosition.getX() + (step * knightPosition.getDirection().dx);
        var newY = knightPosition.getY() + (step * knightPosition.getDirection().dy);
        setNewPosition(newX, newY);
    }

    //todo test empty board
    private void checkOutOfTheBoardPosition(int x, int y){
        if(y > board.length || x > board[0].length){
            throw new DomainException("OUT_OF_THE_BOARD");
        }
    }

    private void setNewPosition(int x, int y){
        var axisToIncrement = Objects.equals(knightPosition.getX(), x) ? Y_AXIS : X_AXIS;
        var target = Objects.equals(knightPosition.getX(), x) ? y : x;
        var keepIncrementing = true;
        while(keepIncrementing){
            keepIncrementing = incrementAxis(axisToIncrement, target);
        }
    }

    private boolean incrementAxis(String axisToIncrement, int target){
        var canIncrement = false;
        if(Objects.equals(X_AXIS, axisToIncrement)){
            var currX = knightPosition.getX();
            var step = currX <= target ? 1 : -1;
            canIncrement = !Objects.equals(currX, target) && checkForObstacles(knightPosition.getY(), currX + step);
            if(canIncrement){
                checkOutOfTheBoardPosition(currX + step, knightPosition.getY());
                knightPosition.setX(currX + step);
            }
        } else{
            var currY = knightPosition.getY();
            var step = currY <= target ? 1 : -1;
            canIncrement = !Objects.equals(currY, target) && checkForObstacles(currY + step, knightPosition.getX());
            if(canIncrement){
                checkOutOfTheBoardPosition(knightPosition.getX(), currY + step);
                knightPosition.setY(currY + step);
            }
        }
        return canIncrement;
    }

    private boolean checkForObstacles(int y, int x){
        return !Objects.equals(1, board[y][x]);
    }

}
