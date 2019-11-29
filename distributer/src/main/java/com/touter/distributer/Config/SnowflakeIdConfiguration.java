package com.touter.distributer.Config;

import com.touter.distributer.Generator.DefaultSnowflakeIdGeneratorImpl;
import com.touter.distributer.Generator.SnowflakeIdGenerator;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Represents the snowflake id configurations. */
@Configuration
@Data
public class SnowflakeIdConfiguration {

  public static final int TOTAL_BITS = 64;
  public static final int EPOCH_BITS = 42;
  public static final int NODE_ID_BITS = 4;
  public static final int SEQUENCE_BITS = 18;

  public static final int MAX_NODE_ID = ~(-1 << SnowflakeIdConfiguration.NODE_ID_BITS);
  public static final int MAX_SEQUENCE = ~(-1 << SnowflakeIdConfiguration.SEQUENCE_BITS);

  private final int nodeId = 1;

  /**
   * Represents the bean of snowflake generator.
   *
   * @return Snowflake id generator.
   */
  @Bean
  public SnowflakeIdGenerator getGenerator() {
    return new DefaultSnowflakeIdGeneratorImpl(this);
  }
}
