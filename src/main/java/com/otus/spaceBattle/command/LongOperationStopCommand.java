package com.otus.spaceBattle.command;

import com.otus.spaceBattle.dto.UObject;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LongOperationStopCommand implements Command {
  @NonNull
  private final UObject stopableObject;
  @NonNull
  private final String stopableCommand;

  @Override
  public void execute() {
    ((Stopable) stopableObject.getParam("Command." + stopableCommand))
        .stop();
  }
}
