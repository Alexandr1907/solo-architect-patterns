package com.otus.spaceBattle;

import com.otus.spaceBattle.dto.Coords;

public class TestData {
  public static final Coords START_POSITION = new Coords(12, 5);
  public static final Coords VELOCITY = new Coords(-7, 3);
  public static final Coords END_POSITION = new Coords(5, 8);

  public static final int STARTED_SPEED = 10;
  public static final int CHANGED_SPEED = -3;
  public static final int FINAL_SPEED = 7;

  public static final int STARTED_ROTATION_VELOCITY = 10;
  public static final int CHANGE_ROTATION_VELOCITY = 5;
  public static final int FINAL_ROTATION_VELOCITY = 15;

  public static final int NORMAL_FUEL_LEVEL = 2;
  public static final int LOW_FUEL_LEVEL = 1;
  public static final int RESULT_FUEL_LEVEL = 0;
  public static final int CONSUME_FUEL = 2;

  public final static String ERROR_MSG = "Test exception msg";
}
