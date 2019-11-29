package com.touter.distributer.Service;

import com.touter.distributer.Generator.SnowflakeIdGenerator;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/** Represents the snowflake id service implementation. */
@Service
public class DefaultSnowflakeIdServiceImpl implements SnowflakeIdService {

  private final SnowflakeIdGenerator snowflakeIdGenerator;

  /**
   * Constructs the snowflake id service.
   *
   * @param snowflakeIdGenerator Snowflake id generator.
   */
  public DefaultSnowflakeIdServiceImpl(SnowflakeIdGenerator snowflakeIdGenerator) {
    this.snowflakeIdGenerator = snowflakeIdGenerator;
  }

  /**
   * Generates the next id.
   *
   * @return Id.
   */
  @Override
  public Mono<Long> nextId() {
    return Mono.just(this.snowflakeIdGenerator.nextId()).subscribeOn(Schedulers.single());
  }
}
