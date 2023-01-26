package com.otus.spaceBattle.exceptionHandler.impl;

import com.otus.spaceBattle.command.Command;
import com.otus.spaceBattle.command.LogExceptionCommand;
import com.otus.spaceBattle.exceptionHandler.CommandExceptionHandler;
import java.util.Queue;

public class LogWriterExceptionHandler implements CommandExceptionHandler {

  @Override
  public void handle(Exception exception, Command command, Queue<Command> commandQueue) {
    commandQueue.add(new LogExceptionCommand(exception));
  }
}
