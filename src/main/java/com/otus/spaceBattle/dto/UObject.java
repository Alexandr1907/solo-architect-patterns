package com.otus.spaceBattle.dto;

import lombok.NonNull;

public interface UObject {

  long getId();

  boolean putParam(String key, Object value);

  @NonNull Object getParam(String key);
}
