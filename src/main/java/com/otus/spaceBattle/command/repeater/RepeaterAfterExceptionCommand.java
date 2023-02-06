package com.otus.spaceBattle.command.repeater;

import com.otus.spaceBattle.command.Command;
import com.otus.spaceBattle.exception.CommandException;
import com.otus.spaceBattle.exception.RepeatCommandException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class RepeaterAfterExceptionCommand implements Command {
  @NonNull
  private final Command command;

  @Override
  public void execute() {
    try {
      command.execute();
    } catch (CommandException exception) {
      throw new RepeatCommandException(exception);
    }
  }
}