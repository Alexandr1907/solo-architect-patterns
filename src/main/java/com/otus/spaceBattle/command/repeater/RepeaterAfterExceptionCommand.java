package com.otus.spaceBattle.command.repeater;

import com.otus.spaceBattle.command.Command;
import com.otus.spaceBattle.excepton.CommandException;
import com.otus.spaceBattle.excepton.RepeatCommandException;
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