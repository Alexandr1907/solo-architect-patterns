package com.otus.spaceBattle.dto;

import lombok.EqualsAndHashCode;
import lombok.NonNull;

@EqualsAndHashCode
public class Coords {
  private final int x;
  private final int y;

  public Coords(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public static Coords append(@NonNull Coords position, @NonNull Coords velocity) {
    return new Coords(position.x + velocity.x, position.y + velocity.y);
  }

}
