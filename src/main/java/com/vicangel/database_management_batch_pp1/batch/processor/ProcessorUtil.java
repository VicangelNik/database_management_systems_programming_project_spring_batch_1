package com.vicangel.database_management_batch_pp1.batch.processor;

import jakarta.annotation.Nullable;

final class ProcessorUtil {

  private ProcessorUtil() {
    throw new IllegalStateException("Utility class");
  }

  static String normalizeSpace(@Nullable final String itemValue) {
    if (itemValue == null) return null;
    return itemValue.trim().replaceAll(" +", " ");
  }
}
