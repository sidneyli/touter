package com.touter.distributer.Controller;

import com.touter.distributer.Service.SnowflakeIdService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

/** Represents the rsocket controller to get a distributed id. */
@Controller
public class SnowflakeIdController {

  private final SnowflakeIdService snowflakeIdService;

  /**
   * Constructs the snowflake id controller.
   *
   * @param snowflakeIdService Snowflake id service.
   */
  public SnowflakeIdController(SnowflakeIdService snowflakeIdService) {
    this.snowflakeIdService = snowflakeIdService;
  }

  /**
   * Retrieves the next snowflake id.
   *
   * @return Snowflake id.
   */
  @MessageMapping("getNextId")
  public Mono<Long> getNextId() {
    return this.snowflakeIdService.nextId();
  }
}
