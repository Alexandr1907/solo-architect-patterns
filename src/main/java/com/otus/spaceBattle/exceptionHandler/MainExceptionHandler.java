package com.otus.spaceBattle.exceptionHandler;

import com.otus.spaceBattle.command.Command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;

@Slf4j
public class MainExceptionHandler implements CommandExceptionHandler {

  @NonNull
  private final Map<
      Pair<Class<? extends Exception>, Class<? extends Command>>,
      List<CommandExceptionHandler>
      > exceptionAndCommand2Handlers = new HashMap<>();


  @Override
  public void handle(Exception exception, Command executedCommand, Queue<Command> queue) {
    Class<? extends Exception> eClass = exception.getClass();
    Class<? extends Command> cClass = executedCommand.getClass();

    List<List<CommandExceptionHandler>> existHandlersByGrop = Stream.of(
            exceptionAndCommand2Handlers.get(Pair.of(eClass, cClass)),
            exceptionAndCommand2Handlers.get(Pair.of(null, cClass)),
            exceptionAndCommand2Handlers.get(Pair.of(eClass, null))
        )
        .filter(Objects::nonNull)
        .collect(Collectors.toList());

    if (existHandlersByGrop.isEmpty()) {
      exception.printStackTrace();
    } else {
      for (List<CommandExceptionHandler> handlers : existHandlersByGrop) {
        handlers.forEach(h -> h.handle(exception, executedCommand, queue));
      }
    }
  }

  public boolean registerHandler(Class<? extends Exception> exceptionClass, Class<? extends Command> commandClass,
      CommandExceptionHandler handler) {
    Pair<Class<? extends Exception>, Class<? extends Command>> key = Pair.of(exceptionClass, commandClass);

    List<CommandExceptionHandler> handlers = exceptionAndCommand2Handlers.get(key);
    if (handlers == null) {
      List<CommandExceptionHandler> newHandlers = new ArrayList<>();
      newHandlers.add(handler);
      exceptionAndCommand2Handlers.put(key, newHandlers);
    } else if (handlers.stream().anyMatch(h->h.getClass().equals(handler.getClass()))) {
      log.warn("Handler '{}' already register for exception '{}' and command '{}'",
          handler.getClass(), exceptionClass, commandClass);
      return false;
    } else {
      handlers.add(handler);
    }
    return true;
  }

}
