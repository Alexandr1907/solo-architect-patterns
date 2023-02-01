package com.otus.spaceBattle.command.repeater;

import com.otus.spaceBattle.command.Command;
import lombok.NonNull;

public class FirstRepeatAfterExceptionCommand extends RepeaterAfterExceptionCommand {

  public FirstRepeatAfterExceptionCommand(@NonNull Command command) {
    super(command);
  }
}
