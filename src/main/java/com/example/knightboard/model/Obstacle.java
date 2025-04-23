package com.example.knightboard.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Obstacle {
    public int x;
    public int y;
}
