package com.otus.spaceBattle.command;

import com.otus.spaceBattle.action.Rotatable;
import java.util.Objects;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Rotate {
    private final @NonNull Rotatable rotatable;

    public void execute() {
        rotatableDirectionNullCheck();
        rotatable.setDirection(rotatable.getDirection().next(rotatable.getAngularVelocity()));
    }

    private void rotatableDirectionNullCheck() {
        if (Objects.isNull(rotatable.getDirection())) {
            throw new RuntimeException("Direction in rotatable can't be null");
        }
    }
}
