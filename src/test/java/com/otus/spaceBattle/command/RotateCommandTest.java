package com.otus.spaceBattle.command;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.otus.spaceBattle.action.Rotatable;
import com.otus.spaceBattle.dto.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RotateCommandTest {
    @Mock
    private Rotatable rotatable;
    @Mock
    private Direction direction;

    private RotateCommand rotateCommand;

    @BeforeEach
    void beforeEach() {
        rotateCommand = new RotateCommand(rotatable);
    }

    @Test
    @DisplayName("Npe check test")
    void test1() {
        assertThrows(NullPointerException.class, () -> new RotateCommand(null));
    }

    @Test
    @DisplayName("Rotate rotatable check")
    void test2() {
        when(rotatable.getDirection()).thenReturn(direction);
        when(direction.next(any(Integer.class))).thenReturn(direction);
        rotateCommand.execute();

        verify(rotatable, times(1)).setDirection(direction);
    }

    @Test
    @DisplayName("Test get direction is null")
    void test4() {
        when(rotatable.getDirection()).thenReturn(null);

        assertThrows(RuntimeException.class, () -> rotateCommand.execute());
    }
}