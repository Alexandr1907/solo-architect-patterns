package com.otus.spaceBattle.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MoveWithBurnFuelCommandTest extends MacroCommandTest {

  @Mock
  private CheckFuelCommand checkFuelCommand;
  @Mock
  private MoveCommand moveCommand;
  @Mock
  private BurnFuelCommand burnFuelCommand;


  @Override
  @BeforeEach
  public void setUp() {
    commands = new Command[]{checkFuelCommand, moveCommand, burnFuelCommand};
    macro = new MoveWithBurnFuelCommand(checkFuelCommand, moveCommand, burnFuelCommand);
  }

  @Test
  void execute_throwNpeIfNullCommand() {
    Assertions.assertThrows(NullPointerException.class, () ->
        new MoveWithBurnFuelCommand(null, moveCommand, burnFuelCommand));
    Assertions.assertThrows(NullPointerException.class, () ->
        new MoveWithBurnFuelCommand(checkFuelCommand, null, burnFuelCommand));
    Assertions.assertThrows(NullPointerException.class, () ->
        new MoveWithBurnFuelCommand(checkFuelCommand, moveCommand, null));
  }

  @Test
  void execute_shouldCallCommandInOrder() {
    //when
    macro.execute();

    //then
    InOrder inOrder = Mockito.inOrder(checkFuelCommand, moveCommand, burnFuelCommand);
    inOrder.verify(checkFuelCommand).execute();
    inOrder.verify(moveCommand).execute();
    inOrder.verify(burnFuelCommand).execute();
  }
}