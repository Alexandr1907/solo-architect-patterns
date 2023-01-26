package com.otus.spaceBattle.action;

import com.otus.spaceBattle.dto.Direction;

public interface Rotatable {
    Direction getDirection();

    int getAngularVelocity();

    void setDirection(Direction direction);
}
