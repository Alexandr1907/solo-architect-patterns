package com.otus.spaceBattle.command.repeater;

import com.otus.spaceBattle.command.Command;
import lombok.NonNull;

public class SecondRepeatAfterExceptionCommand extends RepeaterAfterExceptionCommand {

  public SecondRepeatAfterExceptionCommand(@NonNull Command command) {
    super(command);
  }
}
