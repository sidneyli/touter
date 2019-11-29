package com.touter.distributer.Generator;

import reactor.core.publisher.Mono;

/** Represents the snowflake generator interface. */
public interface SnowflakeIdGenerator {

  /**
   * Generates the next snowflake id.
   * Note: be careful about thread safety.
   *
   * @return Snowflake id.
   */
  long nextId();
}
