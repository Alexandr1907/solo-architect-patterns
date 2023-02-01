package com.otus.spaceBattle.exceptionHandler;

import static org.mockito.ArgumentMatchers.any;

import com.otus.spaceBattle.command.Command;
import com.otus.spaceBattle.command.MoveCommand;
import com.otus.spaceBattle.exceptionHandler.impl.LogWriterExceptionHandler;
import com.otus.spaceBattle.exceptionHandler.impl.repeater.FirstRepeaterExceptionHandler;
import java.util.LinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MainExceptionHandlerTest {


  @Test
  void registerHandler_addNewHandler() {
    //given
    MainExceptionHandler mainHandler = new MainExceptionHandler();

    //when
    boolean isSuccessFirst = mainHandler
        .registerHandler(RuntimeException.class, MoveCommand.class, new LogWriterExceptionHandler());
    boolean isSuccessSecond = mainHandler
        .registerHandler(RuntimeException.class, MoveCommand.class, new FirstRepeaterExceptionHandler());
    boolean isSuccessDuplicate = mainHandler
        .registerHandler(RuntimeException.class, MoveCommand.class, new FirstRepeaterExceptionHandler());

    //then
    Assertions.assertTrue(isSuccessFirst);
    Assertions.assertTrue(isSuccessSecond);
    Assertions.assertFalse(isSuccessDuplicate);
  }

  @Test
  void handle_shouldCall3TypesOfHandlers() {
    //given
    NullPointerException exception = Mockito.spy(NullPointerException.class);
    Command executedCommand = Mockito.mock(Command.class);
    LinkedList<Command> queue = new LinkedList<>();
    MainExceptionHandler mainHandler = new MainExceptionHandler();

    CommandExceptionHandler h1 = Mockito.mock(CommandExceptionHandler.class);
    mainHandler.registerHandler(exception.getClass(), executedCommand.getClass(), h1);

    CommandExceptionHandler h2 = Mockito.mock(CommandExceptionHandler.class);
    mainHandler.registerHandler(null, executedCommand.getClass(), h2);

    CommandExceptionHandler h3 = Mockito.mock(CommandExceptionHandler.class);
    mainHandler.registerHandler(exception.getClass(), null, h3);

    CommandExceptionHandler extraHandler = Mockito.mock(CommandExceptionHandler.class);
    mainHandler.registerHandler(IndexOutOfBoundsException.class, executedCommand.getClass(), extraHandler);

    //when
    mainHandler.handle(exception, executedCommand, queue);

    //then
    Mockito.verify(h1).handle(exception, executedCommand, queue);
    Mockito.verify(h2).handle(exception, executedCommand, queue);
    Mockito.verify(h3).handle(exception, executedCommand, queue);
    Mockito.verify(extraHandler, Mockito.never()).handle(any(), any(), any());
    Mockito.verify(exception, Mockito.never()).printStackTrace();
  }

  @Test
  void handle_ifNoHandlers_shouldPrintStackTraceInConsole() {
    //given
    NullPointerException exception = Mockito.spy(NullPointerException.class);
    Command executedCommand = Mockito.mock(Command.class);
    LinkedList<Command> queue = new LinkedList<>();
    MainExceptionHandler mainHandler = new MainExceptionHandler();

    CommandExceptionHandler h1 = Mockito.mock(CommandExceptionHandler.class);
    mainHandler.registerHandler(ClassCastException.class, executedCommand.getClass(), h1);

    CommandExceptionHandler h2 = Mockito.mock(CommandExceptionHandler.class);
    mainHandler.registerHandler(null, Command.class, h2);

    CommandExceptionHandler h3 = Mockito.mock(CommandExceptionHandler.class);
    mainHandler.registerHandler(ClassCastException.class, null, h3);

    //when
    mainHandler.handle(exception, executedCommand, queue);

    //then
    Mockito.verify(h1, Mockito.never()).handle(any(), any(), any());
    Mockito.verify(h2, Mockito.never()).handle(any(), any(), any());
    Mockito.verify(h3, Mockito.never()).handle(any(), any(), any());
    Mockito.verify(exception).printStackTrace();
  }

}