package com.otus.spaceBattle.runner;

import com.otus.spaceBattle.command.Command;
import com.otus.spaceBattle.command.repeater.FirstRepeatAfterExceptionCommand;
import com.otus.spaceBattle.command.repeater.SecondRepeatAfterExceptionCommand;
import com.otus.spaceBattle.exceptionHandler.MainExceptionHandler;
import com.otus.spaceBattle.exceptionHandler.impl.LogWriterExceptionHandler;
import com.otus.spaceBattle.exceptionHandler.impl.repeater.FirstRepeaterExceptionHandler;
import com.otus.spaceBattle.exceptionHandler.impl.repeater.SecondRepeaterExceptionHandler;
import com.otus.spaceBattle.exception.CommandException;
import com.otus.spaceBattle.exception.RepeatCommandException;
import java.util.Queue;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommandRunner {

    @NonNull
    private final Queue<Command> commandQueue;
    @NonNull
    private final MainExceptionHandler handler;

    private boolean isDoubleRepeatStrategy = false;

    public void run() {
        if (isDoubleRepeatStrategy) { //todo remove by IoC
            fillExceptionHandler_doubleRepeatAndLogStrategy();
        } else {
            fillExceptionHandler_oneRepeatAndLogStrategy();
        }

        Command command;
        while ((command = commandQueue.poll()) != null) {
            try {
                command.execute();
            } catch (Exception e) {
                handler.handle(e, command, commandQueue);
            }
        }
    }

    private void fillExceptionHandler_oneRepeatAndLogStrategy() { //todo remove by IoC
        handler.registerHandler(
            CommandException.class,
            null,
            new FirstRepeaterExceptionHandler()
        );

        handler.registerHandler(
            RepeatCommandException.class,
            FirstRepeatAfterExceptionCommand.class,
            new LogWriterExceptionHandler()
        );
    }

    private void fillExceptionHandler_doubleRepeatAndLogStrategy() { //todo remove by IoC
        handler.registerHandler(
            CommandException.class,
            null,
            new FirstRepeaterExceptionHandler()
        );

        handler.registerHandler(
            RepeatCommandException.class,
            FirstRepeatAfterExceptionCommand.class,
            new SecondRepeaterExceptionHandler()
        );

        handler.registerHandler(
            RepeatCommandException.class,
            SecondRepeatAfterExceptionCommand.class,
            new LogWriterExceptionHandler()
        );
    }

}
