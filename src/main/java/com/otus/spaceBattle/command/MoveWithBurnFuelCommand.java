package com.otus.spaceBattle.command;

import java.util.Arrays;
import lombok.NonNull;

public class MoveWithBurnFuelCommand extends MacroCommand {


  public MoveWithBurnFuelCommand(@NonNull CheckFuelCommand checkFuelCommand,
      @NonNull MoveCommand moveCommand,
      @NonNull BurnFuelCommand burnFuelCommand) {
    super(Arrays.asList(checkFuelCommand, moveCommand, burnFuelCommand));
  }

  @Override
  public void execute() {
    super.execute();
  }
}
