package com.otus.spaceBattle.command;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;

import com.otus.spaceBattle.dto.UObject;
import java.util.Queue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RepeaterCommandTest {

  private RepeaterCommand command;
  @Mock
  private UObject uObject;
  @Mock
  private Queue<Command> queue;
  @Mock
  private Command repeatableCommand;

  @BeforeEach
  public void setUp() {
    command = new RepeaterCommand(uObject);
  }

  @Test
  void execute_shouldCallRepeatableCommandAndAddHimselfInQueue() {
    //given
    Mockito.when(uObject.getParam(Mockito.anyString())).thenReturn(queue);
    command.setRepeatebleCommand(repeatableCommand);

    //when
    command.execute();

    //then
    Mockito.verify(repeatableCommand).execute();
    Mockito.verify(queue).add(command);
  }

  @Test
  void execute_NpeIfRepeatableCommandNotset() {
    //when - then NPE
    Assertions.assertThrows(NullPointerException.class, () -> command.execute());
  }

  @Test
  void executeAfterCallStopShouldDoNothing() {
    //given
    command.setRepeatebleCommand(repeatableCommand);
    command.stop();

    //when
    command.execute();

    //then
    Mockito.verify(repeatableCommand, never()).execute();
    Mockito.verify(queue, never()).add(any());
  }
}