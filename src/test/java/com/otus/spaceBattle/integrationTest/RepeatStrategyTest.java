package com.otus.spaceBattle.integrationTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import com.otus.spaceBattle.TestData;
import com.otus.spaceBattle.command.Command;
import com.otus.spaceBattle.command.repeater.FirstRepeatAfterExceptionCommand;
import com.otus.spaceBattle.command.repeater.SecondRepeatAfterExceptionCommand;
import com.otus.spaceBattle.exceptionHandler.MainExceptionHandler;
import com.otus.spaceBattle.exception.CommandException;
import com.otus.spaceBattle.runner.CommandRunner;
import java.util.Arrays;
import java.util.LinkedList;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

public class RepeatStrategyTest {


  @Test
  void run_shouldTry1RepeatAndThenLog() {
    //given

    Command c1 = Mockito.mock(Command.class);
    CommandException exception = new CommandException(TestData.ERROR_MSG);
    Mockito.doThrow(exception).when(c1).execute();

    Command c2 = Mockito.mock(Command.class);

    LinkedList<Command> commandQueue = new LinkedList<>(Arrays.asList(c1, c2));

    MainExceptionHandler handler = Mockito.spy(MainExceptionHandler.class);
    CommandRunner cr = new CommandRunner(commandQueue, handler);
    ReflectionTestUtils.setField(cr, "isDoubleRepeatStrategy", false);

    //when
    cr.run();

    //then
    InOrder inOrder = Mockito.inOrder(c1, c2, handler);
    inOrder.verify(c1).execute();
    inOrder.verify(handler).handle(exception, c1, commandQueue);
    inOrder.verify(c2).execute();
    inOrder.verify(c1).execute();
    inOrder.verify(handler).handle(any(), any(FirstRepeatAfterExceptionCommand.class), eq(commandQueue));
    inOrder.verify(c1, Mockito.never()).execute();
  }

  @Test
  void run_shouldTry2RepeatAndThenLog() {
    //given

    Command c1 = Mockito.mock(Command.class);
    CommandException exception = new CommandException(TestData.ERROR_MSG);
    Mockito.doThrow(exception).when(c1).execute();

    Command c2 = Mockito.mock(Command.class);

    LinkedList<Command> commandQueue = new LinkedList<>(Arrays.asList(c1, c2));

    MainExceptionHandler handler = Mockito.spy(MainExceptionHandler.class);
    CommandRunner cr = new CommandRunner(commandQueue, handler);
    ReflectionTestUtils.setField(cr, "isDoubleRepeatStrategy", true);

    //when
    cr.run();

    //then
    InOrder inOrder = Mockito.inOrder(c1, c2, handler);
    inOrder.verify(c1).execute();
    inOrder.verify(handler).handle(exception, c1, commandQueue);
    inOrder.verify(c2).execute();
    inOrder.verify(c1).execute();
    inOrder.verify(handler).handle(any(), any(FirstRepeatAfterExceptionCommand.class), eq(commandQueue));
    inOrder.verify(c1).execute();
    inOrder.verify(handler).handle(any(), any(SecondRepeatAfterExceptionCommand.class), eq(commandQueue));
    inOrder.verify(c1, Mockito.never()).execute();
  }



}
