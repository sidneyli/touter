package com.touter.distributer.Generator;

/** Represents the snowflake generator interface. */
public interface SnowflakeIdGenerator {

  /**
   * Generates the next snowflake id. Note: be careful about thread safety.
   *
   * @return Snowflake id.
   */
  long nextId();
}
