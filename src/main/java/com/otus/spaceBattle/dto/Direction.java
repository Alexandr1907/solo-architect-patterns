package com.otus.spaceBattle.dto;

public interface Direction {
    Direction next(int angularVelocity);
}
