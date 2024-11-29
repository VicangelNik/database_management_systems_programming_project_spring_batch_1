package com.vicangel.database_management_batch_pp1.batch.config;

import org.springframework.batch.item.file.transform.FieldSet;

final class BatchUtil {

  private BatchUtil() {
    throw new IllegalStateException("Utility class");
  }

  static Integer readIntValue(FieldSet fieldSet, String columnName) {
    try {
      return fieldSet.readInt(columnName);
    } catch (NumberFormatException nfe) {
      return null;
    }
  }

  static Short readShortValue(FieldSet fieldSet, String columnName) {
    try {
      return fieldSet.readShort(columnName);
    } catch (NumberFormatException nfe) {
      return null;
    }
  }

  static Character readCharacterValue(FieldSet fieldSet, String columnName) {
    try {
      return fieldSet.readChar(columnName);
    } catch (IllegalArgumentException e) {
      if (e.getMessage().trim().equals("Cannot convert field value '' to char.")) {
        return null;
      }
      throw e;
    }
  }
}
