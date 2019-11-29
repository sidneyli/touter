package com.touter.distributer.Model;

/** Represents the snowflake id interface. */
public interface SnowflakeId {

  /**
   * Converts the snowflake id into long for storage.
   *
   * @return Long typed id.
   */
  long toLong();
}
