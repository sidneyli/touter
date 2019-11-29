package com.touter.distributer.Config;

import com.touter.distributer.Generator.DefaultSnowflakeIdGeneratorImpl;
import com.touter.distributer.Generator.SnowflakeIdGenerator;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/** Represents the snowflake id configurations. */
@Configuration
@ConfigurationProperties(prefix = "snowflake", ignoreUnknownFields = false)
@Data
public class SnowflakeIdConfiguration {

  public static final int TOTAL_BITS = 64;
  public static final int EPOCH_BITS = 42;
  public static final int NODE_ID_BITS = 4;
  public static final int SEQUENCE_BITS = 18;

  public static final int MAX_NODE_ID = ~(-1 << SnowflakeIdConfiguration.NODE_ID_BITS);
  public static final int MAX_SEQUENCE = ~(-1 << SnowflakeIdConfiguration.SEQUENCE_BITS);

  private int nodeId;

  /**
   * Represents the bean of snowflake generator.
   *
   * @return Snowflake id generator.
   */
  @Bean
  public SnowflakeIdGenerator getGenerator() {
    return new DefaultSnowflakeIdGeneratorImpl(this);
  }

  /**
   * Does property check.
   */
  @PostConstruct
  public void init() {
    if (this.nodeId < 0 || this.nodeId > SnowflakeIdConfiguration.MAX_NODE_ID) {
      throw new IllegalArgumentException("Wrong node id.");
    }
  }
}
