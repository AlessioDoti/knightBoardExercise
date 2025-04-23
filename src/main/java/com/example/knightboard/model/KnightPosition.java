package com.example.knightboard.model;

import com.example.knightboard.model.enumeration.Direction;
import lombok.Data;

@Data
public class KnightPosition {

    private int x;
    private int y;
    private Direction direction;
    private String status;
}
