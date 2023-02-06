package com.otus.spaceBattle.command;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LongOperationStartCommand implements Command {

  @NonNull
  private final RepeaterCommand repeater;
  @NonNull
  private final Command longCommand;

  @Override
  public void execute() {
    repeater.setRepeatebleCommand(longCommand);
    repeater.execute();
  }
}
