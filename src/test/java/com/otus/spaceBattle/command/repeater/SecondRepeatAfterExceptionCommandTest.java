package com.otus.spaceBattle.command.repeater;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import com.otus.spaceBattle.command.Command;
import com.otus.spaceBattle.exception.CommandException;
import com.otus.spaceBattle.exception.RepeatCommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SecondRepeatAfterExceptionCommandTest {

    @Mock
    private Command innerCommand;

    private SecondRepeatAfterExceptionCommand afterExceptionCommand;

    @BeforeEach
    public void beforeEach() {
        afterExceptionCommand = new SecondRepeatAfterExceptionCommand(innerCommand);
    }

    @Test
    void execute_run_inner_command() {
        afterExceptionCommand.execute();
        verify(innerCommand).execute();
    }

    @Test
    void execute_catchCommonCommandExceptionAndThrowRepeatExc() {
        doThrow(new CommandException()).when(innerCommand).execute();

        Assertions.assertThrows(RepeatCommandException.class, () -> afterExceptionCommand.execute());
    }
}