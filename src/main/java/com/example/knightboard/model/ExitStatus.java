package com.example.knightboard.model;

import lombok.Data;

@Data
public class ExitStatus {

    private final KnightPosition position;
    private final String status;

    public ExitStatus(String status) {
        this.status = status;
        this.position = null;
    }

    public ExitStatus(KnightPosition knightPosition, String status) {
        this.position = knightPosition;
        this.status = status;
    }
}
