package com.otus.spaceBattle.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.otus.spaceBattle.TestData;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

class LogExceptionCommandTest {


    @Test
    void execute() {
        System.setProperty("logback.configurationFile", "logbackLogExceptionComandConfig.xml");
        Logger logger = (Logger) LoggerFactory.getLogger(LogExceptionCommand.class);

        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();

        logger.addAppender(listAppender);
        LogExceptionCommand command = new LogExceptionCommand(new RuntimeException(TestData.ERROR_MSG));
        command.execute();
        List<ILoggingEvent> logsList = listAppender.list;

        assertEquals(1, logsList.size());
        assertEquals(Level.ERROR, logsList.get(0).getLevel());
        assertTrue(logsList.get(0).getMessage().contains(TestData.ERROR_MSG));

        logger.detachAndStopAllAppenders();
    }
}