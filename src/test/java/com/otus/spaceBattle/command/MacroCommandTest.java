package com.otus.spaceBattle.command;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MacroCommandTest {

  protected Command[] commands;
  protected MacroCommand macro;

  @BeforeEach
  void setUp() {
    commands = new Command[]{Mockito.mock(Command.class), Mockito.mock(Command.class),
        Mockito.mock(Command.class), Mockito.mock(Command.class)};
    macro = new MacroCommand(Arrays.asList(commands));
  }

  @Test
  void execute_shouldCallAllCommandInOrder() {
    //when
    macro.execute();

    //then
    InOrder inOrder = Mockito.inOrder((Object) commands);
    for (Command command : commands) {
      inOrder.verify(command).execute();
    }
  }

  @Test
  void execute_shouldThrowExceptionFromInnerCommandAndStopCallCommand() {
    //given
    int wrongCommandNumber = 1;
    Mockito.doThrow(new RuntimeException()).when(commands[wrongCommandNumber]).execute();
    //when
    Assertions.assertThrows(RuntimeException.class, () -> macro.execute());

    //then
    InOrder inOrder = Mockito.inOrder((Object) commands);
    for (int i = 0; i < commands.length; i++) {
      if (i <= wrongCommandNumber) {
        inOrder.verify(commands[i]).execute();
      } else {
        inOrder.verify(commands[i], Mockito.never()).execute();
      }
    }
  }

  @Test
  void execute_shouldThrowNPEIfCommandNull() {
    //given
    List<Command> commandsWithNull = Arrays.asList(commands[0], null);

    //when
    Assertions.assertThrows(NullPointerException.class, () -> new MacroCommand(commandsWithNull));
  }
}