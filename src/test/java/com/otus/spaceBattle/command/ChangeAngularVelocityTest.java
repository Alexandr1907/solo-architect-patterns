package com.otus.spaceBattle.command;

import static com.otus.spaceBattle.TestData.*;

import com.otus.spaceBattle.action.RotationVelocityChangeable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ChangeAngularVelocityTest {

    @Mock
    private RotationVelocityChangeable rotationVelocityChangeable;

    private RotationVelocityChange rotationVelocityChange;

    @BeforeEach
    public void setup(){
        rotationVelocityChange = new RotationVelocityChange(rotationVelocityChangeable);
    }

    @Test
    void changeRotationSpeed(){

        Mockito.when(rotationVelocityChangeable.getAngularVelocity()).thenReturn(STARTED_ROTATION_VELOCITY);
        Mockito.when(rotationVelocityChangeable.getAngularVelocityChange()).thenReturn(CHANGE_ROTATION_VELOCITY);

        rotationVelocityChange.execute();

        Mockito.verify(rotationVelocityChangeable).getAngularVelocity();
        Mockito.verify(rotationVelocityChangeable).getAngularVelocityChange();

        Mockito.verify(rotationVelocityChangeable).setAngularVelocity(Mockito.eq(FINAL_ROTATION_VELOCITY));

    }
}
