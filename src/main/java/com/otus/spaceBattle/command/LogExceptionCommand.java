package com.otus.spaceBattle.command;

import java.util.Arrays;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class LogExceptionCommand implements Command {
    @NonNull
    private final Exception exceptionFromCommand;

    @Override
    public void execute() {
        log.error(exceptionFromCommand.getMessage() + ": " + Arrays.toString(exceptionFromCommand.getStackTrace()));
    }
}
