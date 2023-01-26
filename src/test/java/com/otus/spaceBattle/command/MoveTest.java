package com.otus.spaceBattle.command;

import static com.otus.spaceBattle.TestData.END_POSITION;
import static com.otus.spaceBattle.TestData.START_POSITION;
import static com.otus.spaceBattle.TestData.VELOCITY;

import com.otus.spaceBattle.action.Movable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MoveTest {
  @Mock
  private Movable movable;

  private Move move;


  @BeforeEach
  public void setup() {
    move = new Move(movable);
  }

  @Test
  void execute_shouldAppendCoords() {
    //given
    Mockito.when(movable.getPosition()).thenReturn(START_POSITION);
    Mockito.when(movable.getVelocity()).thenReturn(VELOCITY);

    //when
    move.execute();

    //then
    Mockito.verify(movable).getPosition();
    Mockito.verify(movable).getVelocity();

    Mockito.verify(movable).setPosition(Mockito.eq(END_POSITION));
  }

  @Test
  void execute_shouldThrowException_ifGetPositionNotAvailable() {
    //given
    Mockito.when(movable.getPosition()).thenThrow(RuntimeException.class);

    //when - then exception
    Assertions.assertThrows(RuntimeException.class, () -> move.execute());
  }

  @Test
  void execute_shouldThrowException_ifGetVelocityNotAvailable() {
    //given
    Mockito.when(movable.getVelocity()).thenThrow(RuntimeException.class);

    //when - then exception
    Assertions.assertThrows(RuntimeException.class, () -> move.execute());
  }

  @Test
  void execute_shouldThrowException_ifSetPositionNotAvailable() {
    //given
    Mockito.when(movable.getPosition()).thenReturn(START_POSITION);
    Mockito.when(movable.getVelocity()).thenReturn(VELOCITY);
    Mockito.doThrow(new RuntimeException()).when(movable).setPosition(Mockito.any());

    //when - then exception
    Assertions.assertThrows(RuntimeException.class, () -> move.execute());
  }

  @Test
  void execute_shouldThrowException_ifPositionNull() {
    //given
    Mockito.when(movable.getVelocity()).thenReturn(VELOCITY);

    //when - then exception
    Assertions.assertThrows(RuntimeException.class, () -> move.execute());
  }

  @Test
  void execute_shouldThrowException_ifPVelocityNull() {
    //given
    Mockito.when(movable.getPosition()).thenReturn(START_POSITION);

    //when - then exception
    Assertions.assertThrows(RuntimeException.class, () -> move.execute());
  }


}