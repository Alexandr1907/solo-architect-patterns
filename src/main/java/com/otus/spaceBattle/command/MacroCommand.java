package com.otus.spaceBattle.command;

import com.otus.spaceBattle.exception.CommandException;
import java.util.Collections;
import java.util.List;
import lombok.NonNull;

public class MacroCommand implements Command {

  final List<Command> commands;

  public MacroCommand(@NonNull List<Command> commands) {
    if (commands.contains(null)) {
      throw new NullPointerException();
    }
    this.commands = Collections.unmodifiableList(commands);
  }

  @Override
  public void execute() {
    try {
      for (Command command : commands) {
        command.execute();
      }
    } catch (Exception e) {
      throw new CommandException("Process macro command was finished with exception: " + e.getMessage(), e);
    }
  }
}
