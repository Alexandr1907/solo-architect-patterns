package com.otus.spaceBattle.command;

import static com.otus.spaceBattle.TestData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.otus.spaceBattle.action.SpeedChangeable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ChangeSpeedTest {

    @Mock
    private SpeedChangeable speedChangeable;

    private SpeedChangeCommand speedChangeCommand;

    @BeforeEach
    public void setup() {
        speedChangeCommand = new SpeedChangeCommand(speedChangeable);
    }

    @Test
    void shouldThrowExceptionIfSpeedChangeableNull() {
        assertThrows(NullPointerException.class, () -> new SpeedChangeCommand(null));
    }

    @Test
    void changeSpeed() {
        //given
        Mockito.when(speedChangeable.getSpeed()).thenReturn(STARTED_SPEED);
        Mockito.when(speedChangeable.getSpeedChange()).thenReturn(CHANGED_SPEED);

        //when
        speedChangeCommand.execute();

        //then
        Mockito.verify(speedChangeable).getSpeed();
        Mockito.verify(speedChangeable).getSpeedChange();

        Mockito.verify(speedChangeable).setSpeed(Mockito.eq(FINAL_SPEED));
    }


    @Test
    void shouldThrowExceptionIfGetSpeedChangeNotAvailable() {
        //given
        Mockito.when(speedChangeable.getSpeedChange()).thenThrow(RuntimeException.class);

        //when - then exception
        Assertions.assertThrows(RuntimeException.class, () -> speedChangeCommand.execute());
    }

    @Test
    void executeShouldThrowExceptionIfGetSpeedNotAvailable() {
        //given
        Mockito.when(speedChangeable.getSpeed()).thenThrow(RuntimeException.class);

        //when - then exception
        Assertions.assertThrows(RuntimeException.class, () -> speedChangeCommand.execute());
    }

    @Test
    void executeShouldThrowExceptionIfSetSpeedNotAvailable() {
        //given
        Mockito.when(speedChangeable.getSpeed()).thenReturn(STARTED_SPEED);
        Mockito.when(speedChangeable.getSpeedChange()).thenReturn(CHANGED_SPEED);
        Mockito.doThrow(new RuntimeException()).when(speedChangeable).setSpeed(Mockito.anyInt());

        //when - then exception
        Assertions.assertThrows(RuntimeException.class, () -> speedChangeCommand.execute());
    }

}