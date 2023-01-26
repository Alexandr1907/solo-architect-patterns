package com.otus.spaceBattle.exceptionHandler.impl.repeater;

import com.otus.spaceBattle.command.Command;
import com.otus.spaceBattle.command.repeater.FirstRepeatAfterExceptionCommand;
import com.otus.spaceBattle.command.repeater.SecondRepeatAfterExceptionCommand;
import com.otus.spaceBattle.exceptionHandler.CommandExceptionHandler;
import java.util.Queue;

public class SecondRepeaterExceptionHandler implements CommandExceptionHandler {

  @Override
  public void handle(Exception exception, Command command, Queue<Command> commandQueue) {
    commandQueue.add(new SecondRepeatAfterExceptionCommand(command));
  }
}
