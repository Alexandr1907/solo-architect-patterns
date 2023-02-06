package com.otus.spaceBattle.command;

import com.otus.spaceBattle.dto.UObject;
import com.otus.spaceBattle.util.Prefix;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LongOperationStopCommandTest {

  private LongOperationStopCommand command;
  @Mock
  private UObject stopableObject;
  @Mock
  private Stopable stopable;

  private final String stopableCommandName = "move";

  @BeforeEach
  void setUp() {

    command = new LongOperationStopCommand(stopableObject, stopableCommandName);
  }

  @Test
  void execute_shouldSetLongCommandInRepeaterAndStartRepeater() {
    Mockito.when(stopableObject.getParam(Mockito.anyString())).thenReturn(stopable);

    command.execute();

    Mockito.verify(stopableObject).getParam(Prefix.COMMAND + stopableCommandName);
    Mockito.verify(stopable).stop();
  }

  @Test
  void createCommandWithNullArgs_shouldThrowNPE() {
    Assertions.assertThrows(NullPointerException.class, () ->
        new LongOperationStopCommand(null, stopableCommandName));
    Assertions.assertThrows(NullPointerException.class, () ->
        new LongOperationStopCommand(stopableObject, null));
  }
}