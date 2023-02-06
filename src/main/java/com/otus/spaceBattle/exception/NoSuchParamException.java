package com.otus.spaceBattle.exception;

public class NoSuchParamException extends CommandException {

  public NoSuchParamException(long objectId, String paramKey) {
    super(String.format("У объекта %d не найден параметр %s", objectId, paramKey));
  }

}
