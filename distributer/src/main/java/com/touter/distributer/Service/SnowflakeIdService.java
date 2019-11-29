package com.touter.distributer.Service;

import reactor.core.publisher.Mono;

/** Represents the snowflake id service interface. */
public interface SnowflakeIdService {

  /**
   * Generates the next id.
   *
   * @return Id.
   */
  Mono<Long> nextId();
}
