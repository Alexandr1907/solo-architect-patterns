package com.otus.spaceBattle.action;

import com.otus.spaceBattle.dto.Coords;

public interface Movable {

  Coords getPosition();

  void setPosition(Coords newValue);

  Coords getVelocity();
}
