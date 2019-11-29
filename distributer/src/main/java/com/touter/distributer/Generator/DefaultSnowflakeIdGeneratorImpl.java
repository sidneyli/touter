package com.touter.distributer.Generator;

import com.touter.distributer.Config.SnowflakeIdConfiguration;
import com.touter.distributer.Model.DefaultSnowflakeIdImpl;

/** Represents a default snowflake id generator. */
public class DefaultSnowflakeIdGeneratorImpl implements SnowflakeIdGenerator {

  private volatile long lastTimestamp = -1L;
  private volatile int sequence = 0;
  private SnowflakeIdConfiguration configuration;

  /**
   * Constructs the default generator.
   *
   * @param configuration Configurations.
   */
  public DefaultSnowflakeIdGeneratorImpl(SnowflakeIdConfiguration configuration) {
    this.configuration = configuration;
  }

  /**
   * Generates the next snowflake id.
   *
   * @return Snowflake id.
   */
  @Override
  public synchronized long nextId() {
    long currentTimestamp = this.currentTimestamp();

    if (currentTimestamp < this.lastTimestamp) {
      throw new IllegalStateException("Invalid System Clock!");
    }

    if (this.lastTimestamp == currentTimestamp) {
      this.sequence = (this.sequence + 1) & SnowflakeIdConfiguration.MAX_SEQUENCE;
      if (this.sequence == 0) {
        currentTimestamp = waitNextMillis(currentTimestamp);
      }
    } else {
      this.sequence = 0;
    }

    this.lastTimestamp = currentTimestamp;

    return new DefaultSnowflakeIdImpl(
            this.configuration.getNodeId(), this.lastTimestamp, this.sequence)
        .toLong();
  }

  /**
   * Generates the current timestamp in milli.
   *
   * @return Timestamp in milli.
   */
  protected long currentTimestamp() {
    return System.currentTimeMillis();
  }

  /**
   * Loops until next tick.
   *
   * @param currentTimestamp Current timestamp.
   * @return Updated timestamp.
   */
  private long waitNextMillis(long currentTimestamp) {
    while (currentTimestamp == this.lastTimestamp) {
      currentTimestamp = this.currentTimestamp();
    }
    return currentTimestamp;
  }
}
