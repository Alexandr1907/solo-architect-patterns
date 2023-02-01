package com.otus.spaceBattle.command;

import com.otus.spaceBattle.dto.UObject;
import com.otus.spaceBattle.util.Prefix;
import java.util.Queue;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RepeaterCommand implements Command, Stopable {
  private boolean isStopped = false;
  private final UObject uObject;
  private Command repeatebleCommand;

  @Override
  public void execute() {
    if (!isStopped) {
      repeatebleCommand.execute();
      Queue<Command> queue = (Queue<Command>) uObject.getParam(Prefix.ENVIRONMENT + "CommandQueue");
      queue.add(this);
    }
  }

  @Override
  public void stop() {
    isStopped = true;
  }

  public void setRepeatebleCommand(@NonNull Command repeatebleCommand) {
    this.repeatebleCommand = repeatebleCommand;
  }
}
