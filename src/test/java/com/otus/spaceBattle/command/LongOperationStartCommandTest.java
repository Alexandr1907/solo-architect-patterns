package com.otus.spaceBattle.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LongOperationStartCommandTest {

  private LongOperationStartCommand command;
  @Mock
  private RepeaterCommand repeaterCommand;
  @Mock
  private Command longCommand;

  @BeforeEach
  void setUp() {
    command = new LongOperationStartCommand(repeaterCommand, longCommand);
  }

  @Test
  void execute_shouldSetLongCommandInRepeaterAndStartRepeater() {
    command.execute();

    Mockito.verify(repeaterCommand).setRepeatebleCommand(longCommand);
    Mockito.verify(repeaterCommand).execute();
  }

  @Test
  void createCommandWithNullArgs_shouldThrowNPE() {
    Assertions.assertThrows(NullPointerException.class, () ->
        new LongOperationStartCommand(null, longCommand));
    Assertions.assertThrows(NullPointerException.class, () ->
        new LongOperationStartCommand(repeaterCommand, null));
  }
}