package com.example.knightboard.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Board {
    public int width;
    public int height;
    public List<Obstacle> obstacles;

    public static int[][] toMatrix(Board board) {
        var matrix = new int[board.height][board.width];
        for (var obstacle : board.obstacles) {
            matrix[obstacle.y][obstacle.x] = 1;
        }
        return matrix;
    }
}
